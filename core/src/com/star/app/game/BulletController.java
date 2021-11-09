package com.star.app.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.star.app.game.helpers.ObjectPool;
import com.star.app.screen.utils.Assets;

public class BulletController extends ObjectPool<Bullet> {

    private TextureRegion bulletTexture;

    @Override
    protected Bullet newObject() {
        return new Bullet();
    }

    public BulletController() {
        this.bulletTexture = Assets.getInstance().getAtlas().findRegion("pull");
    }

    public void render(SpriteBatch batch) {
        for(int i = 0; i < activeList.size(); i++) {
            Bullet b = activeList.get(i);
            batch.draw(bulletTexture, b.getPosition().x - 8, b.getPosition().y - 8,8,8,16,16,1,1,b.getAngle());
        }
    }

    public void setup(float x, float y, float vx, float vy, float angle) {
        getActiveElement().activate(x, y, vx, vy, angle);
    }

    public void update(float dt) {
    for(int i = 0; i < activeList.size(); i++) {
        activeList.get(i).update(dt);
    }
    checkPool();
    }
}
