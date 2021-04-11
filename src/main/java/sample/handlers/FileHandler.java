package sample.handlers;

import sample.GungaBank;
import sample.Main;
import sample.core.objects.User;
import sample.util.structures.ArrayList;
import sample.util.structures.HashDictionary;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Objects;

public class FileHandler
{
    private static GungaBank gungaBank;
    public final static File USER_FOLDER = new File("src/main/java/sample/files");
    public final static File USER_FILE = new File(USER_FOLDER, "user.ser");


    public FileHandler(GungaBank gungaBank)
    {
        initFile();
        initFolder();
        this.gungaBank = gungaBank;

    }

    private void initFolder()
    {
        try
        {
            if (!USER_FOLDER.exists())
            {
                USER_FOLDER.mkdir();
            }
        }
        catch (Exception e)
        {
            gungaBank.getLogger().error("USER FOLDER COULD NOT BE CREATED", e);
        }
    }

    private void initFile()
    {
        try
        {
            if (!USER_FILE.exists())
            {
                USER_FILE.createNewFile();
            }
        }
        catch(Exception e)
        {
            gungaBank.getLogger().error("USER FILE COULD NOT BE CREATED", e);
        }
    }

    private static ArrayList<File> getAllFilesWithExt(File dir, String ext)
    {

        ArrayList<File> filesWithExt = new ArrayList<>();
        if (dir.isDirectory())
        {

            if (Objects.requireNonNull(dir.listFiles()).length == 0)
                gungaBank.getLogger().warn("There are no files serializable files");
            for (File file : dir.listFiles())
            {
                String[] fileParts = file.getName().split("\\.");
                int lastIndex = fileParts.length - 1;
                if (fileParts[lastIndex].equals(ext))
                {
                    filesWithExt.add(file);
                }
            }
        }
        else
        {
            gungaBank.getLogger().warn(dir.getName() + " is not a directory!");
            return null;
        }
        return filesWithExt;
    }

    public static void loadInformation()
    {
        try
        {
            ArrayList<File> files = getAllFilesWithExt(USER_FOLDER, "ser");
            int serFiles = 0;
            try
            {
                serFiles = files.size();
            }
            catch (NullPointerException npex)
            {
                System.out.println("No files exist and serializer input failed.");
            }

            for (int i = 0; i < serFiles; i++)
            {
                FileInputStream fis = new FileInputStream(files.get(i).getAbsolutePath());
                ObjectInputStream ois = new ObjectInputStream(fis);

                String fileName = files.get(i).getName().split("\\.")[0];

                switch (fileName)
                {
                    case "users":
                        Main.users = (HashDictionary<String, User>) ois.readObject();
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + fileName);
                }


            }
        }
        catch (IOException e)
        {
            gungaBank.getLogger().error("IOException: " + e.getMessage(), e);
        }
        catch(ClassNotFoundException e)
        {
            gungaBank.getLogger().error("ClassNotFoundException: " + e.getMessage() ,e);
        }

    }
}
