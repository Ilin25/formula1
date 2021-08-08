package ru.ilin.formula1.dataReader;

import java.io.IOException;
import java.util.List;

public interface DataFileReader {

    List<String> read(String path) throws IOException;
}
