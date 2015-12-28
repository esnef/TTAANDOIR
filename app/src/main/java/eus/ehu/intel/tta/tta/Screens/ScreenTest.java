package eus.ehu.intel.tta.tta.Screens;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.IOException;
import java.util.ArrayList;

import eus.ehu.intel.tta.tta.AudioPlayer;
import eus.ehu.intel.tta.tta.DataType.Test;
import eus.ehu.intel.tta.tta.R;

public class ScreenTest extends ScreensBase implements Runnable{
    private final static String TAG = ScreenTest.class.getCanonicalName();

    private Button screen_menu_button_tracing;
    private RadioGroup screen_test_RadioGroup_items;
    private TextView screen_test_TextView_question;
    private LinearLayout screen_test_LinearLayout;
    private int positionSelect=-1;
    private ArrayList<RadioButton> radioButtons;


    private AudioPlayer audioPlayer=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_test);


        //Generamos test ejemplo;
        ArrayList<String> items=new ArrayList<String>();
        items.add("prueba1");
        items.add("prueba2");
        items.add("prueba3");
        items.add("prueba4");

        //Test test=new Test(items,items.get(0),"PREGUNTA","<html><body><h1>gfigisagigdspfgagai</h1></body></html>",Test.HTML_HELP);
        //Test test=new Test(items,items.get(0),"PREGUNTA","http://www.google.es",Test.URL_HELP);
        //Test test=new Test(items,items.get(0),"PREGUNTA","http://techslides.com/demos/sample-videos/small.mp4",Test.VIDEO_HELP);
        Test test=new Test(items,items.get(0),"PREGUNTA","http://soundjax.com/reddo/80656%5EHORSES.mp3",Test.AUDIO_HELP);


        dataNow.addTests(test);


        screen_test_TextView_question=(TextView)findViewById(R.id.screen_test_TextView_question);
        screen_menu_button_tracing=(Button)findViewById(R.id.screen_menu_button_tracing);
        screen_test_RadioGroup_items=(RadioGroup)findViewById(R.id.screen_test_RadioGroup_items);
        screen_test_LinearLayout=(LinearLayout)findViewById(R.id.screen_test_LinearLayout);



        screen_test_TextView_question.setText(dataNow.getTests().get(0).getQuestion());
        radioButtons=new ArrayList<RadioButton>();
        if(dataNow!=null && dataNow.getTests().get(0).getItems()!=null){

            for(int con=0;con<dataNow.getTests().get(0).getItems().size();con++){
                RadioButton radioButton=new RadioButton(this);
                radioButton.setText(dataNow.getTests().get(0).getItems().get(con));
                radioButtons.add(radioButton);
                screen_test_RadioGroup_items.addView(radioButton);
            }
        }

        screen_test_RadioGroup_items.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.d(TAG, "id" + checkedId);
                RadioButton radio=(RadioButton)findViewById(checkedId);
                int index=screen_test_RadioGroup_items.indexOfChild(radio);
                if(index==dataNow.getTests().get(0).getSolutionPosition()){
                    //Es correcto

                }else{
                    //Es false
                }
                screen_menu_button_tracing.setVisibility(View.VISIBLE);
                positionSelect=index;
            }

        });





        screen_menu_button_tracing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = screen_test_RadioGroup_items.getCheckedRadioButtonId();
                for(int con=0;con<radioButtons.size();con++){
                    radioButtons.get(con).setEnabled(false);
                }
                if (positionSelect == -1) {
                    return;
                }
                if (positionSelect == dataNow.getTests().get(0).getSolutionPosition()) {
                    //Es correcto
                    Log.d(TAG, getString(R.string.OK));
                    Toast.makeText(getApplicationContext(), getString(R.string.OK), Toast.LENGTH_SHORT).show();
                    radioButtons.get(positionSelect).setBackgroundColor(Color.GREEN);
                } else {
                    //Es false
                    Log.d(TAG, getString(R.string.FAIL));
                    Toast.makeText(getApplicationContext(), getString(R.string.FAIL), Toast.LENGTH_SHORT).show();
                    radioButtons.get(positionSelect).setBackgroundColor(Color.RED);
                    radioButtons.get(dataNow.getTests().get(0).getSolutionPosition()).setBackgroundColor(Color.GREEN);
                    showHtml(dataNow.getTest(0).getHelp(),dataNow.getTest(0).getTypeHelp());
                }

            }
        });
    }

    private void showHtml(String help, int typeHelp){

        if(help==null){
            return;
        }
        Uri uri;
        switch (typeHelp){
            case Test.HTML_HELP:
                WebView web=new WebView(this);
                web.loadData(help,"text/html",null);
                web.setBackgroundColor(Color.TRANSPARENT);
                web.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
                screen_test_LinearLayout.addView(web);
                break;
            case Test.URL_HELP:
                uri= Uri.parse(help);
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
                break;
            case Test.AUDIO_HELP:
                uri= Uri.parse(help);
                audioPlayer=new AudioPlayer(screen_test_LinearLayout,this);
                try {
                    audioPlayer.setAudioUri(uri);
                } catch (IOException e) {
                }
                break;
            case Test.VIDEO_HELP:
                uri= Uri.parse(help);
                VideoView videoView=new VideoView(this);
                ViewGroup.LayoutParams params=new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                );

                MediaController controller=new MediaController(this){
                    @Override
                    public void hide(){

                    }

                    @Override
                    public boolean dispatchKeyEvent(KeyEvent event){
                        if(event.getKeyCode()==KeyEvent.KEYCODE_BACK){
                            try {
                                finalize();
                            } catch (Throwable throwable) {
                            }
                        }

                        return super.dispatchKeyEvent(event);
                    }
                };
                videoView.setMediaController(controller);
                videoView.setLayoutParams(params);
                //Indicamos la URL del video
                videoView.setVideoURI(uri);


                controller.setAnchorView(videoView);

                screen_test_LinearLayout.addView(videoView);
                videoView.start();
                break;
            default:
                break;
        }

    }


    @Override
    public void run() {
        //Ejecutar al final de la ejecución de la musica.

    }
}
