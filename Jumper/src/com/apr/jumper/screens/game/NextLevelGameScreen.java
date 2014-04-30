package com.apr.jumper.screens.game;

import com.apr.jumper.JumperGame;
import com.apr.jumper.screens.AbstractGameScreen;
import com.apr.jumper.screens.StartGameScreen;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class NextLevelGameScreen extends AbstractGameScreen{

	public NextLevelGameScreen(JumperGame game) {
		super(game);
		
	}
	
	@Override
	public void show(){
		super.show();
		
		
		
		
        Table table = super.getTable();
        
        // register the button "start game"
        final Label info = new Label( "You passed to the next level", getSkin() );
        
        table.add( info ).size( 480, 50 ).uniform();
        info.setPosition(-480,500);
        
        info.addAction(Actions.sequence(Actions.moveTo(300,500, 1.0f),
        		 Actions.delay(1.0f),
        		 Actions.run(new Runnable(){
        			 public void run(){
     	                game.setScreen( new StartGameScreen( game ) );

        			 }
        		 })));

	}
	
	public void render(float delta){
		super.render(delta);
		world.update(delta);
		
	}

}
