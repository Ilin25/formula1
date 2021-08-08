import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ilin.formula1.RaceResultsPrinter;
import ru.ilin.formula1.dataReader.DataFileReader;
import ru.ilin.formula1.domain.Racer;
import ru.ilin.formula1.exceptions.InvalidFileContentException;
import ru.ilin.formula1.formatter.ViewFormatter;
import ru.ilin.formula1.parser.RaceParser;
import ru.ilin.formula1.validator.FileValidator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class RaceResultsPrinterTests {

    @Mock
    FileValidator validator;
    @Mock
    DataFileReader dataFileReader;
    @Mock
    RaceParser parser;
    @Mock
    ViewFormatter viewFormatter;
    @InjectMocks
    private RaceResultsPrinter raceResultsPrinter;

    @Test
     void printResultsRaceShould() throws IOException, InvalidFileContentException {

        List<String> abbreviations = new ArrayList<>();
        List<String> start = new ArrayList<>();
        List<String> end = new ArrayList<>();
        List<Racer> racers = new ArrayList<>();

        raceResultsPrinter.printResultsRace(
                "abbreviations.txt",
                "start.log",
                "end.log");

        verify(validator).validate(
                "abbreviations.txt",
                "start.log",
                "end.log"
        );

        verify(dataFileReader).read("abbreviations.txt");

        verify(dataFileReader).read("start.log");

        verify(dataFileReader).read("end.log");

        verify(parser).parse(abbreviations,start,end);

        verify(viewFormatter).format(racers);
    }

    @Test
     void printResultsRaceShouldIOExceptionWhenValidatorThrowIOException() throws IOException, InvalidFileContentException {

        doThrow(IOException.class)
                .when(validator)
                .validate(
                        "INVALID_PATH.txt",
                        "start.log",
                        "end.log"
                )
        ;

        Assertions.assertThrows(
                IOException.class, () -> raceResultsPrinter.printResultsRace(
                        "INVALID_PATH.txt",
                        "start.log",
                        "end.log"
                )
        );

    }

    @Test
     void printResultsRaceShouldInvalidFileContentExceptionWhenValidatorThrowInvalidFileContentException() throws IOException, InvalidFileContentException {

        doThrow(InvalidFileContentException.class)
                .when(validator)
                .validate(
                        "INVALID_FILE_CONTENT.txt",
                        "start.log",
                        "end.log"
                )
        ;

        Assertions.assertThrows(
                InvalidFileContentException.class, () -> raceResultsPrinter.printResultsRace(
                        "INVALID_FILE_CONTENT.txt",
                        "start.log",
                        "end.log"
                )
        );
    }
}
