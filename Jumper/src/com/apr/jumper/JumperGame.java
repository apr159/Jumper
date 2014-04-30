package com.apr.jumper;

import com.apr.jumper.game.World;
import com.apr.jumper.game.WorldRenderer;
import com.apr.jumper.screens.SplashScreen;
import com.apr.jumper.services.MusicManager;
import com.apr.jumper.services.PreferencesManager;
import com.apr.jumper.services.SoundManager;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;


public class JumperGame extends Game {

	/**
	 * Usado para logs
	 */
	public static final String LOG = JumperGame.class.getSimpleName();

	
	/**
	 * Bandera para ejecutar en modo de prueba
	 */
	public static final boolean DEV_MODE = false;
	
	/**
	 * Factores para manejar multiresolucion
	 */
	public float factorX, factorY;

	/**
	 * Referencia al modelo del juego
	 */
	public World world;
	
	/**
	 * Referencia a la vista del juego
	 */
	public WorldRenderer renderer;
	

    
    /**
     * Administrador de los recursos
     */
    private AssetManager manager = new AssetManager();
    
    
    private PreferencesManager preferencesManager;
    
    private SoundManager soundManager;
    
    private MusicManager musicManager;
    
    /**
     * Constructor
     */
    public JumperGame(){}
    


    public AssetManager getAssetManager(){
    	return manager;
    }
    
    /**
     * Get the preference service
     * @return the preference manager object
     */
    public PreferencesManager getPreferencesManager()
    {
        return preferencesManager;
    }

    /**
     * Get the music service
     * @return the music manager object
     */
    public MusicManager getMusicManager()
    {
        return musicManager;
    }


    /**
     * Get the sound service
     * @return the sound manager object
     */
    public SoundManager getSoundManager()
    {
        return soundManager;
    }
    

    @Override
    public void create()
    {
    	Gdx.app.log( JumperGame.LOG, "Creando juego en " + Gdx.app.getType() );
    	
        // 1. create the preferences manager
        preferencesManager = new PreferencesManager();
        
        // 2. create the music manager
        musicManager = new MusicManager();
        musicManager.setVolume( preferencesManager.getVolume() );
        musicManager.setEnabled( preferencesManager.isMusicEnabled() );

        // 3. create the sound manager
        soundManager = new SoundManager(manager);
        soundManager.setVolume( preferencesManager.getVolume() );
        soundManager.setEnabled( preferencesManager.isSoundEnabled() );


    }

    /**
     * Crea un nuevo modelo y vista
     */
    public void loadWorld(){
	    world = new World();
	    renderer = new WorldRenderer(world, manager);

    }
    
    @Override
    public void resize(
        int width,
        int height )
    {
    	super.resize( width, height );
        Gdx.app.log( JumperGame.LOG, "Cambiando resolucion a : " + width + " x " + height );

        // 1. Adjust factors
        factorX = 800f/(float)(width);
    	factorY = 480f/(float)(height);

        
        // 2. First load the splash screen
        if( getScreen() == null ) {
        	
            setScreen( new SplashScreen( this ) );
        }
   }

    @Override
    public void render()
    {
    	 super.render();
    }

    @Override
    public void pause()
    {
    	super.pause();
        Gdx.app.log( JumperGame.LOG, "Pausa" );
    }

    @Override
    public void resume()
    {
    	super.resume();
        Gdx.app.log( JumperGame.LOG, "Retornar" );
    }
    
    @Override
    public void setScreen(
        Screen screen )
    {
        super.setScreen( screen );
        Gdx.app.log( JumperGame.LOG, "Moviendo a pantalla: " + screen.getClass().getSimpleName() );
    }


    @Override
    public void dispose()
    {
    	super.dispose();
        Gdx.app.log( JumperGame.LOG, "Salir del juego" );
    }
}
