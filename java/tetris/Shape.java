package tetris;

import java.util.Random;

public class Shape {

    public enum Tetrominoe {
        NoShape, ZShape, SShape, LineShape,
        TShape, SquareShape, LShape, JShape
    }

    private Tetrominoe piece;
    private int[][] coords;

    public Shape() {
        coords = new int[4][2];
        setShape(Tetrominoe.NoShape);
    }

    public void setShape(Tetrominoe shape) {

        int[][][] coordsTable = new int[][][]{
                {{0, 0}, {0, 0}, {0, 0}, {0, 0}},
                {{0, -1}, {0, 0}, {-1, 0}, {-1, 1}},
                {{0, -1}, {0, 0}, {1, 0}, {1, 1}},
                {{0, -1}, {0, 0}, {0, 1}, {0, 2}},
                {{-1, 0}, {0, 0}, {1, 0}, {0, 1}},
                {{0, 0}, {1, 0}, {0, 1}, {1, 1}},
                {{-1, -1}, {0, -1}, {0, 0}, {0, 1}},
                {{1, -1}, {0, -1}, {0, 0}, {0, 1}}
        };

        for (int i = 0; i <4; i++) { System.arraycopy(coordsTable[shape.ordinal()], 0, coords, 0, 4); }

        piece = shape;
    }

    private void setX(int index, int x) { coords[index][0] = x; }

    private void setY(int index, int y) { coords[index][1] = y; }

    public int x(int index) { return coords[index][0]; }

    public int y(int index) { return coords[index][1]; }

    public Tetrominoe getShape() { return piece; }

    public void setRandomShape() {

        var r = new Random();
        int x = Math.abs(r.nextInt() % 7 + 1);

        Tetrominoe[] values = Tetrominoe.values();
        setShape(values[x]);

    }

    public int minX() {

        int min = coords[0][0];
        for (int i = 0; i < 4; i++) min = Math.min(min, coords[i][1]);

        return min;

    }

    public int minY() {

        int min = coords[0][1];
        for (int i = 0; i < 4; i++) min = Math.min(min, coords[i][1]);

        return min;

    }

    public Shape rLeft() {

        if (piece == Tetrominoe.SquareShape) return this;

        var result = new Shape();
        result.piece = piece;

        for (int i = 0; i < 4; i++) {
            result.setX(i, y(i));
            result.setY(i, -x(i));
        }

        return result;

    }

    public Shape rRight() {

        if (piece == Tetrominoe.SquareShape) return this;

        var result = new Shape();
        result.piece = piece;

        for (int i = 0; i < 4; i++) {
            result.setX(i, -y(i));
            result.setY(i, x(i));
        }

        return result;

    }

}
