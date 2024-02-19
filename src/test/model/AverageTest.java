package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AverageTest {
    private Average average;
    private Sleep sleep1;
    private Sleep sleep2;
    private Sleep sleep3;

    @BeforeEach
    public void runBefore() {
        average = new Average();
        sleep1 = new Sleep(7, new Date(2024, 2, 18), 80, "overnight Sleep");
        sleep2 = new Sleep(8, new Date(2024, 2, 19), 90, "overnight Sleep");
        sleep3 = new Sleep(6, new Date(2024, 2, 20), 75, "overnight Sleep");
        average.addSleep(sleep1);
        average.addSleep(sleep2);
        average.addSleep(sleep3);
    }

    @Test
    public void testAverage() {
        average = new Average();
        assertEquals(0, average.getNum());
    }

    @Test
    public void testAddSleep() {
        assertEquals(3, average.getNum());

        average = new Average();
        average.addSleep(sleep1);
        average.addSleep(sleep3);
        assertEquals(2, average.getNum());

        average = new Average();
        average.addSleep(sleep3);
        assertEquals(1, average.getNum());

        average = new Average();
        assertEquals(0, average.getNum());
    }

    @Test
    public void testGetNum() {
        assertEquals(3, average.getNum());

        average = new Average();
        average.addSleep(sleep1);
        average.addSleep(sleep3);
        assertEquals(2, average.getNum());

        average = new Average();
        average.addSleep(sleep3);
        assertEquals(1, average.getNum());

        average = new Average();
        assertEquals(0, average.getNum());
    }

    @Test
    public void testCalculateTime() {
        assertEquals(7, average.calculateTime());
    }

    @Test
    public void testCalculateScore() {
        assertEquals(81, average.calculateScore());
    }
}
