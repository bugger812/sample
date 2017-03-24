package com.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Sigal on 5/20/2016.
 */
@Component
public class Persist {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getQuerySession() {
        return sessionFactory.getCurrentSession();
    }

    public void save(Object o) {
        sessionFactory.getCurrentSession().saveOrUpdate(o);
    }

    public Object load(Class clazz, int id) {
        return getQuerySession().get(clazz, id);
    }

    public Object load(Class clazz, long id) {
        return getQuerySession().get(clazz, id);
    }


}