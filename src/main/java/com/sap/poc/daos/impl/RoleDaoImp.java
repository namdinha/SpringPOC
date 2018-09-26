package com.sap.poc.daos.impl;

import com.sap.poc.daos.RoleDao;
import com.sap.poc.models.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

public class RoleDaoImp extends HibernateDaoSupport implements RoleDao {

    private SessionFactory sessionFactory;

    @Override
    public void create(Role role) {
        getHibernateTemplate().save(role);
    }

    @Override
    public Role getRoleByName(String roleName) {
        try (Session session = sessionFactory.openSession()) {
            DetachedCriteria criteria = DetachedCriteria.forClass(Role.class);
            criteria.add(Restrictions.like("roleName", roleName));
            return (Role) criteria.getExecutableCriteria(session).uniqueResult();
        }
    }
}
