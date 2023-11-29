package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ContractAdministratorTest {
    ContractAdministrator contractAdministrator;

    @BeforeEach
    void setUp() {
        this.contractAdministrator = new ContractAdministrator(104, "ConAdmin", "Johnson",
                "cAdJohn", LocalDateTime.of(2023,10,27, 00, 00), "Con-Admi-nSSN", "123-con-admn", LocalDateTime.of(2023,10,27, 00, 00), 300.00);
    }

    @Test
    void testToString() {
        String result = "104 Johnson, ConAdmin,  birthDate=2023/10/27, ssn='Con-Admi-nSSN', phone='123-con-admn', employmentStartDate=2023/10/27 monthlyRate=300.0 grossPay=300.0";
        assertEquals(result, this.contractAdministrator.toString());
    }

    @Test
    void calcGrossPay() {
        assertEquals(300.0, this.contractAdministrator.calcGrossPay());
    }
}