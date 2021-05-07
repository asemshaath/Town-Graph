import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

import static org.junit.Assert.*;

public class Graph_STUDENT_Test {

    private GraphInterface<Town,Road> graph;
    Town frederick;
    Town baltimore;
    Town dc;
    Town alex;

    @Before
    public void setUp() throws Exception {
        graph = new Graph();

        frederick = new Town("Frederick");
        baltimore = new Town("Baltimore");
        dc = new Town("Washington DC");
        alex = new Town("Alexandria");

        graph.addVertex(frederick);
        graph.addVertex(baltimore);
        graph.addVertex(dc);
        graph.addVertex(alex);

        graph.addEdge(frederick, baltimore, 50, "I-70");
        graph.addEdge(frederick, dc, 45, "I-270");
        graph.addEdge(dc, baltimore, 40, "I-95");
        graph.addEdge(dc, alex, 10, "I-66");

    }

    @After
    public void tearDown() throws Exception {
        graph = null;
    }

    @Test
    public void testGetEdge() {
        assertEquals(new Road(frederick, dc,45, "I-270"), graph.getEdge(frederick, dc));
        assertEquals(new Road(dc, alex,10, "I-66"), graph.getEdge(dc, alex));
    }

    @Test
    public void testAddEdge() {
        assertEquals(false, graph.containsEdge(frederick, alex));
        graph.addEdge(frederick, alex, 60, "US-40");
        assertEquals(true, graph.containsEdge(frederick, alex));
    }

    @Test
    public void testAddVertex() {
        Town newTown = new Town("NYC");
        assertEquals(false, graph.containsVertex(newTown));
        graph.addVertex(newTown);
        assertEquals(true, graph.containsVertex(newTown));
    }

    @Test
    public void testContainsEdge() {
        assertEquals(true, graph.containsEdge(frederick, baltimore));
        assertEquals(false, graph.containsEdge(alex, baltimore));
    }

    @Test
    public void testContainsVertex() {
        assertEquals(true, graph.containsVertex(new Town("Baltimore")));
        assertEquals(false, graph.containsVertex(new Town("Ocean City")));
    }

    @Test
    public void testEdgeSet() {

        Set<Road> roads = graph.edgeSet();
        ArrayList<String> roadArrayList = new ArrayList<String>();

        for(Road road : roads)
            roadArrayList.add(road.getName());

        Collections.sort(roadArrayList);

        assertEquals("I-270", roadArrayList.get(0));
        assertEquals("I-66", roadArrayList.get(1));
        assertEquals("I-70", roadArrayList.get(2));
        assertEquals("I-95", roadArrayList.get(3));

    }

    @Test
    public void testEdgesOf() {

        Set<Road> roads = graph.edgesOf(frederick);
        ArrayList<String> roadArrayList = new ArrayList<String>();

        for(Road road : roads)
            roadArrayList.add(road.getName());

        Collections.sort(roadArrayList);

        assertEquals("I-270", roadArrayList.get(0));
        assertEquals("I-70", roadArrayList.get(1));

    }

    @Test
    public void testRemoveEdge() {

        assertEquals(true, graph.containsEdge(dc, alex));
        graph.removeEdge(dc, alex, 10, "I-66");

        assertEquals(false, graph.containsEdge(dc, alex));

    }

    @Test
    public void testRemoveVertex() {

        assertEquals(true, graph.containsVertex(alex));
        graph.removeVertex(alex);
        assertEquals(false, graph.containsVertex(alex));

    }

    @Test
    public void testVertexSet() {

        Set<Town> roads = graph.vertexSet();

        assertEquals(true,roads.contains(frederick));
        assertEquals(true, roads.contains(dc));
        assertEquals(true, roads.contains(baltimore));
        assertEquals(true, roads.contains(alex));

    }

    @Test
    public void testFrederickToAlexandria() {

        String beginTown = "Frederick", endTown = "Alexandria";
        Town beginIndex=null, endIndex=null;

        Set<Town> towns = graph.vertexSet();
        Iterator<Town> iterator = towns.iterator();

        while(iterator.hasNext()) {

            Town town = iterator.next();

            if(town.getName().equals(beginTown))
                beginIndex = town;

            if(town.getName().equals(endTown))
                endIndex = town;
        }

        if(beginIndex != null && endIndex != null)
        {

            ArrayList<String> path = graph.shortestPath(beginIndex,endIndex);
            assertNotNull(path);
            assertTrue(path.size() > 0);
            assertEquals("Frederick via I-270 to Washington DC 45 mi",path.get(0).trim());
            assertEquals("Washington DC via I-66 to Alexandria 10 mi",path.get(1).trim());
        }
        else
            fail("Town names are not valid");

    }


}
