package com.samsung.business.spaceinvaders.entity;

import com.badlogic.gdx.Gdx;
import com.samsung.business.spaceinvaders.manager.GraphicsManager;

//strzal wroga//
public class EnemyShoot extends Shoot {

    public EnemyShoot(GraphicsManager.Graphics graphics, float originX, float originY) {
        super(graphics, originX, originY);
    }

    @Override
    public void updateState() {
        this.position.y -= 200 * Gdx.graphics.getDeltaTime();
    }

    @Override
    public boolean isOutsideScreen() {
        return this.position.y + 10 < 0;
    }
}
