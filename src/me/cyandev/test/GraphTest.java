package me.cyandev.test;

import me.cyandev.Graph;
import me.cyandev.GraphSearching;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * TDD for {@link Graph}.
 */
@SuppressWarnings("unchecked")
@RunWith(JUnit4.class)
public class GraphTest {

    private Graph<String, String>[] mSampleGraphs;

    @Before
    public void initialize() {
        mSampleGraphs = new Graph[2];

        mSampleGraphs[0] = new Graph<>();
        mSampleGraphs[0].addVertex("A", "A");
        mSampleGraphs[0].addVertex("B", "B");
        mSampleGraphs[0].addVertex("C", "C");
        mSampleGraphs[0].addVertex("D", "D");
        mSampleGraphs[0].addVertex("E", "E");
        mSampleGraphs[0].addVertex("F", "F");
        mSampleGraphs[0].addVertex("G", "G");
        mSampleGraphs[0].addEdge("A", "F");
        mSampleGraphs[0].addEdge("F", "C");
        mSampleGraphs[0].addEdge("F", "D");
        mSampleGraphs[0].addEdge("C", "G");
        mSampleGraphs[0].addEdge("D", "B");
        mSampleGraphs[0].addEdge("D", "E");
        mSampleGraphs[0].addEdge("E", "B");

        mSampleGraphs[1] = new Graph<>();
        mSampleGraphs[1].addVertex("A", "A");
        mSampleGraphs[1].addVertex("B", "B");
        mSampleGraphs[1].addVertex("C", "C");
        mSampleGraphs[1].addVertex("D", "D");
        mSampleGraphs[1].addVertex("E", "E");
        mSampleGraphs[1].addEdge("D", "B", 1);
        mSampleGraphs[1].addEdge("D", "C", 2);
        mSampleGraphs[1].addEdge("D", "E", 4);
        mSampleGraphs[1].addEdge("B", "E", 2);
        mSampleGraphs[1].addEdge("E", "A", 1);
        mSampleGraphs[1].addEdge("C", "A", 5);
    }

    @Test
    public void testFundamentalOperations() {
        Graph<String, String> graph = mSampleGraphs[0];
        assertEquals("B", graph.getVertex("B"));
        assertEquals(2, graph.getAdjacent("D").size());
        assertEquals(7, graph.getVertices().size());

        graph.removeEdge("F", "D");
        assertEquals(1, graph.getAdjacent("F").size());

        graph.removeEdges("D");
        assertNull(graph.getAdjacent("D"));

        // Removing edges out from a vertex that is not exists won't lead to error.
        graph.removeEdges("X");

        graph.removeVertex("A");
        assertNull(graph.getAdjacent("A"));

        // Removing a vertex that is not exists won't lead to error.
        graph.removeVertex("Z");

        graph = mSampleGraphs[1];
        Iterator<Graph.Edge<String>> iterator = graph.getAdjacent("C").iterator();
        assertNotNull(iterator);
        Graph.Edge<String> edgeCA = iterator.next();
        assertEquals("C", edgeCA.getSource());
        assertEquals("A", edgeCA.getDestination());
        assertEquals(5, edgeCA.getWeight());
    }

    @Test
    public void testDFS() {
        testSearching(GraphSearching::dfs);
    }

    @Test
    public void testBFS() {
        testSearching(GraphSearching::bfs);
    }

    private void testSearching(Searcher<String, String> searcher) {
        Graph<String, String> graph = mSampleGraphs[0];
        assertTrue(searcher.search(graph, "A", "B"));
        assertTrue(searcher.search(graph, "A", "G"));
        assertFalse(searcher.search(graph, "G", "D"));
        assertFalse(searcher.search(graph, "D", "G"));

        graph = mSampleGraphs[1];
        assertTrue(searcher.search(graph, "D", "A"));
        assertFalse(searcher.search(graph, "B", "D"));
        assertFalse(searcher.search(graph, "E", "C"));
    }

    private interface Searcher<K, V> {
        boolean search(Graph<K, V> graph, K from, K to);
    }

}
