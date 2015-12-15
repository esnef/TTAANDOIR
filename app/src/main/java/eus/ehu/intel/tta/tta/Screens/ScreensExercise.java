package eus.ehu.intel.tta.tta.Screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import eus.ehu.intel.tta.tta.R;

public class ScreensExercise extends ScreensBase implements View.OnClickListener {
    private final static String TAG = ScreensExercise.class.getCanonicalName();
    private EditText screen_login_editText_user;
    private EditText screen_login_editText_pass;
    private Button screen_login_button_access;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_exercise);
        findViewById(R.id.screen_exercise_button_up_image).setOnClickListener(this);
        findViewById(R.id.screen_exercise_button_making_photo).setOnClickListener(this);
        findViewById(R.id.screen_exercise_button_audio_recording).setOnClickListener(this);
        findViewById(R.id.screen_exercise_button_video_recording).setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        int idOnClick=v.getId();
        switch (idOnClick){
            case R.id.screen_exercise_button_up_image:
                break;
            case R.id.screen_exercise_button_making_photo:
                break;
            case R.id.screen_exercise_button_audio_recording:
                break;
            case R.id.screen_exercise_button_video_recording:
                break;
            default:
                break;
        }
    }

}
