import java.util.*;

/**
 * This is a data structure class. All the methods in this class are supports structuring the elements.
 *
 * @author Asem Shaath
 */
public class Graph implements GraphInterface<Town, Road>{



    private Set<Town> towns = new HashSet<>();
    private Set<Road> roads = new HashSet<>();

    private int finishTown;
    private Town destination;
    private ArrayList<String> shortestPath = new ArrayList<>();

    /**
     * Returns an edge connecting source vertex to target vertex if such
     * vertices and such edge exist in this graph. Otherwise returns
     * null. If any of the specified vertices is null
     * returns null
     * <p>
     * In undirected graphs, the returned edge may have its source and target
     * vertices in the opposite order.
     *
     * @param sourceVertex      source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @return an edge connecting source vertex to target vertex.
     */
    @Override
    public Road getEdge(Town sourceVertex, Town destinationVertex) {

        if (sourceVertex == null || destinationVertex == null)
            {
                return null;
            }

            Iterator<Road> it = roads.iterator();

            while (it.hasNext())
            {
                Road someRoad = it.next();

            if (someRoad.contains(sourceVertex) && someRoad.contains(destinationVertex))
            {
                return someRoad;
            }
        }

        return null;
    }

    /**
     * Creates a new edge in this graph, going from the source vertex to the
     * target vertex, and returns the created edge.
     * <p>
     * The source and target vertices must already be contained in this
     * graph. If they are not found in graph IllegalArgumentException is
     * thrown.
     *
     * @param sourceVertex      source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight            weight of the edge
     * @param description       description for edge
     * @return The newly created edge if added to the graph, otherwise null.
     * @throws IllegalArgumentException if source or target vertices are not
     *                                  found in the graph.
     * @throws NullPointerException     if any of the specified vertices is null.
     */
    @Override
    public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {

        Road road = new Road(sourceVertex,destinationVertex, weight, description);


        if (sourceVertex == null || destinationVertex == null)
        {
            throw new NullPointerException();
        }
        else if(!towns.contains(sourceVertex) || !towns.contains(destinationVertex)){

            throw new IllegalArgumentException();
        }

        roads.add(road);
        sourceVertex.connectRoad(road);
        sourceVertex.connectTown(destinationVertex);
        destinationVertex.connectTown(sourceVertex);
        destinationVertex.connectRoad(road);

        return road;


    }

    /**
     * Adds the specified vertex to this graph if not already present. More
     * formally, adds the specified vertex, v, to this graph if
     * this graph contains no vertex u such that
     * u.equals(v). If this graph already contains such vertex, the call
     * leaves this graph unchanged and returns false. In combination
     * with the restriction on constructors, this ensures that graphs never
     * contain duplicate vertices.
     *
     * @param town vertex to be added to this graph.
     * @return true if this graph did not already contain the specified
     * vertex.
     * @throws NullPointerException if the specified vertex is null.
     */
    @Override
    public boolean addVertex(Town town) {

        if (town == null)
            throw new NullPointerException();

        if (towns.contains(town)){


            return false;
        }

        towns.add(town);

        return true;

    }

    /**
     * Returns true if and only if this graph contains an edge going
     * from the source vertex to the target vertex. In undirected graphs the
     * same result is obtained when source and target are inverted. If any of
     * the specified vertices does not exist in the graph, or if is
     * null, returns false.
     *
     * @param sourceVertex      source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @return true if this graph contains the specified edge.
     */
    @Override
    public boolean containsEdge(Town sourceVertex, Town destinationVertex) {

        Iterator<Road> it = roads.iterator();

        while (it.hasNext())
        {
            Road someRoad = it.next();

            if ((someRoad.getDestination().equals(destinationVertex) && someRoad.getSource().equals(sourceVertex)) ||
                    (someRoad.getDestination().equals(sourceVertex) && someRoad.getSource().equals(destinationVertex)))
            {
                return true;
            }
        }

        return false;

    }

    /**
     * Returns true if this graph contains the specified vertex. More
     * formally, returns true if and only if this graph contains a
     * vertex u such that u.equals(v). If the
     * specified vertex is null returns false.
     *
     * @param town vertex whose presence in this graph is to be tested.
     * @return true if this graph contains the specified vertex.
     */
    @Override
    public boolean containsVertex(Town town)
    {

        if (town == null)
            return false;

        if (towns.contains(town))
            return true;

        return false;

    }

    /**
     * Returns a set of the edges contained in this graph. The set is backed by
     * the graph, so changes to the graph are reflected in the set. If the graph
     * is modified while an iteration over the set is in progress, the results
     * of the iteration are undefined.
     *
     * @return a set of the edges contained in this graph.
     */
    @Override
    public Set<Road> edgeSet() {

        return roads;
    }

    /**
     * Returns a set of all edges touching the specified vertex (also
     * referred to as adjacent vertices). If no edges are
     * touching the specified vertex returns an empty set.
     *
     * @param vertex the vertex for which a set of touching edges is to be
     *               returned.
     * @return a set of all edges touching the specified vertex.
     * @throws IllegalArgumentException if vertex is not found in the graph.
     * @throws NullPointerException     if vertex is null.
     */
    @Override
    public Set<Road> edgesOf(Town vertex) {


        return vertex.getConnectedRoads();
    }

    /**
     * Removes an edge going from source vertex to target vertex, if such
     * vertices and such edge exist in this graph.
     * <p>
     * If weight >- 1 it must be checked
     * If description != null, it must be checked
     * <p>
     * Returns the edge if removed
     * or null otherwise.
     *
     * @param sourceVertex      source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight            weight of the edge
     * @param description       description of the edge
     * @return The removed edge, or null if no edge removed.
     */
    @Override
    public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {

        if (sourceVertex == null || destinationVertex == null )
        {
            throw new NullPointerException();
        }

        if (!towns.contains(sourceVertex) || !towns.contains(destinationVertex))
        {
            throw new IllegalArgumentException();
        }

        Iterator<Road> it = roads.iterator();

        while (it.hasNext())
        {
            Road someRoad = it.next();

            if (someRoad.equals(new Road(sourceVertex, destinationVertex, weight,description)))
            {
                roads.remove(someRoad);
                return someRoad;
            }
        }

        return null;

    }

    /**
     * Removes the specified vertex from this graph including all its touching
     * edges if present. More formally, if the graph contains a vertex
     * u such that u.equals(v), the call removes all edges
     * that touch u and then removes u itself. If no
     * such u is found, the call leaves the graph unchanged.
     * Returns true if the graph contained the specified vertex. (The
     * graph will not contain the specified vertex once the call returns).
     * <p>
     * If the specified vertex is null returns false.
     *
     * @param town vertex to be removed from this graph, if present.
     * @return true if the graph contained the specified vertex;
     * false otherwise.
     */
    @Override
    public boolean removeVertex(Town town) {

        return towns.remove(town);
    }

    /**
     * Returns a set of the vertices contained in this graph. The set is backed
     * by the graph, so changes to the graph are reflected in the set. If the
     * graph is modified while an iteration over the set is in progress, the
     * results of the iteration are undefined.
     *
     * @return a set view of the vertices contained in this graph.
     */
    @Override
    public Set<Town> vertexSet() {

        return towns;
    }

    /**
     * Find the shortest path from the sourceVertex to the destinationVertex
     * call the dijkstraShortestPath with the sourceVertex
     *
     * @param sourceVertex      starting vertex
     * @param destinationVertex ending vertex
     * @return An arraylist of Strings that describe the path from sourceVertex
     * to destinationVertex
     * They will be in the format: startVertex "via" Edge "to" endVertex weight
     * As an example: if finding path from Vertex_1 to Vertex_10, the ArrayList<String>
     * would be in the following format(this is a hypothetical solution):
     * Vertex_1 via Edge_2 to Vertex_3 4 (first string in ArrayList)
     * Vertex_3 via Edge_5 to Vertex_8 2 (second string in ArrayList)
     * Vertex_8 via Edge_9 to Vertex_10 2 (third string in ArrayList)
     */
    @Override
    public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {

        destination = destinationVertex;

        dijkstraShortestPath(sourceVertex);

        String ourPath = "";

        int totalDistance = 0;

        for (int index = 0; index < shortestPath.size() - 1; index++) {
            Town town = new Town(shortestPath.get(index));
            Town destination = new Town(shortestPath.get(index + 1));
            Road road = getEdge(town, destination);

            if(road==null)
            {
                totalDistance = 0;
                ourPath = "no path";
            }
            else {
                totalDistance += road.getWeight();
                ourPath += town + " via " + road.getName() + " to " + destination
                        + " " + road.getWeight() + " mi;";
            }
        }
        shortestPath.clear();

        for (String step : ourPath.split(";")) {
            shortestPath.add(step);
        }

        shortestPath.add("Total miles: " + totalDistance + " miles");

        return shortestPath;

    }

    /**
     * Dijkstra's Shortest Path Method.  Internal structures are built which
     * hold the ability to retrieve the path, shortest distance from the
     * sourceVertex to all the other vertices in the graph, etc.
     *
     * @param sourceVertex the vertex to find shortest path from
     */
    @Override
    public void dijkstraShortestPath(Town sourceVertex) {

        shortestPath.clear();

        Town[] rowsAndCols = new Town[towns.size()];
        int s = 0;

        for (Town t : towns) {
            rowsAndCols[s] = new Town(t);
            s++;
        }

        int[][] matrix = new int[towns.size()][towns.size()];

        for (int i = 0; i < matrix.length; i++) {

            for (int j = 0; j < matrix[i].length; j++) {

                if (i == j || !containsEdge(rowsAndCols[i], rowsAndCols[j])) {
                    matrix[i][j] = 0;
                } else {
                    int weight = getEdge(rowsAndCols[i], rowsAndCols[j]).getWeight();
                    matrix[i][j] = matrix[j][i] = weight;
                }
            }
        }

        int startTown = 0;

        for (Town t : rowsAndCols) {

            if (!t.equals(sourceVertex)) {
                startTown++;
            } else {
                break;
            }
        }

        finishTown = 0;

        for (Town t : rowsAndCols) {
            if (!t.equals(destination)) {
                finishTown++;
            } else {
                break;
            }
        }

        int numTowns = matrix[0].length;

        int[] smallestWeights = new int[numTowns];

        boolean[] added = new boolean[numTowns];

        for (int townIdx = 0; townIdx < numTowns; townIdx++) {

            smallestWeights[townIdx] = Integer.MAX_VALUE;
            added[townIdx] = false;
        }

        smallestWeights[startTown] = 0;

        int[] parents = new int[numTowns];

        parents[startTown] = -1;

        for (int i = 1; i < numTowns; i++) {

            int nearestTown = -1;
            int smallestWeight = Integer.MAX_VALUE;

            for (int townIdx = 0; townIdx < numTowns; townIdx++) {

                if (!added[townIdx] &&
                        smallestWeights[townIdx] < smallestWeight) {
                    nearestTown = townIdx;
                    smallestWeight = smallestWeights[townIdx];
                }
            }

            added[nearestTown] = true;

            for (int townIdx = 0; townIdx < numTowns; townIdx++) {

                int roadDist = matrix[nearestTown][townIdx];

                if (roadDist > 0 &&
                        ((smallestWeight + roadDist) < smallestWeights[townIdx])) {
                    parents[townIdx] = nearestTown;
                    smallestWeights[townIdx] = smallestWeight + roadDist;
                }
            }
        }

        fillArrayList(finishTown, parents);

    }

    /**
     * This is a method that fills the arraylist that contains the shortest path
     *
     * @param currentVertex
     * @param parents
     */
    private void fillArrayList(int currentVertex, int[] parents) {

        if (currentVertex == -1) {
            return;
        }
        fillArrayList(parents[currentVertex], parents);
        int townIdx = 0;
        for (Town t : towns) {
            if (townIdx == currentVertex) {
                shortestPath.add(t.getName());
            }
            townIdx++;
        }
    }

}


