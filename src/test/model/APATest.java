package model;

import model.styles.APA;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class APATest {
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
        citation1 = new APA(paper1.getID());
        citation2 = new APA(paper2.getID());
    }

    @Test
    void testGetOutput() {
        String expect = "APA: " + citation1.getAuthor() + ", " + citation1.getTitle() + ".";
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