package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SleepTest {
    private Sleep sleep;
    private Goals goals;

    @BeforeEach
    void runBefore() {
        sleep = new Sleep(8, new Date(2024, 2, 18), 85, "overnight Sleep");
        goals = new Goals(8, 90);
    }

    @Test
    void testSleepConstructor() {
        assertEquals(8, sleep.getTime());
        assertEquals(85, sleep.getScore());
        assertEquals("overnight Sleep", sleep.getType());
        assertNotNull(sleep.getDate());
    }

    @Test
    void testReachGoals() {
        assertFalse(sleep.reachGoal(goals));
        assertTrue(sleep.reachGoal(new Goals(7, 80)));
        assertFalse(sleep.reachGoal(new Goals(10, 80)));
        assertFalse(sleep.reachGoal(new Goals(7, 100)));
    }

    @Test
    public void testGetTime() {
        assertEquals(8, sleep.getTime());
    }

    @Test
    public void testGetScore() {
        assertEquals(85, sleep.getScore());
    }

    @Test
    public void testGetType() {
        assertEquals("overnight Sleep", sleep.getType());
    }

    @Test
    public void testGetDate() {
        Date date = new Date(2024, 2, 18);
        sleep = new Sleep(8, date, 85, "overnight Sleep");
        assertEquals(sleep.getDate(),date.getDatee());
    }

    @Test
    public void testgetDatee() {
        Date date = new Date(2024, 2, 18);
        sleep = new Sleep(8, date, 85, "overnight Sleep");
        assertEquals(sleep.getDatee(),date);
    }

}
