package persistence;

import model.Citation;
import model.Paper;
import model.User;
import model.styles.APA;
import model.styles.MLA;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class LoadTest {
    User userUnsaved;
    User userSaved;

    User user1;
    User user2;
    Paper paper1;
    Paper paper2;
    Citation citation1;
    Citation citation2;
    Citation citation3;

    @BeforeEach
    void runBefore() {
        userUnsaved = new User("unsaved");
        userSaved = new User("saved");
        Save.saveUser(userSaved);

        user1 = new User("Michael");
        user2 = new User("Giovanni");
        paper1 = new Paper("CPSC 210","Java History and Origins");
        paper2 = new Paper("CPSC 110","OOP");
        citation1 = new MLA(paper1.getID());
        citation2 = new MLA(paper2.getID());
        citation3 = new APA(paper1.getID());

        Date date = new Date();

        citation1.setAuthor("Kiczales, Gregor");
        citation1.setTitle("Aspect-Oriented Programming Foundations");
        citation1.setContainer("EdX");
        citation1.setLocation("City1");
        citation1.setPublisher("Publisher1");
        citation1.setPublicationDate(date);

        citation2.setAuthor("Bates, Simon");
        citation2.setTitle("Kinematics & Dynamics");
        citation2.setContainer("EdX");
        citation2.setLocation("City2");
        citation2.setPublisher("Publisher2");
        citation2.setPublicationDate(date);

        citation3.setAuthor("Mom, My");
        citation3.setTitle("How To Cook");
        citation3.setContainer("The Cupboard");
        citation3.setLocation("City3");
        citation3.setPublisher("Publisher3");
        citation3.setPublicationDate(date);
    }

    @Test
    void testConstructor() {
        Load load = new Load();
        assertTrue(load.equals(load));
    }

    @Test
    void testLoadUser() {
        assertEquals(null, Load.loadUser(userUnsaved.getName()));
        assertTrue(userSaved.equals(Load.loadUser(userSaved.getName())));
    }

    @Test
    void testUsernameTaken() {
        assertFalse(Load.usernameTaken("helloWorld"));

        assertTrue(Load.usernameTaken("saved"));
    }

    @Test
    void testGetExportsList() {
        try {
            paper1.generateCitations();
            paper2.generateCitations();
        } catch (Exception e) {
            fail();
        }
        assertEquals(2, Load.getExportsList().size());
    }

    @Test
    void testGetExportFromString() {
        try {
            paper1.generateCitations();
            Load.getExportFromString("hello world");
            fail();
        } catch (IOException e) {
            // Okay
        }

        try {
            Load.getExportFromString(paper1.getID() + ".html");
            // Okay
        } catch (Exception e) {
            fail();
        }
    }
}
