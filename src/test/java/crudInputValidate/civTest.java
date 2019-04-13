package crudInputValidate;

import dao.DemandDaoImpl;
import dao.ProductsDaoImpl;
import dao.PurchaseDaoImpl;
import dao.SoldDaoImpl;
import entity.Demand;
import entity.Products;
import entity.Purchase;
import entity.Sold;
import org.junit.Test;
import service.CmdInput;
import validation.InputSplitAndCheck;

import java.util.Date;

public class civTest {

    ProductsDaoImpl productsDao = new ProductsDaoImpl();
    PurchaseDaoImpl purchaseDao = new PurchaseDaoImpl();
    DemandDaoImpl demandDao = new DemandDaoImpl();
    SoldDaoImpl soldDao = new SoldDaoImpl();
    InputSplitAndCheck inputSplitAndCheck = new InputSplitAndCheck();
    CmdInput cmdInput = new CmdInput();
    Date date = new Date();

    Products products;
    Purchase purchase;
    Demand demand;
    Sold sold;



    @Test
    public void civ() {

        products = new Products("Test Crud");
        productsDao.save(products);

        Long id = productsDao.findIdByName("Test Crud");
        purchase  = new Purchase(id, 5, 3000, date);
        demand  = new Demand(id, 3, 5000, date);
        sold = new Sold(id, 30000, date);

        purchaseDao.save(purchase);
        demandDao.save(demand);
        soldDao.save(sold);

        productsDao.update(products);
        purchaseDao.update(purchase);
        demandDao.update(demand);
        soldDao.update(sold);

        purchaseDao.delete(purchase);
        demandDao.delete(demand);
        soldDao.delete(sold);
        productsDao.delete(products);

        /*------------------------------------------------------------*/

        products = cmdInput.newProduct("Test Input");
        productsDao.save(products);

        purchase = cmdInput.newPurchase("Test Input", 5, 20000, date);
        purchaseDao.save(purchase);

        demand = cmdInput.newDemand("Test Input", 5, 30000, date);
        demandDao.save(demand);

        sold = cmdInput.newSold("Test Input", 50000, date);
        soldDao.save(sold);


        purchaseDao.delete(purchase);
        demandDao.delete(demand);
        soldDao.delete(sold);
        productsDao.delete(products);

        /*----------------------------------------------------------*/

        inputSplitAndCheck.checkAndExec("newproduct Test Validate");
        inputSplitAndCheck.checkAndExec("purchase Test Validate 5 5000 23.04.2019");
        inputSplitAndCheck.checkAndExec("demand Test Validate 5 10000 25.04.2019");
        inputSplitAndCheck.checkAndExec("salesreport Test Validate 27.04.2019");

        id = productsDao.findIdByName("Test Validate");
        purchaseDao.deleteById(id);
        demandDao.deleteById(id);
        soldDao.deleteById(id);
        productsDao.deleteById(id);
    }


}
