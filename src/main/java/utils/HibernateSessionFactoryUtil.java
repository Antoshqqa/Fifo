package utils;

import entity.Demand;
import entity.Products;
import entity.Purchase;
import entity.Sold;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.io.File;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        File hiberConfig = new File("./config/hibernate.cfg.xml");
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure(hiberConfig);
                configuration.addAnnotatedClass(Products.class);
                configuration.addAnnotatedClass(Purchase.class);
                configuration.addAnnotatedClass(Demand.class);
                configuration.addAnnotatedClass(Sold.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Ошибка, файл с конфигурацией не найден!" + e);
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}