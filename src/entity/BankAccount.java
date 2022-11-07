package entity;

import entity.enumarate.Currency;

import java.math.BigDecimal;

public class BankAccount {
    private Integer id;

    private BigDecimal amount;
    private Currency currency;

    public BankAccount(Integer id) {
        this.id = id;
        this.amount = BigDecimal.ZERO;
        this.currency = Currency.TURKISH_LIRA;
    }

    public BankAccount(Integer id,BigDecimal amount, Currency currency) {
        this.id = id;
        this.amount = amount;
        this.currency = currency;

    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Id:"+id+" Bakiye: " + amount + " "+ currency + "\n";
    }
}
