package org.academiadecodigo.simplegraphics.SnakeGame;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Doraemon implements KeyboardHandler {


    private GridPosition pos;
    private SimplegfxGrid grid;
    private boolean dead = false;
    private Keyboard keyboard;
    private Rectangle doraemon;

    protected GridDirection currentDirection;


    public Doraemon(GridPosition pos, SimplegfxGrid grid) {
        this.grid = grid;
        this.doraemon = new Rectangle(100,100,grid.getCellsize(),grid.getCellsize());
        //this.doraemon = new Picture(50,50, "BwPMAyDK_400x400.jpg");
        this.pos = pos;
        keyboard = new Keyboard(this);
        this.doraemon.draw();
        this.doraemon.fill();
       // this.doraemon.grow(-150,-150);
        this.currentDirection = GridDirection.values()[(int) (Math.random() * GridDirection.values().length)];

        init();
    }

    public int getX() {
        return doraemon.getX();
    }

    public int getY() {
        return doraemon.getY();
    }


    /**
     * Initialize keyboard handlers
     */
    public void init() {
        KeyboardEvent left = new KeyboardEvent();
        left.setKey(KeyboardEvent.KEY_A);
        left.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent right = new KeyboardEvent();
        right.setKey(KeyboardEvent.KEY_D);
        right.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent up = new KeyboardEvent();
        up.setKey(KeyboardEvent.KEY_W);
        up.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent down = new KeyboardEvent();
        down.setKey(KeyboardEvent.KEY_S);
        down.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent faster = new KeyboardEvent();
        faster.setKey(KeyboardEvent.KEY_SPACE);
        faster.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent slower = new KeyboardEvent();
        slower.setKey(KeyboardEvent.KEY_SPACE);
        slower.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);


        keyboard.addEventListener(left);
        keyboard.addEventListener(right);
        keyboard.addEventListener(up);
        keyboard.addEventListener(down);
        keyboard.addEventListener(faster);
        keyboard.addEventListener(slower);
    }
    public boolean isHittingWall() {

        switch (currentDirection) {
            case LEFT:
                if (doraemon.getX() == grid.getPadding()) {
                    //System.out.println(doraemon.getX());
                    return true;

                }
            case RIGHT:
                if (doraemon.getX() + grid.getCellsize() == grid.getPadding() + grid.getWidth()){
                    //System.out.println(doraemon.getX());
                    return true;
                }
            case UP:
                if (doraemon.getY() == grid.getPadding()) {
                    //System.out.println(doraemon.getY());
                    return true;
                }
            case DOWN:
                if (doraemon.getY() + grid.getCellsize() == grid.getPadding() + grid.getHeigth() ){
                    //System.out.println(doraemon.getY());
                    return true;
                }
        }

        return false;

    }


    public void move() {
        accelerate(currentDirection);
    }

    public boolean isDead(){
        return dead;
    }

    public void setDead(){
        dead = true;
    }

    public GridPosition getPos() {
        return pos;
    }

    public void accelerate(GridDirection direction) {

        if (isDead()) {
            return;
        }

        this.currentDirection = direction;

        getPos().moveDirection(direction, doraemon);
        if (isHittingWall()) {
            setDead();
        }
    }



    /**
     * Handles key press events
     *
     * @param keyboardEvent the keyboard event
     */
    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        if (keyboardEvent.getKey() == KeyboardEvent.KEY_SPACE) {
            return;
        }

        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_A:
                currentDirection = GridDirection.LEFT;
                break;
            case KeyboardEvent.KEY_D:
                currentDirection = GridDirection.RIGHT;
                break;
            case KeyboardEvent.KEY_W:
                currentDirection = GridDirection.UP;
                break;
            case KeyboardEvent.KEY_S:
                currentDirection = GridDirection.DOWN;
                break;
        }


    }

    @Override
    public void keyReleased(KeyboardEvent e) {

    }


}



