package ru.ilin.formula1.formatter;

import ru.ilin.formula1.domain.Racer;
import ru.ilin.formula1.utils.Calculator;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

 public class ViewFormatterImpl implements ViewFormatter {

    private static final String PATTERN = "mm:ss.SSS";
    private static final int TOP15_RESULTS = 16;
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(PATTERN);

    @Override
     public String format(List<Racer> racers) {
        StringBuilder fullTextToDisplay = new StringBuilder();
        final int[] serialNumber = {1};

        racers.stream()
                .sorted(Comparator.comparing(Calculator::calculateBestTime))
                .forEach(racer ->
                {
                    String bestTimeToDisplay = timeFormatter.format(Calculator.calculateBestTime(racer));
                    String lineToDisplay = String.format
                            ("%-2d.%-17s |%-26s|%s %n",
                                    serialNumber[0]++,
                                    racer.getname(),
                                    racer.getTeam(),
                                    bestTimeToDisplay
                            );

                    fullTextToDisplay.append(lineToDisplay);

                    if(serialNumber[0] == TOP15_RESULTS){
                        fullTextToDisplay
                                .append("----------------------------------------------------------")
                                .append(System.lineSeparator());
                    }
                }
        );
        return fullTextToDisplay.toString();
    }
}
