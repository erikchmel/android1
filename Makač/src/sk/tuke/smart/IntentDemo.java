package sk.tuke.smart;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class IntentDemo extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intent_demo);
	}
	
	public void onButtonClicked(View view){
		Intent intent = new Intent(
				Intent.ACTION_VIEW,
				Uri.parse("https://www.youtube.com/watch?v=W857ys3BlRI"));
		startActivity(intent);
	}
	
	public void onConvertClicked(View view){
		Intent intent = new Intent(this, IntentDemo1.class);
		
		EditText editText = (EditText) findViewById(R.id.editText1);
		
		intent.putExtra("meters", Integer.parseInt(editText.getText().toString()));
		startActivity(intent);
	}
}
