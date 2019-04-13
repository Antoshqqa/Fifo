package entity;

import javax.persistence.*;
import java.util.Date;


@Entity
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long purchase_id;

    private Long id;

    private int amount;

    @Column(name = "buyprice")
    private double buyPrice;

    @Column(name = "buydate")
    @Temporal(TemporalType.DATE)
    private Date buyDate;

    public Purchase() {
    }

    public Purchase(Long id, int amount, double buyPrice, Date buyDate) {
        this.id = id;
        this.amount = amount;
        this.buyPrice = buyPrice;
        this.buyDate = buyDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPurchase_id() {
        return purchase_id;
    }

    public void setPurchase_id(Long purchase_id) {
        this.purchase_id = purchase_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    @Override
    public String toString() {
        return  "amount: " + amount +
                "; buyPrice: " + buyPrice +
                "; buyDate: " + buyDate;
    }
}
