package com.kidbear.logical.gm.admin;

import org.springframework.stereotype.Service;

import com.kidbear.logical.util.hibernate.HibernateUtil;
import com.kidbear.logical.util.hibernate.TableIDCreator;

@Service
public class AdminService {

	public boolean login(Admin admin) {
		Admin tmp = HibernateUtil.find(
				Admin.class,
				"where name='" + admin.getName() + "' and pwd='"
						+ admin.getPwd() + "'");
        return tmp != null;
    }

	public boolean saveAdmin(Admin admin) {
		Admin tmp = HibernateUtil.find(Admin.class,
				"where name='" + admin.getName() + "'");
		Throwable t = null;
		if (tmp == null) {
			admin.setId(TableIDCreator.getTableID(Admin.class, 1));
			t = HibernateUtil.insert(admin, tmp.getId());
		} else {
			t = HibernateUtil.save(admin, tmp.getId());
		}
        return t == null;
    }

	public boolean deleteAdmin(Admin admin) {
		Throwable t = HibernateUtil.delete(admin, admin.getId());
        return t == null;
    }
}
