package me.cyandev;

import java.util.*;

/**
 * Encapsulates a set of algorithms to resolve path-finding problem.
 */
public final class Pathfinding {

    private static final int[][] DETECT_VECTORS = {
            {-1, -1}, {0, -1}, {1, -1},
            {-1, 0}, {0, 0}, {1, 0},
            {-1, 1}, {0, 1}, {1, 1}
    };

    public static int[][] aStar(Maze maze, int[] src, int[] dest, int openTyle) {
        int srcType = maze.get(src[0], src[1]);
        int destType = maze.get(dest[0], dest[1]);

        AStarState srcState = new AStarState(0, estimateDistance(src[0], src[1], dest[0], dest[1]), src[0], src[1]);

        Queue<AStarState> openQ = new PriorityQueue<>(Comparator.reverseOrder());
        List<AStarState> closeL = new LinkedList<>();

        openQ.offer(srcState);

        while (!openQ.isEmpty()) {
            AStarState state = openQ.poll();
            closeL.add(state);

            for (int[] vector : DETECT_VECTORS) {
                if (vector[0] == 0 && vector[1] == 0) {
                    continue;
                }
                if (attemptDirection(vector, maze, state, openQ, closeL, openTyle, destType, dest)) {
                    return null;
                }
            }
        }

        return null;
    }

    private static boolean attemptDirection(int[] vectors, Maze maze, AStarState ori, Queue<AStarState> openQueue,
                                         List<AStarState> closeList, int openType, int destType, int[] dest) {
        int newX = ori.x + vectors[0];
        int newY = ori.y + vectors[1];

        if (newX < 0 || newX >= maze.getWidth() || newY < 0 || newY >= maze.getHeight()) {
            return false;
        }

        AStarState newState = new AStarState(ori.g + 1, estimateDistance(newX, newY, dest[0], dest[1]), newX, newY);
        newState.ancestor = ori;

        if (maze.get(newX, newY) == destType) {
            closeList.add(newState);

            // We found the destination, oh yeah!
            return true;
        }

        if (maze.get(newX, newY) != openType) {
            // We can't go here.
            return false;
        }

        for (AStarState state : closeList) {
            if (state.x == newX && state.y == newY) {
                // This node is already in close list, ignore it.
                return false;
            }
        }

        // Look for existing state with the same position.
        for (AStarState state : openQueue) {
            if (state.x == newX && state.y == newY) {
                int newF = newState.g + newState.h;
                if (newF < state.g + state.h) {
                    state.g = newState.g;
                    state.h = newState.h;

                    // No more state will have the same position, finish attempting.
                    return false;
                }
            }
        }

        openQueue.add(newState);

        return false;
    }

    private static int estimateDistance(int beginX, int beginY, int endX, int endY) {
        return Math.abs(beginX - endX) + Math.abs(beginY - endY);
    }

    private static class AStarState implements Comparable<AStarState> {
        int g;
        int h;
        int x;
        int y;
        AStarState ancestor;

        AStarState(int g, int h, int x, int y) {
            this.g = g;
            this.h = h;
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(AStarState o) {
            int f = g + h;
            int otherF = o.g + o.h;
            if (f == otherF) {
                return 0;
            } else if (f < otherF) {
                return -1;
            } else {
                return 1;
            }
        }

    }

}
