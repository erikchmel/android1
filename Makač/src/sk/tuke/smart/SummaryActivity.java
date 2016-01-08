package sk.tuke.smart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SummaryActivity extends Activity {
	
	Button back, share;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_summary);
		back = (Button) findViewById(R.id.back);
		share = (Button) findViewById(R.id.share);

		back.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent myIntent = new Intent(SummaryActivity.this, TrackerActivity.class);
	        SummaryActivity.this.startActivity(myIntent);	
		}
	});
		
		share.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_SEND);
				intent.setType("text/plain");
				intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
				intent.putExtra(Intent.EXTRA_TEXT, "mail body");
				startActivity(Intent.createChooser(intent, "share"));
			}
		});
		} 
	
}
