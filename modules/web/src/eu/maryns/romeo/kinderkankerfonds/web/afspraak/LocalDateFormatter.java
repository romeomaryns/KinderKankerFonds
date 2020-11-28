package eu.maryns.romeo.kinderkankerfonds.web.afspraak;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

public class LocalDateFormatter implements Function<LocalDateTime, String> {


    @Override
    public String apply(LocalDateTime localDate) {
        return localDate.format(DateTimeFormatter.ofPattern("HH:mm"));
    }
}
