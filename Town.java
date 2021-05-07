import java.util.HashSet;
import java.util.Set;

/**
 * This is the data element class that holds each town's stuff. All the methods in this class will support the town element
 *
 * @author Asem Shaath
 */
public class Town implements Comparable<Town>{

    private String name;
    private Set<Road> connectedRoads = new HashSet<>();
    private Set<Town> connectedTowns = new HashSet<>();

    /**
     * This is a constructor that sets the name of the town.
     *
     * @param name of the town
     */
    public Town(String name){

        this.name = name;
    }

    /**
     * This is a constructor that takes a town object and copy its name.
     *
     * @param templateTown
     */
    public Town(Town templateTown){

        this.name = templateTown.name;
    }

    /**
     * This is an override method from Comparable.java interface.
     * The method compares between the name of two given towns.
     *
     * @param o
     * @return 0 if the are equal.
     */
    @Override
    public int compareTo(Town o) {

        return o.getName().compareTo(name);
    }

    /**
     * A getter method
     *
     * @return name of the town
     */
    public String getName(){

        return name;
    }

    /**
     * @return townName
     */
    public String toString(){

        return name;
    }

    /**
     *
     * @return hashcode of the name of the town
     */
    public int hashCode(){

        return name.hashCode();
    }

    /**
     *
     * A method that checks if the two towns are equal or not
     *
     * @param o
     *
     * @return true when they are equal; otherwise, false.
     */
    public boolean equals(Object o){

        if (o.getClass().equals(this.getClass())){


            return ((Town)o).name.equals(this.name);

        }

        return false;
    }

    /**
     * A method that connects a road to the town
     *
     * @param road
     */
    public void connectRoad(Road road)
    {

        connectedRoads.add(road);
    }

    /**
     * A method that sets the adjacent town of our town
     *
     * @param town
     */
    public void connectTown(Town town)
    {

        connectedTowns.add(town);
    }

    /**
     *
     * @return set of adjacent towns
     */
    public Set<Town> getConnectedTowns()
    {

        return connectedTowns;
    }

    /**
     *
     * @return set of adjacent roads
     */
    public Set<Road> getConnectedRoads(){

        return connectedRoads;
    }




}
