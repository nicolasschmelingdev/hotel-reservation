package com.ntconsult.hotelreservation.util;

import org.springframework.stereotype.Controller;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

@Controller
public class Util implements Serializable {

    public static String retornaMensagem(String chave, Object... params) {
        final Locale locale = new Locale("pt", "BR");

        final ResourceBundle rb = ResourceBundle.getBundle("messages", locale);
        if (rb.containsKey(chave)) {
            if (params.length > 0) {
                return MessageFormat.format(rb.getString(chave), params);
            } else {
                return rb.getString(chave);
            }
        }
        return "";

    }

    public static Locale recuperaLocale() {
        return new Locale("pt", "BR");
    }

}
