/**
 * This is a data element class that builds the road element.
 * All the metjods and fields in this class will support the road element.
 *
 * @author Asem Shaath
 *
 */
public class Road implements Comparable<Road>{

    private Road road;
    private Town source;            // edge
    private Town destination;       // edge
    private int weight;
    private String roadName;

    /*
    Constructors goes here
     */

    /**
     * A constructor that takes the source town, the destination, length, and road name.
     *
     * @param source
     * @param destination
     * @param degrees
     * @param name
     */
    public Road(Town source, Town destination,int degrees, String name){

        this.source = source;
        this.destination = destination;
        this.weight = degrees;
        this.roadName = name;
    }


    /**
     *
     * A constructor that takes the source town, the destination, and road name.
     *
     * @param source
     * @param destination
     * @param name
     */
    public Road(Town source, Town destination, String name){

        this.source = source;
        this.destination = destination;
        this.weight = 1;
        this.roadName = name;
    }


    /*
    Methods goes here
     */

    /**
     *
     * This is an override method from Comparable.java interface.
     * The method compares between the name of two given roads.
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(Road o) {

        return o.getName().compareTo(roadName);
    }

    /**
     * Checks of the road contains the given town.
     *
     * @param town
     *
     * @return true if the town exists; otherwise, false.
     */
    public boolean contains(Town town) {

        if (destination.equals(town) || source.equals(town))
            return true;

        return false;
    }

    /**
     *
     * @return source --- name --- destination
     *
     */
    public String toString(){

        String myStr = "";

        myStr += "(" + source.getName() + " --- " + roadName + " --- " + destination.getName() + ")";

        return myStr;
    }

    /**
     *
     * @return roadName
     */
    public String getName(){

        return roadName;
    }

    /**
     *
     * @return destination
     */
    public Town getDestination(){

        return destination;
    }

    /**
     *
     * @return source
     */
    public Town getSource(){

        return source;
    }

    /**
     *
     * @return length, distance, weight
     */
    public int getWeight(){

        return weight;
    }

    /**
     * Check of two roads are equal or not.
     * The method is also considering the undirected road
     *
     * @param o
     *
     * @return true if they are equal; otherwise, false.
     */
    public boolean equals(Object o){

        if (o.getClass().equals(this.getClass())){


            return (((Road)o).destination.equals(this.destination)
                    && ((Road)o).source.equals(this.source) ||
                    (((Road)o).destination.equals(this.source)
                    && ((Road)o).source.equals(this.destination)));

        }

        return false;
    }

}
