package model;

import model.styles.APA;
import model.styles.MLA;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class PaperTest {
    User user1;
    User user2;
    Paper paper1;
    Paper paper2;
    Citation citation1;
    Citation citation2;
    Citation citation3;

    @BeforeEach
    void runBefore() {
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
    void testGenerateCitation() throws IOException {
        boolean foundFile = false;
        paper1.addCitation(citation1);
        paper1.addCitation(citation2);
        paper1.generateCitations();
        File folder = new File("./data/exports");
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile() && file.getName().equals(paper1.getID() + ".html")) {
                foundFile = true;
            }
        }
        assertTrue(foundFile);

        cleanDirectory(listOfFiles);
    }

    @Test
    void testCreateID() {
        assertEquals("paperCPSC210javah",paper1.getID());
        assertEquals("paperCPSC110oop",paper2.getID());
    }

    @Test
    void testAddPaper() {
        paper1.addCitation(citation1);
        assertEquals(citation1,paper1.getCitations().get(0));
    }

    @Test
    void testRemovePaper() {
        paper1.addCitation(citation1);
        assertFalse(paper1.removeCitation(citation2));
        assertTrue(paper1.removeCitation(citation1));
        assertEquals(0,paper1.getCitations().size());
    }

    @Test
    void testSetTitle() {
        paper1.setTitle("hello");
        assertEquals("hello",paper1.getTitle());
    }

    @Test
    void testSetCourse() {
        paper1.setCourse("hello");
        assertEquals("hello",paper1.getCourse());
    }

    @Test
    void testSetCitations() {
        ArrayList<Citation> citations = new ArrayList<>();
        citations.add(citation1);
        citations.add(citation2);
        paper1.setCitations(citations);
        assertEquals(citations,paper1.getCitations());
    }

    @Test
    void testSetID() {
        paper1.setID("hello");
        assertEquals("hello",paper1.getID());
    }

    @Test
    void testToString() {
        assertEquals("\"Java History and Origins\" for CPSC 210",paper1.toString());
    }

    void cleanDirectory(File[] listOfFiles) {
        // Helper code to clean directory
        for(File file: listOfFiles) {
            if (!file.isDirectory() && !(file.getName().equals("tobs.jpg"))) {
                file.delete();
            }
        }
    }

}