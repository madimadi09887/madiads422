import java.util.List;

public interface Search<V> {
    List<Vertex<V>> searchPath(Vertex<V> startVertex, Vertex<V> targetVertex);
}
