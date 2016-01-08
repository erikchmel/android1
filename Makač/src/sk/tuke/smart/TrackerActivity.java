package sk.tuke.smart;

import java.text.DecimalFormat;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle; 
import android.os.Handler; 
import android.os.SystemClock; 
import android.app.Activity; 
import android.content.Intent;
import android.util.Log;
import android.view.View; 
import android.view.View.OnClickListener; 
import android.widget.Button; 
import android.widget.TextView; 

public class TrackerActivity extends Activity implements LocationListener{
	private Button start, reset, pause, resume, stop; 
	private TextView time, distanceTextView; 
	int starttime = 0; 
	int timeInMilliseconds = 0; 
	int timeSwapBuff = 0; 
	int updatedtime = 0; 
	int secs = 0; 
	int mins = 0; 
	int milliseconds = 0; 
	int hour = 0;
	Handler handler = new Handler(); 
	private LocationManager locationManager;
	private static final long TEN_SECONDS = 10 * 1000;
	private static final float ONE_HUNDRED_METERS = 100f;
	private static final boolean ONLY_ENABLED_LOCATION_PROVIDERS = true;
	public static final String NO_PROVIDER = null;
	private String locationProviderName;
	
	@Override 
	protected void onCreate(Bundle savedInstanceState) { 
	super.onCreate(savedInstanceState); 
	setContentView(R.layout.activity_tracker);
	
	locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
	
	this.distanceTextView = (TextView) findViewById(R.id.distanceTextView);
	start = (Button) findViewById(R.id.start_button); 
	reset = (Button) findViewById(R.id.reset_button); 
	pause = (Button) findViewById(R.id.pause_button);
	resume = (Button) findViewById(R.id.resume_button);
	time = (TextView) findViewById(R.id.time); 
	stop = (Button) findViewById(R.id.stop_button);

	start.setOnClickListener(new OnClickListener() { 
	@Override 
	public void onClick(View v) { 
		onStartClicked(v);
	} 
	}); 
	
	pause.setOnClickListener(new OnClickListener() { 

		@Override 
		public void onClick(View v) { 
			onPauseClicked(v);
		} 
		}); 
	
	resume.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			onResumeClicked(v);
		}
	});

	reset.setOnClickListener(new OnClickListener() { 

		@Override 
		public void onClick(View v) { 
			onResetClicked(v);
		}}); 
	
	stop.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent myIntent = new Intent(TrackerActivity.this, SummaryActivity.class);
	        TrackerActivity.this.startActivity(myIntent);	
		}
	});
		} 

	public Runnable updateTimer = new Runnable() { 
	public void run() { 
	timeInMilliseconds = (int) (SystemClock.uptimeMillis() - starttime); 
	updatedtime = timeSwapBuff + timeInMilliseconds; 
	System.out.println(timeSwapBuff);
	secs = (int) (updatedtime / 1000); 
	mins = secs / 60; 
	hour = mins / 60;
	if(mins == 60){
		mins = 0;
	}
	secs = secs % 60; 
	milliseconds = (int) (updatedtime % 1000) / 100; 
	if(hour > 0){
		if(secs > 9){
			if(mins > 9){
				time.setText("0" + hour + ":" + String.format("%d", mins) + ":" 
						+ String.format("%d", secs));
			}else{
				time.setText("0" + hour + ":0" + String.format("%d", mins) + ":" 
						+ String.format("%d", secs));
			}
		}else{
			if(mins > 9){
				time.setText("0" + hour + ":" + String.format("%d", mins) + ":0" 
						+ String.format("%d", secs));
			}else{
				time.setText("0" + hour + ":0" + String.format("%d", mins) + ":0" 
						+ String.format("%d", secs));
		}}
		if(hour>9){
			if(secs > 9){
				if(mins > 9){
					time.setText("" + hour + ":" + String.format("%d", mins) + ":" 
							+ String.format("%d", secs));
				}else{
					time.setText("" + hour + ":0" + String.format("%d", mins) + ":" 
							+ String.format("%d", secs));
				}
			}else{
				if(mins > 9){
					time.setText("" + hour + ":" + String.format("%d", mins) + ":0" 
							+ String.format("%d", secs));
				}else{
					time.setText("" + hour + ":0" + String.format("%d", mins) + ":0" 
							+ String.format("%d", secs));
			}}
		}
	}else{
	if(secs > 9){
		if(mins > 9){
			time.setText("" + mins + ":" + String.format("%d", secs) + ":" 
					+ String.format("%d", milliseconds));
		}else{
			time.setText("0" + mins + ":" + String.format("%d", secs) + ":" 
					+ String.format("%d", milliseconds));
		}
	}else{
		if(mins > 9){
			time.setText("" + mins + ":0" + String.format("%d", secs) + ":" 
					+ String.format("%d", milliseconds));
		}else{
			time.setText("0" + mins + ":0" + String.format("%d", secs) + ":" 
					+ String.format("%d", milliseconds));
		}
	}}
	handler.postDelayed(this, 0); 
	}}; 
	
	public void onStartClicked(View view){
		starttime = (int) SystemClock.uptimeMillis(); 
		handler.postDelayed(updateTimer, 0);  
		pause.setVisibility(view.VISIBLE);
		resume.setVisibility(view.INVISIBLE);
		reset.setVisibility(view.INVISIBLE);
		start.setVisibility(view.INVISIBLE);
	}
	
	public void onPauseClicked(View view){
		timeSwapBuff += timeInMilliseconds; 
		handler.removeCallbacks(updateTimer); 
		pause.setVisibility(view.INVISIBLE);
		resume.setVisibility(view.VISIBLE);
		reset.setVisibility(view.VISIBLE);
		start.setVisibility(view.INVISIBLE);
	}
	
	public void onResumeClicked(View view){
		starttime = (int) SystemClock.uptimeMillis(); 
		handler.postDelayed(updateTimer, 0); 
		pause.setVisibility(view.VISIBLE);
		resume.setVisibility(view.INVISIBLE);
		reset.setVisibility(view.INVISIBLE);
		start.setVisibility(view.INVISIBLE);
	}
	
	public void onResetClicked(View view){
		starttime = 0; 
		timeInMilliseconds = 0; 
		timeSwapBuff = 0; 
		updatedtime = 0; 
		secs = 0; 
		mins = 0; 
		milliseconds = 0; 
		hour = 0;
		handler.removeCallbacks(updateTimer); 
		time.setText("00:00:0"); 
		pause.setVisibility(View.INVISIBLE);
		resume.setVisibility(View.INVISIBLE);
		reset.setVisibility(View.INVISIBLE);
		start.setVisibility(View.VISIBLE);
	}

	/*@Override
	public void onLocationChanged(Location location) {
		Location kosiceLocation = new Location(NO_PROVIDER);
	    kosiceLocation.setLatitude(48.697265);
	    kosiceLocation.setLongitude(21.2644253429128);

	    float distanceInMeters = kosiceLocation.distanceTo(location);

	    DecimalFormat distanceFormatter = new DecimalFormat("#.# km");
	    this.distanceTextView.setText(distanceFormatter.format(distanceInMeters / 1000));
		
	}*/
	
	@Override
    protected void onResume() {
        super.onResume();
        //requestLocationUpdates();
    }
	
	/*private void requestLocationUpdates() {
	    Criteria criteria = new Criteria();
	    criteria.setAccuracy(Criteria.ACCURACY_FINE);

	    locationProviderName = locationManager.getBestProvider(criteria, ONLY_ENABLED_LOCATION_PROVIDERS);
	    locationManager.requestLocationUpdates(locationProviderName, TEN_SECONDS, ONE_HUNDRED_METERS, this);
	}*/

    @Override
    protected void onPause() {
        //locationManager.removeUpdates(this);
        super.onPause();
    }

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	/*@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// prázdna metóda
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		requestLocationUpdates();
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// prázdna metóda
		
	}*/

	
	
	} 
