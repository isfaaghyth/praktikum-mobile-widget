package isfaaghyth.app.praktikummenu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by isfaaghyth on 10/20/17.
 * github: @isfaaghyth
 */

public class MainActivity extends AppCompatActivity {

    private ActionMode.Callback mActionModelCallback;
    private ActionMode mActionMode;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActionModelCallback = new ActionMode.Callback() {
            @Override public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.action_menu, menu);
                return true;
            }

            @Override public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_share:
                        Toast.makeText(getApplicationContext(), "Option share clicked", Toast.LENGTH_LONG).show();
                        mode.finish();
                        break;
                    default:
                        break;
                }
                return true;
            }

            @Override public void onDestroyActionMode(ActionMode mode) {
                mActionMode = null;
            }
        };

        TextView txt1 = (TextView) findViewById(R.id.txt1);
        txt1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override public boolean onLongClick(View v) {
                if (mActionMode != null) return false;
                mActionMode = startActionMode(mActionModelCallback);
                v.setSelected(true);
                return true;
            }
        });
    }

}
