package com.apr.jumper.screens.game;

import com.apr.jumper.JumperGame;
import com.apr.jumper.screens.AbstractGameScreen;
import com.apr.jumper.screens.MenuScreen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class FinishLevelGameScreen extends AbstractGameScreen{

	public FinishLevelGameScreen(JumperGame game) {
		super(game);
		
	}
	
	@Override
	public void show(){
		super.show();
		
        Table table = super.getTable();
        
        // register the button "start game"
        
        table.add("Congratulations, level finished");
        table.row();
        
        final TextButton quitGameButton = new TextButton( "Back", getSkin() );


        
        quitGameButton.addListener( new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y)
            {
            	
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

        quitGameButton.setPosition(800,600);
        quitGameButton.addAction(Actions.moveTo(150,600,0.5f));

	}

}
