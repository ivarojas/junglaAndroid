package com.taller.jandroid;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class AnimalAttackActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_attack);
        
        //indie
        ImageView image = (ImageView) findViewById(R.id.imageView1);
        Animation anim1 = AnimationUtils.loadAnimation(this, R.anim.translate_indie);
        anim1.setDuration(3000);
        
        //left leaf
        ImageView leaf_left = (ImageView) findViewById(R.id.leafLeft);
        Animation anim_leaf_left = AnimationUtils.loadAnimation(this, R.anim.rotate_leaf_left);
        anim_leaf_left.setDuration(3000);
        
        //right leaf
        ImageView leaf_right = (ImageView) findViewById(R.id.leafRight);
        Animation anim_leaf_right = AnimationUtils.loadAnimation(this, R.anim.rotate_leaf_right);
        anim_leaf_right.setDuration(3000);
        
        ImageView plant_left = (ImageView) findViewById(R.id.plantLeft);
        Animation anim_plant_left = AnimationUtils.loadAnimation(this, R.anim.slide_out_left);
        anim_plant_left.setDuration(3000);
        
        ImageView plant_right = (ImageView) findViewById(R.id.plantRight);
        Animation anim_plant_right = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
        anim_plant_right.setDuration(3000);
        
        leaf_left.startAnimation(anim_leaf_left);
        leaf_right.startAnimation(anim_leaf_right);
        plant_left.startAnimation(anim_plant_left);
        plant_right.startAnimation(anim_plant_right);
        image.startAnimation(anim1);
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_animal_attack, menu);
        return true;
    }*/
}
