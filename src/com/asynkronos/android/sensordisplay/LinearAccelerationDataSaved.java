package com.asynkronos.android.sensordisplay;

import com.asynkronos.android.sensordisplay.R;

import android.os.Bundle;

/**
 * 
 * @author Michael Bruno
 *
 */
public class LinearAccelerationDataSaved extends SensorDashSavedActivity  {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.four_saved);
        
        sensorId = SensorType.LINEAR_ACCELERATION;
        init();
        
        if(supported()){

            createViews(R.string.linear_acceleration,R.string.xaxis_accel_lb,
                    R.string.yaxis_accel_lb,R.string.zaxis_accel_lb, R.string.force_accel_lb);
        }else{
        	createNoSupport(R.string.linear_acceleration);
        }

    }
    
    @Override 
    public void onResume(){
    	super.onResume();
        if(supported()){

            createViews(R.string.linear_acceleration,R.string.xaxis_accel_lb,
                    R.string.yaxis_accel_lb,R.string.zaxis_accel_lb, R.string.force_accel_lb);
        }else{
        	createNoSupport(R.string.linear_acceleration);
        }
    }

	
}