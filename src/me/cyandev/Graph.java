package me.cyandev;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A simple directional graph data structure implementation.
 */
public class Graph<K, V> {

    private Map<K, V> mVertices;
    private Map<K, Set<Edge<K>>> mEdges;

    public Graph() {
        mVertices = new HashMap<>();
        mEdges = new HashMap<>();
    }

    public void addVertex(K key, V value) {
        mVertices.put(key, value);
    }

    public V removeVertex(K key) {
        V removed = mVertices.remove(key);

        if (removed != null) {
            // Remove all edges out from this vertex.
            mEdges.remove(key);
        }

        return removed;
    }

    public V getVertex(K key) {
        return mVertices.get(key);
    }

    public void addEdge(K from, K to) {
        addEdge(from, to, 0);
    }

    public void addEdge(K from, K to, int weight) {
        Edge<K> edge = new Edge<>();
        edge.mWeight = weight;
        edge.mFrom = from;
        edge.mTo = to;

        Set<Edge<K>> edges = mEdges.computeIfAbsent(from, k -> new HashSet<>());
        edges.add(edge);
    }

    public void removeEdges(K from) {
        removeEdge(from, null);
    }

    public void removeEdge(K from, K to) {
        if (!mEdges.containsKey(from)) {
            return;
        }

        if (to == null) {
            // Remove all edges out from specific vertex.
            mEdges.remove(from);
        } else {
            mEdges.get(from).removeIf(e -> e.mTo == to);
        }
    }

    public Set<Map.Entry<K, V>> getVertices() {
        return mVertices.entrySet();
    }

    public Set<Edge<K>> getAdjacent(K from) {
        return mEdges.get(from);
    }

    public static class Edge<K> {
        private int mWeight;
        private K mFrom;
        private K mTo;

        public int getWeight() {
            return mWeight;
        }

        public K getSource() {
            return mFrom;
        }

        public K getDestination() {
            return mTo;
        }
    }

}
