package com.asynkronos.android.sensordisplay;

import com.asynkronos.android.sensordisplay.R;
import android.os.Bundle;

/**
 * 
 * @author Michael Bruno
 *
 */
public class MagneticFieldDataSaved extends SensorDashSavedActivity {
    /** Called when the activity is first created. */
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.four_saved);
        
        sensorId = SensorType.MAGNETIC_FIELD;
        init();
        
        if(supported()){

            createViews(R.string.magnetic_field,R.string.xaxis_mag_lb,
                    R.string.yaxis_mag_lb,R.string.zaxis_mag_lb, null);
        }else{
        	createNoSupport(R.string.magnetic_field);
        }

    }
    
    @Override 
    public void onResume(){
    	super.onResume();
        if(supported()){

            createViews(R.string.magnetic_field,R.string.xaxis_mag_lb,
                    R.string.yaxis_mag_lb,R.string.zaxis_mag_lb, null);
        }else{
        	createNoSupport(R.string.magnetic_field);
        }
    }
	
}