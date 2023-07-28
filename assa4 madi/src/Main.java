public class Main {
    public static void main(String[] args) {
        // Example usage of the weighted graph and search algorithms.

        // Creating vertices
        Vertex<String> v1 = new Vertex<>("A");
        Vertex<String> v2 = new Vertex<>("B");
        Vertex<String> v3 = new Vertex<>("C");

        // Creating the graph
        WeightedGraph<String> graph = new WeightedGraph<>();
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);

        // Adding edges with weights
        graph.addEdge(v1, v2, 5.0);
        graph.addEdge(v2, v3, 3.0);
        graph.addEdge(v1, v3, 10.0);

        // Performing searches
        Search<String> bfsSearch = new BreadthFirstSearch<>();
        List<Vertex<String>> bfsPath = bfsSearch.searchPath(v1, v3);

        Search<String> dijkstraSearch = new DijkstraSearch<>();
        List<Vertex<String>> dijkstraPath = dijkstraSearch.searchPath(v1, v3);

        // Print paths
        System.out.println("BFS Path from A to C: " + bfsPath);
        System.out.println("Dijkstra Path from A to C: " + dijkstraPath);
    }
}
