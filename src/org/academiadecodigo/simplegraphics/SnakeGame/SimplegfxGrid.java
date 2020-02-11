package org.academiadecodigo.simplegraphics.SnakeGame;


import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class SimplegfxGrid implements Grid {


    private int cols;
    private int rows;
    public static final int PADDING = 10;
    public static final int cellsize = 15;
    private Rectangle field;


    public SimplegfxGrid(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
    }


    public void init() {
        field = new Rectangle(PADDING, PADDING, cols * cellsize, rows * cellsize);
        field.draw();
    }

    public int getPadding() {
        return PADDING;
    }

    public int getWidth(){
        return cols * cellsize;
    }


    public int getHeigth(){
        return rows * cellsize;
    }

    public int getX(){
        return field.getX();
    }

    public int getY() {
        return field.getY();
    }

    public int rowToY(int row) {
        return (row * cellsize) + PADDING;
    }

    public int columnToX(int column) {
        return (column * cellsize) + PADDING;

    }

        // creates a random SimplegfxGridPosition
    public GridPosition makeGridPosition() {
        return new SimplegfxGridPosition(this);
    }

    public GridPosition makeGridPosition(int col, int row) {
        return new SimplegfxGridPosition(col, row, this);
    }

    public int getCellsize() {
        return cellsize;
    }

    public int getCols() {
        return cols;
    }


    public int getRows() {
        return rows;
    }



















}
