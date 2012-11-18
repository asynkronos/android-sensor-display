package com.asynkronos.android.sensordisplay;

import com.asynkronos.android.sensordisplay.R;
import android.os.Bundle;

/**
 * 
 * @author Michael Bruno
 *
 */
public class ProximityDataSaved extends SensorDashSavedActivity {

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.four);
        
        sensorId = SensorType.PROXIMITY;
        init();
        
        if(supported()){

            createViews(R.string.proximity,R.string.proximity_lb,
                    null,null, null);
        }else{
        	createNoSupport(R.string.proximity);
        }


    }
    
    @Override 
    public void onResume(){
    	super.onResume();
        if(supported()){

            createViews(R.string.proximity,R.string.proximity_lb,
                    null,null, null);
        }else{
        	createNoSupport(R.string.proximity);
        }
    }

	
}