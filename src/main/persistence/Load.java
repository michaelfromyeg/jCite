package persistence;

import model.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Load {

    public static Scanner scan = new Scanner(System.in);

    // MODIFIES: User
    // EFFECTS: parses user input to load in a user
    public static User loadUser(String name) {
        //System.out.println("Welcome back!");
        //System.out.println("What is your username?");
        //String name = scan.next();
        User user = null;
        try {
            FileInputStream fileIn = new FileInputStream("./data/users/" + name + ".cite");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            user = (User) in.readObject();
            in.close();
            fileIn.close();
        } catch (Exception e) {
            return null;
            //loadUser(name);
            //i.printStackTrace();
        }
        return user;
    }

    // EFFECTS: returns boolean based on whether on not username is taken
    public static boolean usernameTaken(String username) {
        boolean userExists = false;
        File folder = new File("./data/users");
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.getName().equals(username + ".cite")) {
                userExists = true;
            }
        }
        return userExists;
    }

    // EFFECTS: returns a list of all exports currently in the exports folder
    public static List<String> getExportsList() {
        File folder = new File("./data/exports");
        File[] listOfFiles = folder.listFiles();
        List<String> exports = new ArrayList<>();

        for (File file : listOfFiles) {
            exports.add(file.getName());
        }
        return exports;
    }

    // EFFECTS: returns the absolute path of an export from its filename
    public static String getExportFromString(String fileName) throws FileNotFoundException {
        File folder = new File("./data/exports");
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            if (file.getName().equals(fileName)) {
                return file.getAbsolutePath();
            }
        }
        throw new FileNotFoundException();
    }
}
