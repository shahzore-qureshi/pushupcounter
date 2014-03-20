package com.shaq.pushcounter;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class Preferences extends PreferenceActivity {

    /*private RadioButton cat;
    private RadioButton chicken;
    private RadioButton goat;
    private RadioButton horse;
    private RadioButton pig;
    private RadioGroup group;*/

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        
        /*group.setOnCheckedChangeListener (new OnCheckedChangeListener()
        {
			@Override
			public void onCheckedChanged(RadioGroup grp, int button)
			{
				switch (button)
				{
				case R.id.catRadio: //Cat
					MainMenu.chosenPushup = CatPushup.class;
					//cat.setChecked(true);
				case R.id.chickenRadio: //Chicken
					MainMenu.chosenPushup = ChickenPushup.class;
					//chicken.setChecked(true);
				case R.id.goatRadio: //Goat
					MainMenu.chosenPushup = GoatPushup.class;
					//goat.setChecked(true);
				case R.id.horseRadio: //Horse
					MainMenu.chosenPushup = HorsePushup.class;
					//horse.setChecked(true);
				case R.id.pigRadio: //Pig
					MainMenu.chosenPushup = PigPushup.class;
					//pig.setChecked(true);
				}
			}
		});*/
    }

    
}
