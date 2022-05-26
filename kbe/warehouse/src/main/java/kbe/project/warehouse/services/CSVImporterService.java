package kbe.project.warehouse.services;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface CSVImporterService {
    List<List<String>> importCSV(File file) throws IOException;
}
