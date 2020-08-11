package persistence;

import model.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeleteTest {
    File file;
    User user;

    @BeforeEach
    void runBefore() {
        user = new User("michaelfromyeg");
        file = new File("./data/users/" + user.getName() + ".cite");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testConstructor() {
        Delete del = new Delete();
        assertTrue(del.equals(del));
    }

    @Test
    void testDeleteFile() {
        Delete.deleteFile(user);
        assertFalse(file.exists());
    }
}