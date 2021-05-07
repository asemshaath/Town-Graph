import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * This is the manager class.
 * All the methods and fields are implemented to manage the graph class
 *
 * @author Asem Shaath
 */
public class TownGraphManager implements TownGraphManagerInterface {


    private Graph graph = new Graph();

    /**
     * Adds a road with 2 towns and a road name
     *
     * @param town1    name of town 1 (lastname, firstname)
     * @param town2    name of town 2 (lastname, firstname)
     * @param weight
     * @param roadName name of road
     * @return true if the road was added successfully
     */
    @Override
    public boolean addRoad(String town1, String town2, int weight, String roadName) {

        return graph.addEdge(new Town(town1),new Town(town2),weight,roadName) != null;
    }

    /**
     * Returns the name of the road that both towns are connected through
     *
     * @param town1 name of town 1 (lastname, firstname)
     * @param town2 name of town 2 (lastname, firstname)
     * @return name of road if town 1 and town2 are in the same road, returns null if not
     */
    @Override
    public String getRoad(String town1, String town2) {

        return graph.getEdge(new Town(town1), new Town(town2)).getName();
    }

    /**
     * Adds a town to the graph
     *
     * @param v the town's name  (lastname, firstname)
     * @return true if the town was successfully added, false if not
     */
    @Override
    public boolean addTown(String v) {

        return graph.addVertex(new Town(v));
    }

    /**
     * Gets a town with a given name
     *
     * @param name the town's name
     * @return the Town specified by the name, or null if town does not exist
     */
    @Override
    public Town getTown(String name) {

        Town town = null;
        Set<Town> towns = graph.vertexSet();

        Iterator<Town> it = towns.iterator();

        while (it.hasNext())
        {
            Town someTown = it.next();

            if (someTown.getName().equals(name)){
                return someTown;
            }
        }

        return null;
    }

    /**
     * Determines if a town is already in the graph
     *
     * @param v the town's name
     * @return true if the town is in the graph, false if not
     */
    @Override
    public boolean containsTown(String v) {

        return graph.containsVertex(getTown(v));
    }

    /**
     * Determines if a road is in the graph
     *
     * @param town1 name of town 1 (lastname, firstname)
     * @param town2 name of town 2 (lastname, firstname)
     * @return true if the road is in the graph, false if not
     */
    @Override
    public boolean containsRoadConnection(String town1, String town2) {

        return graph.getEdge(getTown(town1),getTown(town2)) != null;
    }

    /**
     * Creates an arraylist of all road titles in sorted order by road name
     *
     * @return an arraylist of all road titles in sorted order by road name
     */
    @Override
    public ArrayList<String> allRoads() {

        ArrayList<String> roadNames = new ArrayList<String>();
        Set<Road> roadSet = graph.edgeSet();

        Iterator<Road> it = roadSet.iterator();

        while (it.hasNext())
        {
            Road someRoad = it.next();

            roadNames.add(someRoad.getName());
        }

        Collections.sort(roadNames);

        return roadNames;
    }

    /**
     * Deletes a road from the graph
     *
     * @param town1 name of town 1 (lastname, firstname)
     * @param town2 name of town 2 (lastname, firstname)
     * @param road
     * @return true if the road was successfully deleted, false if not
     */
    @Override
    public boolean deleteRoadConnection(String town1, String town2, String road) {

        int distance = 0;
        Road road1 = graph.getEdge(getTown(town1), getTown(town2));
        Set<Road> roadSet = graph.edgeSet();

        Iterator<Road> it = roadSet.iterator();

        while (it.hasNext())
        {
            Road someRoad = it.next();

            if (someRoad.getName().equals(road)){

                distance = someRoad.getWeight();
            }
        }

        return graph.removeEdge(getTown(town1),getTown(town2),distance,road) != null;

    }

    /**
     * Deletes a town from the graph
     *
     * @param v name of town (lastname, firstname)
     * @return true if the town was successfully deleted, false if not
     */
    @Override
    public boolean deleteTown(String v) {

        return graph.removeVertex(getTown(v));
    }

    /**
     * Creates an arraylist of all towns in alphabetical order (last name, first name)
     *
     * @return an arraylist of all towns in alphabetical order (last name, first name)
     */
    @Override
    public ArrayList<String> allTowns() {

        ArrayList<String> townNames = new ArrayList<String>();
        Set<Town> townSet = graph.vertexSet();

        Iterator<Town> it = townSet.iterator();

        while (it.hasNext())
        {
            Town someTown = it.next();

            townNames.add(someTown.getName());
        }

        Collections.sort(townNames);

        return townNames;

    }

    /**
     * Returns the shortest path from town 1 to town 2
     *
     * @param town1 name of town 1 (lastname, firstname)
     * @param town2 name of town 2 (lastname, firstname)
     * @return an Arraylist of roads connecting the two towns together, null if the
     * towns have no path to connect them.
     */
    @Override
    public ArrayList<String> getPath(String town1, String town2) {

        return graph.shortestPath(new Town(town1), new Town(town2));
    }

    /**
     * This is a method that takes data from the file and add it to the graph
     * @param selectedFile
     */
    public void populateTownGraph(File selectedFile) throws IOException, FileNotFoundException {

        Scanner readInput = new Scanner(selectedFile);
        String text = "";

        while (readInput.hasNextLine()) {
            text += readInput.nextLine() + " ";
        }

        // I270-N,14;Frederick;Clarksburg
        // roadName , weight ; Town1 ; Town2
        
        String[] info = text.split(" ");
        String [][] fullInfo = new String[info.length][];
        
        for (int i = 0; i < info.length; i++){

            fullInfo[i] = new String[4];
            fullInfo[i][0] = info[i].split(";")[0].split(",")[0];
            fullInfo[i][1] = info[i].split(";")[0].split(",")[1];
            fullInfo[i][2] = info[i].split(";")[1];
            fullInfo[i][3] = info[i].split(";")[2];

            addTown(fullInfo[i][2]);
            addTown(fullInfo[i][3]);
            addRoad(fullInfo[i][2], fullInfo[i][3],
                    Integer.parseInt(fullInfo[i][1]), fullInfo[i][0]);
            
        }


    }
    
}
