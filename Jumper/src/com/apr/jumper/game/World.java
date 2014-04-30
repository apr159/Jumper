package com.apr.jumper.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;


public class World {
    public interface WorldListener {
        public void gameOver();
        public void nextLevel();
        public void finishLevel();
        public void jump();
        public void hit();
        public void destroy();
    }
    
    public static final Color[] COLORS = {Color.RED, Color.BLUE, Color.YELLOW, Color.ORANGE,Color.PINK,Color.CYAN, Color.GREEN, Color.MAGENTA};

    public static final float WORLD_WIDTH = 4.8f;
    public static float WORLD_HEIGHT = 8.0f*2;

    public static final int WORLD_STATE_RUNNING = 0;
    public static final int WORLD_STATE_NEXT_LEVEL = 1;
    public static final int WORLD_STATE_GAME_OVER = 2;
    
    public static final Vector2 gravity = new Vector2(0, -12);

    public static final int POINTS = 50;
    public static final int COMBO_MULTIPLIER = 2;
    
    private static float PROB_MOVE_PLATFORM = 0.9f;
    private static float PROB_EXPLODE_PLATFORM = 0.9f;
    private static float PROB_ENEMY = 0.7f;
    private static float ENEMY_SPEED = 1f;

    public MathOperation operation;
    public final Hero hero;
    public final List<Platform> platforms;
    public final List<Enemy> enemies;
    public WorldListener listener;
    
    public final Random rand;

    public float heightSoFar;
    public float baseHeight;
    public int score;
    public int state;
    public int level;
    public float maxY;
    public float goalHeight;
    public int combo = 0;
    public boolean started;
    public Color color = new Color(0f, 0.75f,1f,1f);
    
    public World(/*WorldListener listener*/) {
        this.hero = new Hero(1.9f, 0.25f);
        this.platforms = new ArrayList<Platform>();
        this.enemies = new ArrayList<Enemy>();
       // this.listener = listener;
        rand = new Random();

        this.heightSoFar = 0;
        this.score = 0;
        this.state = WORLD_STATE_RUNNING;
        this.started = false;
    }
    
    public void setWorldListnener(WorldListener listener){
    	this.listener = listener;
    }
    
    public void increaseLevel(){
        PROB_MOVE_PLATFORM = Math.max(PROB_MOVE_PLATFORM-0.02f, 0.5f);
        PROB_EXPLODE_PLATFORM =  Math.max(PROB_EXPLODE_PLATFORM-0.02f, 0.5f);
        PROB_ENEMY =  Math.max(PROB_ENEMY-0.02f, 0.6f);
        ENEMY_SPEED =  Math.max(PROB_MOVE_PLATFORM-0.02f, 0.7f);
        color.g = Math.max(color.g - 0.02f, 0f);
        color.b = Math.max(color.b - 0.02f, 0.2f);
    	maxY = WORLD_HEIGHT -4.0f;
    	WORLD_HEIGHT *= 2;
    	goalHeight = WORLD_HEIGHT - 8.0f;
	   level++;
	    float maxJumpHeight = Hero.JUMP_VELOCITY * Hero.JUMP_VELOCITY
	            / (2 * - gravity.y);
	    maxY+= (maxJumpHeight - 0.5f);
	    while (maxY < WORLD_HEIGHT ) {
	        int type = rand.nextFloat() > PROB_MOVE_PLATFORM ? Platform.PLATFORM_TYPE_MOVING
	                : rand.nextFloat() > PROB_EXPLODE_PLATFORM ? Platform.PLATFORM_TYPE_EXPLODING: Platform.PLATFORM_TYPE_STATIC;
	        float x = rand.nextFloat()
	                * (WORLD_WIDTH - Platform.PLATFORM_WIDTH)
	                + Platform.PLATFORM_WIDTH / 2;
	
	        Platform platform = new Platform(type, x, maxY);
	        platforms.add(platform);
	        
	
	        if (maxY > WORLD_HEIGHT / 3 && rand.nextFloat() > 0.9f) {
	            Enemy enemy = new Enemy(this,platform.getX()
	                    + rand.nextFloat(), platform.getY()
	                    + Enemy.ENEMY_HEIGHT + rand.nextFloat() * 2, ENEMY_SPEED, rand.nextInt(10*level));
	            
	            enemies.add(enemy);
	        }
	
	
	        maxY += (maxJumpHeight - 0.5f);
	        maxY -= rand.nextFloat() * (maxJumpHeight / 3);
	    }

    }

	public void generateLevel() {
		
		
	    PROB_MOVE_PLATFORM = 0.9f;
	    PROB_EXPLODE_PLATFORM = 0.9f;
	    PROB_ENEMY = 0.9f;
	    ENEMY_SPEED = 1f;
	    
        this.heightSoFar = 0;
        this.score = 0;
        this.level = 1;
        this.combo = 0;
        this.state = WORLD_STATE_RUNNING;
        this.baseHeight = 0;
		hero.setPosition(1.9f, 0.5f);
		hero.velocity.set(0,0);
		hero.state = Hero.STATE_JUMP;
		hero.acceleration = 0f;
		platforms.clear();
		enemies.clear();
		WORLD_HEIGHT = 8.0f*2;
		goalHeight = WORLD_HEIGHT - 8.0f;
		operation = new MathOperation(this);
		
        Platform platform = new Platform(Platform.PLATFORM_STATE_NORMAL, WORLD_WIDTH/2 - Platform.PLATFORM_WIDTH/2, 0);
        platforms.add(platform);

		
        float y = Platform.PLATFORM_HEIGHT / 2;
	    float maxJumpHeight = Hero.JUMP_VELOCITY * Hero.JUMP_VELOCITY
	            / (2 * -gravity.y);
        y += (maxJumpHeight - 0.5f);
        y -= rand.nextFloat() * (maxJumpHeight / 3);

	    while (y < WORLD_HEIGHT - WORLD_WIDTH / 2) {
	        int type = rand.nextFloat() > PROB_MOVE_PLATFORM ? Platform.PLATFORM_TYPE_MOVING
	                : rand.nextFloat() > PROB_EXPLODE_PLATFORM ? Platform.PLATFORM_TYPE_EXPLODING: Platform.PLATFORM_TYPE_STATIC;
	        float x = rand.nextFloat()
	                * (WORLD_WIDTH - Platform.PLATFORM_WIDTH)
	                + Platform.PLATFORM_WIDTH / 2;
	
	        platform = new Platform(type, x, y);
	        platforms.add(platform);
	
	        
	        if ( rand.nextFloat() > PROB_ENEMY) {
	            Enemy enemy = new Enemy(this,platform.getX()
	                    + rand.nextFloat(), platform.getY()
	                    + Enemy.ENEMY_HEIGHT + rand.nextFloat() * 2, ENEMY_SPEED, rand.nextInt(10*level));
	           
	            enemies.add(enemy);
	        }
	
	
	        y += (maxJumpHeight - 0.5f);
	        y -= rand.nextFloat() * (maxJumpHeight / 3);
	    }
//	    Enemy enemy = enemies.get(rand.nextInt(enemies.size()));
	//    enemy.setValue(operation.result);
	}
	


	
	public void update(float deltaTime) {
		if (!started) return ;
        
		checkNextLevel();
		
	    updateHero(deltaTime);
	    updatePlatforms(deltaTime);
	    updateEnemies(deltaTime);
	   // if (hero.state != Hero.STATE_HIT)
	        checkCollisions();
	    checkGameOver();
	}

	private void updateHero(float deltaTime) {
	   // if (hero.state != Hero.STATE_HIT && hero.position.y <= 0.5f)
	    //	hero.hitPlatform();
	   // if (hero.state != Hero.STATE_HIT)
	    	hero.velocity.x = -hero.acceleration / 10 * Hero.MOVE_VELOCITY;
	    hero.update(deltaTime);
	    heightSoFar = Math.max(hero.getY(), heightSoFar);
	}

	private void updatePlatforms(float deltaTime) {
	    int len = platforms.size();
	    for (int i = 0; i < len; i++) {
	        Platform platform = platforms.get(i);
	        platform.update(deltaTime);
	        if (platform.state == Platform.PLATFORM_STATE_PULVERIZING
	                && platform.stateTime > Platform.PLATFORM_PULVERIZE_TIME) {
	            platforms.remove(platform);
	            len = platforms.size();
	        }
	        
	        else if (platform.getY() < heightSoFar - 8.0){
	        	 platforms.remove(platform);
		          len = platforms.size();
	        }
	    }
	}

	private void updateEnemies(float deltaTime) {
	    int len = enemies.size();
	    for (int i = 0; i < len; i++) {
	        Enemy enemy = enemies.get(i);
	        enemy.update(deltaTime);
	    }
	}


	private void checkCollisions() {
	    checkPlatformCollisions();
	    checkEnemiesCollisions();
	}
	
	private void checkPlatformCollisions() {
	    if (hero.velocity.y > 0)
	        return;
	    
	    int len = platforms.size();
	    for (int i = 0; i < len; i++) {
	        Platform platform = platforms.get(i);
	        if (hero.getY() > platform.getY() && platform.state!=Platform.PLATFORM_STATE_PULVERIZING) {
	            if (hero.bounds.overlaps(platform.bounds)) {
	            	System.out.println(listener);
	            	listener.jump();
	            	
	                hero.hitPlatform();
	                
	                if (!platform.passed){
		                score += POINTS + POINTS*COMBO_MULTIPLIER*combo;
	                	combo++;
	                }
	                else{
	                	combo = 0;
	                }
	                

	                platform.passed = true;
	                //listener.jump();
	                
	                if (platform.type ==Platform.PLATFORM_TYPE_EXPLODING){
	                	platform.pulverize();
	                	listener.destroy();
	                }
	               // if (rand.nextFloat() > 0.5f) {
	              //      platform.pulverize();
	              //  }
	                break;
	            }
	        }
	    }
	}
	
	private void checkEnemiesCollisions() {
	    int len = enemies.size();
	    for (int i = 0; i < len; i++) {
	        Enemy enemy = enemies.get(i);
	        if (enemy.bounds.overlaps(hero.bounds) && hero.state!=Hero.STATE_HIT) {
	            hero.hitEnemy();
	            listener.hit();
	        }
	    }
	}
	
	

    private void checkGameOver() {

    	if (baseHeight - Hero.HEIGHT >  hero.getY()) {
            listener.gameOver();
        }
    }
    
    private void checkNextLevel(){
    	
    	
    	if (goalHeight< hero.getY()){
    		increaseLevel();
    		listener.nextLevel();
    	}
    	
    }


}