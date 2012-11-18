package com.asynkronos.android.sensordisplay;

import com.asynkronos.android.sensordisplay.R;

import android.os.Bundle;

/**
 * 
 * @author Michael Bruno
 *
 */
public class TemperatureDataSaved extends SensorDashSavedActivity{
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.four_saved);
        
        sensorId = SensorType.TEMPERATURE;
        init();

        if(supported()){
        	createViews(R.string.temperature,R.string.temp_lb,
                null,null, null);
        }else{
        	createNoSupport(R.string.temperature);
        }

    }
    
    @Override 
    public void onResume(){
    	super.onResume();
        if(supported()){
        	createViews(R.string.temperature,R.string.temp_lb,
                null,null, null);
        }else{
        	createNoSupport(R.string.temperature);
        }

    }
	
}