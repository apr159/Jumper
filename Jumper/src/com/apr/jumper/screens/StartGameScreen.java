package com.apr.jumper.screens;




import com.apr.jumper.JumperGame;
import com.apr.jumper.game.World.WorldListener;
import com.apr.jumper.screens.game.FinishLevelGameScreen;
import com.apr.jumper.screens.game.GameOverGameScreen;
import com.apr.jumper.screens.game.PauseGameScreen;
import com.apr.jumper.services.JumperSound;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class StartGameScreen extends AbstractGameScreen implements WorldListener {
  

    
	
	public StartGameScreen(
		    JumperGame game )
		{
		
		    super( game );
		   

	        

		}
	
		@Override
		public void show()
		{
			super.show();
		    InputMultiplexer multiplexer = new InputMultiplexer();
		    multiplexer.addProcessor(stage);
		    multiplexer.addProcessor(new MyInputProcessor());
		    Gdx.input.setInputProcessor(multiplexer);		
		    
	        Table table = super.getTable();
	        
	        // register the button "start game"
	        final TextButton startGameButton = new TextButton( "Press here \n to start", getSkin() );
	        startGameButton.setColor(getSkin().getColor("green"));

	        startGameButton.addListener( new ClickListener() {
	            @Override
	            public void clicked (InputEvent event, float x, float y)
	            {
	            	startGameButton.addAction(Actions.sequence(Actions.moveTo(480,300,0.5f), 
	            											   Actions.run(new Runnable(){
											            		public void run(){
											                        world.started = true;
											            			
											            		}
	            											   })));
	            			
	            			
	            }
	        } );
	        table.add( startGameButton ).size( 460, 200 ).expand().fill();
	        table.row();
	        startGameButton.setPosition(-500,300);
	        startGameButton.addAction(Actions.moveTo(10,300,0.5f));
	        
	        table.row();
	        TextButton pauseButton = new TextButton( "Pause", getSkin() );
	        pauseButton.setColor(getSkin().getColor("blue"));
	        pauseButton.addListener( new ClickListener() {
	            @Override
	            public void clicked (InputEvent event, float x, float y)
	            {
	                game.setScreen( new PauseGameScreen( game ) );
	            }
	        } );

	        table.add( pauseButton ).size(150,50).bottom().right();

			world.setWorldListnener(this);
			world.generateLevel();

		}
		
		
		@Override
		public void render(float delta){
			stage.act();
	        Gdx.gl.glClearColor( world.color.r, world.color.g, world.color.b, 1f );
	        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );

	        world.update(delta);
		    
		    renderer.render(delta);
		    stage.draw();
	        Table.drawDebug(stage);

		    
		    
		    
		    
		}
		

		class MyInputProcessor implements InputProcessor{
			@Override
			public boolean keyDown(int keycode) {
				if (keycode == Keys.LEFT){
					world.hero.acceleration=2.0f;
				}
				else if (keycode == Keys.RIGHT){
					world.hero.acceleration=-2.0f;
				}
				return true;
			}

			@Override
			public boolean keyUp(int keycode) {
				world.hero.acceleration=0f;

				return true;
			}

			@Override
			public boolean keyTyped(char character) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean touchDown(int screenX, int screenY, int pointer,
					int button) {

				return false;
			}

			@Override
			public boolean touchUp(int screenX, int screenY, int pointer,
					int button) {
				
				
				return false;
			}

			@Override
			public boolean touchDragged(int screenX, int screenY,
					int pointer) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean mouseMoved(int screenX, int screenY) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean scrolled(int amount) {
				// TODO Auto-generated method stub
				return false;
			}
		}

		@Override
		public void gameOver() {
			world.started = false;
            game.setScreen( new GameOverGameScreen( game ) );
			
		}
		
		@Override
		public void nextLevel() {
			
		}

		@Override
		public void finishLevel() {
            game.setScreen( new FinishLevelGameScreen( game ) );
			
		}

		@Override
		public void jump() {
			
			game.getSoundManager().play(JumperSound.JUMP);
		}

		@Override
		public void hit() {
			game.getSoundManager().play(JumperSound.HIT);
			
		}

		@Override
		public void destroy() {
			game.getSoundManager().play(JumperSound.DESTROY);
			
		}

		
}