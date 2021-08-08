package ru.ilin.formula1;

import ru.ilin.formula1.dataReader.DataFileReader;
import ru.ilin.formula1.dataReader.DataFileReaderImpl;
import ru.ilin.formula1.exceptions.InvalidFileContentException;
import ru.ilin.formula1.formatter.ViewFormatter;
import ru.ilin.formula1.formatter.ViewFormatterImpl;
import ru.ilin.formula1.parser.RaceParser;
import ru.ilin.formula1.parser.RaceParserImpl;
import ru.ilin.formula1.validator.FileValidator;
import ru.ilin.formula1.validator.FileValidatorImpl;
import java.io.IOException;

public class ConsoleApplication {
    public static void main(String[] args) throws IOException, InvalidFileContentException {

        DataFileReader dataFileReader = new DataFileReaderImpl();
        RaceParser parser = new RaceParserImpl();
        ViewFormatter formatter = new ViewFormatterImpl();
        FileValidator validator = new FileValidatorImpl();
        RaceResultsPrinter raceResultsPrinter = new RaceResultsPrinter(validator, dataFileReader, parser,formatter);
        System.out.println(raceResultsPrinter.printResultsRace(
                        "abbreviations.txt",
                        "start.log",
                        "end.log"));
    }
}
