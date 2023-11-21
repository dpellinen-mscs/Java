package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TenantTest {
    Tenant tenant = null;

    @BeforeEach
    void setUp() {
        this.tenant = new Tenant(102, "Dan", "Pellinen", "newUserName",
                LocalDateTime.of(1988, 10, 30, 0, 0, 0), "111-11-1111", "123-New-Phone", "YMCA",
                "Director", 10000.00, LocalDateTime.of(2013, 10, 30, 0, 0, 0));
    }

    @Test
    void ToStringTest() {
        String result = "102 Pellinen, Dan,  birthDate=1988/10/30, ssn='111-11-1111', phone='123-New-Phone', employer='YMCA', occupation='Director', grossPay=10000.0, employmentStartDate=2013/10/30";
        assertEquals(result, this.tenant.toString());
    }
}