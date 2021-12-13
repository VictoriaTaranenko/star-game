package com.star.app.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.star.app.game.helpers.ObjectPool;
import com.star.app.screen.utils.Assets;

public class BulletController extends ObjectPool<Bullet> {
    private GameController gc;
    private TextureRegion bulletTexture;

    @Override
    protected Bullet newObject() {
        return new Bullet(gc);
    }

    public BulletController(GameController gc) {
        this.gc = gc;
        this.bulletTexture = Assets.getInstance().getAtlas().findRegion("pull");
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < activeList.size(); i++) {
            Bullet b = activeList.get(i);
            batch.draw(bulletTexture, b.getPosition().x - 8, b.getPosition().y - 8, 8, 8, 16, 16, 1.0f, 1.0f, b.getAngle());
        }
    }

    public void setup(Ship owner, String title, float x, float y, float vx, float vy, int damage, float angle, float lifetimeDistance) {
        getActiveElement().activate(owner, title, x, y, vx, vy, damage, angle, lifetimeDistance);
    }

    public void update(float dt) {
        for (int i = 0; i < activeList.size(); i++) {
            activeList.get(i).update(dt);
        }
        checkPool();
    }
}
