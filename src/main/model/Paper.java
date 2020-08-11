package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/*
    This is the class which instantiates a paper, which is basically any project a student could have with citations.
    Note that this means it is not limited to only written works. A paper will contain all relevant citations,
    and eventually arguments.
 */

public class Paper implements Serializable {
    public String title;
    private String course;
    private ArrayList<Citation> citations;
    private String id;

    // EFFECTS: creates new paper object with a given course, a title, empty citations list, and unique ID
    public Paper(String course, String title) {
        this.course = course;
        this.title = title;
        this.citations = new ArrayList<>();
        this.id = createID(course,title);
    }

    // MODIFIES: this
    // EFFECTS: generates a new global ID by incrementing static variable globalID
    public static String createID(String course, String title) {
        course = course.replaceAll("\\s+","");
        title = title.replaceAll("\\s+","");
        if (title.length() > 5) {
            title = title.substring(0,5);
        }
        return ("paper" + course.toUpperCase() + title.toLowerCase());
    }

    // MODIFIES: this
    // EFFECTS: Adds a give citation to the list of citations
    public void addCitation(Citation citation) {
        citations.add(citation);
    }

    // MODIFIES: this
    // EFFECTS: if a citation exists in citations, removes that citation and returns true; else false
    public boolean removeCitation(Citation citation) {
        for (Citation c : this.citations) {
            if (c.equals(citation)) {
                this.citations.remove(citation);
                return true;
            }
        }
        return false;
    }

    // EFFECTS: outputs a HTML file formatted according to citation style's specifications for each citation in paper
    public void generateCitations() throws IOException {
        File export = new File("./data/exports", this.getID() + ".html");

        String output = returnSkeleton();

        for (Citation citation : this.getCitations()) {
            output += citation.getBook();
            output += "<br>";
            output += "<br>";
        }

        output += "\n"
            + "</body>\n"
            + "\n"
            + "</html>";

        FileWriter fw = new FileWriter(export);
        fw.write(output);
        fw.close();
    }

    // EFFECTS: returns HTML template
    private String returnSkeleton() {
        return "<!DOCTYPE html>\n"
            + "<html>\n"
            + "\n"
            + "<head>\n"
            + "  <meta charset=\"utf-8\">\n"
            + "  <title></title>\n"
            + "  <meta name=\"author\" content=\"\">\n"
            + "  <meta name=\"description\" content=\"\">\n"
            + "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
            + "\n"
            //+ "  <link href=\"ui.css/normalize.ui.css\" rel=\"stylesheet\">\n"
            + "  <link href=\"ui.css/styles.ui.css\" rel=\"stylesheet\">\n"
            + "</head>\n"
            + "\n"
            + "<body>\n"
            + "\n"
            + "  <p>Hello, world!</p>\n"
            + "  <p> " + this.getTitle() + " </p>\n"
            + "  <p> " + this.getCourse() + " </p>\n";
    }


    // GETTERS & SETTERS

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public ArrayList<Citation> getCitations() {
        return citations;
    }

    public void setCitations(ArrayList<Citation> citations) {
        this.citations = citations;
    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    // EFFECTS: prints nicely formatted paper object with all fields
    @Override
    public String toString() {
        return "\"" + this.title + "\"" + " for " + this.course;
    }

}
