package com.asynkronos.android.sensordisplay;

/**
 * 
 * @author Michael Bruno
 *
 */
public class DatabaseEntry {
	
	protected Integer sensorId;
	protected Float val0;
	protected Float val1;
	protected Float val2;
	protected Float val3;
	
	public DatabaseEntry(){
		
	}
	
	public DatabaseEntry(Integer sensorId, Float val0, Float val1, Float val2,
			Float val3){
		this.sensorId = sensorId;
		this.val0 = val0;
		this.val1 = val1;
		this.val2 = val2;
		this.val3 = val3;
	}
	
	public Integer getSensorId() {
		return sensorId;
	}
	public void setSensorId(Integer sensorId) {
		this.sensorId = sensorId;
	}
	public Float getVal0() {
		return val0;
	}
	public void setVal0(Float val0) {
		this.val0 = val0;
	}
	public Float getVal1() {
		return val1;
	}
	public void setVal1(Float val1) {
		this.val1 = val1;
	}
	public Float getVal2() {
		return val2;
	}
	public void setVal2(Float val2) {
		this.val2 = val2;
	}
	public Float getVal3() {
		return val3;
	}
	public void setVal3(Float val3) {
		this.val3 = val3;
	}

}
