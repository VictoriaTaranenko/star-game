package com.star.app.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class WorldRenderer {
    private GameController gc;
    private SpriteBatch batch;

    public WorldRenderer(GameController gc, SpriteBatch batch) {
        this.gc = gc;
        this.batch = batch;
    }
    public void render() {
        ScreenUtils.clear(1, 1, 1, 1);
        batch.begin();// хотим начаь рисовать
        gc.getBackground().render(batch);
        gc.getHero().render(batch);
        gc.getAsteroidController().render(batch);
        gc.getBulletController().render(batch);
        batch.end();// хотим закончить рисовать

    }
}
