package ru.ilin.formula1.validator;

import ru.ilin.formula1.exceptions.InvalidFileContentException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileValidatorImpl implements FileValidator{

    private static final String ABBREVIATION_PATTERN = "[A-Z]{3}_(\\w* \\w*_[A-Z]*.*)";
    private static final String LAP_TIME_PATTERN = "[A-Z]{3}(\\d*-\\d{2}-\\d{2}_\\d{2}:\\d{2}:\\d{2}.\\d{3})";

    @Override
    public void validate(String abbreviationFilePath, String startFilePath, String endFilePath) throws IOException, InvalidFileContentException {
        validateAbbreviationFile(abbreviationFilePath,ABBREVIATION_PATTERN);
        validateStartOrEndFile(startFilePath,LAP_TIME_PATTERN);
        validateStartOrEndFile(endFilePath,LAP_TIME_PATTERN);

        long numberLinesAbbreviation = countLinesAbbreviationFile(abbreviationFilePath);
        long numberLinesStart = countLinesStartFile(startFilePath);
        long numberLinesEnd = countLinesEndFile(endFilePath);

        if(numberLinesAbbreviation != numberLinesStart || numberLinesAbbreviation != numberLinesEnd){
            throw new InvalidFileContentException
                    ("\nDifferent number of lines in files : \n" +
                            abbreviationFilePath + " " + numberLinesAbbreviation + " \n" +
                            startFilePath + " " + numberLinesStart + " \n" +
                            endFilePath + " " + numberLinesEnd + " \n");
        }
    }

    private void validateAbbreviationFile (String abbreviationFilePath, String abbreviationPattern) throws IOException, InvalidFileContentException {
        try (BufferedReader reader = new BufferedReader(new FileReader(abbreviationFilePath))){
            String line;
            while ((line = reader.readLine()) != null) {
                if (!checkLinesFile(abbreviationPattern,line)) {
                    throw new InvalidFileContentException
                            ("The file content " + abbreviationFilePath + " does not match the template");
                }
            }
        }

    }

    private void validateStartOrEndFile (String startOrEndFilePath, String startOrEndLapTimePattern) throws IOException, InvalidFileContentException {
        try(BufferedReader reader = new BufferedReader(new FileReader(startOrEndFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!checkLinesFile(startOrEndLapTimePattern, line)) {
                    throw new InvalidFileContentException("The file content " + startOrEndFilePath + " does not match the template");
                }
            }
        }
    }

    private long countLinesAbbreviationFile(String abbreviationFilePath) throws IOException {
        return Files.lines(Paths.get(abbreviationFilePath), Charset.defaultCharset()).count();
    }

    private long countLinesStartFile(String startFilePath) throws IOException {
        return Files.lines(Paths.get(startFilePath), Charset.defaultCharset()).count();
    }

    private long countLinesEndFile(String endFilePath) throws IOException {
        return Files.lines(Paths.get(endFilePath), Charset.defaultCharset()).count();
    }

    private boolean checkLinesFile(String abbreviationPattern, String row){
        Pattern pattern = Pattern.compile(abbreviationPattern);
        Matcher matcher = pattern.matcher(row);
        return matcher.matches();
    }
}
