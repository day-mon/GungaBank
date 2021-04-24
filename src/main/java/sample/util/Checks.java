package sample.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Checks
{
    public static boolean number(String number)
    {
        return number.chars().allMatch(Character::isDigit);
    }

    public static boolean emailValidator(String email)
    {
        // Checks pattern for emails
        final String EMAIL_REGEX = "^(.+)@(.+)$";
        Pattern pat = Pattern.compile(EMAIL_REGEX);
        Matcher match = pat.matcher(email);
        return match.matches();
    }

    public static boolean passwordValidator(String pass)
    {
        final String PASS_REGEX = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{4,20}$";
        Pattern pt = Pattern.compile(PASS_REGEX);
        Matcher match = pt.matcher(pass);
        return match.matches();
    }

    public static boolean dateValidator(String date)
    {
        final String DATE_REGEX = "^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$";
        Pattern pt = Pattern.compile(DATE_REGEX);
        Matcher match = pt.matcher(date);
        return match.matches();
    }

    public static boolean phoneValidator(String phone)
    {
        final String PHONE_REGEX = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
                + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
                + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$";
        Pattern pt = Pattern.compile(PHONE_REGEX);
        Matcher match = pt.matcher(phone);
        return match.matches();
    }
}