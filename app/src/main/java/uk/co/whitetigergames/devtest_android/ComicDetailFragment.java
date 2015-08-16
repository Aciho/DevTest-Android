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
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";
    public static final String ARG_ITEM_DESCRIPTION = "item_descrription";

    /**
     * The dummy content this fragment is presenting.
     */
    private ComicData mItem;
    private String mDesc;

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

        try
        {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(getArguments());
            Log.d("ComicListActivity", json);
        } catch (JsonProcessingException e)
        {
            e.printStackTrace();
        }

        if (getArguments().containsKey(ARG_ITEM_DESCRIPTION))
        {
            mDesc = getArguments().getString(ARG_ITEM_DESCRIPTION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_comic_detail, container, false);

        Log.d("ComicDetailFragment", "Description: " + mDesc);

        // Show the dummy content as text in a TextView.
        if (mDesc != null)
        {
            ((TextView) rootView.findViewById(R.id.description_text)).setText(mDesc);
        }

        return rootView;
    }
}
