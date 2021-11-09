package com.star.app.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.star.app.screen.ScreenManager;
import com.star.app.screen.utils.Assets;

public class Hero {
    private GameController gc;
    private TextureRegion texture;
    private Vector2 position;
    private Vector2 velocity;
    private float angle;
    private float enginePower;
    private float fireTimer;

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public Hero(GameController gc) {
        this.gc = gc;
        this.texture = Assets.getInstance().getAtlas().findRegion("ship");
        this.position = new Vector2(640,360);
        this.velocity = new Vector2(0,0);
        this.angle = 0.0f;
        this.enginePower = 750.0f;

    }

    public void render(SpriteBatch batch) {
    batch.draw(texture,position.x -32, position.y -32,32,32,64,64,1,1,angle);

    }
//    boolean x = true;

    public void update(float dt) {
        fireTimer += dt;
        if(Gdx.input.isKeyPressed(Input.Keys.P)) {
            if(fireTimer > 0.4f) {
                fireTimer = 0.0f;
                float wx = position.x + (float)Math.cos(Math.toRadians(angle)) * 25;
                float wy = position.y + (float)Math.sin(Math.toRadians(angle)) * 25;
                gc.getBulletController().setup(wx, wy,(float)Math.cos(Math.toRadians(angle)) * 600 + velocity.x, (float)Math.sin(Math.toRadians(angle)) * 600 + velocity.y, angle);


//                // стрельба из боковых пушек
//                float wx = 0.0f, wy =
//                x = !x;
//                if(x) {
//                 wx = position.x + (float)Math.cos(Math.toRadians(angle + 90)) * 28;
//                 wy = position.y + (float)Math.sin(Math.toRadians(angle + 90)) * 28;
//                gc.getBulletController().setup(wx, wy,(float)Math.cos(Math.toRadians(angle)) * 600 + velocity.x, (float)Math.sin(Math.toRadians(angle)) * 600 + velocity.y, angle);
//               }else {
//                 wx = position.x + (float)Math.cos(Math.toRadians(angle - 90)) * 28;
//                 wy = position.y + (float)Math.sin(Math.toRadians(angle - 90)) * 28;
//                gc.getBulletController().setup(wx, wy,(float)Math.cos(Math.toRadians(angle)) * 600 + velocity.x, (float)Math.sin(Math.toRadians(angle)) * 600 + velocity.y, angle);
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            angle += 180.0f * dt;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            angle -= 180.0f * dt;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            velocity.x += (float)Math.cos(Math.toRadians(angle)) * enginePower * dt;
            velocity.y += (float)Math.sin(Math.toRadians(angle)) * enginePower * dt;

        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            position.x -= (float)Math.cos(Math.toRadians(angle)) * enginePower * dt / 2.0f;
            position.y -= (float)Math.cos(Math.toRadians(angle)) * enginePower * dt / 2.0f;

        }

        position.mulAdd(velocity, dt);
        float stopKoef = 1.0f - 2.0f * dt;
        if(stopKoef < 0.0f) {
            stopKoef = 0.0f;
        }
        velocity.scl(stopKoef);



        if(position.x < 0.0f) {
            position.x = 0.0f;
            velocity.x *= -1;
        }
        if(position.x > ScreenManager.SCREEN_WIDTH) {
            position.x = ScreenManager.SCREEN_WIDTH;
            velocity.x *= -1;
        }
        if(position.y < 0.0f) {
            position.y = 0.0f;
            velocity.y *= -1;
        }
        if(position.y > ScreenManager.SCREEN_HEIGHT) {
            position.y = ScreenManager.SCREEN_HEIGHT;
            velocity.y *= -1;
        }

    }
}
