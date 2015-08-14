package uk.co.whitetigergames.devtest_android;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;

import uk.co.whitetigergames.devtest_android.interfaces.IRawComicDataSource;

/**
 * Created by Simon on 09/08/2015.
 */
public class CSVParser implements IRawComicDataSource
{
    @Override
    public int getCount() { return count; }

    private int count;
    private ArrayList<String[]> parsedValues;

    public CSVParser(Reader input) throws IOException {
        count = 0;
        parsedValues = new ArrayList<>();

        BufferedReader bufferedReader = null;

        try
        {
            String sCurrentLine;
            bufferedReader = new BufferedReader(input);

            bufferedReader.readLine(); //Discard title line

            while ((sCurrentLine = bufferedReader.readLine()) != null)
            {
                count++;
                String[] split = sCurrentLine.split("\",\"");
                split[0] = StringUtils.strip(split[0], "\"");
                split[split.length-1] = StringUtils.strip(split[split.length-1], "\"");
                parsedValues.add(split);
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        finally
        {
            assert bufferedReader != null;
            bufferedReader.close();
        }
    }

    @Override
    public String[] getLine(int index)
    {
        return parsedValues.get(index);
    }
}
