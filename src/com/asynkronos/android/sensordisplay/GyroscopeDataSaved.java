package com.asynkronos.android.sensordisplay;

import com.asynkronos.android.sensordisplay.R;

import android.os.Bundle;

/**
 * 
 * @author Michael Bruno
 *
 */
public class GyroscopeDataSaved extends SensorDashSavedActivity {
    /** Called when the activity is first created. */
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.four_saved);
        
        sensorId = SensorType.GYROSCOPE;
        init();
        
        if(supported()){

        	createViews(R.string.gyroscope,R.string.xaxis_gyro_lb,
                    R.string.yaxis_gyro_lb,R.string.zaxis_gyro_lb, null);
        }else{
        	createNoSupport(R.string.gyroscope);
        }

    }
    
    @Override 
    public void onResume(){
    	super.onResume();
        if(supported()){

        	createViews(R.string.gyroscope,R.string.xaxis_gyro_lb,
                    R.string.yaxis_gyro_lb,R.string.zaxis_gyro_lb, null);
        }else{
        	createNoSupport(R.string.gyroscope);
        }

    }

	
}