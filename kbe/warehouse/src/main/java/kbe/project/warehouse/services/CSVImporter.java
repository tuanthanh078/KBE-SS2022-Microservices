package kbe.project.warehouse.services;

import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class CSVImporter implements CSVImporterService{

    public static final char DEFAULT_CSV_SEPARATOR = ';', CSV_DELIMITER = '"';

    @Override
    public List<List<String>> importCSV(File file) throws IOException, NullPointerException{
        return importCSV(file, DEFAULT_CSV_SEPARATOR);
    }
    public List<List<String>> importCSV(File file, char csvSeparator) throws IOException, NullPointerException{

        List<List<String>> data = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

        String line = reader.readLine();
        while(line != null){
            List<String> lineData = getLineData(line, csvSeparator);
            data.add(lineData);
            line = reader.readLine();
        }

        reader.close();

        return data;
    }

    private List<String> getLineData(String line, char separator){
        List<String> lineData = new ArrayList<>();

        boolean delimited = false;
        boolean justDelimited = false;
        String word = "";

        for(int i = 0; i < line.length(); i++){

            char c = line.charAt(i);

            if(delimited){

                if(c == CSV_DELIMITER){

                    if(justDelimited){
                        word += c;
                    }
                    delimited = false;
                }else{

                    word += c;
                }
                justDelimited = false;
            }else{

                if(c == CSV_DELIMITER){

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
