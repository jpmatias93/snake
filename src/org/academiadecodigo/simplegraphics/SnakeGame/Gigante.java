package org.academiadecodigo.simplegraphics.SnakeGame;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Gigante {

    private GridPosition pos;
    private SimplegfxGrid grid;
    private Picture picture;
    private GridDirection currentDirection;
    private int directionChangeLevel = 8;



    public Gigante(GridPosition pos, SimplegfxGrid grid, String string) {
        this.grid = grid;

        int randomX = grid.getPadding() + grid.getCellsize() * (int) (Math.random() * grid.getCols());
        int randomY = grid.getPadding() + grid.getCellsize() * (int) (Math.random() * grid.getRows());
        setPicture(string);
        this.pos = pos;

        //this.currentDirection = GridDirection.values()[(int) (Math.random() * GridDirection.values().length)];
        this.currentDirection = GridDirection.LEFT;
    }

    public void setPicture(String string) {
        picture = new Picture(grid.columnToX(15), grid.rowToY(15), string);
    }



    public void draw(){
        picture.draw();
    }

    public void delete(){
        picture.delete();
    }

    public int getX() {
        return picture.getX();
    }

    public int getY() {
        return picture.getY();
    }


    public boolean isHittingWall() {
        switch (currentDirection) {
            case LEFT:
                if (picture.getX() == grid.getPadding()) {
                    System.out.println(picture.getX());
                    //picture.translate(grid.getCellsize(), 0);
                    return true;

                }
            case RIGHT:
                if (picture.getX() + grid.getCellsize() == grid.getPadding() + grid.getWidth()) {
                    System.out.println(picture.getX());
                    //picture.translate(-grid.getCellsize(), 0);
                    return true;
                }
            case UP:
                if (picture.getY() == grid.getPadding()) {
                    System.out.println(picture.getY());
                    //picture.translate(0, grid.getCellsize());
                    return true;
                }
            case DOWN:
                if (picture.getY() + grid.getCellsize() == grid.getPadding() + grid.getHeigth()) {
                    System.out.println(picture.getY());
                    //picture.translate(0, -grid.getCellsize());
                    return true;
                }
        }

        return false;

    }



    public GridDirection chooseDirection() {

        GridDirection newDirection = currentDirection;

        if (Math.random() > ((double) directionChangeLevel / 10)) {
            newDirection = GridDirection.values()[(int) (Math.random() * GridDirection.values().length)];

            if (newDirection.isOpposite(currentDirection)) {
                return chooseDirection();
            }

        }


        return newDirection;
    }

    public void move() {
        accelerate(chooseDirection());
    }


    public GridPosition getPos() {
        return pos;
    }

    public void accelerate(GridDirection direction) {

        GridDirection newDirection = direction;

        if (isHittingWall()) {
            newDirection = direction.oppositeDirection();
        }

        this.currentDirection = newDirection;

        getPos().moveDirection(newDirection, picture);

    }


}
