package persistence;

import model.User;

import java.io.File;

public class Delete {

    // REQUIRES: user has already saved their account before
    // EFFECTS: deletes the user's file from /users/
    public static void deleteFile(User user) {
        File file = new File("./data/users/" + user.getName() + ".cite");
        file.delete();
    }

}
