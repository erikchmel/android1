package sk.tuke.smart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class IntentDemo1 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intent_demo1);
		
		Intent intent = getIntent();
		int meters = intent.getIntExtra("meters", -1);

        TextView textView = (TextView) findViewById(R.id.textView1);
        textView.setText(String.format("%d m are %d cm", meters, meters * 100));
		/*String data = intent.getStringExtra("input");
		TextView text = (TextView) findViewById(R.id.textView1);
		text.setText(data);*/
	}

	public void onBackClicked(View view){
		Intent intent = new Intent(this, IntentDemo.class);
		startActivity(intent);
	}
}
