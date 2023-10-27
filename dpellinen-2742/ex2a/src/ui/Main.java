package ui;

import domain.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Person person = new Person(101, "Dan", "Pellinen", "dpell");
        System.out.println(person);
        Tenant tenant = new Tenant(101, "Dan", "Pellinen", "dpell",
                LocalDateTime.now(), "111-11-1111", "111-111-1111", "YMCA",
                "Director", 10000.00, LocalDateTime.now());
        tenant.setUserName("newUserName");
        tenant.setPhone("123-New-Phone");
        System.out.println(tenant);

        Administrator administrator = new Administrator(101, "AdminDan", "Pellinen", "dpell",
                LocalDateTime.now(), "111-11-1111", "111-111-1111", LocalDateTime.now());
        System.out.println(administrator);

        ContractAdministrator contractAdministrator = new ContractAdministrator(104, "ConAdmin", "Johnson",
                "cAdJohn", LocalDateTime.now(), "Con-Admi-nSSN", "123-con-admn", LocalDateTime.now(), 300.00);
        System.out.println(contractAdministrator);

        HourlyAdministrator hourlyAdministrator = new HourlyAdministrator(105, "Hourly", "Admin", "HrAdmin",
                LocalDateTime.now(), "hrl-admn-stra", "123-hrAd-phone", LocalDateTime.now(), 50.0);

        hourlyAdministrator.addTimeCard(LocalDateTime.of(2023,10, 25, 14, 0),
                LocalDateTime.of(2023,10, 25, 23, 0));

        hourlyAdministrator.addTimeCard(LocalDateTime.of(2023,10, 26, 14, 0),
                LocalDateTime.of(2023,10, 26, 23, 0));

        hourlyAdministrator.addTimeCard(LocalDateTime.of(2023,10, 27, 14, 0),
                LocalDateTime.of(2023,10, 27, 23, 0));

        hourlyAdministrator.addTimeCard(LocalDateTime.of(2023,10, 28, 14, 0),
                LocalDateTime.of(2023,10, 28, 23, 0));

        hourlyAdministrator.addTimeCard(LocalDateTime.of(2023,10, 29, 14, 0),
                LocalDateTime.of(2023,10, 29, 23, 0));


        System.out.println(hourlyAdministrator);

        ArrayList<TimeCard> timeCards = hourlyAdministrator.getTimeCards();
        for (TimeCard timeCard: timeCards) {
            System.out.println("\t" + timeCard);

//            System.out.println(calcTotalHours);
        }
    }
}
