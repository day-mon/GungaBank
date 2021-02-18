package sample.util;

import java.sql.SQLOutput;
import java.util.Iterator;

public class arrayListDriver {

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        System.out.println("should return false: " + list.isEmpty());
        System.out.println("should return 10: " + list.size());
        System.out.println("should return true: " + list.add(10));
        System.out.println("should return true: " + list.contains(10));
        System.out.println("should return true" + list.remove(0));
        System.out.println("should return false: " + list.contains(3));
        System.out.println("should return: " + list.get(3));
        System.out.println(list.indexOf(3));

        for(int x = 0; x < list.size(); x++){
            System.out.println(list.get(x));
        }

    }
}
