package com.star.app;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.star.app.screen.GameScreen;

public class StarGame extends Game {
    private SpriteBatch batch;
	private GameScreen gameScreen;


	@Override// когда приложение запускается срабатывает этот метод, инициализация
	public void create() {
		batch = new SpriteBatch();
		gameScreen = new GameScreen(batch);
		setScreen(gameScreen);
	}

	@Override //отрисовка чего-то на экране(фона),этот метод работает 60 раз в секунду
	public void render() {
		float dt = Gdx.graphics.getDeltaTime();
		getScreen().render(dt);
	}

	@Override// когда приложение закрывается, освобождаем batch  и текстуру
	public void dispose() {
		batch.dispose();
	}
}
