package uk.co.whitetigergames.devtest_android;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Simon on 09/08/2015.
 */
public class CSVParser
{
    public int Count;
    private ArrayList<String[]> parsedValues;

    public CSVParser(Reader input) throws IOException {
        Count = 0;
        parsedValues = new ArrayList<>();

        BufferedReader bufferedReader = null;

        try
        {
            String sCurrentLine;
            bufferedReader = new BufferedReader(input);

            bufferedReader.readLine(); //Discard title line

            while ((sCurrentLine = bufferedReader.readLine()) != null)
            {
                Count++;
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

    public String[] GetLine(int index)
    {
        return parsedValues.get(index);
    }
}
