package dao;

import entity.Products;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.Date;
import java.util.List;

public class ProductsDaoImpl implements DaoInterface<Products> {

    public List<Products> orderByDate(Long id, Date date) {
        return null;
    }

    public List<Products> findAll() {
        List<Products> products = (List<Products>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Products ").list();
        return products;
    }

    public Long findIdByName(String name) {
        return Long.parseLong(HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .createQuery("Select P.id From Products P WHERE P.name = :nameParam")
                .setParameter("nameParam", name).list().toString().replace("]", "").replace("[", "")
        );
    }

    public boolean ifExistsInProducts(String name) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .createQuery("Select 1 From Products P WHERE P.name = :nameParam")
                .setParameter("nameParam", name).uniqueResult() != null;
    }

    public boolean ifExistsInSold(Long id) {
        return false;
    }

    public Products findById(Long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Products.class, id);
    }

    public void updateAmount(Long purchase_id, int amount) {

    }

    public void save(Products product) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(product);
        tx1.commit();
        session.close();
    }

    public void update(Products product) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(product);
        tx1.commit();
        session.close();
    }

    public void delete(Products product) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(product);
        tx1.commit();
        session.close();
    }

    public void deleteById(Long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.createQuery("delete from Products P where P.id= :idParam")
                .setParameter("idParam", id).executeUpdate();
        tx1.commit();
        session.close();
    }
}
