package com.apr.jumper.game;


public class Hero extends DynamicGameObject{
	
	public static final int STATE_BASE = 0;
    public static final int STATE_JUMP = 1;
    public static final int STATE_FALL = 2;
    public static final int STATE_HIT = 3;
    public static final float JUMP_VELOCITY = 7;    
    public static final float MOVE_VELOCITY = 10;
    public static final float WIDTH = 0.6f;
    public static final float HEIGHT = 1.4f;
    
    int state;
    public float stateTime;    
    public float acceleration;
    
    public Hero(float x, float y) {
        super(x, y, WIDTH, HEIGHT);
        state = STATE_JUMP;
        stateTime = 0;        
    }
    
    

    public void update(float deltaTime) {     
    	
    	if (state == STATE_BASE) return;
    	
    	if (state == STATE_HIT){
            stateTime += deltaTime;
            if (stateTime>1){
            	state=STATE_BASE;
            }
            	
    	}
    	
        velocity.add(World.gravity.x * deltaTime, World.gravity.y * deltaTime);
        setPosition(getX()+velocity.x * deltaTime, getY()+velocity.y * deltaTime);
        bounds.set(getX(), getY(),bounds.width, bounds.height);
        
        if(velocity.y > 0 && state != STATE_HIT) {
            if(state != STATE_JUMP) {
                state = STATE_JUMP;
                stateTime = 0;
            }
        }
        
        if(velocity.y < 0 && state != STATE_HIT) {
            if(state != STATE_FALL) {
                state = STATE_FALL;
                stateTime = 0;
            }
        }
        
        if(getX() < 0)
            setX( World.WORLD_WIDTH);
        if(getX() > World.WORLD_WIDTH)
            setX( 0);
        
        stateTime += deltaTime;
    }
    
    public void hitEnemy() {
       // velocity.set(0,0);
        state = STATE_HIT;        
        stateTime = 0;
    }
    
    public void hitPlatform() {
        velocity.y = JUMP_VELOCITY;
        state = STATE_JUMP;
        stateTime = 0;
     }

    public void hitSpring() {
        velocity.y = JUMP_VELOCITY * 1.5f;
        state = STATE_JUMP;
        stateTime = 0;   
    }
}
