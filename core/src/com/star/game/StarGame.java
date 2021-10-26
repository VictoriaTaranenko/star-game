package com.star.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class StarGame extends ApplicationAdapter {

	private SpriteBatch batch;// фон игры, область отрисовки
	private Background background;
	private Hero hero;

	public Hero getHero() {
		return hero;
	}


	@Override// когда приложение запускается срабатывает этот метод, инициализация
	public void create() {
		batch = new SpriteBatch();
		background = new Background(this);
		hero = new Hero();

	}

	@Override //отрисовка чего-то на экране(фона),этот метод работает 60 раз в секунду
	public void render() {
		float dt = Gdx.graphics.getDeltaTime();
		update(dt);

		ScreenUtils.clear(1, 1, 1, 1);
		batch.begin();// хотим начаь рисовать
		background.render(batch);
		hero.render(batch);
		batch.end();// хотим закончить рисовать
	}

	public void update(float dt) {
		background.update(dt);
		hero.update(dt);
	}

	@Override// когда приложение закрывается, освобождаем batch  и текстуру
	public void dispose() {
		batch.dispose();

	}
}
