package com.asynkronos.android.sensordisplay;

import com.asynkronos.android.sensordisplay.R;
import android.os.Bundle;

/**
 * 
 * @author Michael Bruno
 *
 */
public class MagneticFieldData extends SensorDashActivity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.four);
        
        sensorId = SensorType.MAGNETIC_FIELD;
        init();
        
        if(supported()){

            createViews(R.string.magnetic_field,R.string.xaxis_mag_lb,
                    R.string.yaxis_mag_lb,R.string.zaxis_mag_lb, null);
                    
            createSlider(true);
            createSaveButton(true);
        }else{
        	createNoSupport(R.string.magnetic_field);
        }

    }

	
}