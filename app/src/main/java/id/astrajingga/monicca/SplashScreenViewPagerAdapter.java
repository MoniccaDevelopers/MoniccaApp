package id.astrajingga.monicca;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class SplashScreenViewPagerAdapter extends PagerAdapter {
    // variables
    private Context context;
    private LayoutInflater layoutInflater;
    private int[] imageSrc = {R.drawable.splashscreen_one, R.drawable.splashscreen_two, R.drawable.splashscreen_three, R.drawable.splashscreen_four};

    SplashScreenViewPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return imageSrc.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.splashscreen_viewpager_container, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.splashscreen_img_swipe);
        imageView.setImageResource(imageSrc[position]);

        /*
        // the thing that make view pager clickable
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position == 2) {
                    Toast.makeText(context, "dummy", Toast.LENGTH_SHORT).show();
                }

            }
        });
        */

        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view, 0);
        return  view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }
}
