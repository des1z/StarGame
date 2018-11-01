package ru.geekbrains.stargame.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.base.Base2DScreen;

public class MenuScreen extends Base2DScreen {

    private SpriteBatch batch;
    private Texture bl;
    private Texture background;
    Vector2 currentPosition;
    private Vector2 v;
    private Vector2 touch;
    private Vector2 buf;

    public MenuScreen(Game game) {
        super(game);
        currentPosition = new Vector2(0,0);
    }

    @Override
    public void show () {
        super.show();
        batch = new SpriteBatch();
        bl = new Texture("badlogic.jpg");
        background = new Texture("background.jpg");
        currentPosition = new Vector2(0,0);
        v = new Vector2(0.5f, 0.3f);
        touch = new Vector2(0,0);
        buf = new Vector2();
    }

    @Override
    public void render (float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0.19f, 0.43f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        buf.set(touch);
        if (buf.cpy().sub(currentPosition).len() > v.len()){
            currentPosition.add(v);
        } else {
            currentPosition.set(touch);
        }
        batch.begin();
        batch.draw(background, 0,0);
        batch.draw(bl, currentPosition.x, currentPosition.y);
        batch.end();

    }
    @Override
    public void dispose () {
        batch.dispose();
        bl.dispose();
        background.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touch.set(screenX, Gdx.graphics.getHeight() - screenY);
        v.set(touch.cpy().sub(currentPosition).setLength(0.5f));
        return super.touchDown(screenX, screenY, pointer, button);
    }
}

