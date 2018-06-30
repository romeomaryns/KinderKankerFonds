package eu.maryns.romeo.kinderkankerfonds.service;


import java.io.File;
import java.io.IOException;

public interface ImportService {
    String NAME = "kinderkankerfonds_ImportService";

    void importCSV(File file) throws IOException;

    void importAdres(File file) throws IOException;
}