import org.junit.jupiter.api.Test;
import ru.ilin.formula1.domain.Racer;
import ru.ilin.formula1.utils.Calculator;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CalculatorTests {

    @Test
    void countBestTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");

        String abbreviation = "DRR";
        String name = "Daniel Ricciardo";
        String team = "RED BULL RACING TAG HEUER";
        LocalDateTime startTimeLap = LocalDateTime.parse("2018-05-24_12:14:12.054", formatter);
        LocalDateTime endTimeLap = LocalDateTime.parse("2018-05-24_12:15:24.067", formatter);

        Racer racer = Racer.builder()
                .withAbbreviation(abbreviation)
                .withname(name)
                .withTeam(team)
                .withStartTime(startTimeLap)
                .withEndTime(endTimeLap)
                .build();
        LocalDateTime bestTime = countBestTime(racer);

        assertThat(bestTime, equalTo(Calculator.calculateBestTime(racer)));
    }

    private LocalDateTime countBestTime(Racer racer){
        long startTime = Timestamp.valueOf(racer.getStartTime()).getTime();
        long endTime = Timestamp.valueOf(racer.getEndTime()).getTime();
        long res = endTime - startTime;
        LocalDateTime bestTime = new Timestamp(res).toLocalDateTime();
        return bestTime;
    }
}
