package com.asynkronos.android.sensordisplay;

import com.asynkronos.android.sensordisplay.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * 
 * @author Michael Bruno
 *
 */
public class MainScreen extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void clickButton(View view){
    	
    	int sensorType=0;
    	String sensorName = null;
    	String className = null;
    	
    	switch(view.getId()){
			case R.id.accelerometer_button:
				sensorType = SensorType.ACCELEROMETER;
				sensorName = this.getString(R.string.accelerometer);
				className = "com.asynkronos.android.sensordisplay.AccelerometerData";
				break;
			case R.id.gravity_button:
				sensorType = SensorType.GRAVITY;
				sensorName = this.getString(R.string.gravity);
				className = "com.asynkronos.android.sensordisplay.GravityData";
				break;
    		case R.id.gyroscope_button:
    			sensorType = SensorType.GYROSCOPE;
    			sensorName = this.getString(R.string.gyroscope);
    			className = "com.asynkronos.android.sensordisplay.GyroscopeData";
    			break;
    		case R.id.light_button:
    			sensorType = SensorType.LIGHT;
    			sensorName = this.getString(R.string.light);
    			className = "com.asynkronos.android.sensordisplay.LightData";
    			break;
    		case R.id.linear_acceleration_button:
    			sensorType = SensorType.LINEAR_ACCELERATION;
    			sensorName = this.getString(R.string.linear_acceleration);
    			className = "com.asynkronos.android.sensordisplay.LinearAccelerationData";
    			break;
    		case R.id.orientation_button:
    			sensorType = SensorType.ORIENTATION;
    			sensorName = this.getString(R.string.orientation);
    			className = "com.asynkronos.android.sensordisplay.OrientationData";
    			break;
    		case R.id.pressure_button:
    			sensorType = SensorType.PRESSURE;
    			sensorName = this.getString(R.string.pressure);
    			className = "com.asynkronos.android.sensordisplay.PressureData";
    			break;
    		case R.id.proximity_button:
    			sensorType = SensorType.PROXIMITY;
    			sensorName = this.getString(R.string.proximity);
    			className = "com.asynkronos.android.sensordisplay.ProximityData";
    			break;
    		case R.id.rotation_vector_button:
    			sensorType = SensorType.ROTATION_VECTOR;
    			sensorName = this.getString(R.string.rotation_vector);
    			className = "com.asynkronos.android.sensordisplay.RotationVectorData";
    			break;
    		case R.id.temperature_button:
    			sensorType = SensorType.TEMPERATURE;
    			sensorName = this.getString(R.string.temperature);
    			className = "com.asynkronos.android.sensordisplay.TemperatureData";
    			break;
    		case R.id.magnetic_field_button:
    			sensorType = SensorType.MAGNETIC_FIELD;
    			sensorName = this.getString(R.string.temperature);
    			className = "com.asynkronos.android.sensordisplay.MagneticFieldData";
    			break;
    	}

		Bundle b = new Bundle();
		b.putInt("sensorType",sensorType);
		b.putString("sensorName",sensorName);
		b.putString("className",className);
		
    	Intent intent = new Intent(this,SensorTabWidget.class);
    	intent.putExtras(b);
    	startActivity(intent);
    }
    
    
}