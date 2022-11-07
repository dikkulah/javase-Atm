package service;

import entity.BankAccount;
import entity.Person;
import entity.enumarate.ProcessType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MoneyServiceImpl implements MoneyService {

    public static List<Person> personList = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    BigDecimal price;

    @Override
    public void sendMoney(Person person, ProcessType processType) {
        System.out.print("Alıcı kişinin emailini giriniz:(ipucu fake@gmail.com) ");
        String mailToSend = sc.nextLine();
        if (personList.stream().anyMatch(person1 -> person1.getMail().equals(mailToSend))) {
            Person receiver = personList.stream().filter(person1 -> person1.getMail().equals(mailToSend)).findFirst().orElseThrow();
            System.out.println("Hesaplar");
            System.out.println(receiver.getAccounts());
            System.out.print("Alıcı banka hesabının idsini giriniz:(ipucu 2) ");

            int accountIdForReceive = sc.nextInt();
            if (receiver.getAccounts().stream().anyMatch(bankAccount -> bankAccount.getId() == accountIdForReceive)) {
                System.out.println("Hesaplar");
                System.out.println(person.getAccounts());
                System.out.print("Göndermek istediğiniz hesabı seçiniz:(ipucu 1)");
                int accountIdForSend = sc.nextInt();
                if (person.getAccounts().stream().anyMatch(bankAccount -> bankAccount.getId() == accountIdForSend)) {
                    System.out.println("Göndermek istediğiniz miktarı giriniz: ");
                    BigDecimal price = BigDecimal.valueOf(sc.nextLong());
                    BankAccount accountForSend= person.getAccounts().stream().filter(bankAccount -> bankAccount.getId() == accountIdForSend).findFirst().orElseThrow();
                    BankAccount accountForReceive = receiver.getAccounts().stream().filter(bankAccount -> bankAccount.getId() == accountIdForReceive).findFirst().orElseThrow();
                    if (price.compareTo(accountForSend.getAmount()) < 1) {
                        accountForSend.setAmount(accountForSend.getAmount().subtract(price));
                        accountForReceive.setAmount(accountForReceive.getAmount().add(price));
                        if (processType == ProcessType.DRAFT)
                            System.out.println("Havale işlemi başarı ile gerçekleşti.");
                        else if (processType == ProcessType.EFT) {
                            System.out.println("EFT işlemi başarı ile gerçekleşti.");
                        }
                    }else {
                        System.out.println("Göndermek istediğiniz miktar hesapbın bakiyesinden fazla");
                    }
                }else {
                    System.out.println("Göndermek istediğiniz hesap mevcut değil.");
                }


            } else {
                System.out.println("Böyle bir banka hesabı mevcut değil.");
            }
        } else {
            System.out.println("Böyle bir mail adresi bankamızda kayıtlı değil.");
        }

    }

    @Override
    public void withdrawMoney(Person person) {

        System.out.println("Çekmek istediğiniz hesabın idsini giriniz: ");
        System.out.println(person.getAccounts());
        int selectedAccount = sc.nextInt();
        if (person.getAccounts().stream().anyMatch(bankAccount -> bankAccount.getId() == selectedAccount)) {
            System.out.print("Çekmek istediğiniz miktarı giriniz: ");
            price = BigDecimal.valueOf(sc.nextLong());
            BankAccount selectedAccountEntity = person.getAccounts().get(selectedAccount - 1);
            if (price.compareTo(BigDecimal.ZERO) > 0 && price.compareTo(selectedAccountEntity.getAmount()) < 1) {
                BigDecimal amount = selectedAccountEntity.getAmount();
                person.getAccounts().get(selectedAccount - 1).setAmount(amount.subtract(price));
                System.out.println("Para çekme işlemi başarı ile tamamlandı.");
            } else {
                System.out.println("Hatalı miktar");
            }

        } else {
            System.out.println("Böyle bir hesap mevcut değil.");
        }
    }

    @Override
    public void depositMoney(Person person) {
        System.out.println("Yatırmak istediğiniz hesabın idsini giriniz: ");
        System.out.println(person.getAccounts());
        int selectAccount = sc.nextInt();
        if (person.getAccounts().stream().anyMatch(bankAccount -> bankAccount.getId() == selectAccount)) {
            System.out.print("Yatırmak istediğiniz miktarı giriniz: ");
            price = BigDecimal.valueOf(sc.nextLong());
            if (price.compareTo(BigDecimal.ZERO) > 0) {
                BigDecimal amount = person.getAccounts().get(selectAccount - 1).getAmount();
                person.getAccounts().get(selectAccount - 1).setAmount(amount.add(price));
                System.out.println("işlem başarı ile gerçekleşti.");
            } else {
                System.out.println("Hatalı miktar");
            }

        } else {
            System.out.println("Böyle bir hesap mevcut değil.");
        }
    }
}
