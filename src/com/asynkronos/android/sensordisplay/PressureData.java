package com.asynkronos.android.sensordisplay;

import com.asynkronos.android.sensordisplay.R;

import android.os.Bundle;

/**
 * 
 * @author Michael Bruno
 *
 */
public class PressureData extends SensorDashActivity{
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.four);
        
        sensorId = SensorType.PRESSURE;
        init();
         
        if(supported()){

        	createViews(R.string.pressure,R.string.pressure_lb,
                null,null, null);
        
			createSlider(true);
			createSaveButton(true);
        }else{
        	createNoSupport(R.string.pressure);
        }
        
    }

	
}