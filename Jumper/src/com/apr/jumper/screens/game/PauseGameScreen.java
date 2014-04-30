package com.apr.jumper.screens.game;

import com.apr.jumper.JumperGame;
import com.apr.jumper.screens.AbstractGameScreen;
import com.apr.jumper.screens.MenuScreen;
import com.apr.jumper.screens.StartGameScreen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class PauseGameScreen extends AbstractGameScreen{

	public PauseGameScreen(JumperGame game) {
		super(game);
		
	}
	
	@Override
	public void show(){
		super.show();
		
        Table table = super.getTable();
        
        // register the button "start game"
        
        table.add("PAUSE");
        table.row();
        
        final TextButton resumeGameButton = new TextButton( "Resume", getSkin() );
        final TextButton quitGameButton = new TextButton( "Quit", getSkin() );

        resumeGameButton.addListener( new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y)
            {
            	resumeGameButton.addAction(Actions.moveTo(800,500,0.5f));
            	
            	quitGameButton.addAction(Actions.sequence(Actions.moveTo(-480,600,0.5f), 
						   Actions.run(new Runnable(){
		            		public void run(){
		            			
		            			  game.setScreen( new StartGameScreen( game ) );		            			
		            		}
						   })));

              
                
            }
        } );
        table.add( resumeGameButton ).size( 500, 80 ).uniform();
        table.row();

        
        quitGameButton.addListener( new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y)
            {
            	resumeGameButton.addAction(Actions.moveTo(800,500,0.5f));
            	
            	quitGameButton.addAction(Actions.sequence(Actions.moveTo(-480,600,0.5f), 
						   Actions.run(new Runnable(){
		            		public void run(){
		                    	world.generateLevel();
		                    	renderer.setCamera();
		                        game.setScreen( new MenuScreen( game ) );
		            		}
						   })));

            }
        } );
        table.add( quitGameButton ).size( 500, 80 ).uniform();
        resumeGameButton.setPosition(-480,500);
        resumeGameButton.addAction(Actions.moveTo(150,500,0.5f));
        quitGameButton.setPosition(800,600);
        quitGameButton.addAction(Actions.moveTo(150,600,0.5f));


	}

}
