package uk.co.whitetigergames.devtest_android;

import org.apache.commons.lang3.ArrayUtils;

import java.util.*;

import uk.co.whitetigergames.devtest_android.interfaces.*;

/**
 * Created by Simon on 14/08/2015.
 */
public class ComicDataList implements IComicDataSourceListWithFavourites
{
    int MAX_FAVOURITES = 10;

    List<Integer> favourites = new ArrayList<>();
    List<IComicDataSource> data = new ArrayList<> ();
    PublisherList publishers = new PublisherList();

    public ComicDataList(IRawComicDataSource source)
    {
        for (int i = 0; i < source.getCount(); ++i)
        {
            IComicDataSource comicData = GenerateComicData(i, source.getLine(i));
            data.add(comicData);
            publishers.RecordInstance(comicData.getPublisher());
        }
    }

    private IComicDataSource GenerateComicData(int ID, String[] dataSource)
    {
        return new ComicData(ID, dataSource[0], dataSource[1], dataSource[19], dataSource[14], dataSource[15]);
    }

    @Override
    public void ToggleFavourite(int ID)
    {
        if (favourites.contains(ID))
        {
            favourites.remove(Integer.valueOf(ID));
        }
        else if (favourites.size() < MAX_FAVOURITES)
        {
            favourites.add(ID);
            Collections.sort(favourites);
        }
    }

    @Override
    public boolean IsFavourite(int position)
    {
        return favourites.contains(getData(position).getID());
    }

    @Override
    public int GetPublisherCount(String publisher)
    {
        return publishers.GetCount(publisher);
    }

    @Override
    public int[] getFavourites()
    {
        return ArrayUtils.toPrimitive(favourites.toArray(new Integer[favourites.size()]));
    }

    @Override
    public int getCount()
    {
        return data.size() + favourites.size();
    }

    @Override
    public IComicDataSource getData(int position)
    {
        if (position < favourites.size())
        {
            return data.get(favourites.get(position));
        }

        return data.get(position - favourites.size());
    }
}
