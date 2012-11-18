package com.asynkronos.android.sensordisplay;

import com.asynkronos.android.sensordisplay.R;
import android.os.Bundle;

/**
 * 
 * @author Michael Bruno
 *
 */
public class RotationVectorData extends SensorDashActivity {

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.four);
        
        sensorId = SensorType.ROTATION_VECTOR;
        init();
        
        if(supported()){

        	createViews(R.string.rotation_vector,R.string.x_rot_lb,
        	        R.string.y_rot_lb,R.string.z_rot_lb, R.string.f_rot_lb);
        	
            createSlider(true);
            createSaveToggle(true);
        }else{
        	createNoSupport(R.string.rotation_vector);
        }

    }

}