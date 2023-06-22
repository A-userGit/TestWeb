package com.testtask.dao.repositories;

import com.testtask.dao.interfaces.IDealerRepository;
import com.testtask.dao.models.Dealer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DealerRepository implements IDealerRepository {

    @Autowired
    private SessionFactory sessionFactory;
    public long create(Dealer data) {
        try (Session currentSession = sessionFactory.openSession()){
            Transaction transaction = currentSession.beginTransaction();
            long id = (Long)currentSession.save(data);
            transaction.commit();
            return id;
        }
    }

    public boolean delete(long id) {
        try(Session currentSession = sessionFactory.openSession()) {
            Query query = currentSession.createQuery("delete from Dealer where id = :id");
            query.setParameter("id",id);
            Transaction transaction = currentSession.beginTransaction();
            int affectedAmount = query.executeUpdate();
            transaction.commit();
            return affectedAmount == 1;
        }
    }

    public Dealer update(Dealer data) {
        try(Session currentSession = sessionFactory.openSession()) {
            Transaction transaction = currentSession.beginTransaction();
            currentSession.update(data);
            transaction.commit();
        }
        return data;
    }

    public Dealer get(long id) {
        try(Session currentSession = sessionFactory.openSession()) {
            Dealer found = currentSession.get(Dealer.class,id);
            return found;
        }
    }

    @Override
    public Dealer getByEmail(String email) {
        try(Session currentSession = sessionFactory.openSession()) {
            Query query = currentSession.createQuery("from Dealer WHERE email = :param1");
            query.setParameter("param1",email);
            query.setMaxResults(1);
            List<Dealer> found = query.list();
            if(found == null||found.size() == 0)
                return null;
            return found.get(0);
        }
    }
}
