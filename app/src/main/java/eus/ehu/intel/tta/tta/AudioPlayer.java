package eus.ehu.intel.tta.tta;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.KeyEvent;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by eduardo on 28/12/15.
 */
public class AudioPlayer implements MediaController.MediaPlayerControl, MediaPlayer.OnPreparedListener{

    private View view;
    private MediaPlayer player;
    private MediaController controller;


    public AudioPlayer(View view, final Runnable onExit){
        this.view=view;
        player=new MediaPlayer();
        player.setOnPreparedListener(this);
        controller=new MediaController(view.getContext()){
          @Override
          public boolean dispatchKeyEvent(KeyEvent event){
              if(event.getKeyCode()==KeyEvent.KEYCODE_BACK){
                  try {
                      release();
                      onExit.run();
                  } catch (Throwable throwable) {
                  }
              }

              return super.dispatchKeyEvent(event);
          }
        };
    }

    public void setAudioUri(Uri uri) throws IOException {
       player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.setDataSource(view.getContext(), uri);
        player.prepare();
        player.start();
    }

    public void release(){
        if(player!=null){
            player.stop();
            player.release();
            player=null;
        }
    }

    @Override
    public void start() {
        player.start();
    }

    @Override
    public void pause() {
        player.pause();
        //player.stop();
    }



    @Override
    public void onPrepared(MediaPlayer mp) {
        controller.setMediaPlayer(this);
        controller.setAnchorView(view);
        controller.show(0);
    }


    @Override
    public int getDuration() {
        return player.getDuration();
    }

    @Override
    public int getCurrentPosition() {
        return player.getCurrentPosition();
    }

    @Override
    public void seekTo(int pos) {
        player.seekTo(pos);
    }

    @Override
    public boolean isPlaying() {
        return player.isPlaying();
    }

    @Override
    public int getBufferPercentage() {
        return 0;
    }

    @Override
    public boolean canPause() {
        return false;
    }

    @Override
    public boolean canSeekBackward() {
        return false;
    }

    @Override
    public boolean canSeekForward() {
        return false;
    }

    @Override
    public int getAudioSessionId() {
        return player.getAudioSessionId();
    }


}
