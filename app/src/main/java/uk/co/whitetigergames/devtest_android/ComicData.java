package uk.co.whitetigergames.devtest_android;

import uk.co.whitetigergames.devtest_android.interfaces.IComicDataSource;

/**
 * Created by Simon on 14/08/2015.
 */
public class ComicData implements IComicDataSource
{
    public ComicData(int id, String name, String subtitle, String desc, String publisher, String date)
    {
        ID = id;
        Name = name;
        Subtitle = subtitle;
        Description = desc;
        Publisher = publisher;
        Date = date;
    }

    public int getID() {
        return ID;
    }
    private int ID;

    public String getName() {
        return Name;
    }
    private String Name;

    public String getSubtitle() {
        return Subtitle;
    }
    private String Subtitle;

    public String getDescription() {
        return Description;
    }
    private String Description;

    public String getPublisher() {
        return Publisher;
    }
    private String Publisher;

    public String getDate() {
        return Date;
    }
    private String Date;

}
