package model.styles;

import model.Citation;

import java.io.Serializable;
import java.text.SimpleDateFormat;

/*
    This is an example of one extension of the citation class for a certain format, MLA. More will be added
    once a basic UI is running.
 */
public class MLA extends Citation implements Serializable {

    // EFFECTS: creates an MLA citation object with only a Paper ID
    public MLA(String id) {
        super(id);
    }

    // EFFECTS: returns a formatted string for a 'generic' MLA citation
    @Override
    protected String getOutput() {
        return "MLA: " + this.getAuthor() + ", " + this.getTitle() + ".";
    }

    // EFFECTS: returns a formatted string for a book in MLA
    @Override
    protected String getBook() {
        String pattern = "d MMM yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(this.getPublicationDate());

        return this.getAuthor() + ". <i>" + this.getTitle() + "</i>. " + this.getLocation() + ", " + this.getPublisher()
                + ", " + date + ".";
    }

    // EFFECTS: returns a formatted string for a magazine in MLA
    @Override
    protected String getMagazine() {
        //Author(s). "Title of Article." Title of Periodical, Day Month Year, pages.

        String pattern = "dd MMMMM yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(this.getPublicationDate());

        return this.getAuthor() + ". \"" + this.getTitle() + ".\" <i>" + this.getContainer() + "</i>, " + date
                + ", pp. " + this.getStartRange() + "-" + this.getEndRange();
    }

    // EFFECTS: returns a formatted string for a newspaper in MLA
    @Override
    protected String getNewspaper() {
        //Brubaker, Bill. "New Health Center Targets County's Uninsured Patients."
        // Washington Post, 24 May 2007, p. LZ01.

        String pattern = "dd MMMMM yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(this.getPublicationDate());

        return this.getAuthor() + ". \"" + this.getTitle() + ".\" " + this.getContainer() + ", " + date + ", "
                + "p. " + this.getStartRange();
    }

    // EFFECTS: returns a formatted string for a journal in MLA
    @Override
    protected String getJournal() {
        //Duvall, John N. "The (Super)Marketplace of Images: Television as Unmediated Mediation in
        // DeLillo's White Noise." Arizona Quarterly, vol. 50, no. 3, 1994, pp. 127-53.

        String pattern = "yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String year = simpleDateFormat.format(this.getPublicationDate());

        return this.getAuthor() + ". \"" + this.getTitle() + ".\" " + this.getContainer() + ", vol. " + this.getVolume()
                + ", no. " + this.getNumber() + ", " + year + ", pp. " + this.getStartRange() + "-" + this.getEndRange()
                + ".";
    }

    // EFFECTS: returns a formatted string for an interview in MLA
    @Override
    protected String getInterview() {
        //Smith, Jane. Personal interview. 19 May 2014.

        String pattern = "dd MMMMM yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(this.getPublicationDate());

        return this.getAuthor() + ". Personal interview. " + date + ".";
    }


    // EFFECTS: returns a formatted string for a website in MLA
    @Override
    protected String getWebsite() {
        //The Purdue OWL Family of Sites. The Writing Lab and OWL at Purdue and Purdue U, 2008,
        // owl.english.purdue.edu/owl. Accessed 23 Apr. 2008.

        String pattern = "yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String year = simpleDateFormat.format(this.getPublicationDate());

        return this.getAuthor() + ". <i>" + this.getTitle() + "</i>. " +  this.getContainer() + ", "
                + year + ", " + this.getURL() + ". Accessed " + this.getAccessDate() + ".";
    }

    // EFFECTS: returns a formatted string for a YouTube video in MLA
    @Override
    protected String getYouTubeVideo() {
        // McGonigal, Jane. “Gaming and Productivity.” YouTube, uploaded by Big Think, 3 July 2012,
        // www.youtube.com/watch?v=mkdzy9bWW3E.

        String pattern = "dd MMMMM yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(this.getPublicationDate());

        return this.getAuthor() + ". \"" + this.getTitle() + ".\" YouTube, uploaded by " + this.getContainer()
                + ", " + date + ", " + this.getURL() + ".";
    }

    // EFFECTS: returns a formatted string for a Tweet in MLA
    @Override
    protected String getTweet() {
        //@tombrokaw. “SC demonstrated why all the debates are the engines of this campaign.”
        // Twitter, 22 Jan. 2012, 3:06 a.m., twitter.com/tombrokaw/status/160996868971704320.


        String pattern = "dd MMMMM yyyy, HH:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(this.getPublicationDate());

        return this.getAuthor() + ". \"" + this.getTitle() + ".\" <i>Twitter,</i> " + this.getContainer()
                + ", " + date + ", " + this.getURL() + ".";
    }

}
