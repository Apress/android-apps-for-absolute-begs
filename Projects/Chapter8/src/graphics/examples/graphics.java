package graphics.examples;

import android.os.Bundle;
import android.app.Activity;
//import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;
import android.net.Uri;
import android.widget.VideoView;
import android.widget.MediaController;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.TransitionDrawable;

public class graphics extends Activity {
	AnimationDrawable logoAnimation;
    @Override                            /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //ImageView logoImage = (ImageView) findViewById(R.id.iv1);
        //logoImage.setBackgroundResource(R.drawable.logo_animation);
        //logoAnimation = (AnimationDrawable) logoImage.getBackground();           
        TextView textAnim = (TextView) findViewById(R.id.TV1);
        Animation textAnimation = AnimationUtils.loadAnimation(this, R.anim.text_animation);
        textAnim.startAnimation(textAnimation);        
    	TransitionDrawable trans = (TransitionDrawable) getResources().getDrawable(R.drawable.image_transition);
        ImageView transImage = (ImageView) findViewById(R.id.imgTrans);
        transImage.setImageDrawable(trans);
        trans.startTransition(10000);
        
        Uri vidFile = Uri.parse("http://www.example.com/test.3gp");        
        VideoView videoView = (VideoView) findViewById(R.id.VideoView);
        videoView.setVideoURI(vidFile);
        videoView.setMediaController(new MediaController(this));     
        videoView.start();       
    }
    //public boolean onTouchEvent(MotionEvent event) {
    //	  if (event.getAction() == MotionEvent.ACTION_DOWN) {
    //	    logoAnimation.start();
    //	    return true;
    //	  }
    //	  else return super.onTouchEvent(event);
    //}
}