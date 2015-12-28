package eus.ehu.intel.tta.tta;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by eduardo on 28/12/15.
 */
public class Engine extends BroadcastReceiver{
    private final static String TAG = Engine.class.getCanonicalName();
    private static Engine mEngine;
    private boolean start=false;
    private Context mContext;
    public Engine(){

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(mContext,"6547", Toast.LENGTH_SHORT).show();
        if(intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            ConnectivityManager conn=(ConnectivityManager)mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo =conn.getActiveNetworkInfo();
            if(networkInfo!=null && !networkInfo.isConnected()){
                Log.d(TAG, "Internet is disconnected: " + String.valueOf(networkInfo));
                Toast.makeText(mContext,"Internet is disconnected: " + String.valueOf(networkInfo), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public static Engine getInstance(){
        if(mEngine==null){
            mEngine=new Engine();
        }
        return mEngine;
    }



    public boolean start(Context context){
        //Iniciamos la escucha de los mensajes de broadcast
        Log.v(TAG,"start Engine");
        this.mContext=context;
        IntentFilter filter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        mContext.registerReceiver(this,filter);
        start=true;

        return start;
    }

    public boolean isStart(){
        return start;
    }

    public boolean stop(){
        start=false;
        return start;
    }
}
