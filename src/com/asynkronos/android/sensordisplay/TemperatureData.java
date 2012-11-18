package com.asynkronos.android.sensordisplay;

import com.asynkronos.android.sensordisplay.R;

import android.os.Bundle;

/**
 * 
 * @author Michael Bruno
 *
 */
public class TemperatureData extends SensorDashActivity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.four);
        
        sensorId = SensorType.TEMPERATURE;
        init();
        
        if(supported()){

        	createViews(R.string.temperature,R.string.temp_lb,
                null,null, null);
        
			createSlider(true);
			createSaveButton(true);
        }else{
        	createNoSupport(R.string.temperature);
        }
        
    }
	
}