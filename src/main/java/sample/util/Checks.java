package sample.util;

public class Checks
{
    public static boolean number(String number)
    {
        return number.chars().allMatch(Character::isDigit);
    }

    public static boolean emailValidator(String email)
    {
        return email.matches("^(.+)@(.+)$\"");
    }

    public static boolean passwordValidator(String pass)
    {
        return pass.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{4,20}$");
    }

    public static boolean dateValidator(String date)
    {
        return date.matches("^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$");
    }

    public static boolean phoneValidator(String phone)
    {
        return phone.matches("^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$");
    }
}