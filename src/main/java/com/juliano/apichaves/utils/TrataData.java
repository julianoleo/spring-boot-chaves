package com.juliano.apichaves.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TrataData {
    public static Date formataData(String data) throws ParseException {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date date = sdf.parse(data);
            return date;
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}
