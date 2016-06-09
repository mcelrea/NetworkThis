package com.mcelrea;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Tech on 5/26/2016.
 */
public class GameplayScreen implements Screen {

    MyGdxGame game;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch spriteBatch;
    private Viewport viewport;
    private OrthographicCamera camera;

    Player player1;
    Player player2;


    public GameplayScreen(MyGdxGame myGdxGame) {
        this.game = myGdxGame;
        player1 = new Player(Input.Keys.A, Input.Keys.D, Input.Keys.S, Input.Keys.W);
        player2 = new Player(Input.Keys.LEFT, Input.Keys.RIGHT, Input.Keys.DOWN, Input.Keys.UP);
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
        viewport = new FitViewport(800,600,camera);
        viewport.apply(true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                UDP_Server server = new UDP_Server();
                server.createAndListen();
            }
        }).start();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);
        drawDebug();
    }

    private void update(float delta) {
        player1.update();
    }

    private void drawDebug() {
        shapeRenderer.begin();
        player1.drawDebug(shapeRenderer);
        player2.drawDebug(shapeRenderer);
        shapeRenderer.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
