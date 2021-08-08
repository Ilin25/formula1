import org.junit.jupiter.api.Test;
import ru.ilin.formula1.exceptions.InvalidFileContentException;
import ru.ilin.formula1.validator.FileValidator;
import ru.ilin.formula1.validator.FileValidatorImpl;
import java.io.IOException;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FileValidatorTests {

    private  final FileValidator validator = new FileValidatorImpl();

    @Test
    void validateShouldIoExceptionWhenInvalidPathFile() {
        assertThrows(IOException.class, () -> validator.validate(
                "INVALID_PATH",
                "startTests.log",
                "endTests.log")
        );
    }

    @Test
    void validateShouldInvalidFileContentExceptionMessageWhenInvalidContentFileAbbreviation(){
        Throwable thrown = assertThrows(InvalidFileContentException.class, () -> {
            validator.validate(
                    "abbreviationsTestsInvalidContent.txt",
                    "startTests.log",
                    "endTests.log"
            );
        });

        assertThat(thrown.getMessage(), equalTo
                ("The file content abbreviationsTestsInvalidContent.txt does not match the template")
        );
    }

    @Test
    void validateShouldInvalidFileContentExceptionWhenInvalidContentFileStartTime() {
        Throwable thrown = assertThrows(InvalidFileContentException.class, () -> {
            validator.validate(
                    "abbreviationsTests.txt",
                    "startTestsInvalidContent.log",
                    "endTests.log"
            );
        });

        assertThat(thrown.getMessage(), equalTo
                ("The file content startTestsInvalidContent.log does not match the template")
        );
    }

    @Test
    void validateShouldInvalidFileContentExceptionWhenInvalidContentFileEndTime() {
        Throwable thrown = assertThrows(InvalidFileContentException.class, () -> {
            validator.validate(
                    "abbreviationsTests.txt",
                    "startTests.log",
                    "endTestsInvalidContent.log"
            );
        });

        assertThat(thrown.getMessage(), equalTo
                ("The file content endTestsInvalidContent.log does not match the template")
        );
    }

    @Test
    void validateShouldDoesNotThrowException() {
        assertDoesNotThrow(()->validator.validate(
                    "abbreviationsTests.txt",
                    "startTests.log",
                    "endTests.log")
        );

    }

    @Test
    void validateShouldInvalidFileContentExceptionMessageWhenInvalidDifferentNumberLinesInAbbreviation() {

        Throwable thrown = assertThrows(InvalidFileContentException.class, () -> {
            validator.validate(
                    "abbreviationsTestsInvalidDifferentNumberLines.txt",
                    "startTests.log",
                    "endTests.log"
            );
        });

        assertThat(thrown.getMessage(), equalTo("\n" +
                "Different number of lines in files : \n" +
                "abbreviationsTestsInvalidDifferentNumberLines.txt 18 \n" +
                "startTests.log 19 \n" +
                "endTests.log 19 \n")
        );
    }

    @Test
     void validateShouldInvalidFileContentExceptionMessageWhenInvalidDifferentNumberLinesInStart() {

        Throwable thrown = assertThrows(InvalidFileContentException.class, () -> {
            validator.validate(
                    "abbreviationsTests.txt",
                    "startTestsInvalidDifferentNumberLines.log",
                    "endTests.log"
            );
        });

        assertThat(thrown.getMessage(), equalTo("\n" +
                "Different number of lines in files : \n" +
                "abbreviationsTests.txt 19 \n" +
                "startTestsInvalidDifferentNumberLines.log 18 \n" +
                "endTests.log 19 \n")
        );

    }

    @Test
     void validateShouldInvalidFileContentExceptionMessageWhenInvalidDifferentNumberLinesInEnd(){
        Throwable thrown = assertThrows(InvalidFileContentException.class, () -> {
            validator.validate(
                    "abbreviationsTests.txt",
                    "startTests.log",
                    "endTestsInvalidDifferentNumberLines.log"
            );
        });

        assertThat(thrown.getMessage(), equalTo("\n" +
                "Different number of lines in files : \n" +
                "abbreviationsTests.txt 19 \n" +
                "startTests.log 19 \n" +
                "endTestsInvalidDifferentNumberLines.log 18 \n")
        );
    }

    @Test
     void validateShouldInvalidFileContentExceptionMessageWhenInvalidDifferentNumberLinesInStartAndEnd(){
        Throwable thrown = assertThrows(InvalidFileContentException.class, () -> {
            validator.validate(
                    "abbreviationsTests.txt",
                    "startTestsInvalidDifferentNumberLines.log",
                    "endTestsInvalidDifferentNumberLines.log"
            );
        });

        assertThat(thrown.getMessage(), equalTo("\n" +
                "Different number of lines in files : \n" +
                "abbreviationsTests.txt 19 \n" +
                "startTestsInvalidDifferentNumberLines.log 18 \n" +
                "endTestsInvalidDifferentNumberLines.log 18 \n")
        );
    }

    @Test
     void validateShouldInvalidFileContentExceptionMessageWhenInvalidDifferentNumberLinesInAbrAndEnd() {
        Throwable thrown = assertThrows(InvalidFileContentException.class, () -> {
            validator.validate(
                    "abbreviationsTestsInvalidDifferentNumberLines.txt",
                    "startTestsInvalidDifferentNumberLines.log",
                    "endTests.log"
            );
        });

        assertThat(thrown.getMessage(), equalTo("\n" +
                "Different number of lines in files : \n" +
                "abbreviationsTestsInvalidDifferentNumberLines.txt 18 \n" +
                "startTestsInvalidDifferentNumberLines.log 18 \n" +
                "endTests.log 19 \n")
        );
    }
}
