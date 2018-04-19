package com.samsung.business.spaceinvaders.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.samsung.business.spaceinvaders.SpaceInvaders;
import com.samsung.business.spaceinvaders.ui.DisplayInfo;
import com.samsung.business.spaceinvaders.ui.TouchInput;
//ekran zwycięstwa po 5 lvlach z wynikami punktów//
public class WinScreen extends AbstractScreen{
    private final SpaceInvaders spaceInvaders;
    private OrthographicCamera camera;
    private BitmapFont font;
    private TouchInput touchInput;

    public WinScreen(SpaceInvaders spaceInvaders) {
        font = new BitmapFont();


        camera = new OrthographicCamera();
        camera.setToOrtho(false, DisplayInfo.getWidth(), DisplayInfo.getHeight());
        this.spaceInvaders = spaceInvaders;
        this.touchInput = new TouchInput(camera);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        spaceInvaders.batch.setProjectionMatrix(camera.combined);
        spaceInvaders.batch.begin();
        font.draw(spaceInvaders.batch, "You WIN!!!!! ", 200, Gdx.graphics.getHeight()/2);
        font.draw(spaceInvaders.batch, "You Got  " + spaceInvaders.getScore().getValue()+ " points!!!", 200, Gdx.graphics.getHeight()/2);
        font.draw(spaceInvaders.batch, "Touch screen to restart game", 10, (Gdx.graphics.getHeight()/2)-50);

        spaceInvaders.batch.end();
        if (touchInput.start()){
            spaceInvaders.restart();
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
        camera.update();
    }

    @Override
    public void dispose() {
        font.dispose();
    }
}
