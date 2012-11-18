package com.asynkronos.android.sensordisplay;

import com.asynkronos.android.sensordisplay.R;

import android.os.Bundle;

/**
 * 
 * @author Michael Bruno
 *
 */
public class GravityData extends SensorDashActivity {
    /** Called when the activity is first created. */
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.four);
        
        sensorId = SensorType.GRAVITY;
        init();
        
        if(supported()){

            createViews(R.string.gravity,R.string.xaxis_accel_lb,
                    R.string.yaxis_accel_lb,R.string.zaxis_accel_lb, R.string.force_accel_lb);
                    
                    
        	createSlider(true);
            createSaveToggle(true);
        }else{
        	createNoSupport(R.string.gravity);
        }
       
    }
	
}