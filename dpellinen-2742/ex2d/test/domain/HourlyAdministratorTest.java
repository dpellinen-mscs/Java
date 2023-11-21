package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HourlyAdministratorTest {
    HourlyAdministrator hourlyAdministrator;

    @BeforeEach
    void setUp() {
        this.hourlyAdministrator = new HourlyAdministrator(105, "Hourly", "Admin", "HrAdmin",
                LocalDateTime.now(), "hrl-admn-stra", "123-hrAd-phone", LocalDateTime.now(), 50.0);
    }

    @Test
    void addTimeCard() {
       this.hourlyAdministrator.addTimeCard(LocalDateTime.of(2023,10, 25, 14, 0),
                LocalDateTime.of(2023,10, 25, 23, 0));
        TimeCard a =  this.hourlyAdministrator.getTimeCard(0);
        String strA = a.toString();

        int i = strA.indexOf("startDateTime");
        strA = strA.substring(i);
        String result = "startDateTime=2023/10/25 02:00PM, endDateTime=2023/10/25 11:00PM, hours=9.00";
        assertEquals(result, strA);
    }

    @Test
    void removeTimeCard() {
        assertEquals(null, this.hourlyAdministrator.removeTimeCard(0));

        this.hourlyAdministrator.addTimeCard((LocalDateTime.of(2023,10, 25, 14, 0)),
                LocalDateTime.of(2023,10, 25, 23, 0));
        TimeCard tc1 = this.hourlyAdministrator.removeTimeCard(0);

        String strTC1= tc1.toString();

        int i = strTC1.indexOf("startDateTime");
        strTC1 = strTC1.substring(i);
        String result = "startDateTime=2023/10/25 02:00PM, endDateTime=2023/10/25 11:00PM, hours=9.00";
        assertEquals(result, strTC1);

      int tcSize =  this.hourlyAdministrator.getTimeCards().size();
      assertEquals(tcSize, 0);
    }

    @Test
    void getTimeCard() {
       assertEquals(null, this.hourlyAdministrator.getTimeCard(0));

       this.hourlyAdministrator.addTimeCard((LocalDateTime.of(2023,10, 25, 14, 0)),
               LocalDateTime.of(2023,10, 25, 23, 0));
       TimeCard tc1 = this.hourlyAdministrator.getTimeCard(0);

        String strTC1= tc1.toString();

        int i = strTC1.indexOf("startDateTime");
        strTC1 = strTC1.substring(i);
        String result = "startDateTime=2023/10/25 02:00PM, endDateTime=2023/10/25 11:00PM, hours=9.00";
        assertEquals(result, strTC1);
    }

    @Test
    void getTimeCards() {
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

        ArrayList<TimeCard> timeCardsTest = this.hourlyAdministrator.getTimeCards();

        for (TimeCard timeCard : timeCardsTest) {
            int i = 0;  i++;
            assertEquals(this.hourlyAdministrator.getTimeCard(i), timeCardsTest.get(i));

            assert(this.hourlyAdministrator.getTimeCard(i) != timeCardsTest.get(i));
        }
    }

    @Test
    void testToString() {
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
      String result =   "105 Admin, Hourly,  birthDate=2023/10/27, ssn='hrl-admn-stra', phone='123-hrAd-phone'," +
              " employmentStartDate=2023/10/27 hourlyRate=50.0 total hours=45.0 gross pay=2250.0";
        assertEquals(result, this.hourlyAdministrator.toString());
    }

    @Test
    void calcTotalHours() {
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
        assertEquals(45, this.hourlyAdministrator.calcTotalHours());
    }

    @Test
    void calcGrossPay() {
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
        assertEquals(45, this.hourlyAdministrator.calcTotalHours());
        assertEquals(2250.00, this.hourlyAdministrator.calcGrossPay());
    }
}