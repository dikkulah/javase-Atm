package entity;

import entity.BankAccount;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Person {
    private String mail;
    private String password;
    private List<BankAccount> accounts = new ArrayList<>();


    public Person(String mail, String password) {
        this.mail = mail;
        this.password = password;
    }

    public Person(String mail, String password, List<BankAccount> accounts) {
        this.mail = mail;
        this.password = password;
        this.accounts = accounts;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<BankAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<BankAccount> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return "Person{" +
                "mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
