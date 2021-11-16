package com.star.app.game;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.star.app.screen.ScreenManager;

public class GameController {
    private Background background;
    private AsteroidController asteroidController;
    private BulletController bulletController;
    private ParticleController particleController;
    private Hero hero;
    private Vector2 tmpVec;

    public AsteroidController getAsteroidController() {
        return asteroidController;
    }

    public BulletController getBulletController() {
        return bulletController;
    }

    public Background getBackground() {
        return background;
    }

    public ParticleController getParticleController() {
        return particleController;
    }

    public Hero getHero() {
        return hero;
    }

    public GameController() {
        this.background = new Background(this);
        this.hero = new Hero(this);
        this.asteroidController = new AsteroidController(this);
        this.bulletController = new BulletController(this);
        particleController = new ParticleController();
        this.tmpVec = new Vector2(0.0f, 0.0f);
        for(int i = 0; i < 2; i++) {
            this.asteroidController.setup(MathUtils.random(0, ScreenManager.SCREEN_WIDTH),MathUtils.random(0, ScreenManager.SCREEN_HEIGHT),
                    MathUtils.random(-150.0f, 150.0f), MathUtils.random(-150.0f, 150.0f), 1.0f);
        }
    }

    public void update(float dt) {
        background.update(dt);
        hero.update(dt);
        asteroidController.update(dt);
        bulletController.update(dt);
        particleController.update(dt);
        checkCollisions();
    }
    public void checkCollisions() {
        for(int i = 0; i < asteroidController.getActiveList().size(); i++) {
            Asteroid a = asteroidController.getActiveList().get(i);
            if(a.getHitArea().overlaps(hero.getHitArea())) { // если они сталкнулись
                float dst = a.getPosition().dst(hero.getPosition()); // смотрим расстояние между ними
                float halfOverlen = (a.getHitArea().radius + hero.getHitArea().radius - dst) / 2.0f;
                tmpVec.set(hero.getPosition()).sub(a.getPosition()).nor();
                hero.getPosition().mulAdd(tmpVec, halfOverlen);
                a.getPosition().mulAdd(tmpVec, -halfOverlen);

                float sumScl = hero.getHitArea().radius * 2 + a.getHitArea().radius;

                hero.getVelocity().mulAdd(tmpVec, 400.0f * halfOverlen * a.getHitArea().radius / sumScl);
                a.getVelocity().mulAdd(tmpVec, 400.0f * -halfOverlen * hero.getHitArea().radius / sumScl);

                if(a.takeDamage(2)) {
                    hero.addScore(a.getHpMax() * 10);
                }
                hero.takeDamage(2);

            }
        }

        for(int i = 0; i < bulletController.getActiveList().size(); i++) {
            Bullet b = bulletController.getActiveList().get(i);
           for(int j = 0; j < asteroidController.getActiveList().size(); j++) {
               Asteroid a = asteroidController.getActiveList().get(j);


               if(a.getHitArea().contains(b.getPosition())) {
                   particleController.setup(
                           b.getPosition().x + MathUtils.random(-4, 4), b.getPosition().y + MathUtils.random(-4, 4),
                           b.getVelocity().x * -0.5f + MathUtils.random(-30, 30), b.getVelocity().y * -0.5f + MathUtils.random(-30, 30),
                           0.2f,
                           2.2f, 1.7f,
                           0.0f, 0.0f, 1.0f, 1.0f,
                           1.0f, 1.0f, 1.0f, 0.0f
                   );
                   b.deactivate();
                   if(a.takeDamage(1)) {
                       hero.addScore(a.getHpMax() * 100);
                   }
                break;
               }
           }
        }
    }
}