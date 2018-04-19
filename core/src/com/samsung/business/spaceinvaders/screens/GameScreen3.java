package com.samsung.business.spaceinvaders.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.samsung.business.spaceinvaders.SpaceInvaders;
import com.samsung.business.spaceinvaders.entity.EnemySpaceship;
import com.samsung.business.spaceinvaders.entity.Invasion;
import com.samsung.business.spaceinvaders.entity.PlayerSpaceship;
import com.samsung.business.spaceinvaders.entity.Spaceship;
import com.samsung.business.spaceinvaders.manager.GraphicsManager;
import com.samsung.business.spaceinvaders.manager.ShootManager;
import com.samsung.business.spaceinvaders.ui.DisplayInfo;
import com.samsung.business.spaceinvaders.ui.TouchInput;
import com.samsung.business.spaceinvaders.ui.components.ScoreGuiLabel;
//screengry//
public class GameScreen3 extends AbstractScreen {
    private final SpaceInvaders spaceInvaders;

    private OrthographicCamera camera;

    private ShootManager shootManager;
    private GraphicsManager graphicsManager;

    private PlayerSpaceship player;
    private Invasion invasion;

    private ScoreGuiLabel scoreGuiLabel;
    private TouchInput touchInput;

    private float animationTime;

    public GameScreen3(SpaceInvaders spaceInvaders) {
        this.spaceInvaders = spaceInvaders;
        scoreGuiLabel = new ScoreGuiLabel();
        create();
    }

    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, DisplayInfo.getWidth(), DisplayInfo.getHeight());

        touchInput = new TouchInput(camera);

        graphicsManager = GraphicsManager.loadGraphics();

        player = new PlayerSpaceship(graphicsManager.find("rakieta"), camera);
        player.registerOnSpaceshipHit(new Spaceship.OnSpaceshipHit() {
            @Override
            public void onSpaceshipHit() {
                spaceInvaders.gameOver();
                dispose();
            }
        });

        invasion = Invasion.raid(graphicsManager);

        invasion.listenOnDestroyed(new Invasion.OnEnemyDestroyed() {
            @Override
            public void onEnemyDestroyed(EnemySpaceship enemy) {
                spaceInvaders.getScore().addScore(120);
            }
        });

        invasion.listenOnInvasionDestroyed(new Invasion.OnInvasionDestroyed() {
            @Override
            public void onInvasionDestroyed() {
                spaceInvaders.win3();
                Invasion.ENEMY_IN_ROW_COUNT = 20;
                Invasion.ENEMY_ROWS_COUNT = 3;
                Invasion.ENEMY_HEIGHT =20;
                Invasion.ENEMY_WIDTH =20;
                dispose();
            }
        });

        shootManager = new ShootManager(graphicsManager, player, invasion);
        shootManager.onMissed(()->{
            spaceInvaders.getScore().addScore(-10);
        });
    }

    private void updatGameState() {
        player.update(camera, shootManager);
        invasion.update(camera, shootManager);

        shootManager.updateShots();


        scoreGuiLabel.setScore(spaceInvaders.getScore().getValue());
    }

    @Override
    public void render(float delta) {
        animationTime+= delta;
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        spaceInvaders.batch.setProjectionMatrix(camera.combined);

        spaceInvaders.batch.begin();

        player.render(spaceInvaders.batch, animationTime);
        shootManager.render(spaceInvaders.batch, animationTime);
        invasion.render(spaceInvaders.batch, animationTime);
        scoreGuiLabel.render(spaceInvaders.batch, animationTime);

        spaceInvaders.batch.end();

        updatGameState();
        if (touchInput.exit()){
            spaceInvaders.setScreen(new MainMenuScreen(spaceInvaders));
            spaceInvaders.getScore().reset();
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {
        camera.update();
    }

    @Override
    public void dispose() {
    }
}