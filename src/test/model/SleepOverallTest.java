package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SleepOverallTest {
    private SleepOverall sleepOverall;
    private Sleep sleep;
    private Sleep sleep1;
    private Sleep sleep2;
    private Sleep sleep3;

    @BeforeEach
    public void runBefore() {
        sleepOverall = new SleepOverall();
        sleep = new Sleep(8, new Date(2024, 2, 18), 85, "overnight Sleep");
        sleep1 = new Sleep(7, new Date(2024, 2, 18), 80, "Nap");
        sleep2 = new Sleep(8, new Date(2024, 2, 19), 90, "overnight Sleep");
        sleep3 = new Sleep(6, new Date(2024, 2, 20), 75, "Afternoon Sleep");
        sleepOverall.addSleep(sleep);
    }

    @Test
    public void testSleepOverall() {
        sleepOverall = new SleepOverall();
        sleepOverall.addSleep(sleep);
        sleepOverall.addSleep(sleep1);
        sleepOverall.addSleep(sleep2);
        assertEquals(3, sleepOverall.getNumber());
    }

    @Test
    public void testAddSleep() {
        assertEquals(1, sleepOverall.getNumber());
        sleepOverall.addSleep(sleep1);
        assertEquals(2, sleepOverall.getNumber());
        sleepOverall.addSleep(sleep2);
        assertEquals(3, sleepOverall.getNumber());
        sleepOverall.addSleep(sleep3);
        assertEquals(4, sleepOverall.getNumber());
    }

    @Test
    public void testGetNum() {
        assertEquals(1, sleepOverall.getNumber());
        sleepOverall.addSleep(sleep1);
        assertEquals(2, sleepOverall.getNumber());
        sleepOverall.addSleep(sleep2);
        assertEquals(3, sleepOverall.getNumber());
        sleepOverall.addSleep(sleep3);
        assertEquals(4, sleepOverall.getNumber());
    }


    @Test
    public void testResetSleep() {
        sleepOverall.resetSleep();
        assertEquals(0, sleepOverall.getNumber());
    }

    @Test
    public void testGetLastSeven() {
        Average lastSeven = sleepOverall.getLastSeven();
        assertEquals(1, lastSeven.getNum());
        sleepOverall.addSleep(sleep1);
        lastSeven = sleepOverall.getLastSeven();
        assertEquals(2, lastSeven.getNum());
        sleepOverall.addSleep(sleep2);
        lastSeven = sleepOverall.getLastSeven();
        assertEquals(3, lastSeven.getNum());
        sleepOverall.addSleep(sleep3);
        lastSeven = sleepOverall.getLastSeven();
        assertEquals(4, lastSeven.getNum());
    }

    @Test
    public void testGetLastMonth() {
        Average lastMonth = sleepOverall.getLastMonth();
        assertEquals(1, lastMonth.getNum());
        sleepOverall.addSleep(sleep1);
        lastMonth = sleepOverall.getLastMonth();
        assertEquals(2, lastMonth.getNum());
        sleepOverall.addSleep(sleep2);
        lastMonth = sleepOverall.getLastMonth();
        assertEquals(3, lastMonth.getNum());
        sleepOverall.addSleep(sleep3);
        lastMonth = sleepOverall.getLastMonth();
        assertEquals(4, lastMonth.getNum());
    }

    @Test
    public void testGetNapNum() {
        sleepOverall.addSleep(sleep1);
        sleepOverall.addSleep(sleep2);
        sleepOverall.addSleep(sleep3);
        assertEquals(1,sleepOverall.getNapNum());
    }

    @Test
    public void testGetAftNum() {
        sleepOverall.addSleep(sleep1);
        sleepOverall.addSleep(sleep2);
        sleepOverall.addSleep(sleep3);
        assertEquals(1,sleepOverall.getAftNum());
    }

    @Test
    public void testGetOverNum() {
        sleepOverall.addSleep(sleep1);
        sleepOverall.addSleep(sleep2);
        sleepOverall.addSleep(sleep3);
        assertEquals(2,sleepOverall.getOverNum());
    }
}
