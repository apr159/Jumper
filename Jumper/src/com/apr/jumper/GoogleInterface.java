package com.apr.jumper;


import com.badlogic.gdx.utils.Array;

/**
 * GoogleInterface
 * ---------------------------------------
 * Define how to connect to the google
 * play services as the same for IAP
 * 
 */
public interface GoogleInterface {

/**
 * try to login to google play services and send the callback to the listener
 * @param listener the callback to execute if everything goes well
 */
public void Login(LoginListener listener);

/**
 * logout from google play services
 */
public void LogOut();

/**
 * check if client is signed in to Google+
 * @return true if signed
 */
public boolean getSignedIn();

/**
 * submit a score to a leaderboard
 * @param score the coins
 */
public void submitScore(int score);

/**
 * gets the scores and displays them using google default widget
 */
public void getScores();

/**
 * gets the achievements and displays them threw googles default widget
 */
public void getAchievements();


/**
 * gets the score and gives access to the raw score data
 */
public void getScoresData();

/**
 * unlock an achievement
 * @param ach the id of the achievement to unlock
 */
public void unlockAchievement(String ach);

public Array<PurchaseItem> getList(PurchaseListener listener, String[] list);
public void buy(String sku);

public boolean checkInternetConnection();

}