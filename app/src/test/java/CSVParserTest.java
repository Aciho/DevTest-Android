package uk.co.whitetigergames.devtest_android;

import static org.junit.Assert.*;

import org.junit.*;
import java.io.*;

/**
 * Created by Simon on 09/08/2015.
 */
public class CSVParserTest
{
    File input;

    @Before
    public void SetUp() throws IOException
    {
        input = new File("test.csv");
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(input)));
        writer.println("\"Title\",\"Other titles\",\"BL record ID\",\"BNB number\",\"ISBN\",\"Name\",\"Dates associated with name\",\"Type of name\",\"Role\",\"All names\",\"Series title\",\"Number within series\",\"Country of publication\",\"Place of publication\",\"Publisher\",\"Date of publication\",\"Physical description\",\"Dewey classification\",\"BL shelfmark\",\"Topics\",\"Abstract\",\"Notes\"");
        writer.println("\"1001 spot illustrations of the lively twenties\",\"La Vie Parisienne\",\"008077927\",\"GB8865678\",\"0486250210\",\"\",\"\",\"\",\"\",\"Grafton, Carol Belanger\",\"\",\"\",\"England\",\"New York ; London\",\"Dover ; Constable\",\"1986\",\"123 pages, chiefly illustrations, 28 cm\",\"741.5944\",\"YV.1988.b.2439\",\"Design--History--20th century--Themes, motives ; Art deco ; French caricatures--1920-1930\",\"\",\"Illustrations from La Vie Parisienne\"");
        writer.println("\"Are we there yet? : a Frank and Ernest history of the world\",\"\",\"012030669\",\"GB9168045\",\"1853042250\",\"Thaves, Bob\",\"\",\"person\",\"\",\"Thaves, Bob\",\"\",\"\",\"England\",\"Horsham\",\"Ravette Books\",\"1989\",\"[127] pages, chiefly illustrations, 13x21 cm\",\"741.5973\",\"YK.1992.a.10449\",\"Humorous cartoons ; United States\",\"\",\"Originally published: New York : Topper Books, 1988\"");
        writer.println("\"Emerald warriors\",\"\",\"016062838\",\"GBB233514\",\"9780857684820 ; 0857684825\",\"Tomasi, Peter\",\"\",\"person\",\"\",\"Tomasi, Peter ; Pasarin, Fernando\",\"Green Lantern\",\"1 [Green Lantern]\",\"England\",\"London\",\"Titan\",\"2012\",\"1 v, chiefly colour illustrations, 26 cm\",\"741.5\",\"\",\"Green Lantern (Fictitious character)--Comic books, strips, etc--Fiction\",\"\",\"Originally published: New York, N.Y.: DC Comics, 2011\"");
        writer.println("\"How Obelix fell into the magic potion\",\"\",\"015103770\",\"GBA933631\",\"9781444000269 ; 1444000268\",\"Goscinny\",\"1926-1977\",\"person\",\"\",\"Goscinny ; Uderzo\",\"\",\"\",\"England\",\"London\",\"Orion Children\'s\",\"2009\",\"1 v, chiefly illustrations, 29 cm\",\"741.5\",\"\",\"Astérix (Fictitious character)--Comic books, strips, etc--Juvenile fiction ; Obelix (Fictitious character : Uderzo)--Comic books, strips, etc--Juvenile fiction\",\"\",\"\"");
        writer.println("\"More constant minx\",\"\",\"012216745\",\"GB6118131\",\"\",\"Raymonde\",\"\",\"person\",\"\",\"Raymonde\",\"\",\"\",\"United Kingdom\",\"\",\"Hammond\",\"1961\",\"45 pages,illustrations, 17 cm\",\"741.59\",\"\",\"\",\"\",\"\"");
        writer.println("\"Smeegin W. I. Smirk cartoons\",\"\",\"012021978\",\"GB9005299\",\"0900662581\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"England\",\"Lerwick\",\"Shetland Times\",\"1987\",\"[64] pages, 15x21 cm\",\"741.59411\",\"YV.1990.a.297\",\"Scottish wit and humor, Pictorial ; Scottish humorous cartoons\",\"\",\"\"");
        writer.println("\"The best of Mac, 2000-2009 : a decade of cartoons from the Daily Mail\",\"\",\"015327677\",\"GBA970373\",\"9781906032739 ; 1906032734\",\"McMurtry, Stan\",\"1936-\",\"person\",\"\",\"McMurtry, Stan ; Bryant, Mark\",\"\",\"\",\"England\",\"London\",\"Portico\",\"2009\",\"1 v. (unpaged), chiefly illustrations, 18 x 25 cm\",\"741.56941\",\"LC.31.a.9110\",\"English wit and humor, Pictorial ; Caricatures and cartoons--Great Britain\",\"\",\"\"");
        writer.println("\"The king of beasts & other creatures\",\"\",\"008082748\",\"GB8101035\",\"0713913363\",\"Searle, Ronald\",\"1920-2011\",\"person\",\"\",\"Searle, Ronald\",\"\",\"\",\"England\",\"London\",\"Allen Lane\",\"1980\",\"[56] pages, chiefly colour illustrations, 23x24 cm\",\"741.5942\",\"L.49/643\",\"English wit and humor, Pictorial ; Animals--Caricatures and cartoons ; English humorous cartoons--Collections\",\"\",\"Col. ill on lining papers\"");
        writer.println("\"Thomas Nast : cartoons and illustrations\",\"\",\"009037808\",\"GB7428296\",\"0486229831 ; 0486230678\",\"Nast, Thomas\",\"\",\"person\",\"\",\"Nast, Thomas ; St Hill, Thomas Nast\",\"\",\"\",\"England\",\"New York ; London\",\"Dover Publications ; Constable\",\"1974\",\"ii-x, 146 pages, chiefly illustrations, portraits, 31 cm\",\"741.5973\",\"f75/40472\",\"American wit and humor, Pictorial ; American cartoons--Collections from individual artists\",\"\",\"\"");
        writer.println("\"What is it, Tink, is Pan in trouble?\",\"\",\"010348111\",\"GB98W5176\",\"0836218868\",\"Trudeau, G. B. (Garry B.)\",\"1948-\",\"person\",\"\",\"Trudeau, G. B. (Garry B.)\",\"\",\"\",\"England\",\"Sl\",\"Andrews McMeel\",\"1998\",\"96 pages, chiefly illustrations, 23 cm\",\"741.5973\",\"\",\"\",\"\",\"Originally published: London: Fourth Estate, 1992\"");
        writer.println("\"¿Qué le pasa a Mateoy : los MediKidz explican qué el VIH\",\"\",\"015847522\",\"GBB184526\",\"9781906935375 ; 1906935378\",\"Chilman-Blair, Kim\",\"\",\"person\",\"\",\"Chilman-Blair, Kim ; DeLoache, Shawn\",\"\",\"\",\"England\",\"London\",\"MediKidz\",\"2011\",\"1 v, chiefly colour illustrations, 26 cm\",\"741.5\",\"\",\"HIV infections--Comic books, strips, etc--Juvenile fiction ; HIV (Viruses)--Comic books, strips, etc--Juvenile fiction ; Medikidz (Fictitious characters)--Comic books, strips, etc--Juvenile fiction\",\"\",\"\"");
        writer.close();
    }

    @After
    public void TearDown()
    {
        input.delete();
    }

    @Test
    public void ReadsCorrectNumber() throws Exception
    {
        CSVParser parser = new CSVParser(new FileReader(input));
        assertEquals(11, parser.getCount());
    }

    @Test
    public void FirstLineCorrect() throws Exception
    {
        CSVParser parser = new CSVParser(new FileReader(input));

        String[] testArray = { "1001 spot illustrations of the lively twenties","La Vie Parisienne","008077927","GB8865678","0486250210","","","","","Grafton, Carol Belanger","","","England","New York ; London","Dover ; Constable","1986","123 pages, chiefly illustrations, 28 cm","741.5944","YV.1988.b.2439","Design--History--20th century--Themes, motives ; Art deco ; French caricatures--1920-1930","","Illustrations from La Vie Parisienne"};
        assertArrayEquals(testArray, parser.getLine(0));
    }

    @Test
    public void FourthLineCorrect() throws Exception
    {
        CSVParser parser = new CSVParser(new FileReader(input));

        String[] testArray = { "How Obelix fell into the magic potion","","015103770","GBA933631","9781444000269 ; 1444000268","Goscinny","1926-1977","person","","Goscinny ; Uderzo","","","England","London","Orion Children's","2009","1 v, chiefly illustrations, 29 cm","741.5","","Astérix (Fictitious character)--Comic books, strips, etc--Juvenile fiction ; Obelix (Fictitious character : Uderzo)--Comic books, strips, etc--Juvenile fiction","",""};
        assertArrayEquals(testArray, parser.getLine(3));
    }
}
