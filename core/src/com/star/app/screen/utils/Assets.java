package com.star.app.screen.utils;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.star.app.screen.ScreenManager;

public class Assets {

    private static final Assets ourInstance = new Assets();

    public static Assets getInstance() {
        return ourInstance;
    }

    private AssetManager assetManager;
    private TextureAtlas textureAtlas;

    public TextureAtlas getAtlas() {
        return textureAtlas;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    private Assets() {
        assetManager = new AssetManager();
    }

    public void loadAssets(ScreenManager.ScreenType type) {
        switch(type) {
            case GAME:
                assetManager.load("images/game.pack",TextureAtlas.class);
                createStandardFont(16);
                assetManager.finishLoading();
                textureAtlas = assetManager.get("images/game.pack", TextureAtlas.class);
               break;

        }
    }

    private void createStandardFont(int size) {
        FileHandleResolver resolver = new InternalFileHandleResolver();
        assetManager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        assetManager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));
        FreetypeFontLoader.FreeTypeFontLoaderParameter fontLoaderParameter = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        fontLoaderParameter.fontFileName = "fonts/Roboto-Medium.ttf";
        fontLoaderParameter.fontParameters.size = size;
        fontLoaderParameter.fontParameters.color = Color.WHITE;
        fontLoaderParameter.fontParameters.shadowOffsetX = 1;
        fontLoaderParameter.fontParameters.shadowOffsetY = 1;
        fontLoaderParameter.fontParameters.shadowColor = Color.DARK_GRAY;

        assetManager.load("fonts/font" + size + ".ttf", BitmapFont.class, fontLoaderParameter);

    }


    public void clear() {
        assetManager.clear();
    }

}