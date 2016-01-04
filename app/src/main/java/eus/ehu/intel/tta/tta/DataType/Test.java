package eus.ehu.intel.tta.tta.DataType;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import eus.ehu.intel.tta.tta.AudioPlayer;

/**
 * Created by alumno on 15/12/15.
 */

public class Test implements Serializable {
    /*
    public  final static int HTML_HELP=1;
    public final static int URL_HELP=2;
    public  final static int VIDEO_HELP=3;
    public final static int AUDIO_HELP=4;


    public  final static String HTML_HELP_STRING="html";
    public final static String URL_HELP_STRING="html";
    public  final static String VIDEO_HELP_STRING="mp4";
    public final static String AUDIO_HELP_STRING="mp3";
    */

    @SerializedName("wording")
    private String wording=null;
    private ArrayList<Choice> choices=null;


    public Test(){
        choices=new ArrayList<>();
        wording=new String();
    }

    public Test(ArrayList<Choice> choices,String wording){
        this.choices=choices;
        this.wording=wording;

    }

    public int getSolutionPosition(){
        int solutionPosition=-1;
        if(choices!=null){
            for(int con=0;con<choices.size();con++){
                if(choices.get(con).getCorrect())return con;
            }
        }
        return solutionPosition;
    }

    public String getWording() {
        return wording;
    }

    public void setWording(String wording) {
        this.wording = wording;
    }

    public ArrayList<Choice> getChoices() {
        return choices;
    }

    public void setChoices(ArrayList<Choice> choices) {
        this.choices = choices;
    }

    /*
    public int getTypeHelp(int position){
        int positionHelp=-1;
        if(choices.get(position).getResourceType().getDescription().equals(HTML_HELP_STRING)){
            positionHelp=HTML_HELP;
            if(choices.get(position).getAdvise().compareToIgnoreCase("http://")>=0){
                positionHelp=URL_HELP;
            }
        }else if(choices.get(position).getResourceType().getDescription().equals(URL_HELP_STRING)){
            positionHelp=URL_HELP;
        }else if(choices.get(position).getResourceType().getDescription().equals(VIDEO_HELP_STRING)){
            positionHelp=AUDIO_HELP;
        }else if(choices.get(position).getResourceType().getDescription().equals(AUDIO_HELP_STRING)){
            positionHelp=VIDEO_HELP;
        }
        switch (positionHelp){
            case Test.HTML_HELP:

                break;
            case Test.URL_HELP:

                break;
            case Test.AUDIO_HELP:

                break;
            case Test.VIDEO_HELP:

                break;
            default:
                break;
        }
        return positionHelp;
    }
    */


}
