package com.apr.jumper.services;

/**
 * The available sound files.
 */
public enum JumperSound
{
    JUMP( "sounds/jump.mp3" ),
    HIT( "sounds/hit.mp3" ),
    DESTROY( "sounds/destroy.mp3" );
    

    private final String fileName;

    private JumperSound(
        String fileName )
    {
        this.fileName = fileName;
    }

    public String getFileName()
    {
        return fileName;
    }
}