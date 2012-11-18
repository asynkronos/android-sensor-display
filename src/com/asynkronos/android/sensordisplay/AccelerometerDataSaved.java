package com.asynkronos.android.sensordisplay;

import com.asynkronos.android.sensordisplay.R;

import android.os.Bundle;

/**
 * 
 * @author Michael Bruno
 *
 */
public class AccelerometerDataSaved extends SensorDashSavedActivity {

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.four_saved);
        
        sensorId = SensorType.ACCELEROMETER;
        init();
        
        if(supported()){

            createViews(R.string.accelerometer,R.string.xaxis_accel_lb,
                    R.string.yaxis_accel_lb,R.string.zaxis_accel_lb, R.string.force_accel_lb);
        }else{
        	createNoSupport(R.string.accelerometer);
        }

    }
    
    @Override 
    public void onResume(){
    	super.onResume();
        if(supported()){

            createViews(R.string.accelerometer,R.string.xaxis_accel_lb,
                    R.string.yaxis_accel_lb,R.string.zaxis_accel_lb, R.string.force_accel_lb);
        }else{
        	createNoSupport(R.string.accelerometer);
        }
    }
	
}