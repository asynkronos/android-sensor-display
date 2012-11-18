package com.asynkronos.android.sensordisplay;


import com.asynkronos.android.sensordisplay.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * 
 * @author Michael Bruno
 *
 */
public class SensorInfo extends Activity {
	
	private int sensorType;
	private String sensorName;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor_info);
        
        //AdManager.setTestDevices( new String[] {
        //		AdManager.TEST_EMULATOR, 
        //		"E83D20734F72FB3108F104ABC0FFC738", 
        //		} );
        //AdView adView = (AdView)findViewById(R.id.ad);
        //adView.requestFreshAd();
       
        Bundle b = getIntent().getExtras();
        sensorType = b.getInt("sensorType");
        sensorName = b.getString("sensorName");
        TextView txt = (TextView)findViewById(R.id.sensor_title);
        
        txt.setText(sensorName);
        /*
        
        TextView textview = new TextView(this);        
        textview.setText("This is the Artists tab\n\n\n\n\nokay?\n\n\nsofaring");  
        textview.setTextColor(0xfff11fff);
        setContentView(textview);
        */
        
        SensorManager manager = (SensorManager)getSystemService(SENSOR_SERVICE);
        Sensor sensor = manager.getDefaultSensor(sensorType);
        
        if(sensor==null){
        	View view = (View)findViewById(R.id.not_supported);	
        	view.setVisibility(View.VISIBLE);
        	
        	view = (View)findViewById(R.id.table);	
        	view.setVisibility(View.GONE);
        	
        	return;
        }
        
        txt = (TextView)findViewById(R.id.sensor_name);
        txt.setText(sensor.getName());
        
        txt = (TextView)findViewById(R.id.sensor_vendor);
        txt.setText(sensor.getVendor());
        
        txt = (TextView)findViewById(R.id.sensor_version);
        txt.setText(sensor.getVersion()+"");
        
        txt = (TextView)findViewById(R.id.sensor_max_range);
        txt.setText(sensor.getMaximumRange()+"");
        
        txt = (TextView)findViewById(R.id.sensor_resolution);
        txt.setText(sensor.getResolution()+"");
        
        txt = (TextView)findViewById(R.id.sensor_power);
        txt.setText(sensor.getPower()+"");

    }
    
    @SuppressWarnings("unused")
	private void alert(String message){
    	
    	AlertDialog.Builder alertbox = new AlertDialog.Builder(this); 
    	alertbox.setMessage(message);
    	alertbox.setNeutralButton(this.getString(R.string.ok), new DialogInterface.OnClickListener() {
    		public void onClick(DialogInterface arg0, int arg1){
    			
    		}
    	});
    	
    	alertbox.show();
    }

}