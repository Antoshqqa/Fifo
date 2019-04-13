package validation;

import dao.DemandDaoImpl;
import dao.ProductsDaoImpl;
import dao.PurchaseDaoImpl;
import dao.SoldDaoImpl;
import entity.Products;
import entity.Purchase;
import entity.Sold;
import service.CmdInput;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class InputSplitAndCheck {

    private CmdInput cmdInput = new CmdInput();
    private Products product;
    private Sold sold;
    private ProductsDaoImpl productsDao = new ProductsDaoImpl();
    private PurchaseDaoImpl purchaseDao = new PurchaseDaoImpl();
    private SoldDaoImpl soldDao = new SoldDaoImpl();
    private DemandDaoImpl demandDao = new DemandDaoImpl();
    private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
    private String productName;
    private Date date;
    private int amount;
    private double price;
    private boolean valid;


    //check purchase and demand commands
    public void pNd(String[] arr) {

        if (arr.length >= 5) {

            if (isDateValid(arr[arr.length - 1]) && arr[arr.length - 1].matches("[0-3]?[0-9]{1}[.|,]{1}[0-1]?[0-9]{1}[.|,][0-9]{4}")
                    && arr[arr.length - 2].matches("[0-9]+[.]?[0-9]*|[0-9]+[,]?[0-9]*")
                    && arr[arr.length - 3].matches("[0-9]+")) {
                for (int i = 1; i < arr.length - 3; i++) {
                    productName += " " + arr[i];
                }
                productName = productName.trim();
                amount = Integer.parseInt(arr[arr.length - 3]);
                price = Double.parseDouble(arr[arr.length - 2].replaceAll(",", "."));
                try {
                    date = format.parse((arr[arr.length - 1]));
                } catch (ParseException e) {
                    System.out.println("Дата введена некорректно");
                }
                if (amount > 0 && price > 0) valid = true; else {
                    valid=false;
                    System.out.println("Количество или цена не могут равняться нулю!");
                }

            } else {
                valid=false;
                System.out.println("Параметры введены некорректно!");
            }

        } else {
            valid = false;
            System.out.println("Недостаточно параметров!");
        }
    }

    /*------------------------------------------------------------------------------*/

    public boolean isDateValid(String s) {
        format.setLenient(false);
        try {
            format.parse(s);
            return true;
        } catch (ParseException e) {
            return false;
        }

    }

    /*NEWPRODUCT_PURCHASE_DEMAND_SALESREPORT---------------------------------------------------------------------------------*/

    public void checkAndExec(String s) {

        while (s.contains("  ")) s = s.replace("  ", " ");
        s = s.trim();

        String[] arrSplit = s.split(" ");

        /*NEWPRODUCT-----------------------------------------------------------------------------*/

        productName = "";
        if (s.toLowerCase().matches("newproduct .*")) {
            if (arrSplit.length > 2) {
                for (int i = 1; i < arrSplit.length; i++) {
                    productName += " " + arrSplit[i];
                }
                productName = productName.trim();
            } else {
                if (arrSplit.length == 2) {
                    productName = arrSplit[1];
                }
            }
            product = cmdInput.newProduct(productName);
            if (s != null && s != "" && product != null) productsDao.save(product);
        }

        /*PURCHASE---------------------------------------------------------------------------------------*/


        if (s.toLowerCase().matches("purchase .*")) {

            pNd(arrSplit);
            if (valid && productsDao.ifExistsInProducts(productName) && s != null && s != "") {
                purchaseDao.save(cmdInput.newPurchase(productName, amount, price, date));
            } else if (valid) System.out.println("Продукт не был добавлен!");
            productName = "";
            amount = 0;
            price = 0;
        }

        /*DEMAND------------------------------------------------------------------------------------------------*/

        if (s.toLowerCase().matches("demand .*")) {

            pNd(arrSplit);

            if (valid && productsDao.ifExistsInProducts(productName) && s != null && s != "") {

                List<Purchase> allPurchasesForName = purchaseDao.orderByDate(productsDao.findIdByName(productName), date);

                double selfPrice = 0;
                double profit = 0;
                int totalAmount = 0;
                int lastToBuy = 0;
                int size = allPurchasesForName.size();

                if (size > 0) {
                    Date firstAvailableDate = allPurchasesForName.get(0).getBuyDate();
                    Date lastAvailableDate = allPurchasesForName.get(size - 1).getBuyDate();

                    for (int i = 0; i < size; i++) {
                        totalAmount += allPurchasesForName.get(i).getAmount();
                    }

                    if (amount <= totalAmount && (date.after(firstAvailableDate) || date.equals(firstAvailableDate))) {
                        lastToBuy = amount;
                        for (int i = 0; i < size; i++) {
                            if (lastToBuy <= allPurchasesForName.get(i).getAmount() && allPurchasesForName.get(i).getAmount() != 0) {
                                selfPrice += lastToBuy * allPurchasesForName.get(i).getBuyPrice();
                                purchaseDao.updateAmount(allPurchasesForName.get(i).getPurchase_id(), allPurchasesForName.get(i).getAmount()-lastToBuy);
                                break;
                            } else {
                                selfPrice += allPurchasesForName.get(i).getAmount() * allPurchasesForName.get(i).getBuyPrice();

                                lastToBuy = lastToBuy - allPurchasesForName.get(i).getAmount();
                                purchaseDao.updateAmount(allPurchasesForName.get(i).getPurchase_id(), 0);
                            }
                        }
                        selfPrice = selfPrice/amount;
                        profit = amount*price - amount*selfPrice;

                        demandDao.save(cmdInput.newDemand(productName, amount, price, date));
                        soldDao.save(cmdInput.newSold(productName, profit, date));

                    } else System.out.println("К данной дате либо после нее не были закуплены продукты, либо указано некорректное количество ");
                } else {
                    System.out.println("Такой продукт не был закуплен к данной дате, либо указано некорректное название продукта!");
                }
            } else if (valid) System.out.println("Продукт не был закуплен");

            productName = "";
            amount = 0;
            price = 0;
        }

        if (s.toLowerCase().matches("salesreport .*")) {
            if (arrSplit.length >= 3) {
                if(isDateValid(arrSplit[arrSplit.length-1]) && arrSplit[arrSplit.length - 1].matches("[0-3]?[0-9]{1}[.|,]{1}[0-1]?[0-9]{1}[.|,][0-9]{4}")) {
                    for (int i = 1; i < arrSplit.length - 1; i++) {
                        productName += " " + arrSplit[i];
                    }
                    productName = productName.trim();
                    try {
                        date = format.parse((arrSplit[arrSplit.length - 1]));
                    } catch (ParseException e) {
                        System.out.println("Дата введена некорректно");
                    }
                    if (productsDao.ifExistsInProducts(productName)) {
                        if (soldDao.ifExistsInSold(productsDao.findIdByName(productName))) {
                            double profit = 0;
                            List<Sold> solds = soldDao.orderByDate(productsDao.findIdByName(productName), date);
                            if (solds.size() > 0) {
                                if (date.after(solds.get(0).getSaleDate()) || date.equals(solds.get(0).getSaleDate())) {
                                    for (int i = 0; i < solds.size(); i++) {
                                        profit += solds.get(i).getProfit();
                                    }
                                    System.out.println("Общая прибыль: " + profit);
                                } else System.out.println("До данной даты не было закупок!");
                            }
                        } else System.out.println("Для данного продукта не производилось закупок!");
                    } else System.out.println("Продукт не существует!");

                }
            } else {
                System.out.println("Недостаточно параметров!");
            }

            productName = "";
        }

        s = "";

    }

}
