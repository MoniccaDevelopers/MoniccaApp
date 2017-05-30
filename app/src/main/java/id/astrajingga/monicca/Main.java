package id.astrajingga.monicca;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import id.astrajingga.monicca.features.ep.FragmentEpMain;
import id.astrajingga.monicca.features.fc.FragmentFcMain;
import id.astrajingga.monicca.features.gb.FragmentGbMain;
import id.astrajingga.monicca.settings.FragmentSettings;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class Main extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {
    // variables
    String mainStringUsername;

    // view pager
    ViewPager viewPager;

    // view pager indicator
    LinearLayout mainViewPagerIndicator;
    private int dotsCount;
    private ImageView[] dot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_navdrawer_container);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // view pager
        viewPager = (ViewPager) findViewById(R.id.main_viewpager);
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(this);
        viewPager.setAdapter(mainViewPagerAdapter);

        // view pager indicator
        mainViewPagerIndicator = (LinearLayout) findViewById(R.id.main_viewpager_indicator);
        dotsCount = mainViewPagerAdapter.getCount();
        dot = new ImageView[dotsCount];

            for (int i = 0; i < dotsCount; i++) {
                dot[i] = new ImageView(this);
                dot[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.dot_nonactive));

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(8, 0, 8, 0);

                mainViewPagerIndicator.addView(dot[i], params);
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
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

        // view pager timer
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new viewPagerTimerTask(), 3500, 3500);

        Intent intent = getIntent();
        TextView mainTextUsername = (TextView) findViewById(R.id.main_text_username);
        mainStringUsername = intent.getStringExtra("username");
        mainTextUsername.setText(mainStringUsername);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().findFragmentByTag("Main") != null) {
            getSupportFragmentManager().popBackStackImmediate("Main",0);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_rightmenu_container, menu);
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
            FragmentSettings fragmentSettings = new FragmentSettings();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_container, fragmentSettings).addToBackStack("Main").commit();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_button_home) {
            Intent intent = new Intent(Main.this, Main.class);
            intent.putExtra("username", mainStringUsername);
            startActivity(intent);
        } else if (id == R.id.nav_button_editprofile) {
            Toast.makeText(this, "Unavailable in Demo Mode.", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.navIncome) {
            Toast.makeText(this, "Unavailable in Demo Mode.", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.navBankName) {
            Toast.makeText(this, "Unavailable in Demo Mode.", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.navBankAccountType) {
            Toast.makeText(this, "Unavailable in Demo Mode.", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.navSettings) {
            FragmentSettings fragmentSettings = new FragmentSettings();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_container, fragmentSettings).addToBackStack("Main").commit();
        } else if (id == R.id.navLogout) {
            Intent intent = new Intent(Main.this, Signin.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void fc(View view) {
        FragmentFcMain fragmentFcMain = new FragmentFcMain();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragmentFcMain).addToBackStack("Main").commit();
    }

    public void ep(View view) {
        FragmentEpMain fragmentEpMain = new FragmentEpMain();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragmentEpMain).addToBackStack("Main").commit();
    }

    public void gb(View view) {
        FragmentGbMain fragmentGbMain = new FragmentGbMain();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragmentGbMain).addToBackStack("Main").commit();
    }

    // automated view pager
    public class viewPagerTimerTask extends TimerTask {

        @Override
        public void run() {

            Main.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPager.getCurrentItem() == 0) {
                        viewPager.setCurrentItem(1);
                    } else if (viewPager.getCurrentItem() == 1) {
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }

    // hide the soft keyboard when user touch other place
    // and still don't know how the fuck this method works
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View view = getCurrentFocus();
        if (view != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            view.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + view.getLeft() - scrcoords[0];
            float y = ev.getRawY() + view.getTop() - scrcoords[1];
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom())
                ((InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);
        }
        return super.dispatchTouchEvent(ev);
    }
}
