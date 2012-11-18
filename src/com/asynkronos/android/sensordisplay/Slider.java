package com.asynkronos.android.sensordisplay;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import java.text.DecimalFormat;
import com.asynkronos.android.sensordisplay.R;

/**
 * 
 * @author Michael Bruno
 *
 */
public class Slider extends RelativeLayout implements
		SeekBar.OnSeekBarChangeListener {

	private float step = 0.1F;
	private int maxRange = 1000;
	private float minValue;
	private float maxValue;
	private int divideBy;
	private float current;
	private SeekBar seek;
	private Paint paint;
	private View.OnClickListener listener;
	private DecimalFormat decimalFormatter;
	private DisplayMetrics displayMetrics;
	private double chunk;
	
	public float getCurrent() {
		return this.current;
	}

	public void setCurrent(float current) {
		this.current = (this.minValue + current / this.maxRange
				* (this.maxValue - this.minValue));
	}

	public float getStep() {
		return this.step;
	}

	public void setStep(float step) {
		this.maxRange = Math.round((this.maxValue - this.minValue) / step);
		this.seek.setMax(this.maxRange);
		setUserProgressBar();
		this.step = step;
	}

	public Slider(Context context) {
		super(context);
		init(context);
	}

	public Slider(Context context, AttributeSet attrs) {

		super(context, attrs, android.R.attr.progressBarStyleHorizontal);//
			//	16842872);
		init(context, attrs);
	}

	private void init(Context context) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService("layout_inflater");
		ViewGroup inflated = (ViewGroup) inflater.inflate(R.drawable.slider_bar, null);
		addView(inflated);
		this.seek = ((SeekBar) findViewById(R.id.seek_bar));
		setCurrent(0.0F);
		setMaxValue(this.maxRange);
		setMinValue(0);
		setDivideBy(0);
		createLabels();
		this.seek.setOnSeekBarChangeListener(this);
	}

	public void setOnClickListener(View.OnClickListener l) {
		this.listener = l;
	}

	private void init(Context context, AttributeSet attrs) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService("layout_inflater");
		ViewGroup inflated = (ViewGroup) inflater.inflate(R.drawable.slider_bar, null);
		//ViewGroup inflated = (ViewGroup) inflater.inflate(R.drawable., null);
		addView(inflated);
		this.seek = ((SeekBar) findViewById(R.id.seek_bar));
		

		//this.seek = new SeekBar(context, attrs);
		this.seek.incrementProgressBy(1);
		setWillNotDraw(false);

		TypedArray a = getContext().obtainStyledAttributes(attrs,
				R.styleable.com_asynkronos_android_sensordisplay_Slider);
		this.minValue = a.getInt(0, 0);
		this.maxValue = a.getInt(1, 100);
		this.divideBy = a.getInt(2, 1);
		this.current = a.getInt(3, 0);
		this.step = a.getFloat(4,0.1F);
		validateDivideBy(this.divideBy);
		setStep(this.step);
		validateCurrent(this.current);
		this.paint = new Paint();
		this.paint.setColor(-3355444);
		this.paint.setTypeface(Typeface.DEFAULT);
		this.paint.setTextSize(14.0F);
		this.paint.setAntiAlias(true);
		this.paint.setTextAlign(Paint.Align.CENTER);

		this.seek.setOnSeekBarChangeListener(this);

		this.decimalFormatter = new DecimalFormat("##.#");
		this.displayMetrics = new DisplayMetrics();
		this.chunk = ((this.maxValue - this.minValue) / this.divideBy);
		createLabels();
		
	}

	public float getMinValue() {
		return this.minValue;
	}

	public void setMinValue(int minValue) {
		this.minValue = minValue;
	}

	public float getMaxValue() {
		return this.maxValue;
	}

	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
		this.seek.setMax(maxValue);
	}

	public int getDivideBy() {
		return this.divideBy;
	}

	public void setDivideBy(int divideBy) {
		this.divideBy = divideBy;
	}

	public void setEnabled(boolean enabled) {
		this.seek.setEnabled(enabled);

		if (enabled)
			this.seek.setFocusable(true);
		else
			this.seek.setFocusable(false);
	}

	public void setFocusable(boolean focusable) {
		this.seek.setEnabled(focusable);
	}

	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		int parts = this.seek.getWidth() / this.divideBy;
		TextView v1 = (TextView) findViewById(R.id.seekbar_label_left);
		this.displayMetrics.setToDefaults();

		for (int i = 1; i < this.divideBy; i++)
			canvas.drawText(this.decimalFormatter.format(this.minValue
					+ this.chunk * i), parts * i + 7, v1.getBottom() - 4.0F
					* this.displayMetrics.density, this.paint);
	}

	private void createLabels() {
		TextView textLeft = (TextView) findViewById(R.id.seekbar_label_left);
		TextView textRight = (TextView) findViewById(R.id.seekbar_label_right);
		textLeft.setText(this.decimalFormatter.format(this.minValue));
		textRight.setText(this.decimalFormatter.format(this.maxValue));
	}

	private void validateCurrent(float current) {
		if ((current <= this.maxValue) && (current >= this.minValue)) {
			setCurrent((current - this.minValue) * this.maxRange
					/ (this.maxValue - this.minValue));
		} else {
			setCurrent(this.minValue);
			this.seek.setProgress((int) this.minValue);
		}
	}

	private void validateDivideBy(int divideBy) {
		if (divideBy <= 0)
			setDivideBy(1);
		else
			setDivideBy(divideBy);
	}

	public void setProgressFillColor(boolean fill) {
		if (fill)
			this.seek.setProgressDrawable(getContext().getResources()
					.getDrawable(R.drawable.slider_bar_fill_style));
		else {
			this.seek.setProgressDrawable(getContext().getResources()
					.getDrawable(R.drawable.slider_bar_style));
		}

		this.seek.setProgress(this.seek.getProgress() - 1);
		this.seek.setProgress(this.seek.getProgress() + 1);
	}

	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		if (this.listener != null) {
			setCurrent(progress);
			this.listener.onClick(this);
		}
	}

	public void onStartTrackingTouch(SeekBar seekBar) {
	}

	public void onStopTrackingTouch(SeekBar seekBar) {
	}

	private void setUserProgressBar() {
		this.seek.setProgress((int) ((this.current - this.minValue)
				* this.maxRange / (this.maxValue - this.minValue)));
	}
}
