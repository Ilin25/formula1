package ru.ilin.formula1.parser;

import ru.ilin.formula1.domain.Racer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class RaceParserImpl implements RaceParser {

    private static final String TIME_PATTERN = "yyyy-MM-dd_HH:mm:ss.SSS";

    @Override
    public List<Racer> parse(List<String> abbreviations, List<String> startTimes, List<String> endTimes) {
        Map<String,LocalDateTime> abbreviationToStartTime = parseTime(startTimes);
        Map<String,LocalDateTime> abbreviationToEndTime = parseTime(endTimes);
        List<Racer> racers = new ArrayList<>();

        abbreviations
                .stream()
                .forEach(abbreviation -> {
                    String[]splitAbbr = abbreviation.split("_");
                    collectRacers(splitAbbr, abbreviationToStartTime, abbreviationToEndTime, racers);
                }
        );

        return racers;
    }

    private void collectRacers(String[] splitAbbreviation,
                               Map<String, LocalDateTime> abbreviationToStartTime,
                               Map<String,LocalDateTime> abbreviationToEndTime,
                               List<Racer> racers)
    {
        Racer racer = Racer.builder()
                .withAbbreviation(splitAbbreviation[0])
                .withname(splitAbbreviation[1])
                .withTeam(splitAbbreviation[2])
                .withStartTime(abbreviationToStartTime.get(splitAbbreviation[0]))
                .withEndTime(abbreviationToEndTime.get(splitAbbreviation[0]))
                .build();
        racers.add(racer);
    }

    private Map<String,LocalDateTime> parseTime(List<String> abbreviations){

        Map<String,LocalDateTime> abbreviationToTime = new HashMap<>();

        abbreviations.stream()
                .forEach(abbrTime->
                {
                    String abbreviation = abbrTime.substring(0,3);
                    String time = abbrTime.substring(3);
                    LocalDateTime dateTime = convertTimeToLocalDateTime(time);
                    abbreviationToTime.put(abbreviation, dateTime);
                });
        return abbreviationToTime;
    }

    private LocalDateTime convertTimeToLocalDateTime(String time){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_PATTERN);
        LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
        return dateTime;
    }
}
