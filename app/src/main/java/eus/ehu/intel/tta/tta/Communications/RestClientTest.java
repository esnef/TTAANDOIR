package eus.ehu.intel.tta.tta.Communications;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.IOException;

import eus.ehu.intel.tta.tta.DataType.Test;

/**
 * Created by eduardo on 4/01/16.
 */
public class RestClientTest {

    private String user="12345678A";
    private String password="tta";
    private String id="1";
    public  final static String PATH_SERVER="http://u017633.ehu.eus:18080/AlumnoTta/rest/tta";
    private static final String APPLICATION_GET_TEST="getTest?id=";
    private static final String APPLICATION_GET_STATUS="getStatus?dni=";
    private static final String APPLICATION_GET_EXERCISE="getExercise?id=";
    private static final String APPLICATION_POST_EXERCISE="postExercise?" + "user=";
    private static final String APPLICATION_POST_CHOICE="postChoice";
    private static OnRestClientListener onRestClientListener=null;

    public void getStatus(String dni){

    }

    public Test getTest(String id) throws IOException {
        RestClient restClient=new RestClient(PATH_SERVER);
        if(user==null || password==null)return null;
        restClient.setHttpBasicAuth(user,password);
            String resultJSON=restClient.getString(new String(APPLICATION_GET_TEST+id));
            if(resultJSON!=null) {
                Gson gson = new Gson();
                Test test = gson.fromJson(resultJSON, Test.class);
                if (onRestClientListener != null) onRestClientListener.onGetTest(test);
                return test;
            }
        return null;
    }

    public Test getTest(int id) throws IOException {
        if(id>0){
            return getTest(new Integer(id).toString());
        }
        return null;

    }

    public Test getTest() throws IOException {
        if(id!=null && !id.equals("")){
            return getTest(id);
        }
        return null;

    }


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public static void setOnRestClientListener(OnRestClientListener onRestClientListener) {
        RestClientTest.onRestClientListener = onRestClientListener;
    }

    public interface OnRestClientListener{
        public void onGetTest(Test test);
        public void onGetTestError(Exception exception);

    }


}
