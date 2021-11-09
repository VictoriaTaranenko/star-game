package com.star.app.game;

import com.badlogic.gdx.math.MathUtils;
import com.star.app.screen.ScreenManager;

public class GameController {
    private Background background;
    private AsteroidController asteroidController;
    private BulletController bulletController;
    private Hero hero;

    public AsteroidController getAsteroidController() {
        return asteroidController;
    }

    public BulletController getBulletController() {
        return bulletController;
    }

    public Background getBackground() {
        return background;
    }

    public Hero getHero() {
        return hero;
    }

    public GameController() {
        this.background = new Background(this);
        this.hero = new Hero(this);
        this.asteroidController = new AsteroidController();
        this.bulletController = new BulletController();
        for(int i = 0; i < 3; i++) {
            this.asteroidController.setup(MathUtils.random(0, ScreenManager.SCREEN_WIDTH),MathUtils.random(0, ScreenManager.SCREEN_HEIGHT),
                    MathUtils.random(-150.0f, 150.0f), MathUtils.random(-150.0f, 150.0f));
        }
    }

    public void update(float dt) {
        background.update(dt);
        hero.update(dt);
        asteroidController.update(dt);
        bulletController.update(dt);
        checkCollisions();
    }
    public void checkCollisions() {
        for(int i = 0; i < bulletController.getActiveList().size(); i++) {
            Bullet b = bulletController.getActiveList().get(i);
            if(hero.getPosition().dst(b.getPosition()) < 32.0f) {
//                b.deactivate();
            }
        }
    }
}
