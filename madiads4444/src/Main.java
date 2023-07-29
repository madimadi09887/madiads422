public class Main {
    public static void main(String[] args) {
        WeightedGraph<String> graph = new WeightedGraph<>();

        Vertex<String> v1 = new Vertex<>("A");
        Vertex<String> v2 = new Vertex<>("B");
        Vertex<String> v3 = new Vertex<>("C");
        Vertex<String> v4 = new Vertex<>("D");

        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);

        graph.addEdge(v1, v2, 1.0);
        graph.addEdge(v1, v3, 2.0);
        graph.addEdge(v2, v3, 3.0);
        graph.addEdge(v3, v4, 4.0);

        System.out.println("Breadth-First Search:");
        BreadthFirstSearch<String> bfs = new BreadthFirstSearch<>(graph);
        bfs.search(v1);
        System.out.println();

        System.out.println("Dijkstra's Shortest Path:");
        DijkstraSearch<String> dijkstra = new DijkstraSearch<>(graph);
        dijkstra.search(v1);
        System.out.println();
    }
}