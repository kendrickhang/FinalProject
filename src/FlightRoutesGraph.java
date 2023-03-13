import edu.greenriver.sdev333.BSTSet;
import edu.greenriver.sdev333.MathSet;
import edu.greenriver.sdev333.SeparateChainingHashSet;

public class FlightRoutesGraph {
    // two sets needed to model a graph (network)
    // 1.  a set of vertices (points, nodes) - airports
    // 2.  a set of edges (connections, lines , relationships) - routes between airports

    private class Edge{
        private String node1;
        private String node2;
        private Edge(String from, String to){
            node1 = from;
            node2 = to;
        }
    }

    private MathSet<String> nodes;
    private MathSet<Edge> edges;

    public FlightRoutesGraph(){
        nodes = new BSTSet<>();   //  BST ok here because strings are comparable
        edges = new SeparateChainingHashSet<>();     // must use HashSet here because edges are not comparable

    }

    public void addNode(String city){
        nodes.add(city);
    }

    public void addEdge(String city1, String city2){
        Edge connection = new Edge(city1, city2);
        edges.add(connection);
    }

    public MathSet<String> getNeighbors(String city){
        MathSet<String> neighbors = new BSTSet<>();
        //loop through the edges and check
        //if the city is either in node1 or node2
        for(Edge e : edges.keys()){
            if(e.node1.equals(city)){
                neighbors.add(e.node2);
            }else if(e.node2.equals(city)){
                neighbors.add(e.node1);
            }
        }
        return neighbors;
    }


    public static void main(String[] args){
        FlightRoutesGraph graph = new FlightRoutesGraph();

        //add all the cities first (nodes)
        graph.addNode("JFK");
        graph.addNode("ORD");
        graph.addNode("ATL");
        graph.addNode("MCO");
        graph.addNode("DEN");
        graph.addNode("LAS");
        graph.addNode("PHX");

        //add connections between nodes
        graph.addEdge("JFK", "MCO");
        graph.addEdge("ATL", "MCO");
        graph.addEdge("DEN", "ORD");
        graph.addEdge("ORD", "ATL");
        graph.addEdge("ORD", "JFK");
        graph.addEdge("LAS", "DEN");
        graph.addEdge("PHX", "DEN");
        graph.addEdge("JFK", "ATL");

        MathSet<String> directFromJFK = graph.getNeighbors("JFK");
        MathSet<String> directFromATL = graph.getNeighbors("ATL");

        for(String n : directFromJFK.keys()){
            System.out.println(n);
        }

    }
}
