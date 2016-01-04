package eus.ehu.intel.tta.tta.Screens;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

import eus.ehu.intel.tta.tta.Communications.RestClient;
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

                //Prueba restful
                /*
                RestExample restExample=new RestExample();
                System.out.println("init0");
                restExample.execute();
                System.out.println("end");
                */

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

    private class RestExample extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            String result;
            RestClient restClient;

            restClient=new RestClient();
            restClient.setHttpBasicAuth("12345678A", "tta");

            try {
                System.out.println("init1");
                result=restClient.getString("getTest?id=1");
                System.out.println("ok");
                System.out.println("result:");
                System.out.println(result);
            } catch (IOException e) {
                System.out.println("Error1");
            }

            /*
            restClient=new RestClient();
            try {
                System.out.println("init2");
                result=restClient.getString("getStatus?dni=12345678A ");
                System.out.println("ok2");
                System.out.println("result2:");
                System.out.println(result);
            } catch (IOException e) {
                System.out.println("Error2");
                System.out.println("e.getMessage(): "+e.getMessage());
                System.out.println("e.toString(): "+e.toString());
                System.out.println("e.getLocalizedMessage(): "+e.getLocalizedMessage());
                System.out.println("e.hashCode(): "+e.hashCode());

                e.printStackTrace();
            }
            */



            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {

        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(Void result) {

        }

        @Override
        protected void onCancelled() {

        }
    }


}
