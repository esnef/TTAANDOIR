package eus.ehu.intel.tta.tta.Screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import eus.ehu.intel.tta.tta.R;

public class ScreenTest extends ScreensBase{
    private final static String TAG = ScreenTest.class.getCanonicalName();

    private Button screen_menu_button_tracing;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_test);
        screen_menu_button_tracing=(Button)findViewById(R.id.screen_menu_button_tracing);

        screen_menu_button_tracing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


}
