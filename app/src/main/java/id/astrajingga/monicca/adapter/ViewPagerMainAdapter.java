package id.astrajingga.monicca.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import id.astrajingga.monicca.R;

public class ViewPagerMainAdapter extends PagerAdapter {

    // VARIABLES
    private Context context;
    private int[] imageSrc = {
            R.drawable.img_pager_first,
            R.drawable.img_pager_second,
            R.drawable.img_pager_third,
            R.drawable.img_pager_fourth,
            R.drawable.img_pager_first
    };

    public ViewPagerMainAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return imageSrc.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.main_viewpager_container, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.main_img_swipe);
        imageView.setImageResource(imageSrc[position]);

        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }
}