package sample.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sample.core.objects.bank.User;
import sample.util.operations.StringOperations;
import sample.util.structures.HashDictionary;

import java.io.*;
import java.util.Date;

public class FileHandler
{
    private final static Logger LOGGER = LoggerFactory.getLogger(FileHandler.class);
    public final static File USER_FOLDER = new File("src/main/java/sample/files");
    public final static File USER_FILE = new File(USER_FOLDER, "users.ser");
    private HashDictionary<String, User> users;


    public FileHandler() {
        initFile();
        initFolder();
        users = initUsers();
        users.put("123", new User("Josh", "Peck", "123", new Date(), "2142323232", "239239232", StringOperations.hashPassword("123")));
        System.out.println(users.size());
        users.elements().forEachRemaining(ele -> System.out.println(ele.getFirstName()));

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
            LOGGER.error("USER FOLDER COULD NOT BE CREATED", e);
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
        } catch (Exception e) {
            LOGGER.error("USER FILE COULD NOT BE CREATED", e);
        }
    }

    public void putUser(User user) {
        users.put(user.getEmail(), user);
        writeToFile();
    }


    public void writeToFile() {
        try {
            FileOutputStream fos = new FileOutputStream(USER_FILE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(users);
            oos.close();
            fos.close();
            LOGGER.info("User wrote successfully to file!");
        } catch (Exception e) {
            LOGGER.error("Error occurred: {}", e.getCause(), e);

        }
    }


    public static HashDictionary<String, User> initUsers() {
        HashDictionary<String, User> users = new HashDictionary<>();
        try {
            FileInputStream fis = new FileInputStream(USER_FILE);
            ObjectInputStream ois = new ObjectInputStream(fis);
            users = (HashDictionary<String, User>) ois.readObject();
        } catch (Exception e) {
            LOGGER.error("Error occurred: {}", e.getCause(), e);
        }
        return users;
    }

    public HashDictionary<String, User> getUsers() {
        return users;
    }
}
