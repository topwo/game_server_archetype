#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.util.cache;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ${package}.client.${artifactId}.Pay;
import ${package}.util.memcached.MemcachedCRUD;

public class MC {
	/**
	 * 控制哪些类进行memcached缓存。 被控制的类在进行创建时，需要注意调用MC的add和hibernate的insert。
	 */
	public static Set<Class<? extends MCSupport>> cachedClass = new HashSet<Class<? extends MCSupport>>();
	static {
		cachedClass.add(Pay.class);
	}

	public static <T> T get(Class<T> t, String id) {
		if (!cachedClass.contains(t)) {
			return null;
		}
		StringBuffer key = new StringBuffer();
		key.append("${artifactId}").append("${symbol_pound}").append(t.getSimpleName()).append("${symbol_pound}")
				.append(id);
		// String c = "${artifactId}" + "${symbol_pound}" + t.getSimpleName();
		// String key = c + "${symbol_pound}" + id;
		Object o = MemcachedCRUD.getInstance().getObject(key.toString());
		return (T) o;
	}

	public static Object getValue(String key) {
		Object o = MemcachedCRUD.getInstance().getObject(key);
		return o;
	}

	public static boolean add(Object t, String id) {
		if (!cachedClass.contains(t.getClass())) {
			return false;
		}
		StringBuffer key = new StringBuffer();
		key.append("${artifactId}").append("${symbol_pound}").append(t.getClass().getSimpleName())
				.append("${symbol_pound}").append(id);
		// String c = "${artifactId}" + "${symbol_pound}" + t.getClass().getSimpleName();
		// String key = c + "${symbol_pound}" + id;
		return MemcachedCRUD.getInstance().add(key.toString(), t);
	}

	public static boolean addKeyValue(String key, Object value) {
		return MemcachedCRUD.getInstance().add(key, value);
	}

	public static void update(Object t, String id) {
		if (!cachedClass.contains(t.getClass())) {
			return;
		}
		StringBuffer key = new StringBuffer();
		key.append("${artifactId}").append("${symbol_pound}").append(t.getClass().getSimpleName())
				.append("${symbol_pound}").append(id);
		// String c = "${artifactId}" + "${symbol_pound}" + t.getClass().getSimpleName();
		// String key = c + "${symbol_pound}" + id;
		MemcachedCRUD.getInstance().update(key.toString(), t);
	}

	/**
	 * 根据主键删除缓存
	 * 
	 * @param obj
	 *            删除对象
	 * @param id
	 *            主键id
	 */
	public static void delete(Class clazz, String id) {
		if (!cachedClass.contains(clazz)) {
			return;
		}
		StringBuffer key = new StringBuffer();
		key.append("${artifactId}").append("${symbol_pound}").append(clazz.getSimpleName()).append("${symbol_pound}")
				.append(id);
		// String prefix = "${artifactId}" + "${symbol_pound}" + clazz.getSimpleName();
		// String key = prefix + "${symbol_pound}" + id;
		MemcachedCRUD.getInstance().deleteObject(key.toString());
	}

}
