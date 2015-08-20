package uk.co.whitetigergames.devtest_android;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import uk.co.whitetigergames.devtest_android.interfaces.IComicDataSource;
import uk.co.whitetigergames.devtest_android.interfaces.IComicDataSourceListWithFavourites;
import uk.co.whitetigergames.devtest_android.interfaces.IRawComicDataSource;

/**
 * This Fragment manages data and maintains
 * itself across configuration changes.
 */
public class DataTaskFragment extends Fragment implements IComicDataSourceListWithFavourites
{
    @Override
    public void toggleFavourite(int ID)
    {
        mData.toggleFavourite(ID);
    }

    @Override
    public boolean isFavourite(int position)
    {
        return mData.isFavourite(position);
    }

    @Override
    public int[] getFavourites()
    {
        return mData.getFavourites();
    }

    @Override
    public int getCount()
    {
        return mData.getCount();
    }

    @Override
    public IComicDataSource getData(int position)
    {
        return mData.getData(position);
    }

    @Override
    public int getPublisherCount(String publisher)
    {
        return mData.getPublisherCount(publisher);
    }

    private IComicDataSourceListWithFavourites mData;

    /**
     * Hold a reference to the parent Activity so we can report the
     * task's current progress and results. The Android framework
     * will pass us a reference to the newly created Activity after
     * each configuration change.
     */
    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
    }

    /**
     * This method will only be called once when the retained
     * Fragment is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // Retain this fragment across configuration changes.
        setRetainInstance(true);

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
        mData = new ComicDataList(dataSource);
    }
}