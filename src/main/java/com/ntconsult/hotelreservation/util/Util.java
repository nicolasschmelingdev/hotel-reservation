package com.ntconsult.hotelreservation.util;

import org.springframework.stereotype.Controller;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

@Controller
public class Util implements Serializable {

    public static final String REGEX_ACCENTS = "[^\\p{ASCII}]";
    public static final String PATTERN_DATA = "dd/MM/yyyy";
    public static final String PATTERN_DATA_JS = "MM/dd/yyyy";
    public static final String PATTERN_REMOVER_ZERO = "^0+(?!$)";
    public static final String SEPARATOR = "/";
    private static final String KEY_CRIPT_PASS = "12omega34";
    private static final String EMAIL_VALIDATOR_BY_SEMICOLONS = "^[a-zA-Z\\d_+&*-]+(?:\\.[a-zA-Z\\d_+&*-]+)*@(?:[a-zA-Z\\d-]+\\.)+[a-zA-Z]{2,}$";
    private static final String CARACTERES_ALFRESCO = "[^()a-zA-Z0-9._/]";

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

}
