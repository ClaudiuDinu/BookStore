package com.bookstore.app.data.dao.impl;

import com.bookstore.app.commons.bo.UserBO;
import com.bookstore.app.data.dao.IUserDao;
import com.bookstore.app.data.entites.User;
import com.bookstore.app.utils.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by cdinu on 11/13/2015.
 */
@Component
public class UserDaoImpl implements IUserDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        if(sessionFactory == null){
            sessionFactory = HibernateUtil.getSessionFactory();
        }
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void login(UserBO userBO) {

        Session session = getSessionFactory().openSession();

        boolean userFound = false;
        UserBO foundUserBO = null;

        try {
            session.beginTransaction();

            Criteria criteria = session.createCriteria(User.class);
            List<User> dbUsers = criteria.list();

            for (User dbU : dbUsers){

                if(dbU.getUserName().equals(userBO.getUserName()) && dbU.getPassword().equals(userBO.getPassword())){
                    userFound = true;
                    foundUserBO = dbU.asBO();
                }
                break;
            }

        }catch (Exception e){
            //log the error
        }finally {
            session.close();
        }

        if(userFound){
            System.out.println("Good job!!!");

        }else{
            System.out.println("Looser!!!");
        }

    }
}
