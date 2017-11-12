package me.cyandev.test;

import me.cyandev.Maze;
import me.cyandev.Pathfinding;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

/**
 * TDD for
 */
@RunWith(JUnit4.class)
public class PathfindingTest {

    private static final char[][] MAP = {
            {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
            {'x', 'x', 'x', 'x', ' ', ' ', ' ', 'x'},
            {'x', 'x', 'x', 'x', ' ', 'x', '0', 'x'},
            {'x', ' ', ' ', 'x', ' ', 'x', 'x', ' '},
            {'x', ' ', ' ', ' ', ' ', ' ', 'x', ' '},
            {'x', ' ', 'x', 'x', 'x', ' ', ' ', ' '},
            {'+', ' ', 'x', 'x', 'x', 'x', 'x', 'x'},
            {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'}
    };

    private static final int TYPE_BLOCK = 0;
    private static final int TYPE_SRC = 1;
    private static final int TYPE_DEST = 2;
    private static final int TYPE_OPEN = 3;

    private static Maze sMaze;

    static {
        sMaze = new Maze(8, 8);

        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                switch (MAP[y][x]) {
                    case '+':
                        sMaze.set(x, y, TYPE_SRC);
                        break;
                    case '0':
                        sMaze.set(x, y, TYPE_DEST);
                        break;
                    case ' ':
                        sMaze.set(x, y, TYPE_OPEN);
                        break;
                }
            }
        }
    }

    @Test
    public void testMaze() {
        assertEquals(TYPE_BLOCK, sMaze.get(2, 2));
        assertEquals(TYPE_OPEN, sMaze.get(4, 1));
        assertEquals(TYPE_SRC, sMaze.get(0, 6));
        assertEquals(TYPE_DEST, sMaze.get(6, 2));
    }

    @Test
    public void testAStar() {
        Pathfinding.aStar(sMaze, new int[]{0, 6}, new int[]{6, 2}, TYPE_OPEN);
    }

}
