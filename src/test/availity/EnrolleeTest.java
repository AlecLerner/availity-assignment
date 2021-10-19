package availity;

import availity.enrollees.Enrollee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnrolleeTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    public void test() {
        Enrollee enr = new Enrollee("106,Alex,Lerner,2,Cigna");
        assertEquals("106", enr.getUserId());
        assertEquals("Alex", enr.getFirstName());
        assertEquals("Lerner", enr.getLastName());
        assertEquals(2, enr.getVersion());
        assertEquals("Cigna", enr.getInsuranceCompany());
    }
    @Test
    public void testToCsv() {
        Enrollee enr = new Enrollee("106,Alex,Lerner,2,Cigna");
        assertEquals("106,Alex,Lerner,2,Cigna\n", enr.toCsv());
    }
}