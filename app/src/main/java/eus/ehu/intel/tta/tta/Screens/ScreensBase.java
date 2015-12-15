package eus.ehu.intel.tta.tta.Screens;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import eus.ehu.intel.tta.tta.R;

public class ScreensBase extends AppCompatActivity {
    private final static String TAG = ScreensBase.class.getCanonicalName();

    protected String userNow=null;
    protected String passNow=null;
    protected Context contextNow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contextNow=this;
    }

}
