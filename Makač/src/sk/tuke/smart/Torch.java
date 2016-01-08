package sk.tuke.smart;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Torch extends Activity {

	private boolean state;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_torch);
		
		if(savedInstanceState != null){
		this.state = savedInstanceState.getBoolean("state");
			if(this.state == true){
				ImageView image = (ImageView) findViewById(R.id.image);
				Button button =(Button) findViewById(R.id.button);
				image.setImageResource(R.drawable.bulb_on);
				button.setText(R.string.turn_off);
			}
		}else{
		this.state = false;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.torch, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void toggle(View view){
		ImageView image = (ImageView) findViewById(R.id.image);
		Button button =(Button) findViewById(R.id.button);
		
	    if(this.state == true){
	        this.state = false;
	        image.setImageResource(R.drawable.bulb_off);
	        button.setText(R.string.turn_on);
	    }else{
	        this.state = true;
	        image.setImageResource(R.drawable.bulb_on);
	        button.setText(R.string.turn_off);
	    }
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		
		outState.putBoolean("state", this.state);
	}
}
