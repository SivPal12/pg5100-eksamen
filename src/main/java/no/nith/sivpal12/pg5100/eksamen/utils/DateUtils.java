package no.nith.sivpal12.pg5100.eksamen.utils;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtils {

    private DateUtils() {
    }

    private static final Logger LOGGER = LoggerFactory
            .getLogger(DateUtils.class);

    public static Date addOneDay(final Date to) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(to);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }
}
