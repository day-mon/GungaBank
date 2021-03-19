package sample.util;

public class ArraySetDriver {
    public static void main(String[] args) {
        ArraySet arraySet = new ArraySet(10);
        for (int i = 0; i < 15; i++) {
            arraySet.add(i);
        }

        System.out.println("Size (should be 15): " + arraySet.size());
        System.out.println("Printing all values (should be 0 - 14): ");
        for (int i = 0; i < arraySet.size(); i++) {
            System.out.print(arraySet.toArray()[i] + " ");
        }

        System.out.println("Check empty (should be false): " + arraySet.isEmpty());
        System.out.println("Check contains 13 (should be true): " + arraySet.contains(13));
        System.out.println("Check contains 27 (should be false): " + arraySet.contains(27));
        System.out.println("Check remove 13:" + arraySet.remove(13));
        System.out.println("Check contains 13 (should be false): " + arraySet.contains(13));
        arraySet.clear();
        System.out.println("Check clear, size should be 0: " + arraySet.size());
        System.out.println("Check empty (should be true): " + arraySet.isEmpty());
        System.out.println("All done!");
    }
}
