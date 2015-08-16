package uk.co.whitetigergames.devtest_android;
import uk.co.whitetigergames.devtest_android.interfaces.IComicDataSource;

/**
 * Created by Simon on 14/08/2015.
 */
public class ComicData implements IComicDataSource
{
    public ComicData()
    {
    }

    public ComicData(int id, String name, String subtitle, String desc, String publisher, String date)
    {
        this.id = id;
        this.name = name;
        this.subtitle = subtitle;
        this.description = desc;
        this.publisher = publisher;
        this.date = date;
    }

    public int getID() {
        return id;
    }
    private int id;

    public String getName() {
        return name;
    }
    private String name;

    public String getSubtitle() {
        return subtitle;
    }
    private String subtitle;

    public String getDescription() {
        return description;
    }
    private String description;

    public String getPublisher() {
        return publisher;
    }
    private String publisher;

    public String getDate() {
        return date;
    }
    private String date;

}
