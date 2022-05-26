package kbe.project.warehouse.services;

import kbe.project.warehouse.data.Component;
import kbe.project.warehouse.data.ComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CSVComponentImporter {

    private static final SimpleDateFormat COMPONENT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private static final int ID = 0;
    private static final int DATE = 1;
    private static final int BRAND = 2;
    private static final int NAME = 3;
    private static final int TYPE = 4;
    private static final int LOCATION = 5;
    private static final int PRICE = 6;
    private static final int LENGTH = 7;
    private static final int WIDTH = 8;
    private static final int POWER = 9;
    private static final int DELIVERABLE = 10;

    private static final int AMOUNT_FIELDS = 11;

    private static File CSV_FILE;

    static {
        try {
            CSV_FILE = ResourceUtils.getFile("classpath:components.csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    private CSVImporter csvImporter;
    @Autowired
    private ComponentRepository componentRepository;

    public void importComponents() throws IOException {
        List<List<String>> data = csvImporter.importCSV(CSV_FILE);
        List<Component> components = new ArrayList<>();

        for(int i = 1; i < data.size(); i++){
            List<String> componentData = data.get(i);

            if(componentData.size() < AMOUNT_FIELDS)continue;

            try {
                components.add(new Component(
                        UUID.fromString(componentData.get(ID)),
                        COMPONENT_DATE_FORMAT.parse(componentData.get(DATE)),
                        componentData.get(BRAND),
                        componentData.get(NAME),
                        componentData.get(TYPE),
                        componentData.get(LOCATION),
                        Float.parseFloat(componentData.get(PRICE)),
                        Float.parseFloat(componentData.get(LENGTH)),
                        Float.parseFloat(componentData.get(WIDTH)),
                        Integer.parseInt(componentData.get(POWER)),
                        Boolean.parseBoolean(componentData.get(DELIVERABLE))
                ));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        componentRepository.saveAll(components);
    }
}
