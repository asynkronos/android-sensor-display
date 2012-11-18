package com.asynkronos.android.sensordisplay;

import com.asynkronos.android.sensordisplay.R;
import android.os.Bundle;

/**
 * 
 * @author Michael Bruno
 *
 */
public class PressureDataSaved extends SensorDashSavedActivity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.four);
        
        sensorId = SensorType.PRESSURE;
        init();
        
        if(supported()){

        	createViews(R.string.pressure,R.string.pressure_lb,
                null,null, null);
        }else{
        	createNoSupport(R.string.pressure);
        }

    }

    
    @Override 
    public void onResume(){
    	super.onResume();
        if(supported()){

        	createViews(R.string.pressure,R.string.pressure_lb,
                null,null, null);
        }else{
        	createNoSupport(R.string.pressure);
        }
    }
	
}