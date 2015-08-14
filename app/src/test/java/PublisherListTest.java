import org.junit.*;

import uk.co.whitetigergames.devtest_android.PublisherList;
import uk.co.whitetigergames.devtest_android.interfaces.IPublisherList;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Simon on 14/08/2015.
 */
public class PublisherListTest
{
    private IPublisherList list;

    @Before
    public void Setup()
    {
        list = new PublisherList();
    }

    @After
    public void Tear()
    {
    }

    @Test
    public void Name()
    {
        assertEquals(0, list.GetCount("Name1"));

        list.RecordInstance("Name1");

        assertEquals(1, list.GetCount("Name1"));

        list.RecordInstance("Name1");

        assertEquals(2, list.GetCount("Name1"));
        assertEquals(0, list.GetCount("Name2"));
    }
}
