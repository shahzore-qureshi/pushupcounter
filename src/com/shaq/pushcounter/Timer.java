package com.shaq.pushcounter;

import com.scoreninja.adapter.ScoreNinjaAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Timer extends Activity
{
	int count = 0;
	TextView timeLeft;
	TextView totalPushups;
	ImageButton button;
	SoundManager mSoundManager;
	Vibrator myVib;
	ScoreNinjaAdapter scoreNinjaAdapter;
	String animalPreference;
	static final String TAG = "ShaQ1nJ";
	CountDownTimer timer;
	Context context;
	
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer);
        
        context = this;
        myVib = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);
        mSoundManager = new SoundManager();
        mSoundManager.initSounds(this);

        timeLeft = (TextView) findViewById(R.id.timeLeft);
        totalPushups = (TextView) findViewById(R.id.totalPushups);
        button = (ImageButton) findViewById(R.id.buttonTimer);
        
        getPrefs();
        if (animalPreference.equals("1"))
        {
        	mSoundManager.addSound(1, R.raw.cat);
			button.setImageResource(R.drawable.catbutton);
        }
		if (animalPreference.equals("2"))
		{
        	mSoundManager.addSound(1, R.raw.chicken);
			button.setImageResource(R.drawable.chickenbutton);
        }
		if (animalPreference.equals("3"))
		{
        	mSoundManager.addSound(1, R.raw.goat);
			button.setImageResource(R.drawable.goatbutton);
        }
		if (animalPreference.equals("4"))
		{
        	mSoundManager.addSound(1, R.raw.horse);
			button.setImageResource(R.drawable.horsebutton);
        }
		if (animalPreference.equals("5"))
		{
        	mSoundManager.addSound(1, R.raw.pig);
			button.setImageResource(R.drawable.pigbutton);
        }
		if (animalPreference.equals(null))
		{
        	mSoundManager.addSound(1, R.raw.chicken);
			button.setImageResource(R.drawable.chickenbutton);
        }
		
		
        countdownTimer();
        
        button.setOnClickListener(new View.OnClickListener()
        {
			
			public void onClick(View v)
			{
				myVib.vibrate(250);
				count++;
				totalPushups.setText("" + count);
				mSoundManager.playSound(1);
			}
		});
    }
    
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.popupmenu, menu);
        return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
        case R.id.goback:
        	/*Intent myIntent = new Intent(getBaseContext(), MainMenu.class);
        	startActivity(myIntent);*/
            finish();
            return true;
     //   case R.id.help:
     //       showHelp();
     //       return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
    
    public void countdownTimer()
    {
    	timer = new CountDownTimer(16000, 1000) {

    		public void onTick(long millisUntilFinished) {
    	    	timeLeft.setText("" + millisUntilFinished / 1000);
    	     }

    		public void onFinish() {
    			ImageButton button = (ImageButton) findViewById(R.id.buttonTimer);
    			button.setEnabled(false);
    			timeLeft.setText("TIME'S UP");
    			
    			Thread pauseThread =  new Thread(){
    				public void run(){
    					try {
    						synchronized(this){
    							// Wait given period of time or exit on touch
    							Log.v(TAG, "WAITING START");
    							wait(2500);
    							int sum = 2 * 5 * 2;
    							Log.v(TAG, "Sum: " + sum);
    							scoreNinjaAdapter = new ScoreNinjaAdapter(context, "cockpushupsv2", "2EB906E81DC1E654FF59337CAA75EE52");
    							Log.v(TAG, "STARTING NINJA");
    							scoreNinjaAdapter.show(count);
    							Log.v(TAG, "ENDING NINJA");


    						}
    					}
    					catch(Exception ex){  
    						Log.e(TAG, "PAUSING ERROR", ex);
    					}
    				}
    			};
    			pauseThread.start();

    		}
    	};

    	timer.start();
    }
    
    protected void onActivityResult(int requestCode, int resultCode, Intent data) 
    {
		super.onActivityResult(requestCode, resultCode, data);
		scoreNinjaAdapter.onActivityResult(requestCode, resultCode, data);
		finish();
	}
    
    /*public void onBackPressed() {
        timer.cancel();
        Log.v(TAG, "Back button pressed. Activity canceled.");
        finish();
    }*/
    
    public void onPause() {		//DON'T TOUCH
    	super.onPause();
    }
    
    public void onDestroy() {
    	super.onDestroy();
        timer.cancel();
        Log.v(TAG, "Destroyed. Activity canceled.");
        finish();
    }
    
    private void getPrefs()
    {
    	SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        animalPreference = prefs.getString("animals", "2");
        Log.v(TAG, "Prefs: " + animalPreference);
    }

}
