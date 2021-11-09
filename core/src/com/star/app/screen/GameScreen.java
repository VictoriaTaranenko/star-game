package com.star.app.screen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.star.app.game.GameController;
import com.star.app.game.WorldRenderer;
import com.star.app.screen.utils.Assets;

// игровой экран
public class GameScreen extends AbstractScreen {

    private GameController gameController;// содержит всю логику игры, и все данные нашей игры
    private WorldRenderer worldRenderer;// модуль отрисовщика
    private SpriteBatch batch;

    public GameScreen(SpriteBatch batch) {
        this.batch = batch;
    }

    @Override
    public void show() {
        Assets.getInstance().loadAssets(ScreenManager.ScreenType.GAME);
        this.gameController = new GameController();
        this.worldRenderer = new WorldRenderer(gameController, batch);

    }

    @Override
    public void render(float delta) {
        gameController.update(delta);
        worldRenderer.render();
    }

}
