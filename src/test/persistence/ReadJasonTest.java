package persistence;

import model.Goals;
import model.SleepOverall;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ReadJasonTest {

    @Test
    void testReaderNonExistentFile() {
        ReadJason reader = new ReadJason("./data/noSuchFile.json");
        try {
            SleepOverall sp = reader.readSleepOverall();
            Goals g = reader.readGoals();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        ReadJason reader = new ReadJason("./data/testReaderEmptyWorkRoom.json");
        try {
            SleepOverall sp = reader.readSleepOverall();
            Goals g = reader.readGoals();
            assertEquals(-1,g.getScore());
            assertEquals(-1,g.getTime());
            assertEquals(0,sp.getNumber());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        ReadJason reader = new ReadJason("./data/testReaderGeneralWorkRoom.json");
        try {
            SleepOverall sp = reader.readSleepOverall();
            Goals g = reader.readGoals();
            assertEquals(80,g.getScore());
            assertEquals(10,g.getTime());
            assertEquals(3,sp.getNumber());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
