package uk.co.whitetigergames.devtest_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import uk.co.whitetigergames.devtest_android.interfaces.IComicDataSource;
import uk.co.whitetigergames.devtest_android.interfaces.IComicDataSourceListWithFavourites;
import uk.co.whitetigergames.devtest_android.interfaces.IRawComicDataSource;


/**
 * An activity representing a list of Comics. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ComicDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p/>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link ComicListFragment} and the item details
 * (if present) is a {@link ComicDetailFragment}.
 * <p/>
 * This activity also implements the required
 * {@link ComicListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class ComicListActivity extends FragmentActivity
        implements ComicListFragment.Callbacks {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;


    private IComicDataSourceListWithFavourites cachedData = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic_list);

        if (findViewById(R.id.comic_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((ComicListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.comic_list))
                    .setActivateOnItemClick(true);
        }

        // TODO: If exposing deep links into your app, handle intents here.
    }

    /**
     * Callback method from {@link ComicListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(String position)
    {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";

        try
        {
            // display to console
            json = mapper.writeValueAsString(getComicData(Integer.parseInt(position)));

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        if (mTwoPane)
        {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(ComicDetailFragment.ARG_ITEM_JSON, json);

            ComicDetailFragment fragment = new ComicDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.comic_detail_container, fragment)
                    .commit();
        }
        else
        {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, ComicDetailActivity.class);
            detailIntent.putExtra(ComicDetailFragment.ARG_ITEM_JSON, json);
            startActivity(detailIntent);
        }
    }


    public IComicDataSource getComicData(int position)
    {
        return getComicData().getData(position);
    }

    public IComicDataSourceListWithFavourites getComicData()
    {
        if (cachedData == null)
        {
            InputStream inputStream = getResources().openRawResource(R.raw.titles);
            Reader reader = new InputStreamReader(inputStream);
            IRawComicDataSource dataSource = null;
            try
            {
                dataSource = new CSVParser(reader);
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            cachedData = new ComicDataList(dataSource);
        }
        return cachedData;
    }
}
