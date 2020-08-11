package persistence;

import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class SaveTest {
    User userDefault;
    User userSaved;
    User userNeverSaved;
    User userIllegal;

    @BeforeEach
    void runBefore() {
        userDefault = new User("testUser");
        userSaved = new User("saved");
        userNeverSaved = new User("neverSaved");
        userIllegal = new User("//:!sdf3978--0-_)13j4-!////////");
        Save.saveUser(userSaved);
    }


    @Test
    void testConstructor() {
        Save save = new Save();
        assertTrue(save.equals(save));
    }

    @Test
    void testSaveUser() {
        Save.saveUser(userSaved);

        File fileDefault = new java.io.File("./data/users/" + userDefault.getName() + ".cite");
        File fileSaved = new java.io.File("./data/users/" + userSaved.getName() + ".cite");
        File fileNeverSaved = new java.io.File("./data/users/" + userNeverSaved.getName() + ".cite");
        File fileIllegal = new java.io.File("./data/users/" + userIllegal.getName() + ".cite");

        Save.saveUser(userDefault);

        assertTrue(fileDefault.exists());
        assertTrue(fileSaved.exists());
        assertFalse(fileNeverSaved.exists());
        assertFalse(fileIllegal.exists());

        Save.saveUser(userIllegal);
        assertFalse(fileIllegal.exists());
    }

    @Test
    void getFileInformation() {
        Save.saveUser(userSaved);
        Save.getFileInformation(userSaved);
        Save.getFileInformation(userNeverSaved);

        File fileSaved = new File("./data/users/" + userSaved.getName() + ".cite");
        File fileNeverSaved = new File("./data/users/" + userNeverSaved.getName() + ".cite");

        assertTrue(fileSaved.exists());
        assertFalse(fileNeverSaved.exists());
    }
}
