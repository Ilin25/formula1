import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.ilin.formula1.dataReader.DataFileReader;
import ru.ilin.formula1.dataReader.DataFileReaderImpl;
import java.io.IOException;
import java.util.List;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;

class DataDataFileReaderImplTests {

    private final DataFileReader reader = new DataFileReaderImpl();

    @Test
    void readShouldListStringsWhenTextFile() throws IOException {

        List<String> abbreviation = reader.read("abbreviationsTests.txt");
        assertThat(abbreviation, hasItems(
                        "DRR_Daniel Ricciardo_RED BULL RACING TAG HEUER",
                        "SVF_Sebastian Vettel_FERRARI",
                        "KMH_Kevin Magnussen_HAAS FERRARI")
        );
    }

    @Test
    void readShouldIoExceptionWhenNotCorrectPath(){
        DataFileReader reader = new DataFileReaderImpl();
        Assertions.assertThrows(
                IOException.class, () -> reader.read("abbreviationsTets.txt"));
    }

    @Test
    void readShouldDoesNotThrowIOException(){
        DataFileReader reader = new DataFileReaderImpl();
        Assertions.assertDoesNotThrow(()-> reader.read("abbreviationsTests.txt"));
    }
}
