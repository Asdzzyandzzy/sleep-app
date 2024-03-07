package persistence;

import model.Date;
import model.Goals;
import model.Sleep;
import model.SleepOverall;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class WriteJasonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            Date d1 = new Date(2024,3,1);
            Date d2 = new Date(2024,3,2);
            Sleep s1 = new Sleep(10,d1,100,"overnight Sleep");
            Sleep s2 = new Sleep(2,d2,50,"Afternoon Sleep");
            Sleep s3 = new Sleep(3,d2,90,"Nap");
            SleepOverall sp = new SleepOverall();
            Goals g = new Goals(10,80);
            WriteJason writer = new WriteJason("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            Date d1 = new Date(2024,3,1);
            Date d2 = new Date(2024,3,2);
            Sleep s1 = new Sleep(10,d1,100,"overnight Sleep");
            Sleep s2 = new Sleep(2,d2,50,"Afternoon Sleep");
            Sleep s3 = new Sleep(3,d2,90,"Nap");
            SleepOverall sp = new SleepOverall();
            Goals g = new Goals(-1,-1);
            WriteJason writer = new WriteJason("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(sp,g);
            writer.close();

            ReadJason reader = new ReadJason("./data/testWriterEmptyWorkroom.json");
            sp = reader.readSleepOverall();
            g = reader.readGoals();
            assertEquals(-1,g.getScore());
            assertEquals(-1,g.getTime());
            assertEquals(0,sp.getNumber());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Date d1 = new Date(2024,3,1);
            Date d2 = new Date(2024,3,2);
            Sleep s1 = new Sleep(10,d1,100,"overnight Sleep");
            Sleep s2 = new Sleep(2,d2,50,"Afternoon Sleep");
            Sleep s3 = new Sleep(3,d2,90,"Nap");
            SleepOverall sp = new SleepOverall();
            sp.addSleep(s1);
            sp.addSleep(s2);
            sp.addSleep(s3);
            Goals g = new Goals(10,80);
            WriteJason writer = new WriteJason("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(sp,g);
            writer.close();

            ReadJason reader = new ReadJason("./data/testWriterGeneralWorkroom.json");
            sp = reader.readSleepOverall();
            g = reader.readGoals();
            assertEquals(80,g.getScore());
            assertEquals(10,g.getTime());
            assertEquals(3,sp.getNumber());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
