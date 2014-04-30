package com.apr.jumper;


import com.badlogic.gdx.utils.Array;

public class DesktopInterface implements GoogleInterface{

@Override
public void Login(LoginListener listener) {
System.out.println("Desktop: would of logged in here");
}

@Override
public void LogOut() {
System.out.println("Desktop: would of logged out here");
}

@Override
public boolean getSignedIn() {
System.out.println("Desktop: getSignIn()");
return false;
}

public void submitScore(int score){
System.out.println("Desktop: submitScore: " +score);
}

@Override
public void getScores() {
System.out.println("Desktop: getScores()");
}

@Override
public void getScoresData() {
System.out.println("Desktop: getScoresData()");
}

@Override
public void unlockAchievement(String ach) {
	// TODO Auto-generated method stub
	
}

@Override
public void getAchievements() {
	// TODO Auto-generated method stub
	
}

@Override
public void buy(String sku) {
	// TODO Auto-generated method stub
	
}


@Override
public Array<PurchaseItem> getList(PurchaseListener listener, String[] list) {
	// TODO Auto-generated method stub
	return new Array<PurchaseItem>();
}

@Override
public boolean checkInternetConnection() {
	// TODO Auto-generated method stub
	return false;
}


}