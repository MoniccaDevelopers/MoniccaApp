package id.astrajingga.monicca.intro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro;

public class IntroActivity extends AppIntro {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirstIntroFragment firstIntro = new FirstIntroFragment();
        SecondIntroFragment secondIntro = new SecondIntroFragment();
        ThirdIntroFragment thirdIntro = new ThirdIntroFragment();
        FourthIntroFragment fourthIntro = new FourthIntroFragment();

        // ADD YOUR SLIDE'S FRAGMENTS HERE
        // APPINTRO WILL AUTOMATICALLY GENERATE THE DOTS INDICATOR AND BUTTONS
        addSlide(firstIntro);
        addSlide(secondIntro);
        addSlide(thirdIntro);
        addSlide(fourthIntro);

        // OPTIONAL METHODS

        //OVERRIDE BAR/SEPARATE COLOR
        // setBarColor(ContextCompat.getColor(this, R.color.viking));
        // setSeparatorColor(ContextCompat.getColor(this, R.color.colorAccent));

        // SHOW OR HIDE THE STATUSBAR
        // showStatusBar(true);

        // HIDE SKIP/DONE BUTTON
        // showSkipButton(false);
        // showDoneButton(false);

        // TURN VIBRATION ON AND SET INTENSITY
        // NOTE: YOU WILL PROBABLY NEED TO ASK VIBRATE PERMISSION IN MANIFEST
        // setVibrate(true);
        // setVibrateIntensity(30);

        //ANIMATIONS --  USE ONLY ONE OF THE BELOW. USING MORE COULD CAUSE ERRORS
        // setFadeAnimation();
        // setZoomAnimation();
        // setFlowAnimation();
        // setSlideOverAnimation();
        setDepthAnimation();
        // setCustomTransformer(yourCustomTransformer);

        // PERMISSIONS -- TAKES A PERMISSION AND SLIDE NUMBER
        // askForPermissions(new String[]{
        //         Manifest.permission.CAMERA
        // }, 3);
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);

        finish();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);

        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
