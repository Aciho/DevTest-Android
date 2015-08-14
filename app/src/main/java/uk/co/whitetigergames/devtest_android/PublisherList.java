package uk.co.whitetigergames.devtest_android;

import java.util.Hashtable;

import uk.co.whitetigergames.devtest_android.interfaces.IPublisherList;

/**
 * Created by Simon on 14/08/2015.
 */
public class PublisherList implements IPublisherList
{
    Hashtable<String, Integer> publishers = new Hashtable<>();

    @Override
    public void RecordInstance(String name)
    {
        if(publishers.containsKey(name))
        {
            Integer val = publishers.get(name);
            publishers.put(name, val + 1);
        }
        else
        {
            publishers.put(name, 1);
        }
    }

    @Override
    public long GetCount(String name)
    {
        if(publishers.containsKey(name))
        {
            return publishers.get(name);
        }
        return 0;
    }
}
