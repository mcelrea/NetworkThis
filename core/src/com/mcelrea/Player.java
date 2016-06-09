package com.mcelrea;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;

import java.io.Serializable;

/**
 * Created by Tech on 5/26/2016.
 */
public class Player implements Serializable{

    private Circle collisionCircle;
    private int radius = 12;
    private float x;
    private float y;
    private float xvel;
    private float yvel;
    private int left, right, down, up;

    public Player(int left, int right, int down, int up) {
        x = 300;
        y = 300;
        collisionCircle = new Circle(300,300,6);
        this.left = left;
        this.right = right;
        this.down = down;
        this.up = up;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
        updateCollisionCircle();
    }

    public void update() {
        Input input = Gdx.input;

        if(input.isKeyPressed(up)) {
            yvel = 5;
        }
        else if(input.isKeyPressed(down)) {
            yvel = -5;
        }
        else {
            yvel = 0;
        }

        if(input.isKeyPressed(right)) {
            xvel = 5;
        }
        else if(input.isKeyPressed(left)) {
            xvel = -5;
        }
        else {
            xvel = 0;
        }

        x += xvel;
        y += yvel;
        updateCollisionCircle();
    }

    private void updateCollisionCircle() {
        collisionCircle.setPosition(x,y);
    }

    public void drawDebug(ShapeRenderer shapeRenderer) {
        shapeRenderer.circle(x,y,radius);
    }
}
