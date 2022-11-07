package service;

import entity.Person;
import entity.enumarate.ProcessType;

import java.math.BigDecimal;

public interface MoneyService {
    void sendMoney(Person person,ProcessType processType);
    void withdrawMoney(Person person);
    void depositMoney(Person person);
}
