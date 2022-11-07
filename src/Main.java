import entity.BankAccount;
import entity.Person;
import entity.enumarate.Currency;
import entity.enumarate.ProcessType;
import service.MoneyService;
import service.MoneyServiceImpl;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        MoneyService moneyService = new MoneyServiceImpl();

        BankAccount adminAccount = new BankAccount(1, BigDecimal.valueOf(1000L), Currency.TURKISH_LIRA);
        Person admin = new Person("admin@gmail.com", "root");
        admin.getAccounts().add(adminAccount);

        Person fake = new Person("fake@gmail.com","root");
        fake.getAccounts().add(new BankAccount(2,BigDecimal.ZERO,Currency.TURKISH_LIRA));

        MoneyServiceImpl.personList.add(admin);
        MoneyServiceImpl.personList.add(fake);

        String email, password;
        int right = 3;
        int select;
        BigDecimal price;

        System.out.println("DikkulahBank Atmye hoş geldiniz lütfen kullanıcı bilgilerini giriniz.");


        while (right > 0) {
            System.out.print("Email: ");
            email = sc.nextLine();
            System.out.print("Şifre: ");
            password = sc.nextLine();
            if (admin.getMail().equals(email) && admin.getPassword().equals(password)) {
                System.out.println("Başarıyla otutum açtınız");
                do {
                    System.out.println("----------------");
                    System.out.println("""
                            1-Bakiye Sorgula
                            2-Para yatırma
                            3-Para Çekme
                            4-Havale yap
                            5-Eft yap
                            6-Fake mail;
                            7-Çıkış Yap
                            """);
                    System.out.print("Lütfen yapmak istediğiniz işlemi seçiniz : ");
                    select = sc.nextInt();

                    switch (select) {
                        case 1:
                            System.out.println(admin.getAccounts().toString());
                            break;
                        case 2:
                            moneyService.depositMoney(admin);
                            break;
                        case 3:
                            moneyService.withdrawMoney(admin);
                            break;
                        case 4:
                            moneyService.sendMoney(admin,ProcessType.DRAFT);
                            break;
                        case 5:
                            moneyService.sendMoney(admin,ProcessType.EFT);
                            break;
                        case 6:
                            System.out.println(fake);
                        default:

                    }

                }while (select!=7);
                System.out.println("Çıkış yaptınız.");
                break;
            } else {
                right--;
                System.out.println("Hatalı kullanıcı girişi lütfen tekrar deneyiniz.");
                if (right == 0) {
                    System.out.println("Hesabınız bloke olmuştur.");
                } else {
                    System.out.println("kalan hakkınız: " + right);
                }
            }
        }


    }


}
