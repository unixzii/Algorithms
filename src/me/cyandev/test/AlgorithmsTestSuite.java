package me.cyandev.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        SortingTest.class,
        GraphTest.class,
        NQueensTest.class,
        PathfindingTest.class,
        LCSTest.class
})
public class AlgorithmsTestSuite {
}
