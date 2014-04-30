package com.apr.jumper.screens.game;

import com.apr.jumper.JumperGame;
import com.apr.jumper.screens.AbstractGameScreen;
import com.apr.jumper.screens.MenuScreen;
import com.apr.jumper.screens.StartGameScreen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class GameOverGameScreen extends AbstractGameScreen{

	public GameOverGameScreen(JumperGame game) {
		super(game);
		
	}
	
	@Override
	public void show(){
		super.show();
		
        Table table = super.getTable();
        
        Label label = new Label("Game Over",getSkin());
        label.setColor(getSkin().getColor("red"));
        table.add(label);
        table.row();
        
        final TextButton resumeGameButton = new TextButton( "Restart", getSkin() );
        resumeGameButton.setColor(getSkin().getColor("green"));
        final TextButton quitGameButton = new TextButton( "Quit", getSkin() );
        quitGameButton.setColor(getSkin().getColor("yellow"));
        resumeGameButton.addListener( new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y)
            {
            	resumeGameButton.addAction(Actions.moveTo(800,350,0.5f));
            	
            	quitGameButton.addAction(Actions.sequence(Actions.moveTo(-480,250,0.5f), 
						   Actions.run(new Runnable(){
		            		public void run(){
		            			world.generateLevel();
		                    	renderer.setCamera();
		                        game.setScreen( new StartGameScreen( game ) );
		            		}
						   })));

            }
        } );
        table.add( resumeGameButton ).size( 300, 80 ).uniform();
        table.row();

        
        quitGameButton.addListener( new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y)
            {
            	resumeGameButton.addAction(Actions.moveTo(800,350,0.5f));
            	
            	quitGameButton.addAction(Actions.sequence(Actions.moveTo(-480,250,0.5f), 
						   Actions.run(new Runnable(){
		            		public void run(){
		                    	world.generateLevel();
		                    	renderer.setCamera();

		                        game.setScreen( new MenuScreen( game ) );
		            		}
						   })));

            }
        } );
        table.add( quitGameButton ).size( 300, 80 ).uniform();

        resumeGameButton.setPosition(-300,350);
        resumeGameButton.addAction(Actions.moveTo(90,350,0.5f));
        quitGameButton.setPosition(-300,250);
        quitGameButton.addAction(Actions.moveTo(90,250,0.5f));

	}

}
