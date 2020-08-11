package model;

import model.styles.APA;
import model.styles.MLA;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CitationTest {
    User user1;
    User user2;
    Paper paper1;
    Paper paper2;
    Citation citation1;
    Citation citation2;

    @BeforeEach
    void runBefore() {
        user1 = new User("Michael");
        user2 = new User("Giovanni");

        paper1 = new Paper("CPSC 210","Java: History and Origins");
        paper2 = new Paper("CPSC 110","Why Functional Programming Will Overtake OOP");

        citation1 = new MLA(paper1.getID());
        citation1.setAuthor("Kiczales, Gregor");
        citation1.setTitle("Aspect-Oriented Programming Foundations");
        citation1.setContainer("EdX");

        citation2 = new APA(paper2.getID());
        citation2.setAuthor("Bates, Simon");
        citation2.setTitle("Kinematics & Dynamics");
        citation2.setContainer("EdX");
    }

    @Test
    void testSetAuthor() {
        citation1.setAuthor("hello");
        assertEquals("hello",citation1.getAuthor());
    }

    @Test
    void testSetTitle() {
        citation1.setTitle("hello");
        assertEquals("hello",citation1.getTitle());
    }

    @Test
    void testSetContainer() {
        citation1.setContainer("hello");
        assertEquals("hello",citation1.getContainer());
    }

    @Test
    void testSetVersion() {
        citation1.setVersion("hello");
        assertEquals("hello",citation1.getVersion());
    }

    @Test
    void testSetVolume() {
        citation1.setVolume(2);
        assertEquals(2,citation1.getVolume());
    }

    @Test
    void testSetNumber() {
        citation1.setNumber(2);
        assertEquals(2,citation1.getNumber());
    }

    @Test
    void testSetPublisher() {
        citation1.setPublisher("hello");
        assertEquals("hello",citation1.getPublisher());
    }

    @Test
    void testSetLocation() {
        citation1.setLocation("hello");
        assertEquals("hello",citation1.getLocation());
    }

    @Test
    void testSetPaperID() {
        citation1.setPaperID("hello");
        assertEquals("hello",citation1.getPaperID());
    }

    @Test
    void testSetContributors() {
        List<String> contributors = new ArrayList<>();
        contributors.add("hello");
        contributors.add("goodbye");
        citation1.setContributors(contributors);
        assertEquals(contributors,citation1.getContributors());
    }

    @Test
    void testSetAccessDate() {
        Date date = new Date();
        citation1.setAccessDate(date);
        assertEquals(date,citation1.getAccessDate());
    }

    @Test
    void testSetURL() {
        citation1.setURL("exampleurl.com");
        assertEquals("exampleurl.com",citation1.getURL());
    }

    @Test
    void testSetStartRange() {
        citation1.setStartRange(3);
        assertEquals(3,citation1.getStartRange());
    }

    @Test
    void testSetEndRange() {
        citation1.setEndRange(4);
        assertEquals(4,citation1.getEndRange());
    }

    @Test
    void testToString() {
        String expected = "\"Aspect-Oriented Programming Foundations\" by Kiczales, Gregor";
        assertEquals(expected,citation1.toString());
    }
}