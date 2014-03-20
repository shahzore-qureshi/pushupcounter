package com.shaq.pushcounter;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Pushup extends Activity
{
	int count = 0;
	TextView title;
	TextView number;
	ImageButton button;
	SoundManager mSoundManager;
	Vibrator myVib;
	
	String animalPreference;

	static final String TAG = "ShaQ1nJ";
	
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pushup);
        
        myVib = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);
        mSoundManager = new SoundManager();
        mSoundManager.initSounds(getBaseContext());
        
        title = (TextView) findViewById(R.id.text);
        number = (TextView) findViewById(R.id.number);
        button = (ImageButton) findViewById(R.id.button);
        
        getPrefs();
        if (animalPreference.equals("1"))
        {
        	mSoundManager.addSound(1, R.raw.cat);
			title.setText("Cat Pushups:");
			button.setImageResource(R.drawable.catbutton);
        }
        else if (animalPreference.equals("2"))
		{
        	mSoundManager.addSound(1, R.raw.chicken);
			title.setText("Cock Pushups:");
			button.setImageResource(R.drawable.chickenbutton);
        }
        else if (animalPreference.equals("3"))
		{
        	mSoundManager.addSound(1, R.raw.goat);
			title.setText("Goat Pushups:");
			button.setImageResource(R.drawable.goatbutton);
        }
        else if (animalPreference.equals("4"))
		{
        	mSoundManager.addSound(1, R.raw.horse);
			title.setText("Horse Pushups:");
			button.setImageResource(R.drawable.horsebutton);
        }
        else if (animalPreference.equals("5"))
		{
        	mSoundManager.addSound(1, R.raw.pig);
			title.setText("Pig Pushups:");
			button.setImageResource(R.drawable.pigbutton);
        }
        else if (animalPreference.equals(null))
		{
        	mSoundManager.addSound(1, R.raw.chicken);
			title.setText("Cock Pushups:");
			button.setImageResource(R.drawable.chickenbutton);
        }
		
        
        button.setOnClickListener(new View.OnClickListener()
        {
			
			public void onClick(View v)
			{
				myVib.vibrate(250);
				count++;
				number.setText("" + count);
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
    
    private void getPrefs()
    {
    	SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        animalPreference = prefs.getString("animals", "2");
        Log.v(TAG, "Prefs: " + animalPreference);
    }
}