import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ilin.formula1.domain.Racer;
import ru.ilin.formula1.formatter.ViewFormatter;
import ru.ilin.formula1.formatter.ViewFormatterImpl;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(MockitoExtension.class)
 class ViewFormatterImplTests {

    private static final ViewFormatter viewFormatter = new ViewFormatterImpl();

    @Test
     void formatShouldStringWhenListRacer() {
        List<Racer> racers = new ArrayList<>();
        long startTime = 1_000_000l;
        long endTime = 1_000_00l;

        for (int i = 0; i < 20; i++) {
            LocalDateTime startDateTime = Instant.ofEpochMilli(startTime).atZone(ZoneId.of("UTC")).toLocalDateTime();
            LocalDateTime endDateTime = Instant.ofEpochMilli(endTime + i).atZone(ZoneId.of("UTC")).toLocalDateTime();

            Racer.Builder builder = Racer.builder();
            builder.withAbbreviation("ABR" + i)
                    .withname("RACER FULL NAME" + i)
                    .withTeam("TEAM" + i)
                    .withStartTime(startDateTime)
                    .withEndTime(endDateTime);
            Racer racer = builder.build();

            racers.add(racer);
        }

        String actual = "1 .RACER FULL NAME0  |TEAM0                     |45:00.000 \r\n" +
                "2 .RACER FULL NAME1  |TEAM1                     |45:00.001 \r\n" +
                "3 .RACER FULL NAME2  |TEAM2                     |45:00.002 \r\n" +
                "4 .RACER FULL NAME3  |TEAM3                     |45:00.003 \r\n" +
                "5 .RACER FULL NAME4  |TEAM4                     |45:00.004 \r\n" +
                "6 .RACER FULL NAME5  |TEAM5                     |45:00.005 \r\n" +
                "7 .RACER FULL NAME6  |TEAM6                     |45:00.006 \r\n" +
                "8 .RACER FULL NAME7  |TEAM7                     |45:00.007 \r\n" +
                "9 .RACER FULL NAME8  |TEAM8                     |45:00.008 \r\n" +
                "10.RACER FULL NAME9  |TEAM9                     |45:00.009 \r\n" +
                "11.RACER FULL NAME10 |TEAM10                    |45:00.010 \r\n" +
                "12.RACER FULL NAME11 |TEAM11                    |45:00.011 \r\n" +
                "13.RACER FULL NAME12 |TEAM12                    |45:00.012 \r\n" +
                "14.RACER FULL NAME13 |TEAM13                    |45:00.013 \r\n" +
                "15.RACER FULL NAME14 |TEAM14                    |45:00.014 \r\n" +
                "----------------------------------------------------------\r\n" +
                "16.RACER FULL NAME15 |TEAM15                    |45:00.015 \r\n" +
                "17.RACER FULL NAME16 |TEAM16                    |45:00.016 \r\n" +
                "18.RACER FULL NAME17 |TEAM17                    |45:00.017 \r\n" +
                "19.RACER FULL NAME18 |TEAM18                    |45:00.018 \r\n" +
                "20.RACER FULL NAME19 |TEAM19                    |45:00.019 \r\n";

        assertThat(viewFormatter.format(racers), equalTo(actual));
    }
}
