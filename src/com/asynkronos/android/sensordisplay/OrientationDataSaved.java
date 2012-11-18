package com.asynkronos.android.sensordisplay;

import com.asynkronos.android.sensordisplay.R;
import android.os.Bundle;

/**
 * 
 * @author Michael Bruno
 *
 */
public class OrientationDataSaved extends SensorDashSavedActivity{
    /** Called when the activity is first created. */
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.four_saved);
        
        sensorId = SensorType.ORIENTATION;
        init();
        
        if(supported()){

        	createViews(R.string.orientation,R.string.azimuth_lb,
                    R.string.pitch_lb,R.string.roll_lb, null);
        }else{
        	createNoSupport(R.string.orientation);
        }

    }
    
    @Override 
    public void onResume(){
    	super.onResume();
        if(supported()){

        	createViews(R.string.orientation,R.string.azimuth_lb,
                    R.string.pitch_lb,R.string.roll_lb, null);
        }else{
        	createNoSupport(R.string.orientation);
        }
    }

	
}