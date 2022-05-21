package kbe.project.warehouse.services;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVImporter {

    public static final char CSV_SEPARATOR = ';';

    public List<List<String>> importCsv(String path) throws IOException, NullPointerException{
        return importCsv(path, CSV_SEPARATOR);
    }
    public List<List<String>> importCsv(String path, char csvSeparator) throws IOException, NullPointerException{

        List<List<String>> data = new ArrayList<>();

        File file = new File(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

        String line = reader.readLine();
        while(line != null){
            List<String> lineData = getLineData(line, csvSeparator);
            data.add(lineData);
            line = reader.readLine();
        }

        return data;
    }

    private List<String> getLineData(String line, char separator){
        List<String> lineData = new ArrayList<>();
        int begin = 0;

        for(int i = 0; i < line.length(); i++){
            if(line.charAt(i) == separator){
                lineData.add(line.substring(begin,i));
                begin = i + 1;
            }
        }
        if(begin <= line.length())
            lineData.add(line.substring(begin));

        return lineData;
    }
}
