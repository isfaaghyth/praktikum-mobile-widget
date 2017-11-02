package isfaaghyth.app.praktikummenu;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.widget.RemoteViews;

import java.util.Random;

/**
 * Created by isfaaghyth on 10/31/17.
 * github: @isfaaghyth
 */

public class MyWidgetProvider extends AppWidgetProvider {

    private final static String PREF_NAME = "kwidgetme";

    @Override public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        ComponentName thisWidget = new ComponentName(context, MyWidgetProvider.class);
        int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
        for (int widgetId : allWidgetIds) {
            int number = (new Random().nextInt());
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.my_appwidget_layout);
            Log.d("WidgetExample", String.valueOf(number));

            remoteViews.setTextColor(
                    R.id.update,
                    context.getSharedPreferences(PREF_NAME, 0)
                            .getInt("warna_teks", Color.WHITE)
            );

            remoteViews.setTextViewText(R.id.update, String.valueOf(number));

            //register onclick
            Intent intent = new Intent(context, MyWidgetProvider.class);
            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.update, pendingIntent);

            appWidgetManager.updateAppWidget(widgetId, remoteViews);
        }
    }
}
