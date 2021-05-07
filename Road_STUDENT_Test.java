import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class Road_STUDENT_Test {


    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testEquals(){

        Town town1 = new Town("Town1");
        Town town2 = new Town("Town2");

        Road road1 = new Road(town1,town2,"Just a Road");
        Road road2 = new Road(town2,town1,"Just a Road");

        assertEquals(road1,road2);
    }


}
