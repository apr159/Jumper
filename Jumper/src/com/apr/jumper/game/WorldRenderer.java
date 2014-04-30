package com.apr.jumper.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.utils.Array;

public class WorldRenderer {
    static final float FRUSTUM_WIDTH = 4.8f;
    static final float FRUSTUM_HEIGHT = 8.0f;    
    World world;
    OrthographicCamera  cam;
    TextureAtlas atlas;
    SpriteBatch batch;    
    BitmapFont font,fontBig;
    boolean doneIncreased;
    Animation explodeAnimation;
    
    
    public WorldRenderer(World world, AssetManager manager) {
        this.world = world;
        this.cam = new OrthographicCamera(480, 800);
        this.cam.position.set(240, 400,0f);
        this.cam.update();
        
        atlas = new TextureAtlas("game.atlas");
        batch = new SpriteBatch();
        batch.setProjectionMatrix(cam.combined);

        
        font = new BitmapFont(Gdx.files.internal("default-16.fnt"),false);
        font.setColor(Color.BLACK);
        fontBig = new BitmapFont(Gdx.files.internal("default-64.fnt"),false);
        

        Array<TextureRegion> frames3 = new Array<TextureRegion>();
        frames3.add(atlas.findRegion("platform-exploding-1"));
        frames3.add(atlas.findRegion("platform-exploding-2"));
        frames3.add(atlas.findRegion("platform-exploding-3"));
        frames3.add(atlas.findRegion("platform-exploding-4"));        
        explodeAnimation = new Animation(0.2f, frames3);

        

    }
    
    public void setCamera(){
    	this.cam.position.set(240, 400,0f);
        this.cam.update();
        batch.setProjectionMatrix(cam.combined);

    }
    
    public void render(float delta) {

    	
    	
        if(world.hero.getY()*100 > cam.position.y ){
        	
        	
            cam.position.set(240,world.hero.getY()*100,0f);
            this.cam.update();
            this.world.baseHeight = cam.position.y/100.0f-4.0f;
            batch.setProjectionMatrix(cam.combined);

        }
        renderObjects(delta);        
        
    }
  
    
    
    public void renderObjects(float delta) {
       
        batch.begin();
                   

        	for (Platform platform: world.platforms){
        		if (platform.type == Platform.PLATFORM_TYPE_STATIC)
        			batch.draw(atlas.findRegion("platform-normal"), platform.getX()*100, platform.getY()*100);
        		else if (platform.type == Platform.PLATFORM_TYPE_MOVING)
        			batch.draw(atlas.findRegion("platform-moving"), platform.getX()*100, platform.getY()*100);
        		else{
        			if (platform.state==Platform.PLATFORM_STATE_PULVERIZING){
        				batch.draw(explodeAnimation.getKeyFrame(platform.stateTime), platform.getX()*100, platform.getY()*100);

        			}else
        				batch.draw(atlas.findRegion("platform-exploding-1"), platform.getX()*100, platform.getY()*100);
        			
        		}

        	}
        	
        	for (Enemy enemy: world.enemies){
        		batch.setColor(enemy.color);
        		batch.draw(atlas.findRegion("ball"), enemy.getX()*100, enemy.getY()*100);
        		font.drawWrapped(batch, enemy.strValue,enemy.getX()*100, enemy.getY()*100+20, 30, HAlignment.CENTER);
        	}
        	batch.setColor(Color.WHITE);
        	if (world.hero.state==Hero.STATE_BASE)
        		batch.draw(atlas.findRegion("hero-normal"), world.hero.getX()*100, world.hero.getY()*100);
        	else if (world.hero.state==Hero.STATE_JUMP)
        		batch.draw(atlas.findRegion("hero-jump"), world.hero.getX()*100, world.hero.getY()*100);
        	else if (world.hero.state==Hero.STATE_HIT)
        		batch.draw(atlas.findRegion("hero-hit"), world.hero.getX()*100, world.hero.getY()*100);
        	else
        		batch.draw(atlas.findRegion("hero-normal"), world.hero.getX()*100, world.hero.getY()*100);
        	font.drawWrapped(batch, "Operation",0, 90+cam.position.y-400, 480, HAlignment.CENTER);
        
        	fontBig.drawWrapped(batch, world.operation.toString(),0, 70+cam.position.y-400, 480, HAlignment.CENTER);
        	batch.end();

        

    }
}
