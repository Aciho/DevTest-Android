package uk.co.whitetigergames.devtest_android;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Created by Simon on 09/08/2015.
 */
public class CSVParser
{
    public int Count;

    public CSVParser(Reader input) throws IOException {
        Count = 0;
        BufferedReader bufferedReader = null;

        try
        {
            String sCurrentLine;
            bufferedReader = new BufferedReader(input);

            bufferedReader.readLine(); //Discard title line
            
            while ((sCurrentLine = bufferedReader.readLine()) != null)
            {
                Count++;
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        finally
        {
            bufferedReader.close();
        }
    }

}
