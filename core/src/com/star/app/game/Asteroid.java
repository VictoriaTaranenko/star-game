package com.star.app.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.star.app.game.helpers.Poolable;
import com.star.app.screen.ScreenManager;
import com.star.app.screen.utils.Assets;

public class Asteroid implements Poolable {

    private TextureRegion texture;
    private Vector2 position;
    private Vector2 velocity;
    private int hpMax;
    private int hp;
    private float scale;
    private float angle;
    private float rotationSpeed;
    private boolean active;
    private Circle hitArea;

    private final float BASE_SIZE = 256.0f;
    private final float BASE_RADIUS = BASE_SIZE / 2.0f;

    public Vector2 getPosition() {
        return position;
    }

    public Circle getHitArea() {
        return hitArea;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    public void deactivate() {
        active = false;
    }

   public Asteroid () {
      this.position = new Vector2(0,0);
      this.velocity = new Vector2(0,0);
      this.hitArea = new Circle(0,0,0);
      this.active = false;
      this.texture = Assets.getInstance().getAtlas().findRegion("asteroid");
   }

   public boolean takeDamage(int amount) { // получение урона
    hp -= amount;
    if(hp <= 0) {
        deactivate();
        return true;
    }
    return false;
   }

   public void activate(float x, float y, float vx, float vy) {
        position.set(x, y);
        velocity.set(vx, vy);
        hpMax = 10;
        hp = hpMax;
        angle = MathUtils.random(0.0f,360.0f);
        hitArea.setPosition(position);
        rotationSpeed = MathUtils.random(-60.0f, 60.0f);
        active = true;
        scale = 1.0f;
       hitArea.setRadius(BASE_RADIUS * scale * 0.9f);

   }

   public void render(SpriteBatch batch) {
        batch.draw(texture, position.x - 128, position.y - 128, 128,128,256,256,scale,scale,angle);
    }

   public void update (float dt) {
    position.mulAdd(velocity, dt);
    angle += rotationSpeed * dt;
    if(position.x < -BASE_RADIUS * scale) {
        position.x = ScreenManager.SCREEN_WIDTH + BASE_RADIUS * scale;
    }
    if(position.x > ScreenManager.SCREEN_WIDTH + BASE_RADIUS * scale) {
        position.x = -BASE_RADIUS * scale;
    }
       if(position.y < -BASE_RADIUS * scale) {
           position.y = ScreenManager.SCREEN_HEIGHT + BASE_RADIUS * scale;
       }
       if(position.y > ScreenManager.SCREEN_HEIGHT + BASE_RADIUS * scale) {
           position.y = -BASE_RADIUS * scale;
       }

       hitArea.setPosition(position);
   }


}
