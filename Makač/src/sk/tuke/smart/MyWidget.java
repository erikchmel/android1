package sk.tuke.smart;

import java.util.Random;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

public class MyWidget extends AppWidgetProvider{
	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		for(int i = 0; i < appWidgetIds.length; i++){
			RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.my_widget);
			
			String text = "KROKY : " + new Random().nextInt(1000);
			views.setTextViewText(R.id.textView1, text);
			
			Intent openApp = new Intent(context, Menu.class);
			PendingIntent pIntent = PendingIntent.getActivity(context, 0, openApp, 0);
			
			views.setOnClickPendingIntent(R.id.buttonOpenApp, pIntent);
			
			Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://it4kt.cnl.sk/c/smart/2015/problemsets/01-makac.html"));
			PendingIntent myPIntent = PendingIntent.getActivity(context, 1, intent, 0);
			
			views.setOnClickPendingIntent(R.id.buttonOpenWing, myPIntent);
			
			appWidgetManager.updateAppWidget(appWidgetIds[i], views);
		}
	}
}
