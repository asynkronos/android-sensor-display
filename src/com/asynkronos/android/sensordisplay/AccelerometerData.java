package com.asynkronos.android.sensordisplay;

import com.asynkronos.android.sensordisplay.R;

import android.os.Bundle;

/**
 * 
 * @author Michael Bruno
 *
 */
public class AccelerometerData extends SensorDashActivity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.four);
        
        sensorId = SensorType.ACCELEROMETER;
        
        init();
        
        if(supported()){

        	createViews(R.string.accelerometer,R.string.xaxis_accel_lb,
        	        R.string.yaxis_accel_lb,R.string.zaxis_accel_lb, R.string.force_accel_lb);
        	              
        	createSlider(true);
            createSaveToggle(true);
        }else{
        	createNoSupport(R.string.accelerometer);
        }
        

    }


	
}