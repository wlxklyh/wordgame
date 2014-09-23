package wordsgame.main.mvc;

import java.util.HashMap;
import java.util.Map;

import smallgameengine.help.FIXVALUE;

import com.example.wordsgame.R;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.Vibrator;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity  implements FIXVALUE{
	private OpenGlRenderer myrenderer = null;
	private GLListener listener;
	public MediaPlayer mp;
	public SoundPool sp;
	public Map<Integer, Integer> map;
	private GestureDetector mGestureDetector;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//////
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		myrenderer = new OpenGlRenderer(this);
		mGestureDetector = new GestureDetector(this,
				new GLListener(myrenderer,
						(Vibrator) getSystemService(Service.VIBRATOR_SERVICE),
						this, mp));

		GLSurfaceView view = new GLSurfaceView(this);
		view.setRenderer(myrenderer);
		setContentView(view);
	}
	
	@Override
    public boolean onTouchEvent(MotionEvent event) {
    
		if(event.getAction()==MotionEvent.ACTION_UP){
			if(myrenderer.game!=null)
			myrenderer.game.touchUp(event);
		}

        if (mGestureDetector.onTouchEvent(event))
            return true;
        else
            return false;
    }

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO 自动生成方法存根
		
		if (keyCode == KeyEvent.KEYCODE_MENU) {

		}
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// finish();
			switch (myrenderer.scene) {
			case HOMEPAGE:
				new AlertDialog.Builder(this)
						.setTitle("确定退出游戏？")
						// 设置标题
						// .setCustomTitle(View) // 以一个 View 作为标题
						.setIcon(R.drawable.ic_launcher)
						// 设置标题图片
						// .setMessage("信息") // 需要显示的弹出内容
						.setPositiveButton("确定", new OnClickListener() { // 设置弹框的确认按钮所显示的文本，以及单击按钮后的响应行为
									@Override
									public void onClick(DialogInterface a0,
											int a1) {
										finish();
									}
								})
						.setNegativeButton("取消", new OnClickListener() { // 设置弹框的确认按钮所显示的文本，以及单击按钮后的响应行为
									@Override
									public void onClick(DialogInterface a0,
											int a1) {

									}
								}).show();

				break;
			case GAME:
				myrenderer.scene=HOMEPAGE;
				break;
			}
		}
		return true;
	}

	public void initSoundPool() {
		sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
		map = new HashMap<Integer, Integer>();
		// mp = MediaPlayer.create( this, R.raw.bgm );
		mp.setLooping(true);
		mp.start();
	}

	public void playSound(int id) {
		AudioManager am = (AudioManager) getSystemService(MainActivity.AUDIO_SERVICE);
		float audioMaxVolume = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		float audioCurrentVolume = am
				.getStreamVolume(AudioManager.STREAM_MUSIC);
		float audioRatio = audioCurrentVolume / audioMaxVolume;
		sp.play(map.get(id), audioRatio, audioRatio, 1, 0, 1);
	}

}
