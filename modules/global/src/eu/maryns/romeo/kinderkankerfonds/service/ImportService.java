package eu.maryns.romeo.kinderkankerfonds.service;


import groovy.json.internal.IO;

import java.io.File;
import java.io.IOException;

public interface ImportService {
    String NAME = "kinderkankerfonds_ImportService";

    void importCSV(File file) throws IOException;

    void importAdres(File file) throws IOException;

    void importKkfMail(File file) throws IOException;

    void importAdresEL(File file) throws IOException;

    void importTmpPat(File file) throws IOException;

    void importKKFrela(File file) throws IOException;
}