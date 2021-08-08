package ru.ilin.formula1.validator;

import ru.ilin.formula1.exceptions.InvalidFileContentException;
import java.io.IOException;

public interface FileValidator {

    void validate(String abbreviationFilePath, String startsFilePath, String endFilePath) throws IOException, InvalidFileContentException;
}
