package no.nith.sivpal12.pg5100.eksamen.utils;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

public class DateUtilsTest {

    @Test
    public void addOneDay_AddsOneDay() {
        final Date date = new Date(0);
        final Date datePlusOne = new Date(86400000L);

        assertEquals(datePlusOne, DateUtils.addOneDay(date));
    }

}
