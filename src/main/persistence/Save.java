package persistence;

import model.User;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Save {

    // EFFECTS: saves the users information to a file
    public static void saveUser(User user) {
        File file = new java.io.File("./data/users/" + user.getName() + ".cite");
        try {
            file.createNewFile();
            FileOutputStream fileOut = new FileOutputStream("./data/users/" + user.getName() + ".cite");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(user);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // EFFECTS: prints the file information for the user (currently the last modified date)
    public static String getFileInformation(User user) {
        File file = new File("./data/users/" + user.getName() + ".cite");
        if (file.exists()) {
            long milliseconds = file.lastModified();
            DateFormat format = new SimpleDateFormat("MMMM dd hh:mm");
            return "Last save: " + format.format(milliseconds);
        } else {
            return "(You've never saved.)";
        }
    }

}
