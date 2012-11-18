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
public class SensorDashSavedActivity extends Activity {
	
	protected Sensor sensor;
	protected int sensorId;
	protected SensorManager manager;
	protected Slider slider;
	protected int delay;
	protected int currentApi;
	protected SensorDBAdapter adapter;
	protected float x;
	protected float y;
	protected float z;
	protected float f;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    
	
	public void init(){
    	SensorManager manager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensor = manager.getDefaultSensor(sensorId);
        openAdapter();

	}

    protected void alert(String message){
    	
    	AlertDialog.Builder alertbox = new AlertDialog.Builder(this); 
    	alertbox.setMessage(message);
    	alertbox.setNeutralButton(this.getString(R.string.ok), new DialogInterface.OnClickListener() {
    		public void onClick(DialogInterface arg0, int arg1){
    			
    		}
    	});
    	
    	alertbox.show();
    }

    
    public void clickClearButton(View view){

    	adapter.removeEntry(sensorId);
    	
    	TextView readout;
    	readout = (TextView)findViewById(R.id.xaxis);	
    	readout.setText("");
    	readout = (TextView)findViewById(R.id.yaxis);	
    	readout.setText("");
    	readout = (TextView)findViewById(R.id.zaxis);	
    	readout.setText("");
    	readout = (TextView)findViewById(R.id.force);	
    	readout.setText("");

    }
    
    public void createNoSupport(Integer title){
    	TextView txt = (TextView)findViewById(R.id.title);	
    	txt.setText(this.getString(title));
    	
    	View view = (View)findViewById(R.id.not_supported);	
    	view.setVisibility(View.VISIBLE);
    	
    	view = (View)findViewById(R.id.table);	
    	view.setVisibility(View.GONE);
    }
    
    public void createViews(Integer title, Integer x, Integer y, Integer z, Integer f){
    	TextView readout;
		View view;
		
		readout = (TextView)findViewById(R.id.title);	
    	readout.setText(this.getString(title));
    	
		readout = (TextView)findViewById(R.id.xlabel);	
    	readout.setText(this.getString(x));
    	view = (View)findViewById(R.id.xrow);
		view.setVisibility(View.VISIBLE);

    	if(y!=null){
    		readout = (TextView)findViewById(R.id.ylabel);	
    		readout.setText(this.getString(y));
    		view = (View)findViewById(R.id.yrow);
    		view.setVisibility(View.VISIBLE);
    	}
    	
    	if(z!=null){
    		readout = (TextView)findViewById(R.id.zlabel);	
    		readout.setText(this.getString(z));
    		view = (View)findViewById(R.id.zrow);
    		view.setVisibility(View.VISIBLE);
    	}
    	
    	if(f!=null){
    		readout = (TextView)findViewById(R.id.flabel);
    		readout.setText(this.getString(f));
    		view = (View)findViewById(R.id.frow);
    		view.setVisibility(View.VISIBLE);
    	}
    	
        DatabaseEntry entry = adapter.getEntry(sensorId);
        
    	if(entry!=null){
    		
    		Float tx = entry.getVal0();
    		Float ty = entry.getVal1();
    		Float tz = entry.getVal2();
    		Float tf = entry.getVal3();
    		if(tx!=null){
    			readout = (TextView)findViewById(R.id.xaxis);	
    			readout.setText(tx+"");
    		}
    		if(ty!=null){
    			readout = (TextView)findViewById(R.id.yaxis);	
        		readout.setText(ty+"");
    		}
    		if(tz!=null){
    			readout = (TextView)findViewById(R.id.zaxis);	
        		readout.setText(tz+"");
    		}
    		if(tf!=null){
    			readout = (TextView)findViewById(R.id.force);	
        		readout.setText(tf+"");
    		}
    	}
        
    }
    
    public void dump(){
    	adapter.dump();
    }
    
    public boolean supported(){
    	
    	boolean support = true;
    	SensorManager manager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensor = manager.getDefaultSensor(sensorId);
        
        if(sensor==null){
        	support = false;
        }
        
        return support;
    }
    
    @Override
    public void onDestroy() {
    	super.onDestroy();
    	if(adapter!=null){
    		adapter.close();
    	}
    }

    public void openAdapter(){
        adapter = new SensorDBAdapter(this);
        adapter.open();
    }

}
