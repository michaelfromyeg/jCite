package ui.cli;

import model.Citation;
import model.Paper;
import model.User;
import model.styles.APA;
import model.styles.Chicago;
import model.styles.I3E;
import model.styles.MLA;
import persistence.Delete;
import persistence.Load;
import persistence.Save;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/*
    Code inspired from 'TellerApp,' from UBC CPSC 210

    Runs main app code for a given user
 */

public class UserApp implements Serializable {

    private User user;
    public Scanner scan = new Scanner(System.in);
    private Paper activePaper = new Paper("","");

    private final String[] supportedStyles = new String[]{"APA","Chicago","IEEE","MLA"};
    private final String[] supportedFormats = new String[]{"Books", "Magazines","Newspapers","Journals",
            "Interviews","Websites","Tweets","YouTube Videos"};

    // EFFECTS: runs the new user app
    public UserApp() {
        runApp();
    }

    // EFFECTS: while the user would like to continue, keep taking commands and loading the menu iteratively
    private void runApp() {
        boolean cont = true; // Continue
        String command;
        checkReturning();
        while (user == null) {
            checkReturning();
        }
        while (cont) {
            loadMenu();
            command = scan.next().toLowerCase();
            cont = runCommand(command);
        }
        System.out.println("Thank you for using iCite.");
        displayLogo();
        System.out.println("Goodbye!");
    }

    // EFFECTS: checks whether or not the user is a returning user or a new user
    private void checkReturning() {
        System.out.println("Welcome to iCite!");
        System.out.println("Are you a new or returning user?");
        String choice = scan.next().toLowerCase();
        handleChoice(choice,false);
    }

    private void handleChoice(String choice, boolean validInput) {
        while (!validInput) {
            switch (choice) {
                case "new":
                    createUser();
                    validInput = true;
                    break;
                case "returning":
                    System.out.println("Welcome back!\nWhat is your username?");
                    String name = scan.next();
                    user = Load.loadUser(name);
                    if (user == null) {
                        System.out.println("Sorry... I couldn't find that username. Maybe try again?");
                        return;
                    } else {
                        validInput = true;
                    }
                    break;
                default:
                    System.out.println("Please enter a valid input! Select either 'new' or 'returning.'");
                    choice = scan.next().toLowerCase();
                    break;
            }
        }
    }

    // MODIFIES: User
    // EFFECTS: parses user input to create a new user
    private void createUser() {
        System.out.println("Thanks for choosing to try iCite.");
        System.out.println("Please select a username.");
        String username = scan.next();
        boolean nameTaken = Load.usernameTaken(username);
        while (nameTaken) {
            System.out.println("Oops... that username is taken. Please select another username:");
            generateUsernames(username);
            username = scan.next();
            nameTaken = Load.usernameTaken(username);
        }
        user = new User(username);
        System.out.println("Hello " + username + "! Thank you for registering.");
    }

    // EFFECTS: loads top-level menu of basic options for user
    private void loadMenu() {
        displayLogo();
        System.out.println("\nHello " + user.getName() + ". Please choose on of the following options:");
        System.out.println("\t(a) Create a new assignment");
        System.out.println("\t(#) Load an assignment (by number)");
        System.out.println("\t(c) Add citation to currently active assignment");
        System.out.println("\t(e) Export citations to data folder");
        System.out.println("\t(s) Save your account");
        System.out.println("\t(d) Delete your account");
        System.out.println("\t(q) Quit");
        System.out.println("\tUnsure? Type \"help\" for more options!");
        printAssignments();
    }

    // EFFECTS: Prints an ASCII logo to the console
    private void displayLogo() {
        System.out.println("\n"
                + "           ,----..             ___                   \n"
                + "  ,--,    /   /   \\   ,--,   ,--.'|_                \n"
                + ",--.'|   |   :     :,--.'|   |  | :,'                \n"
                + "|  |,    .   |  ;. /|  |,    :  : ' :                \n"
                + "`--'_    .   ; /--` `--'_  .;__,'  /     ,---.       \n"
                + ",' ,'|   ;   | ;    ,' ,'| |  |   |     /     \\     \n"
                + "'  | |   |   : |    '  | | :__,'| :    /    /  |     \n"
                + "|  | :   .   | '___ |  | :   '  : |__ .    ' / |     \n"
                + "'  : |__ '   ; : .'|'  : |__ |  | '.'|'   ;   /|     \n"
                + "|  | '.'|'   | '/  :|  | '.'|;  :    ;'   |  / |     \n"
                + ";  :    ;|   :    / ;  :    ;|  ,   / |   :    |     \n"
                + "|  ,   /  \\   \\ .'  |  ,   /  ---`-'   \\   \\  /  \n"
                + " ---`-'    `---`     ---`-'             `----'       \n"
                + "                                                     \n");
    }

    // EFFECTS: prints a list of the users current assignments
    private void printAssignments() {
        boolean foundActive = false;
        String active = "--> ";
        ArrayList<Paper> papers = user.getPapers();
        if (papers.size() == 0) {
            System.out.println("No assignments to see!");
        } else {
            for (int i = 0; i < papers.size(); i++) {
                if (activePaper.equals(papers.get(i))) {
                    System.out.println(active + "Paper " + i + ": " + papers.get(i));
                    foundActive = true;
                } else {
                    System.out.println("Paper " + i + ": " + papers.get(i));
                }
            }
            if (!foundActive) {
                System.out.println("No assignment is currently active! (Be careful...)");
            }
        }
    }

    // EFFECTS: processes user input, a one character string from the menu
    private boolean runCommand(String command) {
        if (runNumberInput(command)) {
            return true;
        } else {
            while (true) {
                switch (command) {
                    case "q":
                        return runQuit();
                    case "a":
                        return runCreateAssignment();
                    case "c":
                        return runAddCitation();
                    case "e":
                        return runExportCitations();
                    case "s":
                        return runSaveUser();
                    case "d":
                        return runDeleteUser();
                    //case "help":
                    //    return runHelp();
                    default:
                        System.out.println("Please input a valid command!");
                        return runCommand(scan.next());
                }
            }
        }
    }

    // EFFECTS: checks if input is a number (rather than a string), and if so, loads active assignment
    private boolean runNumberInput(String command) {
        try {
            int assignment = Integer.parseInt(command);
            if (assignment > user.getPapers().size()) {
                System.out.println("That paper doesn't exist!");
                return false;
            }
            return runGetAssignment(assignment);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // EFFECTS: exits the program after a confirmation message
    private boolean runQuit() {
        Save.getFileInformation(user);
        System.out.println("Are you sure you want to quit? (y/n)");
        String confirmation = scan.next().toLowerCase();
        if (confirmation.equals("y")) {
            return false;
        }
        return true;
    }

    // EFFECTS: deletes the user's saved file
    private boolean runDeleteUser() {
        Save.getFileInformation(user);
        System.out.println("Are you sure you want to delete your saved account? (y/n)");
        String confirmation = scan.next().toLowerCase();
        if (confirmation.equals("y")) {
            Delete.deleteFile(user);
            System.out.println("Your user account was successfully deleted.");
            System.out.println("Press 's' to re-save your information or 'q' to quit without saving.");
        }
        return true;
    }

    // EFFECTS: saves serialized user object to /users/ directory
    private boolean runSaveUser() {
        System.out.println("Saving your assignments to " + user.getName() + ".cite");
        System.out.println("Attempting to save...");
        Save.saveUser(user);
        System.out.println("...Save successful!");
        System.out.println("You may now safely exit the program.");
        return true;
    }

    // MODIFIES: User
    // EFFECTS: creates a new user assignment with course and title
    private boolean runCreateAssignment() {
        //Scanner scanCourse = new Scanner(System.in);
        //Scanner scanTitle = new Scanner(System.in);
        String course = getCourse();
        String title = getTitle();
        Paper p = new Paper(course, title);
        user.addPaper(p);
        System.out.println("Your assignment was added!");
        return true;
    }

    // EFFECTS: gets course of a given assignment
    private String getCourse() {
        Scanner scanCourse = new Scanner(System.in);
        System.out.println("What course is this assignment for?");
        System.out.println("ex. CPSC 110, ENGL 100, FRNH 253");
        String course = scanCourse.nextLine();
        return course;
    }

    // EFFECTS: gets course of a given assignment
    private String getTitle() {
        Scanner scanTitle = new Scanner(System.in);
        System.out.println("What would you like to title your paper?");
        System.out.println("ex. \"How I Went Around The World in 80 Days\"");
        String title = scanTitle.nextLine();
        return title;
    }

    // MODIFIES: activePaper
    // EFFECTS: chooses a new paper based on integer input
    private boolean runGetAssignment(int assignment) {
        Paper chosenPaper = user.getPapers().get(assignment);
        activePaper = chosenPaper;
        System.out.println("You chose " + chosenPaper);
        System.out.println("It is currently active.");
        return true;
    }

    // REQUIRES: Active paper != null
    // EFFECTS: adds a citation to active paper
    private boolean runAddCitation() {
        System.out.println("Choose between " + this.returnStyles());
        String type = scan.next().toLowerCase();
        switch (type) {
            case "mla":
                System.out.println("Creating MLA citation....");
                createMLA();
                break;
            case "apa":
                createAPA();
            case "chicago":
                createChicago();
            case "ieee":
                createI3E();
            default:
                System.out.println("That style is not currently supported, sorry!");
        }
        return true;
    }

    // EFFECTS: creates an MLA citation
    private boolean createMLA() {
        Citation mla = new MLA(activePaper.getID());
        System.out.println("Specify a format for this citation");
        System.out.println("Choose between " + this.returnFormats());
        String format = scan.next().toLowerCase();
        setMLA(mla, format);
        System.out.println("MLA citation added" + mla);
        activePaper.addCitation(mla);
        return true;
    }

    // EFFECTS: sets citation fields needed for MLA
    private Citation setMLA(Citation mla, String format) {
        switch (format) {
            case "book":
                setAuthor(mla);
                setTitle(mla);
                setLocation(mla);
                setPublisher(mla);
                setDate(mla);
                break;
            default:
                System.out.println("Defaulted");
        }
        return mla;
    }

    // EFFECTS: sets the author for a citation
    private Citation setAuthor(Citation cite) {
        Scanner scanAuthor = new Scanner(System.in);
        System.out.println("Enter the author's name as Lastname, Firstname:");
        System.out.println("(Note that only one author is supported at this time.)");
        String authorName = scanAuthor.nextLine();
        cite.setAuthor(authorName);
        return cite;
    }

    // EFFECTS: sets the title for a citation
    private Citation setTitle(Citation cite) {
        System.out.println("Enter the title of the work:");
        String title = scan.next();
        cite.setTitle(title);
        return cite;
    }

    // EFFECTS: sets the location for a citation
    private Citation setLocation(Citation cite) {
        System.out.println("Enter the location where the work was published:");
        String location = scan.next();
        cite.setLocation(location);
        return cite;
    }

    // EFFECTS: sets the publisher for a citation
    private Citation setPublisher(Citation cite) {
        System.out.println("Enter the publisher's title:");;
        String publisher = scan.next();
        cite.setPublisher(publisher);
        return cite;
    }

    // EFFECTS: sets the date for a citation
    private Citation setDate(Citation cite) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Enter the date the work was published as dd/MM/yyyy:");
        String dateString = scan.next();
        System.out.println("DateString: " + dateString);
        Date date;
        try {
            date = formatter.parse(dateString);
        } catch (Exception e) {
            date = new Date();
        }
        cite.setPublicationDate(date);
        return cite;
    }

    // EFFECTS: creates an APA citation
    private boolean createAPA() {
        Citation apa = new APA(activePaper.getID());
        System.out.println("APA citation added" + apa);
        activePaper.addCitation(apa);
        return true;
    }

    // EFFECTS: creates an I3E citation
    private boolean createI3E() {
        Citation i3e = new I3E(activePaper.getID());
        System.out.println("IEEE citation added" + i3e);
        activePaper.addCitation(i3e);
        return true;
    }

    // EFFECTS: creates an Chicago citation
    private boolean createChicago() {
        Citation chicago = new Chicago(activePaper.getID());
        System.out.println("Chicago citation added" + chicago);
        activePaper.addCitation(chicago);
        return true;
    }

    // EFFECTS: exports citations to data folder for active assignment
    // (see Citation class for more detail on how export works)
    private boolean runExportCitations() {
        try {
            activePaper.generateCitations();
        } catch (Exception e) {
            System.out.println("runExportCitations: Uh oh, an error occurred!");
            return false;
        }
        System.out.println("Citations exported!");
        return true;
    }

    // EFFECTS: returns print of currently supported citation formats
    private String returnFormats() {
        String result = "";
        int numStyles = supportedFormats.length;
        for (int i = 0; i < numStyles; i++) {
            if (i == numStyles - 1) {
                result += supportedFormats[i] + ".";
            } else if (i == numStyles - 2) {
                result += supportedFormats[i] + ", and ";
            } else {
                result += supportedFormats[i] + ", ";
            }
        }
        return result;
    }

    // EFFECTS: returns print of currently supported citations styles
    private String returnStyles() {
        String result = "";
        int numStyles = supportedStyles.length;
        for (int i = 0; i < numStyles; i++) {
            if (i == numStyles - 1) {
                result += supportedStyles[i] + ".";
            } else if (i == numStyles - 2) {
                result += supportedStyles[i] + ", and ";
            } else {
                result += supportedStyles[i] + ", ";
            }
        }
        return result;
    }

    // EFFECTS: generates a help message
    private boolean runHelp() {
        System.out.println("Help command is TBD!");
        return true;
    }

    // EFFECTS: prints out new possible usernames
    private void generateUsernames(String name) {
        System.out.println("ex. " + name + "1\tthereal" + name + "\t" + name + "2");
    }

}
