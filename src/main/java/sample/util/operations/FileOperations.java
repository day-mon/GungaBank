package sample.util.operations;

import sample.Main;
import sample.util.structures.ArrayList;
import sample.core.objects.User;
import sample.util.structures.HashDictionary;


import java.io.*;


public class FileOperations {
    public static final File users = new File("src/main/java/sample/files/users.ser");

    public static ArrayList<File> getAllFilesWithExt(File dir, String ext) {
        ArrayList<File> filesWithExt = new ArrayList<>();
        if (dir.isDirectory()) {
            if (dir.listFiles().length == 0)
                return null;
            for (File file : dir.listFiles()) {
                String[] fileParts = file.getName().split("\\.");
                int lastIndex = fileParts.length - 1;
                if (fileParts[lastIndex].equals(ext)) {
                    filesWithExt.add(file);
                }
            }
        } else
            return null;
        return filesWithExt;
    }

    public static void writeToFile(File dir, Object objToWrite) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(dir);
            ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);

            oos.writeObject(objToWrite);
            oos.close();
            fileOutputStream.close();
            System.out.println(objToWrite.getClass().getSimpleName() + " sucessfully written!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToFile(File[] dir, Object[] objToWrite) {

        if (dir.length != objToWrite.length)
            return;
        for (int i = 0; i < dir.length; i++) {
            try {
                FileOutputStream fos = new FileOutputStream(dir[i]);
                ObjectOutputStream oos = new ObjectOutputStream(fos);

                oos.writeObject(objToWrite);
                System.out.println(objToWrite.getClass().getSimpleName() + " sucessfully written!");

                if (i == dir.length - 1) {
                    fos.close();
                    oos.close();
                    return;
                }

                /**
                 * If files or something are not writing the last object or something remove -1.
                 */
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {

            }
        }
    }

    public static void loadInformation() {
        try {
            File f = new File("src/main/java/sample/files");
            if (!f.exists()) {
                f.mkdir();
                return;
            }
            ArrayList<File> files = getAllFilesWithExt(new File("src/main/java/sample/files/"), "ser");
            int serFiles = 0;
            try {
                serFiles = files.size();
            } catch (NullPointerException npex) {
                System.out.println("No files exist and serializer input failed.");
            }

            for (int i = 0; i < serFiles; i++) {
                FileInputStream fis = new FileInputStream(files.get(i).getAbsolutePath());
                ObjectInputStream ois = new ObjectInputStream(fis);

                String fileName = files.get(i).getName().split("\\.")[0];

                switch (fileName) {
                    case "users":
                       Main.users = (HashDictionary<String, User>) ois.readObject();
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + fileName);
                }


            }
        } catch (IOException | ClassNotFoundException e) {};
    }

}
