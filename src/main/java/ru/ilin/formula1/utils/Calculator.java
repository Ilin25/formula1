package ru.ilin.formula1.utils;

import ru.ilin.formula1.domain.Racer;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Calculator {

    private Calculator() {
    }

    public static LocalDateTime calculateBestTime(Racer racer){
        long startTime = Timestamp.valueOf(racer.getStartTime()).getTime();
        long endTime = Timestamp.valueOf(racer.getEndTime()).getTime();
        long res = endTime - startTime;
        LocalDateTime bestTime = new Timestamp(res).toLocalDateTime();
        return bestTime;
    }
}
