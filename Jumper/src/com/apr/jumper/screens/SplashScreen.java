package com.apr.jumper.screens;

import com.apr.jumper.JumperGame;
import com.apr.jumper.services.JumperSound;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;


/**
 * Splash screen to load all assets
 * @author apasos
 *
 */
public class SplashScreen extends AbstractScreen {

    public SplashScreen(JumperGame game){
        super( game );
    }

    @Override
    public void show()
    {
    	super.show();
        // 1. retrieve the default table actor
        Table table = super.getTable();
        Texture back = new Texture( "back.jpg" );
        back.setFilter( TextureFilter.Linear, TextureFilter.Linear );
    	TextureRegion backRegion = new TextureRegion( back, 0, 224, 480, 1024 );
        table.setBackground(new TextureRegionDrawable(backRegion));
        
        Label label = new Label("Loading...",getSkin(),"title");
        table.add(label);
        
        //2. List of stuff to load
	    game.getAssetManager().load("game.atlas", com.badlogic.gdx.graphics.g2d.TextureAtlas.class);
	   	game.getAssetManager().load("default-64.fnt", BitmapFont.class);
	    game.getAssetManager().load("default-32.fnt", BitmapFont.class);
	    game.getAssetManager().load("default-16.fnt", BitmapFont.class);

	    game.getAssetManager().load(JumperSound.JUMP.getFileName(), Sound.class);
	    game.getAssetManager().load(JumperSound.HIT.getFileName(), Sound.class);
	    game.getAssetManager().load(JumperSound.DESTROY.getFileName(), Sound.class);

	    super.resize(800,480);
    }

    @Override
   public void render(float delta){
	   super.render(delta);
	   //If finish loading go to start screen
	   if (game.getAssetManager().update()){
		   stage.addAction( Actions.sequence( Actions.delay( 0.25f ), 
				   							  Actions.fadeOut( 0.75f ),
									            new Action() {
									                @Override
									                public boolean act(
									                    float delta )
									                {
									                	game.loadWorld();
									                	game.setScreen(new MenuScreen(game));
									                    return true;
									                }
									            } ) );	   
	   }
	   
   }
  

    @Override
    public void dispose()
    {
        super.dispose();
    }
}
