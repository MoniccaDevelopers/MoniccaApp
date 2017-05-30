package id.astrajingga.monicca;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class MainViewPagerAdapter extends PagerAdapter {
    // variables
    private Context context;
    private LayoutInflater layoutInflater;
    private int[] imageSrc = {R.drawable.bannerone, R.drawable.bannertwo};

    public MainViewPagerAdapter(Context context) {
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
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.main_viewpager_container, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.main_img_swipe);
        imageView.setImageResource(imageSrc[position]);

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