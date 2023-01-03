package com.meta.vote.main.utils;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class DateFormatterUtil {


    public final static SimpleDateFormat DATE_FORMAT =
            new SimpleDateFormat(
                    "yyyy-MM-dd'T'HH:mm:ssZ",
                    new Locale("pt","BR"));

    public final static SimpleDateFormat DAY_DATE_FORMAT =
            new SimpleDateFormat(
                    "yyyy-MM-ddZ",
                    new Locale("pt","BR"));

}