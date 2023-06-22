package com.testtask.dao.repositories;

import com.testtask.dao.interfaces.IProductsRepository;
import com.testtask.dao.models.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductsRepository implements IProductsRepository {

    @Autowired
    private SessionFactory sessionFactory;
    public long create(Product data) {
        try(Session currentSession = sessionFactory.openSession()) {
            Transaction transaction = currentSession.beginTransaction();
            currentSession.save(data);
            transaction.commit();
            return data.getId();
        }
    }

    public boolean delete(long id) {
        try(Session currentSession = sessionFactory.openSession()) {
            Query query = currentSession.createQuery("delete from Product where id = :id");
            query.setParameter("id",id);
            Transaction transaction = currentSession.beginTransaction();
            int affectedAmount = query.executeUpdate();
            transaction.commit();
            return affectedAmount == 1;
        }
    }

    public Product update(Product data) {
        try(Session currentSession = sessionFactory.openSession()){
            Transaction transaction = currentSession.beginTransaction();
            currentSession.update(data);
            transaction.commit();
            return data;
        }
    }

    public Product get(long id) {
        try(Session currentSession = sessionFactory.openSession()) {
            Product found = currentSession.get(Product.class,id);
            return found;
        }
    }
}
