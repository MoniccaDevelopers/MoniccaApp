package id.astrajingga.monicca.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import id.astrajingga.monicca.R;

/**
 * Created by Djaffar on 7/18/2017.
 */

public class ListViewGoalBaseAdapter extends BaseAdapter {

    private Context mContext;
    private View view;
    private final int[] images;
    private final String[] text;

    public ListViewGoalBaseAdapter(Context mContext, String[] text, int[] images) {
        this.mContext = mContext;
        this.text = text;
        this.images = images;
    }

    @Override
    public int getCount() {
        return text.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            view = new View(mContext);
            view = layoutInflater.inflate(R.layout.gb_feature_item, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.gbmain_img_feature);
            TextView textView = (TextView)view.findViewById(R.id.gbmain_text_feature);
            imageView.setImageResource(images[position]);
            textView.setText(text[position]);
        }
        return view;
    }
}
