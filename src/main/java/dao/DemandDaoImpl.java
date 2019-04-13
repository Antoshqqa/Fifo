package dao;

import entity.Demand;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.Date;
import java.util.List;

public class DemandDaoImpl implements DaoInterface<Demand> {


    public List<Demand> orderByDate(Long id, Date date) {
        return null;
    }

    public List<Demand> findAll() {
        List<Demand> demands = (List<Demand>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Demand ").list();
        return demands;
    }

    public Long findIdByName(String name) {
        return null;
    }

    public boolean ifExistsInProducts(String name) {
        return false;
    }

    public boolean ifExistsInSold(Long id) {
        return false;
    }

    public Demand findById(Long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Demand.class, id);
    }

    public void updateAmount(Long purchase_id, int amount) {

    }

    public void save(Demand demand) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(demand);
        tx1.commit();
        session.close();
    }

    public void update(Demand demand) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(demand);
        tx1.commit();
        session.close();
    }

    public void delete(Demand demand) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(demand);
        tx1.commit();
        session.close();
    }

    public void deleteById(Long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.createQuery("delete from Demand D where D.id= :idParam")
                .setParameter("idParam", id).executeUpdate();
        tx1.commit();
        session.close();
    }
}
