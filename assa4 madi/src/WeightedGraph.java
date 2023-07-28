import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeightedGraph<V> {
    private Map<Vertex<V>, List<Edge<V>>> map;

    public WeightedGraph() {
        map = new HashMap<>();
    }

    public void addVertex(Vertex<V> vertex) {
        map.put(vertex, new ArrayList<>());
    }

    public void addEdge(Vertex<V> source, Vertex<V> destination, double weight) {
        Edge<V> edge = new Edge<>(source, destination, weight);
        map.get(source).add(edge);
    }

    public List<Edge<V>> getEdges(Vertex<V> vertex) {
        return map.get(vertex);
    }
}
