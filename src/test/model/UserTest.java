package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    User user1;
    User user2;
    Paper paper1;
    Paper paper2;

    @BeforeEach
    void runBefore() {
        user1 = new User("Michael");
        user2 = new User("Giovanni");
        paper1 = new Paper("CPSC 210","Java: History and Origins");
        paper2 = new Paper("CPSC 110","Why Functional Programming Will Overtake OOP");
    }

    @Test
    void testAddPaper() {
        user1.addPaper(paper1);
        assertEquals(paper1,user1.getPapers().get(0));
    }

    @Test
    void testRemovePaper() {
        user1.addPaper(paper1);
        assertFalse(user1.removePaper(paper2));
        assertTrue(user1.removePaper(paper1));
        assertEquals(0,user1.getPapers().size());
    }

    @Test
    void testSetName() {
        user1.setName("hello");
        assertEquals("hello",user1.getName());
    }

    @Test
    void testSetPapers() {
        ArrayList<Paper> papers = new ArrayList<>();
        papers.add(paper1);
        papers.add(paper2);
        user1.setPapers(papers);
        assertEquals(papers,user1.getPapers());
    }

    @Test
    void testToString() {
        ArrayList<Paper> papers = new ArrayList<>();
        papers.add(paper1);
        user1.setPapers(papers);
        String testString1 = "This user is named Michael and has these papers ";
        String testStringPaper1 = "\"Java: History and Origins\" for CPSC 210";
        assertEquals(testString1 + "[" + testStringPaper1 + "]",user1.toString());
    }

    @Test
    void testEquals() {
        assertTrue(user1.equals(user1));
        assertFalse(user1.equals(null));
        assertFalse(user1.equals(new String()));
        assertFalse(user1.equals(user2));

        User user3 = user1;
        assertTrue(user1.equals(user3));

        User user4 = new User("Michael");
        assertTrue(user1.equals(user4));
        user4.addPaper(new Paper("Course","Title"));
        assertFalse(user1.equals(user4));

    }

    @Test
    void testHashCode() {
        assertFalse(user1.hashCode() == user2.hashCode());
        User user1Copy = new User("Giovanni");
        assertFalse(user1.hashCode() == user1Copy.hashCode());
    }

}