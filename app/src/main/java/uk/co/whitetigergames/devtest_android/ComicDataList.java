package uk.co.whitetigergames.devtest_android;

import java.util.*;

import uk.co.whitetigergames.devtest_android.interfaces.*;

/**
 * Created by Simon on 14/08/2015.
 */
public class ComicDataList implements IComicDataSourceListWithFavourites
{
    int MAX_FAVOURITES = 10;

    List<Integer> favourites = new ArrayList<>();
    List<ComicData> data = new ArrayList<> ();
    PublisherList publishers = new PublisherList();

    public ComicDataList(IRawComicDataSource source)
    {

    }

    @Override
    public void ToggleFavourite(int ID)
    {
        if (favourites.contains(ID))
        {
            favourites.remove(new Integer(ID));
        }
        else if (favourites.size() < MAX_FAVOURITES)
        {
            favourites.add(ID);
//            favourites.Sort();
        }
    }

    @Override
    public boolean IsFavourite(int ID)
    {
        return false;
    }

    @Override
    public int GetPublisherCount(String publisher)
    {
        return 0;
    }

    @Override
    public int[] getFavourites()
    {
        return new int[0];
    }

    @Override
    public int getCount()
    {
        return 0;
    }

    @Override
    public IComicDataSource getData(int position)
    {
        return null;
    }
}
