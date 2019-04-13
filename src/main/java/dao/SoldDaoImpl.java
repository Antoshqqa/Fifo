package dao;

import entity.Sold;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.Date;
import java.util.List;

public class SoldDaoImpl implements DaoInterface<Sold> {

    public List<Sold> orderByDate(Long id, Date date) {
        return (List<Sold>) HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .createQuery("FROM Sold S where S.id= :idParam and S.saleDate<= :dateParam order by S.saleDate")
                .setParameter("idParam", id).setParameter("dateParam", date).list();

    }

    public List<Sold> findAll() {
        List<Sold> solds = (List<Sold>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Sold ").list();
        return solds;
    }

    public Long findIdByName(String name) {
        return null;
    }

    public boolean ifExistsInProducts(String name) {
        return false;
    }

    public boolean ifExistsInSold(Long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .createQuery("Select 1 From Sold S WHERE S.id = :idParam")
                .setParameter("idParam", id).uniqueResult() != null;
    }

    public Sold findById(Long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Sold.class, id);
    }

    public void updateAmount(Long purchase_id, int amount) {

    }

    public void save(Sold sold) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(sold);
        tx1.commit();
        session.close();
    }

    public void update(Sold sold) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(sold);
        tx1.commit();
        session.close();
    }

    public void delete(Sold sold) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(sold);
        tx1.commit();
        session.close();
    }

    public void deleteById(Long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.createQuery("delete from Sold S where S.id= :idParam")
                .setParameter("idParam", id).executeUpdate();
        tx1.commit();
        session.close();
    }
}
