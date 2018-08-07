package com.vritti.mapapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class InfoActivity extends AppCompatActivity {
	TextView textplace, textaddress, textdesc, texturl, textlatlng;
	String place , address , desc , web="no web";
	private Context parent;
	RelativeLayout layout;
	String Lat, Lng;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		initialize();
		GetIntentExtras();

		texturl.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (web.contains("WIP") || web == null || web.equals("no web")){
					Toast.makeText(InfoActivity.this, "Coming Soon... ",Toast.LENGTH_SHORT).show();
				}else {
					Intent httpIntent = new Intent(Intent.ACTION_VIEW);
					httpIntent.setData(Uri.parse(web));
					startActivity(httpIntent);
				}
			}
		});
	}

	private void GetIntentExtras() {
		Bundle bundle = getIntent().getExtras();
		if( bundle != null){
			Lat = (String) bundle.get("markerLat");
			Lng = (String) bundle.get("markerLng");
			//textlatlng.setText(Lat+", "+Lng);
			getURL(Lat,Lng);
		}
	}

	private void initialize() {
		parent = InfoActivity.this;
		textplace = (TextView) findViewById(R.id.textplace);
		textaddress = (TextView) findViewById(R.id.textaddress);
		textdesc = (TextView) findViewById(R.id.textdesc);
		texturl = (TextView) findViewById(R.id.texturl);
		//textlatlng = (TextView) findViewById(R.id.textlatlng);
		textplace.setText("No info");
		textaddress.setText("No info");
		textdesc.setText("No info");
		texturl.setText("No info");
	}
	private void getURL(String lat , String lng) {
		//String web =null;
		DatabaseHelper db = new DatabaseHelper(getApplicationContext());
		SQLiteDatabase sql = db.getWritableDatabase();

		Cursor c = sql.rawQuery("Select * from " + DatabaseHelper.PLACES_TABLE_NAME +" where "
				+DatabaseHelper.lat+ " ='"+lat+"' and "+
				DatabaseHelper.lng + " = '"+lng+"'", null);
		int count = c.getCount();
		if (count== 0){
			c.close();
			sql.close();
			db.close();
		}else{
			c.moveToFirst();
			place = c.getString( c.getColumnIndex(DatabaseHelper.place_name) );
			address = c.getString( c.getColumnIndex(DatabaseHelper.vicinity) );
			desc = c.getString( c.getColumnIndex(DatabaseHelper.info) );
			web = c.getString( c.getColumnIndex(DatabaseHelper.reference) );
			textplace.setText(place);
			textaddress.setText(address);
			textdesc.setText(desc);
			texturl.setText(web);
			if (web.contains("WIP") || web == null || web.equals("no web")){
				//Toast.makeText(InfoActivity.this, "Coming Soon... ",Toast.LENGTH_SHORT).show();
			}else {
				texturl.setTextColor(getResources().getColor(R.color.hyperlink));
				String s = "<i>"+web+"</i>";
						s = "<u>"+s+"</u>";
				texturl.setText(Html.fromHtml(s));
			}
			c.close();
			sql.close();
			db.close();
		}
		//return web;
	}
}
