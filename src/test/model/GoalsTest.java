package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GoalsTest {
    private Goals g;

    @BeforeEach
    public void runBefore() {
        g = new Goals(8, 90);
    }

    @Test
    public void testGoalsConstructor() {
        assertEquals(8, g.getTime());
        assertEquals(90, g.getScore());
    }

    @Test
    public void testGetTime() {
        assertEquals(8, g.getTime());
    }

    @Test
    public void testGetScore() {
        assertEquals(90, g.getScore());
    }

}
