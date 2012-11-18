package com.asynkronos.android.sensordisplay;

import com.asynkronos.android.sensordisplay.R;

import android.os.Bundle;

/**
 * 
 * @author Michael Bruno
 *
 */
public class GravityDataSaved extends SensorDashSavedActivity {

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.four_saved);
        
        sensorId = SensorType.GRAVITY;
        init();
        
        if(supported()){

            createViews(R.string.gravity,R.string.xaxis_accel_lb,
                    R.string.yaxis_accel_lb,R.string.zaxis_accel_lb, R.string.force_accel_lb);
        }else{
        	createNoSupport(R.string.gravity);
        }
    }

    @Override 
        public void onResume(){
    	super.onResume();
            if(supported()){

                createViews(R.string.gravity,R.string.xaxis_accel_lb,
                        R.string.yaxis_accel_lb,R.string.zaxis_accel_lb, R.string.force_accel_lb);
            }else{
            	createNoSupport(R.string.gravity);
            }

    }

}