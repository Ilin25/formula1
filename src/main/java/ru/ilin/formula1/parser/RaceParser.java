package ru.ilin.formula1.parser;

import ru.ilin.formula1.domain.Racer;
import java.util.List;

public interface RaceParser {

    List<Racer> parse(List<String> abbreviations, List<String> startTimes, List<String> endTimes);
}
