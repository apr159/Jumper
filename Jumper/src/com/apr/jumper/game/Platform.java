package com.apr.jumper.game;



public class Platform extends DynamicGameObject {
    public static final float PLATFORM_WIDTH = 1;
    public static final float PLATFORM_HEIGHT = 0.2f;
    
    
    
    
    public static final int PLATFORM_TYPE_STATIC = 0;
    public static final int PLATFORM_TYPE_MOVING = 1;
    public static final int PLATFORM_TYPE_EXPLODING = 2;

    public static final int PLATFORM_STATE_NORMAL = 0;
    public static final int PLATFORM_STATE_PULVERIZING = 1;
    public static final float PLATFORM_PULVERIZE_TIME = 0.2f * 4;
    public static final float PLATFORM_VELOCITY = 2;
    
    int type;
    int state;
    float stateTime;
    boolean passed;
    
    public Platform(){
    	super();
    	
    }
    
    public Platform(int type, float x, float y) {
        super(x, y, PLATFORM_WIDTH, PLATFORM_HEIGHT);
        this.type = type;
        this.state = PLATFORM_STATE_NORMAL;
        this.stateTime = 0;
        if(type == PLATFORM_TYPE_MOVING) {
            velocity.x = PLATFORM_VELOCITY;
        }
    }
    
    public void define(){
    	super.define(PLATFORM_WIDTH, PLATFORM_HEIGHT);
    	this.state = PLATFORM_STATE_NORMAL;
    	this.stateTime = 0;
        if(type == PLATFORM_TYPE_MOVING) {
            velocity.x = PLATFORM_VELOCITY;
        }
    }
    
    
    public void update(float deltaTime) {
        if(type == PLATFORM_TYPE_MOVING) {
            this.setPosition(getX()+velocity.x * deltaTime, getY());
            bounds.set(getX(), getY(),bounds.width, bounds.height);
            
            if(getX() < PLATFORM_WIDTH / 2) {
                velocity.x = -velocity.x;
                setX(PLATFORM_WIDTH / 2);
            }
            if(getX()> World.WORLD_WIDTH - PLATFORM_WIDTH / 2) {
                velocity.x = -velocity.x;
                setX( World.WORLD_WIDTH - PLATFORM_WIDTH / 2);
            }            
        }
                
        stateTime += deltaTime;        
    }
    
    public void pulverize() {
        state = PLATFORM_STATE_PULVERIZING;
        stateTime = 0;
        velocity.x = 0;
    }
}
