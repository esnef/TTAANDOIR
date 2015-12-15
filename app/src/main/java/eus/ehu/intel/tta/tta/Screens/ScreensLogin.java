package eus.ehu.intel.tta.tta.Screens;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import eus.ehu.intel.tta.tta.R;

public class ScreensLogin extends ScreensBase {
    private final static String TAG = ScreensLogin.class.getCanonicalName();
    private EditText screen_login_editText_user;
    private EditText screen_login_editText_pass;
    private Button screen_login_button_access;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screens_login);
        screen_login_editText_user=(EditText)findViewById(R.id.screen_login_editText_user);
        screen_login_editText_pass=(EditText)findViewById(R.id.screen_login_editText_pass);
        screen_login_button_access=(Button)findViewById(R.id.screen_login_button_access);

        screen_login_button_access.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = screen_login_editText_user.getText().toString();
                String pass = screen_login_editText_pass.getText().toString();
                if (user.compareToIgnoreCase("") != 0 && pass.compareToIgnoreCase("") != 0) {
                    userNow = user;
                    passNow = pass;
                    if (contextNow!=null) {
                        Intent intent = new Intent(contextNow, ScreenMenu.class);
                        startActivity(intent);
                    }

                }
            }
        });

    }

}
