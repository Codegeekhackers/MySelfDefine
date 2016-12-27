package com.lan.myselfdefine;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

/**
 * Created by X_S on 2016/10/29.
 */

public class AnimationUtils {

    public static void out(View view,long delaytime){
        RotateAnimation rotateAnimation = new RotateAnimation(0, -180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1f);
        //execute the animation  time
        rotateAnimation.setDuration(200);
        //set the animation after layout
        rotateAnimation.setStartOffset(delaytime);
        rotateAnimation.setFillAfter(true);

        view.startAnimation(rotateAnimation);
    }
    public static void in(View view,long delaytime){
        RotateAnimation rotateAnimation = new RotateAnimation(-180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1f);
        rotateAnimation.setDuration(200);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setStartOffset(delaytime);
        view.startAnimation(rotateAnimation);
    }
}
