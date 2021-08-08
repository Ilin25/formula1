package ru.ilin.formula1.dataReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class DataFileReaderImpl implements DataFileReader {

    @Override
    public List<String> read(String path) throws IOException {
        List<String> racersInfo = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                racersInfo.add(line);
            }
        }
        return racersInfo;
    }
}
