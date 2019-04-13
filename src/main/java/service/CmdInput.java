package service;

import dao.ProductsDaoImpl;
import entity.Demand;
import entity.Products;
import entity.Purchase;
import entity.Sold;

import java.util.Date;

public class CmdInput {

    ProductsDaoImpl productsDao = new ProductsDaoImpl();

    public Products newProduct(String name) {

        if (name == "" || name == null || productsDao.ifExistsInProducts(name)) {
            System.out.println("Ошибка! Пустое название продукта, либо продукт уже добавлен!");
            return null;
        } else return new Products(name);
    }


    public Purchase newPurchase(String name, int amount, double price, Date date) {

        return new Purchase(productsDao.findIdByName(name), amount, price, date);
    }

    public Demand newDemand(String name, int amount, double price, Date date) {

        return new Demand(productsDao.findIdByName(name), amount, price, date);
    }

    public Sold newSold(String name, double profit, Date date) {

        return new Sold(productsDao.findIdByName(name), profit, date);
    }
}
