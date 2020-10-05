package com.formation.hibernate.configs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

    private  static Session session = builSessionFactory().openSession();

    private  static SessionFactory builSessionFactory() {
        return  new Configuration().configure().buildSessionFactory();
    }

    public  static  Session getSession(){
        if (!session.isOpen()){
            session = builSessionFactory().openSession();
            return  session;
        }
        return  session;
    }
}
