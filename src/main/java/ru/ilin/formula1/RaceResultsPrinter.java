package ru.ilin.formula1;

import ru.ilin.formula1.dataReader.DataFileReader;
import ru.ilin.formula1.domain.Racer;
import ru.ilin.formula1.exceptions.InvalidFileContentException;
import ru.ilin.formula1.formatter.ViewFormatter;
import ru.ilin.formula1.parser.RaceParser;
import ru.ilin.formula1.validator.FileValidator;
import java.io.IOException;
import java.util.List;

public class RaceResultsPrinter {
    private final FileValidator validator;
    private final DataFileReader dataFileReader;
    private final RaceParser parser;
    private final ViewFormatter viewFormatter;

    public RaceResultsPrinter(FileValidator validator, DataFileReader dataFileReader, RaceParser parser, ViewFormatter viewFormatter) {
        this.validator = validator;
        this.dataFileReader = dataFileReader;
        this.parser = parser;
        this.viewFormatter = viewFormatter;
    }

    public String printResultsRace(String abbreviationFilePath, String startsFilePath, String endFilePath) throws IOException, InvalidFileContentException {
        validator.validate(abbreviationFilePath, startsFilePath, endFilePath);
        List<String> abbreviation = dataFileReader.read(abbreviationFilePath);
        List<String> startTime = dataFileReader.read(startsFilePath);
        List<String> endTime = dataFileReader.read(endFilePath);
        List<Racer> racers = parser.parse(abbreviation,startTime,endTime);
        return viewFormatter.format(racers);
    }
}
