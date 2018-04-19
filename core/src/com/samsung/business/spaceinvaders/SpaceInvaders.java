package com.samsung.business.spaceinvaders;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.samsung.business.spaceinvaders.manager.ScoreManager;
import com.samsung.business.spaceinvaders.screens.GameOverScreen;
import com.samsung.business.spaceinvaders.screens.GameScreen;
import com.samsung.business.spaceinvaders.screens.GameScreen2;
import com.samsung.business.spaceinvaders.screens.GameScreen3;
import com.samsung.business.spaceinvaders.screens.GameScreen4;
import com.samsung.business.spaceinvaders.screens.GameScreen5;
import com.samsung.business.spaceinvaders.screens.MainMenuScreen;
import com.samsung.business.spaceinvaders.screens.WinScreen;
import com.samsung.business.spaceinvaders.screens.lvlScreen;
import com.samsung.business.spaceinvaders.screens.lvlScreen2;
import com.samsung.business.spaceinvaders.screens.lvlScreen3;
import com.samsung.business.spaceinvaders.screens.lvlScreen4;
// klasa główa, mainmenuscreen,gameovewscreen levele i przedlevelowescreeny//
//mainmenuscreen - screen poczatkowy//
//gameoverscreen - screen po przegranej//
//lvlScreen - przedlewelowyscreen//
//Gamescreeny screeny gry//
//winScreen screen koncowy//


public class SpaceInvaders extends Game {
    public SpriteBatch batch;
    private ScoreManager score;

    @Override
    public void create() {
        batch = new SpriteBatch();
        this.setScreen(new MainMenuScreen(this));
        score = new ScoreManager(0);

    }
    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
    public void gameOver() {
        this.setScreen(new GameOverScreen(this));
    }
    public void win() {
        this.setScreen(new lvlScreen(this));
    }
    public void lvl() {
        this.setScreen(new GameScreen2(this));
    }
    public void win2() {
        this.setScreen(new lvlScreen2(this));
    }
    public void lvl2() {
        this.setScreen(new GameScreen3(this));

    }
    public void win3(){
        this.setScreen(new lvlScreen3(this));
    }
    public void lvl3() {
        this.setScreen(new GameScreen4(this));
    }
    public void win4() {
        this.setScreen(new lvlScreen4(this));
    }
    public void lvl4(){
        this.setScreen(new GameScreen5(this));
    }
        public void win5(){
            this.setScreen(new WinScreen(this));

    }
    public ScoreManager getScore() {
        return score;
    }

    public void restart() {
        score.reset();
        start();
    }

    public void start() {
        this.setScreen(new GameScreen(this));
        //Rafal Strus IE//
        //pallpallel2@gmail.com//


    }
}
