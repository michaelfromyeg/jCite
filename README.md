# jCite: manage research papers and generate citations

jCite is for students, academics, and researchers of all ages looking to manage a knowledge graph (or "brain dump") of journal articles, podcasts, videos, and other resource material used in creating their work.

This project is written using Java, JavaFX, and CSS. Tests are written using JUnit 5.

## Features

- [x] Create and manage a list of papers you're working on
- [x] Add a list of sources associated with each paper
- [x] Generate citations in common formats (e.g., MLA or APA) for your sources
- [x] Preview a generated bibliography
- [x] Save your work

These features are currently planned or in development:

- [ ] Create a "real" account to manage your data
- [ ] Setup file sync between computers
- [ ] Create user settings (e.g., a default citation style
- [ ] Drastically improve user experience: new UI, better display of journal articles
- [ ] Link a citation to an argument I am making in my paper (i.e., one or more paragraphs)
- [ ] Visualize the relationship between citations and arguments in a mind map form
- [ ] Support for more kinds of sources (e.g., videos, podcasts)
- [ ] Notify users if there is a potential error in creating the bibliography

## Motivation

iCite's objective is two-fold. First, it will aim to outperform existing citation generators, such as [Cite This For Me](https://www.citethisforme.com/) or [EasyBib](https://www.easybib.com/). From both talking to my peers studying humanities, and from my personal experience in high school, these resources often made many errors and were generally frustrating to use (e.g., many pop-up ads, strange unwanted features).

Secondly, iCite aims to help you better _organize_ those same citations. It will aim to help you link past scholarly work to your arguments.

If you're interested in a fully-fledged product, I highly recommend you look at [Zotero](https://www.zotero.org/); it has a lot of the same objectives as this project and is supported by a vast open source community. This project is more-so for my learning, and to experiment with Java.

## Development

Contributors are always welcome. Here are the steps you'll need to take to run this project locally.

1. Clone the project.
    
    ```shell
    gh repo clone michaelfromyeg/jCite
    # OR 
    git clone git@github.com:michaelfromyeg/jCite.git
    ```   

2. Open the project in your editor of choice. [IntelliJ IDEA](https://www.jetbrains.com/idea/) is highly preferred; the `.idea` folder has been included in version control to support this.
3. Install the necessary dependencies.
    
    * [Amazon Corretto 8](https://docs.aws.amazon.com/corretto/latest/corretto-8-ug/downloads-list.html) is a great choice for your version of Java, but any Java 8 version will do
    * You'll need to install JUnit 5 as well

4. Run `Main.java`, and mess around with the application!
5. If you encounter any issues, feel free to [open an issue](https://github.com/michaelfromyeg/jCite/issues).