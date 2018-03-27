package pl.budzet.domowy.destop;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.sql.Date;

public class HomeBudget {
    private long id;
    private String  type;
    private String description;
    private BigDecimal amount;
    private Date date;

    public HomeBudget() {
    }

    public HomeBudget(String type,String description, BigDecimal amount) {
        this.type = type;
        this.description = description;
        this.amount = amount;
        LocalDate typeDate = LocalDate.now();
        this.date = Date.valueOf(typeDate);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String  getType() {
        return type;
    }

    public void setType(String  type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return id + " " + type + " " + description + " " + amount + " " + date;
    }
}
