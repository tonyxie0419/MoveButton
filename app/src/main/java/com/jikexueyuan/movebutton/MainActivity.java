package com.jikexueyuan.movebutton;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnAnimationXML;
    private Button btnAnimationJava;
    private Button btnAnimatorXML;
    private Button btnAnimatorJava;
    private ImageView ivCustomAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setOnClickListener();
    }

    private void init() {
        btnAnimationXML = (Button) findViewById(R.id.btnAnimationXML);
        btnAnimationJava = (Button) findViewById(R.id.btnAnimationJava);
        btnAnimatorXML = (Button) findViewById(R.id.btnAnimatorXML);
        btnAnimatorJava = (Button) findViewById(R.id.btnAnimatorJava);
        ivCustomAnimation = (ImageView) findViewById(R.id.ivCustomAnimation);
    }

    private void setOnClickListener() {
        btnAnimationXML.setOnClickListener(this);
        btnAnimationJava.setOnClickListener(this);
        btnAnimatorXML.setOnClickListener(this);
        btnAnimatorJava.setOnClickListener(this);
        ivCustomAnimation.setOnClickListener(this);
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.btnAnimationXML:
                v.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim
                        .animation_btn));
                break;
            case R.id.btnAnimationJava:
                AnimationSet animationSet = new AnimationSet(true);

                TranslateAnimation taX = new TranslateAnimation(0, 300, 0, 0);
                taX.setDuration(1000);

                TranslateAnimation taY = new TranslateAnimation(0, 0, 0, 300);
                taY.setStartOffset(1000);
                taY.setDuration(1000);

                animationSet.addAnimation(taX);
                animationSet.addAnimation(taY);
                v.startAnimation(animationSet);
                break;
            case R.id.btnAnimatorXML:
                AnimatorSet animatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R
                        .animator.animator_btn);
                animatorSet.setTarget(v);
                animatorSet.start();
                break;
            case R.id.btnAnimatorJava:
                AnimatorSet animator = new AnimatorSet();
                animator.setDuration(1000);
                animator.playSequentially(
                        ObjectAnimator.ofFloat(v, "translationX", 0, 300),
                        ObjectAnimator.ofFloat(v, "translationY", 0, 300),
                        ObjectAnimator.ofFloat(v, "translationY", 300, 0),
                        ObjectAnimator.ofFloat(v, "translationX", 300, 0)
                );
                animator.start();
                break;
            case R.id.ivCustomAnimation:
                CustomAnim caRotateA = new CustomAnim(0, 180);
                caRotateA.setDuration(1000);
                caRotateA.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        CustomAnim caRotateB = new CustomAnim(180, 0);
                        caRotateB.setDuration(1000);
                        v.startAnimation(caRotateB);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                v.startAnimation(caRotateA);
                break;
        }
    }
}
