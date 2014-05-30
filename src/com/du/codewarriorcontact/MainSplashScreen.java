package com.du.codewarriorcontact;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.ProgressBar;

public class MainSplashScreen extends Activity {

	private ProgressBar progBar;
    private Handler mHandler = new Handler();;
    private int mProgressStatus = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_splash_screen);
		MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.beep);
		mp.start();
		progBar= (ProgressBar)findViewById(R.id.progBar1);
		doSomeWork();
		
// METHOD 1		
		
         /****** Create Thread that will sleep for 5 seconds *************/  		
		Thread background = new Thread() {
			public void run() {
				
				try {
					// Thread will sleep for 5 seconds
					sleep(5*1000);
					// After 5 seconds redirect to another intent
				    Intent i=new Intent(getBaseContext(),MainActivity.class);
					startActivity(i);
					//Remove activity
					finish();
					
				} catch (Exception e) {
				
				}
			}
		};
		
		// start thread
		background.start();
		
//METHOD 2	
		
		/*
		new Handler().postDelayed(new Runnable() {
			 
            // Using handler with postDelayed called runnable run method
 
            @Override
            public void run() {
                Intent i = new Intent(MainSplashScreen.this, FirstScreen.class);
                startActivity(i);
 
                // close this activity
                finish();
            }
        }, 5*1000); // wait for 5 seconds
		*/
	}
	
	private void doSomeWork() {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {
            public void run() {
                while (mProgressStatus < 100) {
                    mProgressStatus +=20;
                    // Update the progress bar
                    mHandler.post(new Runnable() {
                        public void run() {
                            progBar.setProgress(mProgressStatus);
                        }
                    });
                    try {
                        //Display progress slowly
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

	@Override
    protected void onDestroy() {
		
        super.onDestroy();
        
    }
}
