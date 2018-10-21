package com.blueice.dao;

import com.blueice.domain.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class ProductDao {

    //注入sessionFactory
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * 保存商品
     * @param product
     */
    public void save(Product product) {
        sessionFactory.getCurrentSession().save(product);
        System.out.println("Dao层中的save方法被调用了....");
    }
}
