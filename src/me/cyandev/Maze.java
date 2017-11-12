package me.cyandev;

import java.util.Arrays;

/**
 * A simple data structure that describes a maze.
 */
public class Maze {

    private int[][] mMaze;

    public Maze(int w, int h) {
        mMaze = new int[w][h];
    }

    public void set(int x, int y, int type) {
        mMaze[x][y] = type;
    }

    public void set(int[][] pos, int type) {
        for (int[] _pos : pos) {
            set(_pos[0], _pos[1], type);
        }
    }

    public int get(int x, int y) {
        return mMaze[x][y];
    }

    public int getWidth() {
        if (mMaze.length == 0) {
            return 0;
        }

        return mMaze[0].length;
    }

    public int getHeight() {
        return mMaze.length;
    }

}
