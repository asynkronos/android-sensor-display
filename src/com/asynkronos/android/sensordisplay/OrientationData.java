package com.asynkronos.android.sensordisplay;

import com.asynkronos.android.sensordisplay.R;
import android.os.Bundle;

/**
 * 
 * @author Michael Bruno
 *
 */
public class OrientationData extends SensorDashActivity{
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.four);
        
        sensorId = SensorType.ORIENTATION;
        init();
        
        if(supported()){

        	createViews(R.string.orientation,R.string.azimuth_lb,
                    R.string.pitch_lb,R.string.roll_lb, null);
        
			createSlider(true);
			createSaveButton(true);
        }else{
        	createNoSupport(R.string.orientation);
        }

    }



	
}