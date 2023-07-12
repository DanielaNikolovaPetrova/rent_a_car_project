package DanielaPetrova.RentACar.util;

import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Slf4j
public class DateFormatterUtil {
    public static LocalDate getDateFromDateTime(Instant date) {
        LocalDate ld = LocalDate.ofInstant(date, ZoneId.of("UTC+3"));
        return ld;
    }

//    public static Instant stringToInstant(String date) {
//        final DateTimeFormatter formatter = DateTimeFormatter
//                .ofPattern("yyyy-MM-dd")
//                .withZone(ZoneId.systemDefault());
//        return Instant.from(formatter.parse(date));
//    }
}
