#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package com.kidbear.${artifactId}.util.cache;

import com.kidbear.${artifactId}.core.GameInit;
import com.kidbear.${artifactId}.util.memcached.MemcachedCRUD;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * MC DAO
 */
public class MC {
    /**
     * 控制哪些类进行memcached缓存。 被控制的类在进行创建时，需要注意调用MC的add和hibernate的insert。
     */
    public static Set<Class<? extends MCSupport>> cachedClass = new HashSet<Class<? extends MCSupport>>();
    /**
     * 控制那些类的数据库结果集需要memcached缓存
     */
    public static Set<String> cachedList = new HashSet<String>();

    static {
//		cachedClass.add(CardInfo.class);
    }

    static {
//		cachedList.add(CardInfo.class.getSimpleName());
    }

    /**
     * Get t.
     *
     * @param <T> the type parameter
     * @param t   the t
     * @param id  the id
     * @return the t
     */
    public static <T> T get(Class<T> t, String id) {
        if (!cachedClass.contains(t)) {
            return null;
        }
        StringBuffer key = new StringBuffer();
        key.append(GameInit.serverId).append("${symbol_pound}").append(t.getSimpleName())
                .append("${symbol_pound}").append(id);
        Object o = MemcachedCRUD.getInstance().getObject(key.toString());
        return (T) o;
    }

    /**
     * Gets list.
     *
     * @param <T>   the type parameter
     * @param t     the t
     * @param id    the id
     * @param where the where
     * @return the list
     */
    public static <T> List<T> getList(Class<T> t, String id, String where) {
        if (!cachedList.contains(t.getSimpleName())) {
            return null;
        }
        StringBuffer key = new StringBuffer();
        key.append(GameInit.serverId).append("${symbol_pound}").append(id).append("${symbol_pound}")
                .append(t.getSimpleName()).append("${symbol_pound}").append(where);
        Object o = MemcachedCRUD.getInstance().getObject(key.toString());
        return (List<T>) o;
    }

    /**
     * Gets list keys.
     *
     * @param <T>   the type parameter
     * @param tName the t name
     * @param id    the id
     * @return the list keys
     */
    public static <T> String getListKeys(String tName, String id) {
        if (!cachedList.contains(tName)) {
            return null;
        }
        StringBuffer key = new StringBuffer();
        key.append(GameInit.serverId).append("${symbol_pound}").append(id).append("${symbol_pound}")
                .append(tName);
        Object o = MemcachedCRUD.getInstance().getObject(key.toString());
        if (o == null) {
            return null;
        }
        return (String) o;
    }

    /**
     * Gets value.
     *
     * @param key the key
     * @return the value
     */
    public static Object getValue(String key) {
        Object o = MemcachedCRUD.getInstance().getObject(key);
        return o;
    }

    /**
     * Add boolean.
     *
     * @param t  the t
     * @param id the id
     * @return the boolean
     */
    public static boolean add(Object t, String id) {
        if (!cachedClass.contains(t.getClass())) {
            return false;
        }
        StringBuffer key = new StringBuffer();
        key.append(GameInit.serverId).append("${symbol_pound}")
                .append(t.getClass().getSimpleName()).append("${symbol_pound}").append(id);
        return MemcachedCRUD.getInstance().add(key.toString(), t);
    }

    /**
     * Add list boolean.
     *
     * @param list  the list
     * @param tName the t name
     * @param id    the id
     * @param where the where
     * @return the boolean
     */
    public static boolean addList(List list, String tName, String id,
                                  String where) {
        if (!cachedList.contains(tName)) {
            return false;
        }
        StringBuffer key = new StringBuffer();
        key.append(GameInit.serverId).append("${symbol_pound}").append(id).append("${symbol_pound}")
                .append(tName);
        String c = key.toString();
        key.append("${symbol_pound}").append(where);
        Object tmp = MemcachedCRUD.getInstance().getObject(c);
        String keys = tmp == null ? "" : (String) tmp;
        if (keys.equals("")) {
            MemcachedCRUD.getInstance().add(c, key.toString());
        } else if (!keys.contains(key.toString())) {
            MemcachedCRUD.getInstance().update(c, keys + "," + key.toString());
        }
        return MemcachedCRUD.getInstance().add(key.toString(), list);
    }

    /**
     * Add key value boolean.
     *
     * @param key   the key
     * @param value the value
     * @return the boolean
     */
    public static boolean addKeyValue(String key, Object value) {
        return MemcachedCRUD.getInstance().add(key, value);
    }

    /**
     * Update.
     *
     * @param t  the t
     * @param id the id
     */
    public static void update(Object t, String id) {
        if (!cachedClass.contains(t.getClass())) {
            return;
        }
        StringBuffer key = new StringBuffer();
        key.append(GameInit.serverId).append("${symbol_pound}")
                .append(t.getClass().getSimpleName()).append("${symbol_pound}").append(id);
        MemcachedCRUD.getInstance().update(key.toString(), t);
    }

    /**
     * Update list boolean.
     *
     * @param list  the list
     * @param tName the t name
     * @param id    the id
     * @param where the where
     * @return the boolean
     */
    public static boolean updateList(List list, String tName, String id,
                                     String where) {
        if (!cachedList.contains(tName)) {
            return false;
        }
        StringBuffer key = new StringBuffer();
        key.append(GameInit.serverId).append("${symbol_pound}").append(id).append("${symbol_pound}")
                .append(tName);
        String c = key.toString();
        key.append("${symbol_pound}").append(where);
        Object tmp = MemcachedCRUD.getInstance().getObject(c);
        String keys = tmp == null ? "" : (String) tmp;
        if (keys.equals("")) {
            MemcachedCRUD.getInstance().add(c, key.toString());
        } else if (!keys.contains(key)) {
            MemcachedCRUD.getInstance().update(c, keys + "," + key.toString());
        }
        return MemcachedCRUD.getInstance().update(key.toString(), list);
    }

    /**
     * 根据主键删除缓存
     *
     * @param clazz the clazz
     * @param id    主键id
     */
    public static void delete(Class clazz, String id) {
        if (!cachedClass.contains(clazz)) {
            return;
        }
        StringBuffer key = new StringBuffer();
        key.append(GameInit.serverId).append("${symbol_pound}").append(clazz.getSimpleName())
                .append("${symbol_pound}").append(id);
        MemcachedCRUD.getInstance().deleteObject(key.toString());
    }

}
