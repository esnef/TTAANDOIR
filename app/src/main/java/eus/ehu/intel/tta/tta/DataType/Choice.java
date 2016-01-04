package eus.ehu.intel.tta.tta.DataType;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by eduardo on 3/01/16.
 */
public class Choice implements Serializable {

    public  final static int HTML_HELP=1;
    public final static int URL_HELP=2;
    public  final static int VIDEO_HELP=3;
    public final static int AUDIO_HELP=4;


    public  final static String HTML_HELP_STRING="html";
    public final static String URL_HELP_STRING="html";
    public  final static String VIDEO_HELP_STRING="mp4";
    public final static String AUDIO_HELP_STRING="mp3";

    private String advise;
    private String answer;
    private Boolean correct;
    @SerializedName("ResourceType")
    private ResourceType resourceType;


    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    public String getAdvise() {
        return advise;
    }

    public void setAdvise(String advise) {
        this.advise = advise;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }
    @SuppressWarnings("unchecked")
    public int getResourceTypeInt(){
        int positionHelp=-1;
        //if(getResourceType()==null)return positionHelp;
        if(getResourceType().getDescription().equals(HTML_HELP_STRING)){
            positionHelp=HTML_HELP;
            if(getAdvise().compareToIgnoreCase("http://")>=0){
                positionHelp=URL_HELP;
            }
        }else if(getResourceType().getDescription().equals(URL_HELP_STRING)){
            positionHelp=URL_HELP;
        }else if(getResourceType().getDescription().equals(VIDEO_HELP_STRING)){
            positionHelp=AUDIO_HELP;
        }else if(getResourceType().getDescription().equals(AUDIO_HELP_STRING)){
            positionHelp=VIDEO_HELP;
        }
        switch (positionHelp){
            case Choice.HTML_HELP:

                break;
            case Choice.URL_HELP:

                break;
            case Choice.AUDIO_HELP:

                break;
            case Choice.VIDEO_HELP:

                break;
            default:
                break;
        }
        return positionHelp;
    }


}
