package domain;

import exceptions.TimeCardIllegalArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TimeCardTest {
    TimeCard timeCard;

    @BeforeEach
    void setUp() {
        this.timeCard = new TimeCard(LocalDateTime.of(2023,10, 25, 14, 0),
                LocalDateTime.of(2023,10, 25, 23, 0));
    }

    @Test
    void copy() {
       TimeCard result = this.timeCard.copy();
       assertEquals(result, this.timeCard);
    }

    @Test
    void calcHours() {
        assertEquals(9.00, this.timeCard.calcHours());
    }

    @Test
    void testToString() {
        String strTimeCard = this.timeCard.toString();
        int i = strTimeCard.indexOf("startDateTime");
        strTimeCard = strTimeCard.substring(i);
        String result = "startDateTime=2023/10/25 02:00PM, endDateTime=2023/10/25 11:00PM, hours=9.00";
        assertEquals(result, strTimeCard);
    }

    @Test
    void startTimeEqualsEndTimeTest() {
        assertThrows(TimeCardIllegalArgumentException.class,
                () -> new TimeCard(LocalDateTime.of(2019,11, 13, 20, 0),
                LocalDateTime.of(2019,11, 13, 20, 0)));

    }

    @Test
    void startTimeAfterEndTimeTest() {
        assertThrows(TimeCardIllegalArgumentException.class,
                () -> new TimeCard(LocalDateTime.of(2019,11, 13, 20, 0),
                        LocalDateTime.of(2019,11, 13, 18, 0)));

    }

}