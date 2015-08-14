package uk.co.whitetigergames.devtest_android;

import org.junit.*;
import static org.junit.Assert.*;

import uk.co.whitetigergames.devtest_android.ComicData;

/**
 * Created by Simon on 14/08/2015.
 */
public class ComicDataTest
{
    ComicData data1, data2;

    @Before
    public void Setup()
    {
        data1 = new ComicData (0, "Name", "Subtitle", "Desc", "Publisher", "1966");
        data2 = new ComicData (2, "OtherName","OtherSubtitle", "OtherDesc", "OtherPublisher", "2013");
    }

    @After
    public void Tear()
    {
    }

    @Test
    public void ID()
    {
        assertEquals(0, data1.getID());
        assertEquals(2, data2.getID());
    }

    @Test
    public void Name()
    {
        assertEquals("Name", data1.getName());
        assertEquals("OtherName", data2.getName());
    }

    @Test
    public void Subtitle()
    {
        assertEquals("Subtitle", data1.getSubtitle());
        assertEquals("OtherSubtitle", data2.getSubtitle());
    }

    @Test
    public void Description()
    {
        assertEquals("Desc", data1.getDescription());
        assertEquals("OtherDesc", data2.getDescription());
    }

    @Test
    public void Publisher()
    {
        assertEquals("Publisher", data1.getPublisher());
        assertEquals("OtherPublisher", data2.getPublisher());
    }

    @Test
    public void Date()
    {
        assertEquals("1966", data1.getDate());
        assertEquals("2013", data2.getDate());
    }
}
