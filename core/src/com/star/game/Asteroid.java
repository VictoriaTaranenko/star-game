package com.star.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Asteroid {
   private Texture texture;
   private Vector2 position;
   private Vector2 velocity;


   public Asteroid () {
       this.texture = new Texture("asteroid.png");
       this.position = new Vector2(MathUtils.random(0,ScreenManager.SCREEN_HEIGHT),MathUtils.random(0,ScreenManager.SCREEN_WIDTH));
        this.velocity = new Vector2(MathUtils.random(-40,-5),0);
   }

   public void render (SpriteBatch batch) {
       batch.draw(texture,position.x,position.y);

   }
   public void update (float dt) {
      position.x += velocity.x * 15 * dt;
      position.y += velocity.y * 15 * dt;
      if(position.x < - 20) {
          position.x = ScreenManager.SCREEN_WIDTH + 20;
      }

   }


}
