package uk.co.whitetigergames.devtest_android;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.view.LayoutInflater;
import android.widget.TextView;

import uk.co.whitetigergames.devtest_android.interfaces.IComicDataSource;
import uk.co.whitetigergames.devtest_android.interfaces.IComicDataSourceListWithFavourites;

/**
 * Created by Simon on 15/08/2015.
 */
public class ComicListAdapter extends BaseAdapter
{
    Context context;
    IComicDataSourceListWithFavourites dataSource;

    public ComicListAdapter(Context context,
                            IComicDataSourceListWithFavourites dataSource)
    {
        this.context = context;
        this.dataSource = dataSource;
    }

    @Override
    public int getCount()
    {
        return dataSource.getCount();
    }

    @Override
    public IComicDataSource getItem(int position)
    {
        return dataSource.getData(position);
    }

    @Override
    public long getItemId(int position)
    {
        return getItem(position).getID();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View rootView = convertView;
        if(rootView == null)
        {
            rootView = LayoutInflater.from(context).inflate(R.layout.comic_list_item, parent, false);
        }

        TextView titleText = (TextView) rootView.findViewById(R.id.comic_name);
        titleText.setText(getItem(position).getName());

        TextView subText = (TextView) rootView.findViewById(R.id.comic_subtitle);
        subText.setText(getItem(position).getSubtitle());

        return rootView;
    }
}
