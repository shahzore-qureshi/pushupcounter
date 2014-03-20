package com.shaq.pushcounter;

import com.scoreninja.adapter.ScoreNinjaAdapter;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainMenu extends Activity
{
	Button startButton;
	Button startTimer;
	Button preferencesButton;
	Button scoresButton;
	String animalPreference;
	private static final String TAG = "ShaQ1nJ";
	String MY_AD_UNIT_ID = "a14dcd6b3e8b0ff";
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu);
        getPrefs();
        
        /*AdView adView = new AdView(this, AdSize.BANNER, MY_AD_UNIT_ID);
        RelativeLayout layout = (RelativeLayout)findViewById(R.id.mainmenulayout);
        layout.addView(adView);
        
        AdRequest ad = new AdRequest();
        ad.addTestDevice(AdRequest.TEST_EMULATOR);
        
        adView.loadAd(ad);*/
        
        /*AdView adView = (AdView)this.findViewById(R.id.adView);
        AdRequest ad = new AdRequest();
        ad.addTestDevice(AdRequest.TEST_EMULATOR);
        adView.loadAd(ad);*/
        
        startButton = (Button) findViewById(R.id.startbutton);
        startTimer = (Button) findViewById(R.id.startTimer);
        preferencesButton = (Button) findViewById(R.id.preferences);
        scoresButton = (Button) findViewById(R.id.scores);
        
        startButton.setOnClickListener(new View.OnClickListener()
        {
			
			public void onClick(View v)
			{
				Intent myIntent = new Intent(v.getContext(), Pushup.class);
                startActivity(myIntent);
			}
		});
        
        startTimer.setOnClickListener(new View.OnClickListener()
        {
			
			public void onClick(View v)
			{
				Intent myIntent = new Intent(v.getContext(), Timer.class);
                startActivity(myIntent);
			}
		});
        
        preferencesButton.setOnClickListener(new View.OnClickListener()
        {
			
			public void onClick(View v)
			{
				Intent myIntent = new Intent(v.getContext(), Preferences.class);
                startActivity(myIntent);
			}
		});
        
        scoresButton.setOnClickListener(new View.OnClickListener()
        {
			
			public void onClick(View v)
			{
				ScoreNinjaAdapter scoreNinjaAdapter = new ScoreNinjaAdapter (v.getContext(), "cockpushupsv2", "2EB906E81DC1E654FF59337CAA75EE52");
				scoreNinjaAdapter.show(-1);
			}
		});
	}
	
	public void onStart() 
    {
		super.onStart();
    	getPrefs();
    }
    
	public void onResume()
	{
		super.onResume();
		getPrefs();
	}
    private void getPrefs()
    {
    	SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        animalPreference = prefs.getString("animals", "2");
        Log.v(TAG, "Prefs: " + animalPreference);
    }
}
