import org.junit.jupiter.api.Test;
import ru.ilin.formula1.domain.Racer;
import ru.ilin.formula1.parser.RaceParser;
import ru.ilin.formula1.parser.RaceParserImpl;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

class RaceParserImplTests {

    private final RaceParser raceParser = new RaceParserImpl();

    @Test
    void parseShouldListRacersWhenListsAbbrStartTimeEndTime() {
        List<String> abbreviations = new ArrayList<>();
        abbreviations.add("DRR_Daniel Ricciardo_RED BULL RACING TAG HEUER");
        abbreviations.add("SVF_Sebastian Vettel_FERRARI");
        abbreviations.add("LHM_Lewis Hamilton_MERCEDES");

        List<String> startTime = new ArrayList<>();
        startTime.add("SVF2018-05-24_12:02:58.917");
        startTime.add("DRR2018-05-24_12:14:12.054");
        startTime.add("LHM2018-05-24_12:18:20.125");

        List<String> endTime = new ArrayList<>();
        endTime.add("LHM2018-05-24_12:19:32.585");
        endTime.add("DRR2018-05-24_12:15:24.067");
        endTime.add("SVF2018-05-24_12:04:03.332");

        List<Racer> racers = raceParser.parse(abbreviations,startTime,endTime);
        assertThat(racers.size(), equalTo(3));

        Racer.Builder builder = Racer.builder();
        builder.build();
        assertThat(racers.get(0), instanceOf(Racer.class));
        assertThat(racers.get(1), instanceOf(Racer.class));
        assertThat(racers.get(2), instanceOf(Racer.class));
    }
}
