package isfaaghyth.app.praktikummenu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.View;
import android.widget.ListView;

/**
 * Created by isfaaghyth on 10/20/17.
 * github: @isfaaghyth
 */

public class FloatingContextDemo extends AppCompatActivity {

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.floating_demo_layout);
        ListView lv = (ListView) findViewById(R.id.lst_menu);
        registerForContextMenu(lv);
    }

    @Override public void onCreateContextMenu(ContextMenu menu, View v,
                                              ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.float_context_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }
}


