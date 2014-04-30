package com.apr.jumper.domain;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.OrderedMap;

public class Profile
implements
    Serializable
{
private int currentLevelId;
private int credits;
private int points;
private Map<Integer,Integer> highScores;

public Profile()
{
    highScores = new HashMap<Integer,Integer>();
}

/**
 * Retrieves the ID of the next playable level.
 */
public int getCurrentLevelId()
{
    return currentLevelId;
}

/**
 * Retrieves the high scores for each level (Level-ID -> High score).
 */
public Map<Integer,Integer> getHighScores()
{
    return highScores;
}

/**
 * Gets the current high score for the given level.
 */
public int getHighScore(
    int levelId )
{
    if( highScores == null ) return 0;
    Integer highScore = highScores.get( levelId );
    return ( highScore == null ? 0 : highScore );
}

/**
 * Notifies the score on the given level. Returns <code>true</code> if its a
 * high score.
 */
public boolean notifyScore(
    int levelId,
    int score )
{
    if( score > getHighScore( levelId ) ) {
        highScores.put( levelId, score );
        return true;
    }
    return false;
}

/**
 * Retrieves the amount of credits the player has.
 */
public int getCredits()
{
    return credits;
}

public int getPoints()
{
	return points;
}



// Serializable implementation


@Override
public void write(
    Json json )
{
    json.writeValue( "currentLevelId", currentLevelId );
    json.writeValue( "credits", credits );
    json.writeValue( "highScores", highScores );
    json.writeValue("points", points);
}

@Override
public void read(Json json, JsonValue jsonData) {
    currentLevelId = json.readValue( "currentLevelId", Integer.class, jsonData );
    credits = json.readValue( "credits", Integer.class, jsonData );

    // libgdx handles the keys of JSON formatted HashMaps as Strings, but we
    // want it to be an integer instead (levelId)
    Map<String,Integer> highScores = json.readValue( "highScores", HashMap.class,
        Integer.class, jsonData );
    for( String levelIdAsString : highScores.keySet() ) {
        int levelId = Integer.valueOf( levelIdAsString );
        Integer highScore = highScores.get( levelIdAsString );
        this.highScores.put( levelId, highScore );
    }

	
}
}