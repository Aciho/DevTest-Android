import org.junit.*;

import uk.co.whitetigergames.devtest_android.ComicDataList;
import uk.co.whitetigergames.devtest_android.interfaces.IComicDataSourceListWithFavourites;
import uk.co.whitetigergames.devtest_android.interfaces.IRawComicDataSource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Simon on 14/08/2015.
 */
public class ComicListTest
{
    TestDataSource source;
    IComicDataSourceListWithFavourites comicList;

    class TestDataSource implements IRawComicDataSource
    {
        @Override
        public int getCount() { return data.length; }

        @Override
        public String[] getLine(int index)
        {
            return data [index];
        }

        private String[][] data =
                {
                        new String[] {"1001 spot illustrations of the lively twenties","La Vie Parisienne","008077927","GB8865678","0486250210","","","","","Grafton, Carol Belanger","","","England","New York ; London","Dover ; Constable","1986","123 pages, chiefly illustrations, 28 cm","741.5944","YV.1988.b.2439","Design--History--20th century--Themes, motives ; Art deco ; French caricatures--1920-1930","","Illustrations from La Vie Parisienne"},
                        new String[] {"Are we there yet? : a Frank and Ernest history of the world","","012030669","GB9168045","1853042250","Thaves, Bob","","person","","Thaves, Bob","","","England","Horsham","Ravette Books","1989","[127] pages, chiefly illustrations, 13x21 cm","741.5973","YK.1992.a.10449","Humorous cartoons ; United States","","Originally published: New York : Topper Books, 1988"},
                        new String[] {"Emerald warriors","","016062838","GBB233514","9780857684820 ; 0857684825","Tomasi, Peter","","person","","Tomasi, Peter ; Pasarin, Fernando","Green Lantern","1 [Green Lantern]","England","London","Titan","2012","1 v, chiefly colour illustrations, 26 cm","741.5","","Green Lantern (Fictitious character)--Comic books, strips, etc--Fiction","","Originally published: New York, N.Y.: DC Comics, 2011"},
                        new String[] {"How Obelix fell into the magic potion","","015103770","GBA933631","9781444000269 ; 1444000268","Goscinny","1926-1977","person","","Goscinny ; Uderzo","","","England","London","Orion Children's","2009","1 v, chiefly illustrations, 29 cm","741.5","","Astérix (Fictitious character)--Comic books, strips, etc--Juvenile fiction ; Obelix (Fictitious character : Uderzo)--Comic books, strips, etc--Juvenile fiction","",""},
                        new String[] {"More constant minx","","012216745","GB6118131","","Raymonde","","person","","Raymonde","","","United Kingdom","","Hammond","1961","45 pages,illustrations, 17 cm","741.59","","","",""},
                        new String[] {"Smeegin W. I. Smirk cartoons","","012021978","GB9005299","0900662581","","","","","","","","England","Lerwick","Shetland Times","1987","[64] pages, 15x21 cm","741.59411","YV.1990.a.297","Scottish wit and humor, Pictorial ; Scottish humorous cartoons","",""},
                        new String[] {"The best of Mac, 2000-2009 : a decade of cartoons from the Daily Mail","","015327677","GBA970373","9781906032739 ; 1906032734","McMurtry, Stan","1936-","person","","McMurtry, Stan ; Bryant, Mark","","","England","London","Portico","2009","1 v. (unpaged), chiefly illustrations, 18 x 25 cm","741.56941","LC.31.a.9110","English wit and humor, Pictorial ; Caricatures and cartoons--Great Britain","",""},
                        new String[] {"The king of beasts & other creatures","","008082748","GB8101035","0713913363","Searle, Ronald","1920-2011","person","","Searle, Ronald","","","England","London","Allen Lane","1980","[56] pages, chiefly colour illustrations, 23x24 cm","741.5942","L.49/643","English wit and humor, Pictorial ; Animals--Caricatures and cartoons ; English humorous cartoons--Collections","","Col. ill on lining papers"},
                        new String[] {"Thomas Nast : cartoons and illustrations","","009037808","GB7428296","0486229831 ; 0486230678","Nast, Thomas","","person","","Nast, Thomas ; St Hill, Thomas Nast","","","England","New York ; London","Dover Publications ; Constable","1974","ii-x, 146 pages, chiefly illustrations, portraits, 31 cm","741.5973","f75/40472","American wit and humor, Pictorial ; American cartoons--Collections from individual artists","",""},
                        new String[] {"What is it, Tink, is Pan in trouble?","","010348111","GB98W5176","0836218868","Trudeau, G. B. (Garry B.)","1948-","person","","Trudeau, G. B. (Garry B.)","","","England","Sl","Andrews McMeel","1998","96 pages, chiefly illustrations, 23 cm","741.5973","","","","Originally published: London: Fourth Estate, 1992"},
                        new String[] {"2000 AD","The complete Judge Dredd in Oz","012029155","GB9474205","1852864362","Wagner, John","1949-","person","","Wagner, John ; Grant, Alan ; Robinson, Cliff","","","England","London","Titan","1994","1 v, chiefly illustrations, 28 cm","741.5942","YK.1994.b.13625","Strip cartoons ; England","","Originally published in: 2000 AD progs, 545-570. - Previous ed. in 3 vols.: 1988"}
                };
    }

    @Before
    public void Setup()
    {
        source = new TestDataSource();
        comicList = new ComicDataList(source);
    }

    @After
    public void Tear()
    {
    }

    @Test
    public void Count()
    {
        assertEquals(11, comicList.getCount());
    }

    @Test
    public void Name()
    {
        assertEquals("1001 spot illustrations of the lively twenties",  comicList.getData(0).getName());
    }

    @Test
    public void Subtitle()
    {
        assertEquals("La Vie Parisienne", comicList.getData(0).getSubtitle());
    }

    @Test
    public void Description()
    {
        assertEquals("Design--History--20th century--Themes, motives ; Art deco ; French caricatures--1920-1930",
                     comicList.getData(0).getDescription());
    }

    @Test
    public void Publisher()
    {
        // Cheating a little here, but the publisher data for the first one seems a bit broken
        // We should really do some data validation at some point
        assertEquals("Ravette Books", comicList.getData(1).getPublisher());
    }

    @Test
    public void Date()
    {
        assertEquals("1989", comicList.getData(1).getDate());
    }

    @Test
    public void AddFavourites()
    {
        comicList.ToggleFavourite(2);

        assertEquals("Favourites go to the top", "Emerald warriors", comicList.getData(0).getName());
        assertEquals("Everything else bumped down", "1001 spot illustrations of the lively twenties", comicList.getData(1).getName());
        assertEquals("Favourites increase the size of the list", 12, comicList.getCount());
        assertEquals("Favourited items keep their place in the list", "Emerald warriors", comicList.getData(3).getName());

        assertEquals(2, comicList.getData(0).getID());
        assertEquals(0, comicList.getData(1).getID());
        assertEquals(2, comicList.getData(3).getID());
    }

    @Test
    public void OrderFavourites()
    {
        comicList.ToggleFavourite (3);
        comicList.ToggleFavourite(2);

        assertEquals("Favourites go to the top in their original order, not selection order", "Emerald warriors", comicList.getData(0).getName());
    }

    @Test
    public void RemoveFavourites()
    {
        comicList.ToggleFavourite (2);
        comicList.ToggleFavourite(2);

        assertEquals("Toggling twice removes favourites", "1001 spot illustrations of the lively twenties", comicList.getData(0).getName());
        assertEquals(11, comicList.getCount());
    }

    @Test
    public void LimitFavourites()
    {
        comicList.ToggleFavourite (0);
        comicList.ToggleFavourite (1);
        comicList.ToggleFavourite (2);
        comicList.ToggleFavourite (3);
        comicList.ToggleFavourite (4);
        comicList.ToggleFavourite (5);
        comicList.ToggleFavourite (6);
        comicList.ToggleFavourite (7);
        comicList.ToggleFavourite(8);
        comicList.ToggleFavourite(9);

        assertEquals("Can add 10 favourites", 21, comicList.getCount());

        comicList.ToggleFavourite(10);

        assertEquals("Cannot add more than 10 favourites", 21, comicList.getCount());
    }

    @Test
    public void IsFavourite()
    {
        comicList.ToggleFavourite (3);

        assertTrue("Favourite check in top position", comicList.IsFavourite(0));
        assertTrue("Favourite check in regular position", comicList.IsFavourite(4));
        assertFalse("Non favourite check", comicList.IsFavourite(2));
    }

    @Test
    public void PublisherCount()
    {
        assertEquals(0, comicList.GetPublisherCount("Not a publisher"));
        assertEquals(1, comicList.GetPublisherCount("Orion Children's"));
        assertEquals(2, comicList.GetPublisherCount("Titan"));
    }

    @Test
    public void AssigningIDs()
    {
        assertEquals(0, comicList.getData(0).getID());
        assertEquals(1, comicList.getData(1).getID());
    }
}
