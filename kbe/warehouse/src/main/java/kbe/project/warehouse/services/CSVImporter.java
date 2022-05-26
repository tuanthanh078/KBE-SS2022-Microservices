package kbe.project.warehouse.services;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVImporter {

    public static final char DEFAULT_CSV_SEPARATOR = ';', DEFAULT_CSV_DELIMITER = '"';

    public List<List<String>> importCsv(String path) throws IOException, NullPointerException{
        return importCsv(path, DEFAULT_CSV_SEPARATOR, DEFAULT_CSV_DELIMITER);
    }
    public List<List<String>> importCsv(String path, char csvSeparator, char csvDelimiter) throws IOException, NullPointerException{

        List<List<String>> data = new ArrayList<>();

        File file = new File(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

        String line = reader.readLine();
        while(line != null){
            List<String> lineData = getLineData(line, csvSeparator, csvDelimiter);
            data.add(lineData);
            line = reader.readLine();
        }

        return data;
    }

    private List<String> getLineData(String line, char separator, char delimiter){
        List<String> lineData = new ArrayList<>();

        boolean delimited = false;
        boolean justDelimited = false;
        String word = "";

        for(int i = 0; i < line.length(); i++){

            char c = line.charAt(i);

            if(delimited){

                if(c == delimiter){

                    if(justDelimited){
                        word += c;
                    }
                    delimited = false;
                }else{

                    word += c;
                }
                justDelimited = false;
            }else{

                if(c == delimiter){

                    delimited = true;
                    justDelimited = true;
                }else if(c == separator){

                    lineData.add(word);
                    word = "";
                }else{

                    word += c;
                }
            }
        }
        lineData.add(word);

        return lineData;
    }
}
