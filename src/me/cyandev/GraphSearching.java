package me.cyandev;

import java.util.*;

/**
 * Searching algorithms for graph data structure.
 */
public final class GraphSearching {

    public static <K, V> boolean dfs(Graph<K, V> graph, K from, K to) {
        Set<K> visited = new HashSet<>();
        return hasPathDfs(graph, from, to, visited);
    }

    private static <K, V> boolean hasPathDfs(Graph<K, V> graph, K from, K to, Set<K> visited) {
        if (Objects.equals(from, to)) {
            return true;
        }

        if (visited.contains(from)) {
            return false;
        }

        visited.add(from);

        Set<Graph.Edge<K>> edges = graph.getAdjacent(from);

        if (edges == null) {
            return false;
        }

        for (Graph.Edge<K> edge : edges) {
            if (hasPathDfs(graph, edge.getDestination(), to, visited)) {
                return true;
            }
        }

        return false;
    }

    public static <K, V> boolean bfs(Graph<K, V> graph, K from, K to) {
        Set<K> visited = new HashSet<>();
        Queue<K> q = new LinkedList<>();

        q.add(from);

        while (!q.isEmpty()) {
            K next = q.poll();
            if (Objects.equals(next, to)) {
                return true;
            }

            if (visited.contains(next)) {
                continue;
            }

            visited.add(next);

            Set<Graph.Edge<K>> edges = graph.getAdjacent(next);
            if (edges != null) {
                for (Graph.Edge<K> edge : edges) {
                    q.offer(edge.getDestination());
                }
            }
        }

        return false;
    }

}
