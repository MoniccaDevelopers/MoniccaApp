package id.astrajingga.monicca;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Intro extends AppCompatActivity {
    // variables
    private ViewPager viewPager;
    private IntroViewPagerAdapter introViewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int[] pages;
    private Button introBtnSkip, introBtnNext;
    private PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_content_activity);

        // checking for first time launch - before calling setContentView()
        prefManager = new PrefManager(this);
        if (!prefManager.isFirstTimeLaunch()) {
            launchSignin();
            finish();
        }

        // making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        setContentView(R.layout.intro_content_activity);

        viewPager = (ViewPager) findViewById(R.id.intro_viewpager);
        dotsLayout = (LinearLayout) findViewById(R.id.intro_indicator);
        introBtnSkip = (Button) findViewById(R.id.intro_btn_skip);
        introBtnNext = (Button) findViewById(R.id.intro_btn_next);

        // layouts of all intro pages
        // add few more pages here - if you want tho
        pages = new int[] {
            R.layout.intro_first,
            R.layout.intro_second,
            R.layout.intro_third,
            R.layout.intro_fourth
        };

        // adding indicator
        addBottomDots(0);

        // making notification bar transparent
        changeStatusBarColor();

        introViewPagerAdapter = new IntroViewPagerAdapter();
        viewPager.setAdapter(introViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        introBtnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchSignin();
            }
        });

        introBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // checking for last page
                // if this is the last intro page - the signin page will be called
                int current = getItem(+1);
                if (current < pages.length) {
                    // move to next screen
                    viewPager.setCurrentItem(current);
                } else {
                    launchSignin();
                }
            }
        });
    }

    private void addBottomDots(int currentPage) {
        dots =  new TextView[pages.length];

        int[] colorActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[currentPage].setTextSize(36);
            dots[currentPage].setTextColor(colorActive[currentPage]);
        }
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    private void launchSignin() {
        prefManager.setFirstTimeLaunch(false);
        startActivity(new Intent(Intro.this, Signin.class));
        finish();
    }

    // view pager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            // change the next button text NEXT - GOT IT
            if (position == pages.length - 1) {
                // if its the last page - make button text to GOT IT
                introBtnNext.setText(getString(R.string.intro_btn_gotit));
                introBtnSkip.setVisibility(View.GONE);
            } else {
                // still pages left
                introBtnNext.setText(getString(R.string.intro_btn_next));
                introBtnSkip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    // making notification bar transparent
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    // view pager adapter
    private class IntroViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public IntroViewPagerAdapter() {

        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(pages[position], container, false);
            container.addView(view);

            return  view;
        }

        @Override
        public int getCount() {
            return pages.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }
}