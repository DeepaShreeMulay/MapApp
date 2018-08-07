package com.vritti.mapapp;

import android.app.ActionBar;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SplashActivity extends AppCompatActivity {
	private Context parent;
	//private ImageView splashImageView, vrittiCreatingWaves;
	public static final String REG_ID = "regId";
	RelativeLayout layout;

	public static String regId;
	private static final String APP_VERSION = "appVersion";
	public static final String MyPREFERENCES = "LoginPrefs";
	SharedPreferences sharedpreferences;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_splash);
		layout =(RelativeLayout)findViewById(R.id.background);
		layout.setBackgroundResource(R.drawable.splashnew);
		initialize();
		//regId = "APA91bGLHoalw3ZzW1OkJYdLt-r7of3Fx0UIOh9iFI6kCyV-IOoAaM0yxorChduO-4woQzOfsdxfxyfkS89Dh7A30UEUmxTPg5_aeLn1X7e29hWQ3aNjx98_qGTR1lTNhz4VF2y9yT4O";


		new Thread() {
			public void run() {

				try {
					applyAnimations();
					sleep(5 * 1000);

					//savemyimages();

						startActivity(new Intent(parent,
							ImageMapActivity.class));

					finish();
				} catch (Exception e) {
				}
			}
		}.start();
	}
/*

	private void savemyimages() {
		saveToInternalStoragewaimapy(BitmapFactory.decodeResource(getResources(), R.drawable.waimap));
		saveToInternalStoragewaisrding(BitmapFactory.decodeResource(getResources(), R.drawable.waisrding));

	}

	private String saveToInternalStoragewaimapy(Bitmap bitmapImage){
		ContextWrapper cw = new ContextWrapper(getApplicationContext());
		// path to /data/data/yourapp/app_data/imageDir
		File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
		// Create imageDir
		File mypath=new File(directory,"waimapy.png");

		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(mypath);
			// Use the compress method on the BitMap object to write image to the OutputStream
			bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return directory.getAbsolutePath();
	}
	private String saveToInternalStoragewaisrding(Bitmap bitmapImage){
		ContextWrapper cw = new ContextWrapper(getApplicationContext());
		// path to /data/data/yourapp/app_data/imageDir
		File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
		// Create imageDir
		File mypath=new File(directory,"waisrding.png");

		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(mypath);
			// Use the compress method on the BitMap object to write image to the OutputStream
			bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return directory.getAbsolutePath();
	}
*/


	private void applyAnimations() {
		new AnimationUtils();
		Animation animation = AnimationUtils.loadAnimation(parent,R.anim.appear);
		animation.reset();
		final Animation animation1 = AnimationUtils.loadAnimation(parent,R.anim.appear);
		animation1.reset();

		animation.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationEnd(Animation animation) {
			}
		});
	}

	private void initialize() {
		parent = SplashActivity.this;
	}
}
