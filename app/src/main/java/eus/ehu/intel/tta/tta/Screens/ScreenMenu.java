package eus.ehu.intel.tta.tta.Screens;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import eus.ehu.intel.tta.tta.R;

public class ScreenMenu extends ScreensBase implements View.OnClickListener{
    private final static String TAG = ScreenMenu.class.getCanonicalName();

    private Button screen_menu_button_new_test;
    private Button screen_menu_button_new_exercise;
    private Button screen_menu_button_tracing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_menu);
        screen_menu_button_new_test=(Button)findViewById(R.id.screen_menu_button_new_test);
        screen_menu_button_new_exercise=(Button)findViewById(R.id.screen_menu_button_new_exercise);
        screen_menu_button_tracing=(Button)findViewById(R.id.screen_menu_button_tracing);

        screen_menu_button_new_test.setOnClickListener(this);
        screen_menu_button_new_exercise.setOnClickListener(this);
        screen_menu_button_tracing.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        int idOnClick=v.getId();
        switch (idOnClick){
            case R.id.screen_menu_button_new_test:
                intent=new Intent(this,ScreenTest.class);
                startActivity(intent);
                break;
            case R.id.screen_menu_button_new_exercise:
                intent=new Intent(this,ScreensExercise.class);
                startActivity(intent);
                break;
            case R.id.screen_menu_button_tracing:
                break;
            default:
                break;
        }
    }
}
