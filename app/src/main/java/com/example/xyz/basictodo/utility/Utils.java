package com.example.xyz.basictodo.utility;

import java.text.SimpleDateFormat;

public class Utils {

    public static final String FORMAT_1 = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_2 = "dd/MM/yyyy HH:mm A";

    /**
     * @return yyyy-MM-dd HH:mm:ss formate date as string
     */
    public static String getFormattedTime(long time, String format) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.format(time);
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }
}
