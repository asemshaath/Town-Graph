import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Town_STUDENT_Test {

    Town nyc;
    Town dc;
    Town richmond;

    @Before
    public void setUp() throws Exception {


        // Richmond <------> Washington <------> NYC

        nyc = new Town("New York");
        dc = new Town("Washington DC");
        richmond = new Town("Richmond");

        nyc.connectTown(dc);
        dc.connectTown(richmond);
        dc.connectTown(nyc);
        richmond.connectTown(dc);

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testConnectedTowns(){

        Set<Town> ct = dc.getConnectedTowns();
        ArrayList<Town> ctArr = new ArrayList<Town>();

        for (Town t : ct){
            ctArr.add(t);
        }

        assertEquals("[New York, Richmond]",ctArr.toString());

    }

}
