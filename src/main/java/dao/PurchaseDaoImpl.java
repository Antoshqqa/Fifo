package dao;

import entity.Purchase;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.Date;
import java.util.List;

public class PurchaseDaoImpl implements DaoInterface<Purchase> {

    public List<Purchase> orderByDate(Long id, Date date) {
        return (List<Purchase>) HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .createQuery("FROM Purchase P where P.id= :idParam and P.buyDate<= :dateParam order by P.buyDate")
                .setParameter("idParam", id).setParameter("dateParam", date).list();

    }

    public List<Purchase> findAll() {
        List<Purchase> purchases = (List<Purchase>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Purchase ").list();
        return purchases;
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

    public Purchase findById(Long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Purchase.class, id);
    }

    public void updateAmount(Long purchase_id, int amount) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.createQuery("update Purchase P set amount= :amountParam where P.purchase_id= :purchase_idParam")
                .setParameter("amountParam", amount).setParameter("purchase_idParam", purchase_id).executeUpdate();
        tx1.commit();
        session.close();
    }

    public void save(Purchase purchase) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(purchase);
        tx1.commit();
        session.close();
    }

    public void update(Purchase purchase) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(purchase);
        tx1.commit();
        session.close();
    }

    public void delete(Purchase purchase) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(purchase);
        tx1.commit();
        session.close();
    }

    public void deleteById(Long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.createQuery("delete from Purchase Pur where Pur.id= :idParam")
                .setParameter("idParam", id).executeUpdate();
        tx1.commit();
        session.close();
    }
}
