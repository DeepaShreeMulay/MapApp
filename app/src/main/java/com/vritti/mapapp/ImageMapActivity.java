package com.vritti.mapapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import uk.co.senab.photoview.PhotoViewAttacher;

public class ImageMapActivity extends AppCompatActivity {
	ImageView imageView;
	Button btngooglemap;
	private Context parent;
	TextView image1, image2;

	PhotoViewAttacher photoViewAttacher ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_imagemap);
		initialize();
		setListener();
		Drawable drawable = getResources().getDrawable(R.drawable.waimap);
		imageView.setImageDrawable(drawable);
		photoViewAttacher = new PhotoViewAttacher(imageView);
		photoViewAttacher.update();
	}

	private void setListener() {
		btngooglemap.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(parent,
						HeritageMapActivity.class));
			}
		});

		image1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				image1.setTextColor(getResources().getColor(R.color.clickcolor));
				image2.setTextColor(getResources().getColor(R.color.colorAccent));
				Drawable drawable = getResources().getDrawable(R.drawable.waisrding);
				imageView.setImageDrawable(drawable);
				photoViewAttacher = new PhotoViewAttacher(imageView);
				photoViewAttacher.update();
			}
		});

		image2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				image2.setTextColor(getResources().getColor(R.color.clickcolor));
				image1.setTextColor(getResources().getColor(R.color.colorAccent));
				Drawable drawable = getResources().getDrawable(R.drawable.waimap);
				imageView.setImageDrawable(drawable);
				photoViewAttacher = new PhotoViewAttacher(imageView);
				photoViewAttacher.update();;
			}
		});
	}

	private void initialize() {
		parent = ImageMapActivity.this;
		imageView = (ImageView) findViewById(R.id.imageView);
		image1 = (TextView) findViewById(R.id.image1);
		image2 = (TextView) findViewById(R.id.image2);
		btngooglemap = (Button) findViewById(R.id.btngooglemap);
		//Picasso.with(parent).load(waimapy).into(imageView);
	}

}
