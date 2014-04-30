package com.apr.jumper.screens;

import com.apr.jumper.JumperGame;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MenuScreen extends AbstractScreen {

	    public MenuScreen(
	        JumperGame game )
	    {
	        super( game );
	    }

	    @Override
	    public void show()
	    {
	        super.show();

	        // retrieve the default table actor
	        Table table = super.getTable();
	        
	    	 // load the texture with the splash image
	     //   Texture back = new Texture( "back.jpg" );
	        
	        Image image = new Image(game.getAssetManager().get("game.atlas",TextureAtlas.class).findRegion("title"));

	        table.add(image).expandY().top();
	        table.row();
	        
	        // set the linear texture filter to improve the image stretching
	       // back.setFilter( TextureFilter.Linear, TextureFilter.Linear );
	    	// TextureRegion backRegion = new TextureRegion( back, 0,224, 480, 1024 );

	       // table.setBackground(new TextureRegionDrawable(backRegion));

	        // register the button "start game"
	        //game.world.levelMode = false;
	        TextButton startGameButton = new TextButton( "Start", getSkin() );
	        startGameButton.setColor(getSkin().getColor("green"));
	        startGameButton.addListener( new ClickListener() {
	            @Override
	            public void clicked (InputEvent event, float x, float y)
	            {
	                game.setScreen( new StartGameScreen( game ) );
	            }
	        } );
	        table.add( startGameButton ).size( 350, 80 ).uniform().top();
	        table.row();


	        
	        // register the button "options"
	        TextButton optionsButton = new TextButton( "Options", getSkin() );
	        optionsButton.setColor(getSkin().getColor("green"));

	        optionsButton.addListener( new ClickListener() {
	            @Override
	            public void clicked (InputEvent event, float x, float y)
	            {
	                game.setScreen( new OptionsScreen( game ) );
	            }
	        } );
	        table.add( optionsButton ).size( 350, 80 ).uniform();
	        table.row();

	        // register the button "high scores"
	        TextButton highScoresButton = new TextButton( "Best", getSkin() );
	        highScoresButton.setColor(getSkin().getColor("yellow"));
	        highScoresButton.addListener( new ClickListener() {
	            @Override
	            public void clicked (InputEvent event, float x, float y)
	            {
	                game.setScreen( new HighScoreScreen( game ) );
	            }
	        } );
	        table.add( highScoresButton ).size(350,80).uniform().padBottom(200);
	    }
}
