package model.styles;

import model.Citation;

import java.io.Serializable;
import java.text.SimpleDateFormat;

/*
    This is an example of one extension of the citation class for a certain format, APA. More will be added
    once a basic UI is running.
 */
public class I3E extends Citation implements Serializable {

    // EFFECTS: creates an I3E citation object with only a Paper ID
    public I3E(String id) {
        super(id);
    }

    // REQUIRES: a publication date to have been instantiated (the user will be warned if not!)
    // EFFECTS: returns a formatted string to be inserted into the final citations file
    @Override
    protected String getOutput() {
        return "I3E: " + this.getAuthor() + ", " + this.getTitle() + ".";
    }

    @Override
    protected String getBook() {
        return null;
    }

    @Override
    protected String getMagazine() {
        return null;
    }

    @Override
    protected String getNewspaper() {
        return null;
    }

    @Override
    protected String getJournal() {
        return null;
    }

    @Override
    protected String getInterview() {
        return null;
    }

    @Override
    protected String getWebsite() {
        return null;
    }

    @Override
    protected String getYouTubeVideo() {
        return null;
    }

    @Override
    protected String getTweet() {
        return null;
    }
}
