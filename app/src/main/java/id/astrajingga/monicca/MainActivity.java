package id.astrajingga.monicca;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import id.astrajingga.monicca.adapter.ViewPagerMainAdapter;
import id.astrajingga.monicca.features.ep.EpMain;
import id.astrajingga.monicca.features.fc.FcMain;
import id.astrajingga.monicca.features.gb.GbMainAlt;
import id.astrajingga.monicca.features.pa.PaMain;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // VARIABLES
    // VIEW PAGER
    ViewPager viewPager;

    // VIEW PAGER INDICATOR
    LinearLayout mainViewPagerIndicator;
    private int dotsCount;
    private ImageView[] dot;

    private static final int TIME_LIMIT = 1800;
    private static long backPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_navdrawer_container);

        // SET CUSTOM TOOLBAR
        TextView tvToolbarTitle = (TextView) findViewById(R.id.tv_toolbar_title);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            tvToolbarTitle.setText(R.string.app_name);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // SET NAVIGATION DRAWER
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle drawerToogle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(drawerToogle);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // SET VIEW PAGER
        viewPager = (ViewPager) findViewById(R.id.main_viewpager);
        ViewPagerMainAdapter viewPagerMainAdapter = new ViewPagerMainAdapter(this);

        // SET MARGIN BETWEEN VIEW PAGER PAGES
        viewPager.setPageMargin(getResources().getDimensionPixelSize(R.dimen.pager_margin));
        viewPager.setClipToPadding(false);
        viewPager.setPadding(getResources().getDimensionPixelSize(R.dimen.pager_padding_left), 0,
                getResources().getDimensionPixelSize(R.dimen.pager_padding_right), 0);

        viewPager.setAdapter(viewPagerMainAdapter);

        // SET VIEW PAGER INDICATOR
        mainViewPagerIndicator = (LinearLayout) findViewById(R.id.main_viewpager_indicator);
        dotsCount = 4;
        dot = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dot[i] = new ImageView(this);
            dot[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.dot_nonactive));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(4, 0, 4, 0);

            mainViewPagerIndicator.addView(dot[i], params);
        }

        dot[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.dot_active));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if (position < 4) {

                    for (int i = 0; i < dotsCount; i++) {
                        dot[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),
                                R.drawable.dot_nonactive));
                    }
                    dot[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),
                            R.drawable.dot_active));
                } else {
                    viewPager.setCurrentItem(0);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        // GRIDVIEW CONTENT
//        GridView gridview = (GridView) findViewById(R.id.main_gridview);
//
//        GridViewMainAdapter gridViewMainAdapter = new GridViewMainAdapter(this, text, images);
//
//        gridview.setAdapter(gridViewMainAdapter);
//
//        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                if (position == 0) {
//                    Intent intent = new Intent(MainActivity.this, FcMain.class);
//                    startActivity(intent);
//                } else if (position == 1) {
//                    Intent intent = new Intent(MainActivity.this, EpMain.class);
//                    startActivity(intent);
//                } else if (position == 2) {
//                    Intent intent = new Intent(MainActivity.this, GbMainAlt.class);
//                    startActivity(intent);
//                } else {
//                    Intent intent = new Intent(MainActivity.this, PaMain.class);
//                    startActivity(intent);
//                }
//            }
//        });

    }

    public void onFinancialCheckup(View view) {
        Intent intent = new Intent(MainActivity.this, FcMain.class);
        startActivity(intent);
    }

    public void onEducationPlan(View view) {
        Intent intent = new Intent(MainActivity.this, EpMain.class);
        startActivity(intent);
    }

    public void onGoalBase(View view) {
        Intent intent = new Intent(MainActivity.this, GbMainAlt.class);
        startActivity(intent);
    }

    public void onPersonalAdvisor(View view) {
        Intent intent = new Intent(MainActivity.this, PaMain.class);
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_button_history) {
            Toast.makeText(this, "Will be available on the next implementation.", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_button_settings) {
            Toast.makeText(this, "Will be available on the next implementation.", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_button_logout) {
            Intent intent = new Intent(this, SigninActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // HIDE THE SOFT KEYBOARD WHEN USER TOUCH OTHER PLACE
    // WTF IS THIS!?
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View view = getCurrentFocus();
        if (view != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            view.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + view.getLeft() - scrcoords[0];
            float y = ev.getRawY() + view.getTop() - scrcoords[1];
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom())
                ((InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);
        }
        return super.dispatchTouchEvent(ev);
    }

    // TELL USER TO TAP BACK TWICE TO EXIT APP
    @Override
    public void onBackPressed() {
        if (TIME_LIMIT + backPressed > System.currentTimeMillis()) {
            // super.onBackPressed();
            moveTaskToBack(true);
        } else {
            Toast.makeText(MainActivity.this, "Press back again to exit.", Toast.LENGTH_SHORT).show();
        }
        backPressed = System.currentTimeMillis();
    }
}
