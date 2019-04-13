package entity;

import javax.persistence.*;
import java.util.Date;


@Entity
public class Demand {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long demand_id;

    private Long id;

    private int amount;

    @Column(name = "demandprice")
    private double demandPrice;

    @Column(name = "demanddate")
    @Temporal(TemporalType.DATE)
    private Date demandDate;

    public Demand() {
    }

    public Demand(Long id, int amount, double demandPrice, Date demandDate) {
        this.id = id;
        this.amount = amount;
        this.demandPrice = demandPrice;
        this.demandDate = demandDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDemand_id() {
        return demand_id;
    }

    public void setDemand_id(Long demand_id) {
        this.demand_id = demand_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getDemandPrice() {
        return demandPrice;
    }

    public void setDemandPrice(double demandPrice) {
        this.demandPrice = demandPrice;
    }

    public Date getDemandDate() {
        return demandDate;
    }

    public void setDemandDate(Date demandDate) {
        this.demandDate = demandDate;
    }

    @Override
    public String toString() {
        return "Product:" +
                "; product_id: " +
                "; demand_id = " + demand_id +
                "; Количество: " + amount +
                "; Цена продажи: " + demandPrice +
                "; Дата продажи: " + demandDate;
    }
}
