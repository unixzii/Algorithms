package me.cyandev.test;

import me.cyandev.NQueens;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

/**
 * TDD for {@link NQueens}.
 */
@RunWith(JUnit4.class)
public class NQueensTest {

    /** Expecting results of input between 4 and 12. */
    private static final int[] EXPECTS = {2, 10, 4, 40, 92, 352, 724, 2680, 14200};

    @Test
    public void testClassic() {
        test(NQueens::classic);
    }

    @Test
    public void testBitwise() {
        test(NQueens::bitwise);
    }

    private void test(Solver solver) {
        final int begin = 4;
        for (int i = begin; i <= 12; i++) {
            assertEquals(EXPECTS[i - begin], solver.solve(i));
        }
    }

    private interface Solver {
        int solve(int n);
    }

}
