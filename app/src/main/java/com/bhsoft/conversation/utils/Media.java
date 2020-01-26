package com.bhsoft.conversation.utils;

import android.media.MediaPlayer;

import com.bhsoft.conversation.Base.BaseApplication;


public class Media {
    public static Media mediaUtils;
    public static MediaPlayer mediaPlayer;

    public static Media getInstance() {
        if (mediaUtils == null) {
            mediaUtils = new Media();

        }


        return mediaUtils;
    }

    private void stop() {
        if (mediaPlayer.isPlaying() == true) {
            mediaPlayer.stop();
        }
    }


    public void play(int source) {

        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(BaseApplication.getContext(), source);
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                }
            });
            try {
                stop();
                mediaPlayer.prepareAsync();
                //     mediaPlayer.seekTo(200);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.start();
                        mp.seekTo(200);
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();

            }
        }
        else {
            stop();
            //   mediaPlayer.release();
            mediaPlayer=null;
            //play(source);

        }

    }

}
