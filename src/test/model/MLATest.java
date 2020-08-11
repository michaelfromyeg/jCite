package model;

import model.styles.MLA;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class MLATest {
    User user1;
    User user2;
    Paper paper1;
    Paper paper2;
    Citation citation1;
    Citation citation2;

    String pattern = "dd MMMMM yyyy";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    String dateString = simpleDateFormat.format(new Date());

    String yearPattern = "yyyy";
    SimpleDateFormat formatYear = new SimpleDateFormat(yearPattern);
    String yearString = formatYear.format(new Date());

    String tweetPattern = "dd MMMMM yyyy, HH:mm";
    SimpleDateFormat formatTweet = new SimpleDateFormat(tweetPattern);
    String tweetString = formatTweet.format(new Date());

    String bookPattern = "d MMM yyyy";
    SimpleDateFormat formatBook = new SimpleDateFormat(bookPattern);
    String bookString = formatBook.format(new Date());


    @BeforeEach
    void runBefore() {
        user1 = new User("Michael");
        user2 = new User("Giovanni");
        paper1 = new Paper("CPSC 210","Java: History and Origins");
        paper2 = new Paper("CPSC 110","Why Functional Programming Will Overtake OOP");
        citation1 = new MLA(paper1.getID());
        citation2 = new MLA(paper2.getID());
        citation1.setPublicationDate(new Date());
        citation2.setPublicationDate(new Date());
    }

    @Test
    void testGetOutput() {
        String expect = "MLA: " + citation1.getAuthor() + ", " + citation1.getTitle() + ".";
        assertEquals(expect, citation1.getOutput());
    }

    @Test
    void testGetBook() {
        assertEquals("null. <i>null</i>. null, null, " + bookString + ".", citation1.getBook());
    }

    @Test
    void testGetMagazine() {
        assertEquals("null. \"null.\" <i>null</i>, " + dateString + ", pp. 0-0", citation1.getMagazine());
    }

    @Test
    void testGetNewspaper() {
        assertEquals("null. \"null.\" null, " + dateString + ", p. 0", citation1.getNewspaper());
    }

    @Test
    void testGetJournal() {
        assertEquals("null. \"null.\" null, vol. 0, no. 0, " + yearString + ", pp. 0-0.", citation1.getJournal());
    }

    @Test
    void testGetInterview() {
        assertEquals("null. Personal interview. " + dateString + ".", citation1.getInterview());
    }

    @Test
    void testGetWebsite() {
        assertEquals("null. <i>null</i>. null, " + yearString + ", null. Accessed null.", citation1.getWebsite());
    }

    @Test
    void testGetYouTubeVideo() {
        assertEquals("null. \"null.\" YouTube, uploaded by null, " + dateString + ", null.", citation1.getYouTubeVideo());
    }

    @Test
    void testGetTweet() {
        assertEquals("null. \"null.\" <i>Twitter,</i> null, " + tweetString + ", null.", citation1.getTweet());
    }
}