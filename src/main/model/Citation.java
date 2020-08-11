package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
    This is an abstract class intended to be extended to all citation styles, such as MLA, APA, etc. It has fields
    that are supposedly general to any citation (e.g., author, date of publication) and regular classes should
    extend to include fields which are needed. It will also be at the discretion of those regular classes
    of how to deal with varying 'types' of citations (e.g., book, video, etc).
 */

public abstract class Citation implements Serializable { // This code assumes you are citing a book in MLA format
    protected String paperID;
    protected String author;
    protected String title;
    protected String container;
    protected List<String> contributors = new ArrayList<String>();
    protected String version;
    protected int volume;
    protected int number;
    protected String publisher;
    protected Date publicationDate = null;
    protected Date accessDate = null;
    protected String location;
    protected String url;
    protected int startRange;
    protected int endRange;

    // EFFECTS: creates a citation where only the paper ID is specified
    public Citation(String id) {
        this.paperID = id;
    }

    // EFFECTS: returns a formatted string to be inserted into the final citations file
    protected abstract String getOutput();

    // EFFECTS: returns a formatted book citation
    protected abstract String getBook();

    // EFFECTS: returns a magazine magazine citation
    protected abstract String getMagazine();

    // EFFECTS: returns a formatted newspaper citation
    protected abstract String getNewspaper();

    // EFFECTS: returns a formatted journal article citation
    protected abstract String getJournal();

    // EFFECTS: returns a formatted interview citation
    protected abstract String getInterview();

    // EFFECTS: returns a formatted website citation
    protected abstract String getWebsite();

    // EFFECTS: returns a formatted YouTube video citation
    protected abstract String getYouTubeVideo();

    // EFFECTS: returns a formatted tweet citation
    protected abstract String getTweet();

    // GETTERS & SETTERS

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContainer() {
        return container;
    }

    public void setContainer(String container) {
        this.container = container;
    }

    public List<String> getContributors() {
        return contributors;
    }

    public void setContributors(List<String> contributors) {
        this.contributors = contributors;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Date getAccessDate() {
        return accessDate;
    }

    public void setAccessDate(Date accessDate) {
        this.accessDate = accessDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPaperID() {
        return paperID;
    }

    public void setPaperID(String paperID) {
        this.paperID = paperID;
    }

    public String getURL() {
        return url;
    }

    public void setURL(String url) {
        this.url = url;
    }

    public int getStartRange() {
        return startRange;
    }

    public void setStartRange(int startRange) {
        this.startRange = startRange;
    }

    public int getEndRange() {
        return endRange;
    }

    public void setEndRange(int endRange) {
        this.endRange = endRange;
    }

    // EFFECTS: prints nicely formatted citation object with all important fields
    @Override
    public String toString() {
        return "\"" + this.title + "\"" + " by " + this.author;
    }
}
