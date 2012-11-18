package com.asynkronos.android.sensordisplay;

import com.asynkronos.android.sensordisplay.R;

import android.os.Bundle;

/**
 * 
 * @author Michael Bruno
 *
 */
public class GyroscopeData extends SensorDashActivity {
    /** Called when the activity is first created. */
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.four);
        
        sensorId = SensorType.GYROSCOPE;
        init();
        
        if(supported()){

        	createViews(R.string.gyroscope,R.string.xaxis_gyro_lb,
                    R.string.yaxis_gyro_lb,R.string.zaxis_gyro_lb, null);
                    
        	createSlider(true);
            createSaveToggle(true);
            
        }else{
        	createNoSupport(R.string.gyroscope);
        }
    }
	
}