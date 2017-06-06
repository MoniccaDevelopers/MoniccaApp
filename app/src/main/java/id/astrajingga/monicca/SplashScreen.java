package id.astrajingga.monicca;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SplashScreen extends AppCompatActivity {
    // variables
    Typeface typeface;

    // view pager
    ViewPager viewPager;

    Button splashscreenButtonLearnmore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen_content_activity);

        // view pager
        viewPager = (ViewPager) findViewById(R.id.splashscreen_viewpager);
        SplashScreenViewPagerAdapter splashScreenViewPagerAdapter = new SplashScreenViewPagerAdapter(this);
        viewPager.setAdapter(splashScreenViewPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if (position == 3) {
                    typeface = Typeface.createFromAsset(getAssets(), "fonts/BebasNeue.otf");
                    splashscreenButtonLearnmore = (Button) findViewById(R.id.splashscreen_btn_learnmore);
                    splashscreenButtonLearnmore.setTypeface(typeface);
                    splashscreenButtonLearnmore.setVisibility(View.VISIBLE);
                    splashscreenButtonLearnmore.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(SplashScreen.this, Signin.class);
                            startActivity(intent);
                        }
                    });
                } else {
                    splashscreenButtonLearnmore = (Button) findViewById(R.id.splashscreen_btn_learnmore);
                    splashscreenButtonLearnmore.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        /* splash screen
        // timer for splash screen
        int splashScreenTimer = 3000;

        // go to Signin class after splashScreenTimer end
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            Intent intent = new Intent(SplashScreen.this, Signin.class);
            startActivity(intent);

            finish();
            }
        }, splashScreenTimer);
        */
    }
}
