import java.util.*;

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
        graph.addEdge(v4, v1, 5.0); // Adding a directed edge from v4 to v1

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

class Vertex<V extends Comparable<V>> {
    private V data;
    private Map<Vertex<V>, Double> adjacentVertices;

    public Vertex(V data) {
        this.data = data;
        this.adjacentVertices = new HashMap<>();
    }

    public V getData() {
        return data;
    }

    public void addAdjacentVertex(Vertex<V> destination, double weight) {
        adjacentVertices.put(destination, weight);
    }

    public Map<Vertex<V>, Double> getAdjacentVertices() {
        return adjacentVertices;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}

class WeightedGraph<V extends Comparable<V>> {
    private Map<Vertex<V>, List<Edge<V>>> map = new HashMap<>();

    public void addVertex(Vertex<V> vertex) {
        map.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(Vertex<V> source, Vertex<V> destination, double weight) {
        map.get(source).add(new Edge<>(source, destination, weight));
    }

    public List<Edge<V>> getEdges(Vertex<V> vertex) {
        return map.get(vertex);
    }
}

class Edge<V extends Comparable<V>> {
    private Vertex<V> source;
    private Vertex<V> dest;
    private Double weight;

    public Edge(Vertex<V> source, Vertex<V> dest, Double weight) {
        this.source = source;
        this.dest = dest;
        this.weight = weight;
    }

    public Vertex<V> getSource() {
        return source;
    }

    public Vertex<V> getDestination() {
        return dest;
    }

    public Double getWeight() {
        return weight;
    }
}

class BreadthFirstSearch<V extends Comparable<V>> {
    private WeightedGraph<V> graph;
    private Set<Vertex<V>> visited;

    public BreadthFirstSearch(WeightedGraph<V> graph) {
        this.graph = graph;
        visited = new HashSet<>();
    }

    public void search(Vertex<V> startVertex) {
        Queue<Vertex<V>> queue = new LinkedList<>();
        queue.add(startVertex);
        visited.add(startVertex);

        while (!queue.isEmpty()) {
            Vertex<V> vertex = queue.poll();
            System.out.print(vertex + " ");

            for (Edge<V> edge : graph.getEdges(vertex)) {
                Vertex<V> neighbor = edge.getDestination();
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }
    }
}

class DijkstraSearch<V extends Comparable<V>> {
    private WeightedGraph<V> graph;
    private Map<Vertex<V>, Double> distanceMap;
    private Map<Vertex<V>, Vertex<V>> previousMap;

    public DijkstraSearch(WeightedGraph<V> graph) {
        this.graph = graph;
    }

    public void search(Vertex<V> startVertex) {
        distanceMap = new HashMap<>();
        previousMap = new HashMap<>();

        PriorityQueue<Vertex<V>> priorityQueue = new PriorityQueue<>(Comparator.comparingDouble(distanceMap::get));
        for (Vertex<V> vertex : graph.getEdges(startVertex).get(0).getSource().getAdjacentVertices().keySet()) {
            double weight = graph.getEdges(startVertex).get(0).getSource().getAdjacentVertices().get(vertex);
            distanceMap.put(vertex, weight);
            previousMap.put(vertex, startVertex);
            priorityQueue.add(vertex);
        }

        Set<Vertex<V>> visited = new HashSet<>();
        visited.add(startVertex);
        distanceMap.put(startVertex, 0.0);

        while (!priorityQueue.isEmpty()) {
            Vertex<V> currentVertex = priorityQueue.poll();

            for (Edge<V> edge : graph.getEdges(currentVertex)) {
                Vertex<V> neighbor = edge.getDestination();

                if (!visited.contains(neighbor)) {
                    double newDistance = distanceMap.get(currentVertex) + edge.getWeight();

                    if (newDistance < distanceMap.getOrDefault(neighbor, Double.MAX_VALUE)) {
                        distanceMap.put(neighbor, newDistance);
                        previousMap.put(neighbor, currentVertex);
                    }
                    priorityQueue.add(neighbor);
                }
            }
            visited.add(currentVertex);
        }

        printShortestPaths(startVertex);
    }

    private void printShortestPaths(Vertex<V> startVertex) {
        for (Vertex<V> vertex : graph.getEdges(startVertex).get(0).getSource
