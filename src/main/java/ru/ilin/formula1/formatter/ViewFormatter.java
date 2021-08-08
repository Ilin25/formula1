package ru.ilin.formula1.formatter;

import ru.ilin.formula1.domain.Racer;
import java.util.List;

public interface ViewFormatter {

    String format(List<Racer> racers);

}
