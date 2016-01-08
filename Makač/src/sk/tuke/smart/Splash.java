package sk.tuke.smart;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

public class Splash extends Activity {

	MediaPlayer ourSong;

	@Override
	protected void onCreate(Bundle TravisLoveBacon) {
		super.onCreate(TravisLoveBacon);
		setContentView(R.layout.activity_splash);
		ourSong = MediaPlayer.create(Splash.this, R.raw.nekozilla);
		ourSong.start();
		Thread timer = new Thread(){
			public void run(){
				try{
					sleep(5000);	
				}catch(InterruptedException e){
					e.printStackTrace();
				}finally{
					Intent openStartingPoint = new Intent("sk.tuke.smart.MENU");
					startActivity(openStartingPoint);
				}
			}
		};
		timer.start();
	}

	@Override
	protected void onPause() {
		super.onPause();
		ourSong.release();
		finish();
	}
}
