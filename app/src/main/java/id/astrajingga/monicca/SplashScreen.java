package id.astrajingga.monicca;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class SplashScreen extends AppCompatActivity {
    // variables
    // view pager
    ViewPager viewPager;

    // view pager indicator
    LinearLayout splashScreenViewPagerIndicator;
    private int dotsCount;
    private ImageView[] dot;

    Button splashscreenButtonLearnmore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen_content_activity);

        // view pager
        viewPager = (ViewPager) findViewById(R.id.splashscreen_viewpager);
        SplashScreenViewPagerAdapter splashScreenViewPagerAdapter = new SplashScreenViewPagerAdapter(this);
        viewPager.setAdapter(splashScreenViewPagerAdapter);

        // view pager indicator
        splashScreenViewPagerIndicator = (LinearLayout) findViewById(R.id.splashscreen_viewpager_indicator);
        dotsCount = splashScreenViewPagerAdapter.getCount();
        dot = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dot[i] = new ImageView(this);
            dot[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.dot_nonactive));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8, 0, 8, 0);

            splashScreenViewPagerIndicator.addView(dot[i], params);
        }

        dot[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.dot_active));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i < dotsCount; i++) {
                    dot[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.dot_nonactive));
                }

                dot[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.dot_active));

                if (position == 2) {
                    splashscreenButtonLearnmore = (Button) findViewById(R.id.splashscreen_btn_learnmore);
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
