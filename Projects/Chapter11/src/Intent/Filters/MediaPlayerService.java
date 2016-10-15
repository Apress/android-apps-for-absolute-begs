package Intent.Filters;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.media.MediaPlayer;

public class MediaPlayerService extends Service {
	MediaPlayer myMediaPlayer;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void onCreate() {	
		myMediaPlayer = MediaPlayer.create(this, R.raw.one_obe_radio_one);
		myMediaPlayer.setLooping(true);
	}
	@Override
	public void onStart(Intent intent, int startid) {
		myMediaPlayer.start();
	}
	@Override
	public void onDestroy() {
		myMediaPlayer.stop();
	}
}
