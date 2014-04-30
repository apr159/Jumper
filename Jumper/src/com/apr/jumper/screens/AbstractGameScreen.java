package com.apr.jumper.screens;

import com.apr.jumper.JumperGame;
import com.apr.jumper.game.World;
import com.apr.jumper.game.WorldRenderer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * La base para todas las pantallas de juego
 * @author apasos
 *
 */
public class AbstractGameScreen extends AbstractScreen{

	protected World world;
	protected WorldRenderer renderer;
	
	
	public AbstractGameScreen(JumperGame game) {
		super(game);
		
	    world = game.world;
	    renderer = game.renderer;

	}

	public void show(){
		super.show();
	    
	}
	
	@Override
	public void render(float delta){
        stage.act( delta );

        Gdx.gl.glClearColor( world.color.r, world.color.g, world.color.b, 1f );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );

        // update and draw the stage actors
        
        if (world!=null) {
            renderer.render(delta);
            world.update(delta);
        }
        stage.draw();
        Table.drawDebug(stage);
    


	}

	

}
