package com.gphsopper.ionglider.demo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.gphsopper.ionglider.IonGlider;


public class Demo extends ActionBarActivity {

    ImageView imageView;
    Button btnLoadImage;
    ProgressBar progressBar;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        imageView = (ImageView) findViewById(R.id.image_view);
        
        btnLoadImage = (Button) findViewById(R.id.btnLoadImage);
        btnLoadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadImage();
            }
        });
        
    }

    static final String IMAGE_URL = "https://rbauat1.cyota.com/generic/images/efamily/stu/BizScience/000310_2119_1019_oslp.jpg";
    
    //
    void loadImage() {
        progressBar.setProgress(0);
        IonGlider.with(this)
                 .load(IMAGE_URL)
                 .progressBar(progressBar)
                 .glide()
                 .placeholder(R.drawable.image_placeholder)
                 .into(imageView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_demo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
