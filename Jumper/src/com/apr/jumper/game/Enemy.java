package com.apr.jumper.game;

import com.badlogic.gdx.graphics.Color;

public class Enemy extends DynamicGameObject {
    public static final float ENEMY_WIDTH = 0.3f;
    public static final float ENEMY_HEIGHT = 0.3f;
    public static final int TYPE_UFO = 0;
    public static final int TYPE_BIRD = 1;
    public static final int TYPE_AIRPLANE = 2;
    
    
    float stateTime = 0;
    int value;
    String strValue;
    Color color;

    public Enemy(){
    	super();
    }
    
    public void setValue(int value){
    	this.value = value;
    	strValue = String.valueOf(value);
    }
    
    public Enemy(World world,  float x, float y, float vx, int value) {
        super(x, y, ENEMY_WIDTH, ENEMY_HEIGHT);
        velocity.set(vx, 0f);
        this.value = value;
    	strValue = String.valueOf(value);
    	color = World.COLORS[world.rand.nextInt(World.COLORS.length)];
    }
    
    public void define(){
    	super.define(ENEMY_WIDTH, ENEMY_HEIGHT);
        velocity.set(velocity.x, 0f);


    }
    
    public void update(float deltaTime) {
        setPosition(getX()+velocity.x * deltaTime, getY()+velocity.y * deltaTime);
        bounds.set(getX(), getY(),bounds.width, bounds.height);
        if(getX() < ENEMY_WIDTH / 2 ) {
            setX(ENEMY_WIDTH / 2);
            velocity.x = -velocity.x;
        }
        if(getX()> World.WORLD_WIDTH - ENEMY_WIDTH / 2) {
           setX( World.WORLD_WIDTH - ENEMY_WIDTH / 2);
            velocity.x = -velocity.x;
        }
        stateTime += deltaTime;
    }
}
