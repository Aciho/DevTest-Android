package uk.co.whitetigergames.devtest_android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;

/**
 * A fragment representing a single Comic detail screen.
 * This fragment is either contained in a {@link ComicListActivity}
 * in two-pane mode (on tablets) or a {@link ComicDetailActivity}
 * on handsets.
 */
public class ComicDetailFragment extends Fragment
{
    /**
     * The fragment argument representing the ComicData that this fragment
     * represents.
     */
    public static final String ARG_ITEM_JSON = "item_json";

    /**
     * The fragment argument representing the number of other items
     * by this publisher.
     */
    public static final String ARG_PUBLISHER_OTHER_ITEMS = "publisher_other_items";

    /**
     * The content this fragment is presenting.
     */
    private ComicData mItem;

    /**
     * The number of other items.
     */
    private int mOtherItems;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ComicDetailFragment()
    {
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        ObjectMapper mapper = new ObjectMapper();
        if (getArguments().containsKey(ARG_ITEM_JSON))
        {
            try
            {
                String json = getArguments().getString(ARG_ITEM_JSON);
                mItem = mapper.readValue(json, ComicData.class);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        if (getArguments().containsKey(ARG_PUBLISHER_OTHER_ITEMS))
        {
            mOtherItems = getArguments().getInt(ARG_PUBLISHER_OTHER_ITEMS);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_comic_detail, container, false);

        // Show the content as text in a TextView.
        if (mItem != null)
        {
            ((TextView) rootView.findViewById(R.id.title_text)).setText(mItem.getName());
            ((TextView) rootView.findViewById(R.id.subtitle_text)).setText(mItem.getSubtitle());
            ((TextView) rootView.findViewById(R.id.description_text)).setText(mItem.getDescription());
            ((TextView) rootView.findViewById(R.id.publisher_text)).setText(mItem.getPublisher());
            ((TextView) rootView.findViewById(R.id.year_text)).setText(mItem.getDate());
            ((TextView) rootView.findViewById(R.id.num_others_text)).setText(mOtherItems + " " + getResources().getString(R.string.others_by_this_publisher));
        }

        return rootView;
    }
}
