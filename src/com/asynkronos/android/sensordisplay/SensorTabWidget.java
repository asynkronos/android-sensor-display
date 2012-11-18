package com.asynkronos.android.sensordisplay;

import com.asynkronos.android.sensordisplay.R;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

/**
 * 
 * @author Michael Bruno
 *
 */
public class SensorTabWidget extends TabActivity{
	
	public void onCreate(Bundle savedInstanceState) {    
		super.onCreate(savedInstanceState);    
		setContentView(R.layout.tab_layout);   
		TabHost tabHost = getTabHost();
		
		TabHost.TabSpec spec;     
		Intent intent; 
		
		Bundle b = getIntent().getExtras();
        int sensorType = b.getInt("sensorType");
        String sensorName = b.getString("sensorName");
        String className = b.getString("className");
        
        try{
        	intent = new Intent().setClass(this, Class.forName(className));    
        	spec = tabHost.newTabSpec("data").setIndicator(getLayoutInflater()
				.inflate(R.layout.tab_data, null)).setContent(intent);  
        	tabHost.addTab(spec); 
        }catch(ClassNotFoundException ex){
        	// really nothing we can do at this point
        }
		
		b = new Bundle();
		b.putInt("sensorType",sensorType);
		b.putString("sensorName",sensorName);
		
		intent = new Intent().setClass(this, SensorInfo.class);  
		intent.putExtras(b);
		spec = tabHost.newTabSpec("data").setIndicator(getLayoutInflater()
				.inflate(R.layout.tab_info, null)).setContent(intent);   
		tabHost.addTab(spec);    
		
		try{
			intent = new Intent().setClass(this, Class.forName(className + "Saved"));  
			intent.putExtras(b);
			spec = tabHost.newTabSpec("saved").setIndicator(getLayoutInflater()
				.inflate(R.layout.tab_saved, null)).setContent(intent);   
			tabHost.addTab(spec); 
		
		}catch(ClassNotFoundException ex){
			// really nothing we can do at this point
		}
 
		tabHost.setCurrentTab(0);
		}
}
