package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DateTest {
    private Date date;

    @BeforeEach
    public void runBefore() {
        date = new Date(2024, 2, 18);
    }

    @Test
    public void testDateConstructor() {
        assertEquals(2024, date.getYear());
        assertEquals(2, date.getMonth());
        assertEquals(18, date.getDay());
    }

    @Test
    public void testGetYear() {
        assertEquals(2024, date.getYear());
    }

    @Test
    public void testGetMonth() {
        assertEquals(2, date.getMonth());
    }

    @Test
    public void testGetDay() {
        assertEquals(18, date.getDay());
    }

    @Test
    public void testGetDatee() {
        assertEquals(date.getDatee(), date.getYear() + "/" + date.getMonth() + "/" + date.getDay());
    }
}
