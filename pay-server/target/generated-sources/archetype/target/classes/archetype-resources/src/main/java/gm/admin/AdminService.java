#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.gm.admin;

import org.springframework.stereotype.Service;

import ${package}.util.hibernate.HibernateUtil;
import ${package}.util.hibernate.TableIDCreator;

@Service
public class AdminService {

	public boolean login(Admin admin) {
		Admin tmp = HibernateUtil.find(
				Admin.class,
				"where name='" + admin.getName() + "' and pwd='"
						+ admin.getPwd() + "'");
		if (tmp == null) {
			return false;
		}
		return true;
	}

	public boolean saveAdmin(Admin admin) {
		Admin tmp = HibernateUtil.find(Admin.class,
				"where name='" + admin.getName() + "'");
		Throwable t = null;
		if (tmp == null) {
			admin.setId(TableIDCreator.getTableID(Admin.class, 1));
			t = HibernateUtil.insert(admin, admin.getId());
		} else {
			t = HibernateUtil.save(admin, admin.getId());
		}
		if (t == null) {
			return true;
		}
		return false;
	}

	public boolean deleteAdmin(Admin admin) {
		Throwable t = HibernateUtil.delete(admin, admin.getId());
		if (t == null) {
			return true;
		}
		return false;
	}
}
