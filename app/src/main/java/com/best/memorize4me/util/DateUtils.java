package com.best.memorize4me.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by konstantin.bogdanov on 08.07.2015.
 */
public class DateUtils {
    public static String dateToString(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return simpleDateFormat.format(date);
    }
}
