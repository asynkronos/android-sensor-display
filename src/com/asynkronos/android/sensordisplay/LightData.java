package com.asynkronos.android.sensordisplay;

import com.asynkronos.android.sensordisplay.R;

import android.os.Bundle;

/**
 * 
 * @author Michael Bruno
 *
 */
public class LightData extends SensorDashActivity {
    /** Called when the activity is first created. */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.four);
        
        sensorId = SensorType.LIGHT;
        init();
        
        if(supported()){

            createViews(R.string.light,R.string.light_level_lb,
                    null,null, null);
                    
            createSlider(true);
    		createSaveButton(true);
        }else{
        	createNoSupport(R.string.light);
        }

    }
	
}