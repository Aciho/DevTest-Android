package uk.co.whitetigergames.devtest_android;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.view.LayoutInflater;
import android.widget.CompoundButton;
import android.widget.TextView;

import uk.co.whitetigergames.devtest_android.interfaces.IComicDataSource;
import uk.co.whitetigergames.devtest_android.interfaces.IComicDataSourceListWithFavourites;

/**
 * Created by Simon on 15/08/2015.
 */
public class ComicListAdapter extends BaseAdapter
{
    Context context;
    ComicListFragment.ComicListCallbacks dataSource;

    public ComicListAdapter(Context context,
                            ComicListFragment.ComicListCallbacks comicCallbacks)
    {
        this.context = context;
        this.dataSource = comicCallbacks;
    }

    @Override
    public int getCount()
    {
        if (dataSource.getComicData() != null)
        {
            return dataSource.getComicData().getCount();
        }
        return 0;
    }

    @Override
    public IComicDataSource getItem(int position)
    {
        if (dataSource.getComicData() != null)
        {
            return dataSource.getComicData().getData(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position)
    {
        return getItem(position).getID();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        View rootView = convertView;
        if(rootView == null)
        {
            rootView = LayoutInflater.from(context).inflate(R.layout.comic_list_item, parent, false);
        }

        View comic_item = rootView.findViewById(R.id.comic_name_layout);
        comic_item.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ((ComicListFragment.ComicListCallbacks) v.getContext()).onItemSelected(String.valueOf(position));
            }
        });

        final IComicDataSource data = getItem(position);
        if (data != null)
        {
            TextView titleText = (TextView) rootView.findViewById(R.id.comic_name);
            titleText.setText(data.getName());

            TextView subText = (TextView) rootView.findViewById(R.id.comic_subtitle);
            subText.setText(data.getSubtitle());

            CompoundButton checkBox = (CompoundButton)rootView.findViewById(R.id.comic_checkbox);

            checkBox.setChecked(dataSource.getComicData().isFavourite(position));
            checkBox.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    dataSource.getComicData().toggleFavourite(data.getID());
                }
            });
        }

        return rootView;
    }
}
