package eus.ehu.intel.tta.tta.Screens;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import eus.ehu.intel.tta.tta.R;

public class ScreensExercise extends ScreensBase implements View.OnClickListener {
    private final static String TAG = ScreensExercise.class.getCanonicalName();
    private EditText screen_login_editText_user;
    private EditText screen_login_editText_pass;
    private Button screen_login_button_access;
    private Uri pictureUri=null;
    private Uri videoUri=null;
    private Uri audioUri=null;

    private static final int UP_PICTURE_REQUEST_CODE=144;
    private static final int PICTURE_REQUEST_CODE=145;
    private static final int VIDEO_REQUEST_CODE=146;
    private static final int AUDIO_REQUEST_CODE=147;



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
        Intent intent;
        switch (idOnClick){
            case R.id.screen_exercise_button_up_image:
                intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,UP_PICTURE_REQUEST_CODE);

                break;
            case R.id.screen_exercise_button_making_photo:
                Log.d(TAG,"you touch screen for making photo");
                Toast.makeText(this,"you touch screen for making photo",Toast.LENGTH_SHORT).show();

                if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
                    Toast.makeText(this, R.string.no_camera,Toast.LENGTH_LONG).show();

                }else{
                    intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if(intent.resolveActivity(getPackageManager())!=null){
                        File dir= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                        File file= null;
                        try {
                            file = File.createTempFile("tta", ".jpg", dir);
                            pictureUri = Uri.fromFile(file);
                            Toast.makeText(this, pictureUri.getPath(),Toast.LENGTH_SHORT).show();
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, pictureUri);
                            startActivityForResult(intent, PICTURE_REQUEST_CODE);
                        } catch (IOException e) {
                        }


                    }else{
                        Toast.makeText(this, R.string.no_app_camera,Toast.LENGTH_LONG).show();
                    }
                }
                break;
            case R.id.screen_exercise_button_audio_recording:
                if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_MICROPHONE)){
                    Toast.makeText(this, R.string.no_microphone,Toast.LENGTH_LONG).show();

                }else{
                    intent=new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
                    if(intent.resolveActivity(getPackageManager())!=null){
                        startActivityForResult(intent, AUDIO_REQUEST_CODE);
                    }else{
                        Toast.makeText(this, R.string.no_app_microphone,Toast.LENGTH_LONG).show();
                    }
                }
                break;
            case R.id.screen_exercise_button_video_recording:
                if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
                    Toast.makeText(this, R.string.no_camera,Toast.LENGTH_LONG).show();

                }else{
                    intent=new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                    if(intent.resolveActivity(getPackageManager())!=null){
                        startActivityForResult(intent, VIDEO_REQUEST_CODE);
                    }else{
                        Toast.makeText(this, R.string.no_app_camera,Toast.LENGTH_LONG).show();
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode!=RESULT_OK)return;
        switch (requestCode){

            case PICTURE_REQUEST_CODE:

                    if(pictureUri!=null && data!=null){
                       //data es null y por ello debemos usar pictureUri para saber la localización del archivo donde se a almacenado la imagen.
                        /*
                        if(dir!=null){
                            Toast.makeText(this, dir,Toast.LENGTH_SHORT).show();
                            Toast.makeText(this, "12",Toast.LENGTH_SHORT).show();
                        }
                        */

                    }


                break;
            case UP_PICTURE_REQUEST_CODE:
            case VIDEO_REQUEST_CODE:
            case AUDIO_REQUEST_CODE:
                if(data!=null){
                    Toast.makeText(this, "12 "+data.getData().getPath(),Toast.LENGTH_SHORT).show();
                    //desde aqui se procedera a subir el dato
                }

                break;
            default:
                break;
        }
    }
}
