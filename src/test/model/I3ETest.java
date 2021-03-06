package model;

import model.styles.I3E;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class I3ETest {
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
        citation1 = new I3E(paper1.getID());
        citation2 = new I3E(paper2.getID());
    }

    @Test
    void testGetOutput() {
        String expect = "I3E: " + citation1.getAuthor() + ", " + citation1.getTitle() + ".";
        assertEquals(expect, citation1.getOutput());
    }

    @Test
    void testGetBook() {
        assertEquals(citation1.getBook(),null);
    }

    @Test
    void testGetMagazine() {
        assertEquals(citation1.getMagazine(),null);
    }

    @Test
    void testGetNewspaper() {
        assertEquals(citation1.getNewspaper(),null);
    }

    @Test
    void testGetJournal() {
        assertEquals(citation1.getJournal(),null);
    }

    @Test
    void testGetInterview() {
        assertEquals(citation1.getInterview(),null);
    }

    @Test
    void testGetWebsite() {
        assertEquals(citation1.getWebsite(),null);
    }

    @Test
    void testGetYouTubeVideo() {
        assertEquals(citation1.getYouTubeVideo(),null);
    }

    @Test
    void testGetTweet() {
        assertEquals(citation1.getTweet(),null);
    }

}