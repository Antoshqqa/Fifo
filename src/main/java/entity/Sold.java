package entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Sold {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sold_id;

    private Long id;

    private double profit;

    @Column(name = "saledate")
    @Temporal(TemporalType.DATE)
    private Date saleDate;

    public Sold(Long id, double profit, Date saleDate) {
        this.id = id;
        this.profit = profit;
        this.saleDate = saleDate;
    }

    public Sold() {
    }

    public Long getSold_id() {
        return sold_id;
    }

    public void setSold_id(Long sold_id) {
        this.sold_id = sold_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    @Override
    public String toString() {
        return  "id: " + id +
                "; profit = " + profit +
                "; saleDate: " + saleDate;
    }
}
