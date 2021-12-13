package com.star.app.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.star.app.screen.utils.Assets;

public class Shop extends Group {
    private Hero hero;
    private BitmapFont font24;

    public Shop(final Hero hero) {
        this.hero = hero;
        this.font24 = Assets.getInstance().getAssetManager().get("fonts/font24.ttf");

        Pixmap pixmap = new Pixmap(400, 400, Pixmap.Format.RGB888);
        pixmap.setColor(Color.rgb888(0.0f, 0.0f, 0.4f));
        pixmap.fill();

        Image image = new Image(new Texture(pixmap));

        Skin skin = new Skin();
        skin.addRegions(Assets.getInstance().getAtlas());

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("smollButton");
        textButtonStyle.font = font24;
        skin.add("smollButtonSkin", textButtonStyle);

        Image[][] lamps = new Image[hero.getSkills().length][];
        this.addActor(image);
        for (int i = 0; i < lamps.length; i++) {
            lamps[i] = new Image[hero.getSkills()[i].getMaxLevel()];
            for (int j = 0; j < lamps[i].length; j++) {
                Image img = new Image(skin.getDrawable("star"));
                img.setColor(0, 1, 0, 1);

                img.setPosition(110 + j * 20, 358 - i * 60);
//            img.setScale(3.0f);
                lamps[i][j] = img;
                this.addActor(img);
            }
        }


        TextButton[] btnSkills = new TextButton[hero.getSkills().length];
        for (int i = 0; i < btnSkills.length; i++) {
            final int skillIndex = i;
            final TextButton hpSkillBtn = new TextButton(hero.getSkills()[i].getTitle(), textButtonStyle);
            hpSkillBtn.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    if (hero.getSkills()[skillIndex].isUpgradable()) {
                        int cost = hero.getSkills()[skillIndex].getCurrentLevelCost();
                        if (hero.isMoneyEnough(cost)) {
                            hero.decreaseMoney(cost);
                            hero.getSkills()[skillIndex].upgrade();
                            lamps[skillIndex][hero.getSkills()[skillIndex].getLevel() - 2].setColor(1, 0, 0, 1);
                        }
                    }
                }
            });
            hpSkillBtn.setPosition(20, 300 - 60 * i);
            this.addActor(hpSkillBtn);
        }

        final TextButton btnClose = new TextButton("x", textButtonStyle);
        final Shop thisShop = this;
        btnClose.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                thisShop.setVisible(false);
            }
        });
        btnClose.setTransform(true);
        btnClose.setScale(0.4f);
        btnClose.setPosition(320, 320);


        this.addActor(btnClose);
        this.setPosition(20, 90);
        this.setVisible(false);

        skin.dispose();
    }
}
