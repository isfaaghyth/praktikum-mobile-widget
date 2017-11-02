package isfaaghyth.app.praktikummenu;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

/**
 * Created by isfaaghyth on 10/31/17.
 * github: @isfaaghyth
 */

public class AppWidgetConfigActivity extends AppCompatActivity {

    private final static String PREF_NAME = "kwidgetme";

    private RadioButton rdHijau, rdKuning, rdMerah;
    private int mAppWidgetId;
    private Button btnSimpan;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_appwidget_config);
        rdHijau = (RadioButton) findViewById(R.id.rd_hijau);
        rdKuning = (RadioButton) findViewById(R.id.rd_kuning);
        rdMerah = (RadioButton) findViewById(R.id.rd_merah);
        btnSimpan = (Button) findViewById(R.id.btn_simpan);

        mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                int color = Color.WHITE;
                if (rdHijau.isChecked()) {
                    color = Color.GREEN;
                    Log.d("Color", "hijau");
                } else if (rdKuning.isChecked()) {
                    color = Color.YELLOW;
                    Log.d("Color", "kuning");
                } else if (rdMerah.isChecked()) {
                    color = Color.RED;
                    Log.d("Color", "merah");
                }
                SharedPreferences.Editor prefs = getApplicationContext()
                                                    .getSharedPreferences(PREF_NAME, 0).edit();
                prefs.putInt("warna_teks", color);
                prefs.apply();

                ComponentName thisAppWidget = new ComponentName(
                                                getApplicationContext().getPackageName(),
                                                MyWidgetProvider.class.getName());
                Intent intent = new Intent(getApplicationContext(), MyWidgetProvider.class);
                int[] appWidgetIds = AppWidgetManager
                                        .getInstance(getApplicationContext())
                                        .getAppWidgetIds(thisAppWidget);
                intent.setAction("android.appwidget.action.APPWIDGET_UPDATE");
                intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
                getApplicationContext().sendBroadcast(intent);

                Intent result = new Intent();
                result.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
                setResult(RESULT_OK, result);
                finish();
            }
        });
    }

}
