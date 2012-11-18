package com.asynkronos.android.sensordisplay;

import com.asynkronos.android.sensordisplay.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * 
 * @author Michael Bruno
 *
 */
public class SensorDashActivity extends Activity implements SensorEventListener {
	
	protected Sensor sensor;
	protected int sensorId;
	protected SensorManager manager;
	protected Slider slider;
	protected int delay;
	protected int currentApi;
	protected SensorDBAdapter adapter;
	protected ToggleButton tb;
	protected float x;
	protected float y;
	protected float z;
	protected float f;
	
	protected float maxVal;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    
	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		x = event.values[0];
		y = event.values[1];
		z = event.values[2];
		
		if(sensorId==SensorType.ACCELEROMETER || sensorId==SensorType.GRAVITY
				|| sensorId==SensorType.LINEAR_ACCELERATION){

			double a = Math.round(Math.sqrt(Math.pow(x, 2) +
					Math.pow(y, 2) + Math.pow(z, 2)));
			if(sensorId==SensorType.LINEAR_ACCELERATION){
				f = Math.abs((float)(a));
			}else{
				f = Math.abs((float)(a- SensorManager.STANDARD_GRAVITY));
			}
			
	    	tb = (ToggleButton)findViewById(R.id.toggle);
	    	if(tb.isChecked()){
	    		
	    		float nowVal = Math.abs(x) + Math.abs(y) + Math.abs(z);
	    		
	    		if(nowVal>maxVal){
	    		
	    			maxVal = nowVal;
	    			
	    			DatabaseEntry entry = new DatabaseEntry(
	        			sensorId,
	        			new Float(x),
	        			new Float(y),
	        			new Float(z),
	        			new Float(f));
	        		if(adapter.getEntry(sensorId)==null){
	        			adapter.insertEntry(entry);
	        			//Log.e("db","inserting entry");
	        			//Log.e("db",val+"");
	        		}else{
	        			adapter.updateEntry(sensorId, entry);
	        			//Log.e("db","updating entry");
	        		}
	    		}
	    	}
			
		}else{
			if(event.values.length==4){
				f = event.values[3];
			}
		}
		TextView readout;
		readout = (TextView)findViewById(R.id.xaxis);	
    	readout.setText(x+"");
    	readout = (TextView)findViewById(R.id.yaxis);	
    	readout.setText(y+"");
    	readout = (TextView)findViewById(R.id.zaxis);	
    	readout.setText(z+"");
    	readout = (TextView)findViewById(R.id.force);
    	readout.setText(f+"");
    	

	}
	
	public void init(){
    	manager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensor = manager.getDefaultSensor(sensorId);
        
        openAdapter();
        /*
        AdManager.setTestDevices( new String[] {
        		AdManager.TEST_EMULATOR, 
        		"E83D20734F72FB3108F104ABC0FFC738", 
        		} );
        AdView adView = (AdView)findViewById(R.id.ad);
        adView.requestFreshAd();
        */
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
    
    public void clickSlider(View view){
    	
    	delay = (int)slider.getCurrent() * 1000;
    	manager.unregisterListener(this);
    	manager.registerListener(this, sensor, delay);
    }
    
    public void clickSaveButton(View view){

    	DatabaseEntry entry = new DatabaseEntry(
    			sensorId,
    			new Float(x),
    			new Float(y),
    			new Float(z),
    			new Float(f));
    	if(adapter.getEntry(sensorId)==null){
    		adapter.insertEntry(entry);
    		//Log.e("db","inserting entry");
    		//Log.e("db",val+"");
    	}else{
    		adapter.updateEntry(sensorId, entry);
    		//Log.e("db","updating entry");
    	}
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
        
    }
    
    public void createSlider(boolean b){
    	currentApi = android.os.Build.VERSION.SDK_INT;
    	currentApi = 10;
    	if(currentApi>=9){
    		View view = (View)findViewById(R.id.slider_display);
        	view.setVisibility(View.VISIBLE);
        	slider = (Slider)findViewById(R.id.slider);
    	}
    }
    
    public void createSaveToggle(boolean b){
    	if(b){
    		View view = (View)findViewById(R.id.save_toggle_display);
    		view.setVisibility(View.VISIBLE);
    	}
    }
    
    public void createSaveButton(boolean b){
    	
    	if(b){
    		View view = (View)findViewById(R.id.save_button_display);
    		view.setVisibility(View.VISIBLE);
    	}
    	
    }
    
    public boolean supported(){
    	
    	boolean support = true;
        
        if(sensor==null){
       	support = false;
        }else{
        	manager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
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
    
    @Override
    public void onPause() {
    	manager.unregisterListener(this, sensor);
    	if(tb!=null){
    		tb.setChecked(false);
    	}
    	super.onPause();

    }
    
    @Override
    public void onResume() {
    	super.onResume();
    	manager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    	if(slider!=null){
    		slider.setCurrent(200);
    	}
    }

}
