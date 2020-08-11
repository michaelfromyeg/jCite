# iCite: A desktop application to assist with organization citations for papers and large assignments

## What will iCite do?

iCite's objective is two-fold. First, it will aim to outperform existing online options that generate citations, such as [Cite This For Me](https://www.citethisforme.com/) or [EasyBib](https://www.easybib.com/). From both talking to my peers studying humanities, and from my personal experience in high school, these resources often made many errors and were generally frustrating to use (e.g., many pop-up ads, strange unwanted features, and more).

Secondly, iCite, beyond the aforementioned existing apps, will aim to help you *organize* those same citations. It will have some sort of mind map functionality to help you create a brief description of arugments in your essay, and then link your already created citations to those paragraphs, so that when you go to write the final piece your citations are already organized and in-front of you.

> Organizing is what you do before you do something, so that when you do it, it is not all mixed up.
> —A. A. Milne

## Who will use it?

I could see a few primary user groups for this app.

- A student who is already effective at writing papers, but looking for something to generate citations for them (to reduce errors)
- A student who struggles with organizing their citations in writing term papers
- Graduate students, authors, and researchers who write very long papers and thus have difficulty with managing their citations and argument form

## Why is this project of interest to you?

Well, in deciding what I wanted to create for CPSC 210, I wanted to create something both challenging and personally rewarding. Building a to-do list or weather app would have to be an Android application to be of any real use to me, so I brainstormed something that would really only be useful on a desktop. Additionally, in writing a 4,000 word research paper required to receive my high school diploma, I surprisingly found citation management to pose much more of a challenge that reaching the word count.

## How is it built

This app will be built using Java in IntelliJ, and I expect I will need to use the Swing library for the GUI.

## User Stories

As I user...

### First four (Phase 1)

- I want to be able to create a new assignment for a specific class
- I want to be able to add a citation to a paper I am currently working on
- I want to be able to output my citation list to a file I can 'use' (in this case, it will be an HTML export which can copy-paste into Word, since writing a Word document directly would be unncessarily difficult!)
- I want to be able to specify which citation style this paper needs to use

### Data persistence (Phase 2)
- I want to be able to save my citations as a part of my user account to my machine
- I want to be able to load my past citations via inputting my username
- I want to be able to delete a user account
- I want to be able to update my account after loading it, and overwrite my past save automatically
- I want to be notified if I have saved my file before I quit

### GUI (Phase 3)
- I want to be able to add a paper to myself as a user, and add a citation to a given paper
- I want to be able to see my citation in an HTML window
- I want to still be able to save and load my account
- I want to see the product's logo in the main screen (look-and-feel)


### And for the future...

- I want to be able to link a citation to an argument I am making in my paper (i.e., one or more paragraphs)
- I want to be able to visualize the relationship between the citations and my arguments in a mind map form
- I want a comfortable look and feel to the application
- I want to be able to specify the type of citation (e.g., a YouTube video versus a book versus a recorded interview) and input different information based on the type
- I want to be confident the software has specified my citation according to modern styles, or be notified if the software is unsure
- and more to come...

## Instructions for Grader

### GUI (Phase 3) Guide

- First, note that this project is not completely robust, so please do follow the instructions carefully. Additionally, currently only MLA citations are supported, as creating the many iterations of forms needed for varying types of citations is beyond the scope of this phase (and is not needed for the GUI to be functional). However, expect it to appear in phase 4!
- To use the JavaFX HTML Editor with the JDK our class uses, I had to manually add a DLL file. The steps you'll need to take are:

1. Download the latest version of JavaFX from Gluon [here](https://gluonhq.com/products/javafx/)
2. Extract the folder on your computer 
3. Find the jfxmedia.dll file
4. Copy and paste the file into the /bin folder of your Amazon Corette JDK folder.

- If you feel uncomfortable doing as the TA, I am more than happy to provide a video demo. The HTMLEditor is a key feature of my app's functionality, though actually you can see the audiovisual component, add X to Y, and save and load all without it, so it's not completely necessary.

Now for the actual project!

- Run the 'Main' file contained in the UI package.
- Hit the sign up link at the bottom and create an account with some username. Note that password and 'remember me' functionality weren't implemented in this build.
- You can locate my visual component by...: Note the logo in the header at the top of the screen. That was my AV component (a logo image).
- You can generate the first required event by...: Click 'Manage Assignments' and hit 'New' at the bottom to create an assignment. This is adding 'X' (assignments) to 'Y' yourself, the user.
- You can also add 'X' citations to 'Y' the assignment. Select the assignment in the table, and then hit add citation. Note only MLA is supported at this time. Creating an assignment with a button is my first event for add 'X' to 'Y'. Select the assignment in the table and you can see the citation list on the screen.
- You can generate the second required event by...: After creating a paper, you may also edit its title and course. Select the paper in the table and hit the edit button. Selecting the assignment is my second event for add 'X' to 'Y'.
- Hit generate citations once you've tried making a few citations. Then go back and navigate to 'View Exports'.
- Select your exported HTML file and view it by hitting preview. Note the program will crash if you did not add the DLL file required for HTMLViewer.
- You can save the state of my application by...: To save your account, you may sign out (this auto saves) on the main screen. You may also hit the close window button ('X' in the top right corner) and see an option to manually save. It also notifies ths user of when they last saved before quitting.
- You can reload the state of my application by...: To load your account, type your username into the login page.

Though there is more the application does, this is all needed for Phase 3. Thanks for taking the time to mark my project!