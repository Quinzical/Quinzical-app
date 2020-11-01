# Quinzical

## Introduction

Quinzical is a quiz platform written in java and designed for players to test their knowledge of New Zealand through multiple categories. The quiz platform contains a game module and a practice module for the players to practice and measure their knowledge. The game module contains 25 random questions (5 questions per category) each with a score ranging from $100 to $500. The practice module contains all categories and the user will be given a random question from the category chosen. Each question is read out aloud using TTS. Quinzical multiplayer is an online feature that is multi-platform with an implemented web client to play online with your friend and family. Heavily inspired by Among Us.

### Features

- New Zealand themed
- TTS for reading aloud questions using [espeak](http://espeak.sourceforge.net/)
- SQLite database
- Cloud based User login/register using REST API backend
- Responsive UI design with [fxml](https://openjfx.io/javadoc/12/javafx.fxml/javafx/fxml/doc-files/introduction_to_fxml.html)
- Settings menu to change volume and TTS speed
- Github workflows to check code style and lint
- Online Multiplayer Quinzical (with web client)

### Images
| ![](/images/login-screen.png)  | ![](/images//opening-menu.png)     |
| :------------------------------: | :---------------------------: |
| ![](/images/game-categories.png)     | ![](/images/practice-module.png) |
| ![](/images/local-leaderboard.png)  | ![](/images/game-question.png)     |

## How to Run

Check our server status [here](https://quinzical.gq)

### Requirements

- Linux or Unix system (designed for ubuntu)
- [espeak](http://espeak.sourceforge.net/) installed (for TTS support)
- [JavaFX 11](https://openjfx.io/) sdk installed
- Java installed (Java 14 recommended)
- Atleast 4GB of system RAM (uses approx 1GB of memory)

##### Recommendations

- Eclipse IDE
- Checkstyle installed and use [checkstyle.xml](/checkstyle.xml)
- Eclipse formatter setup using [eclipse-formatter.xml](/eclipse-formatter.xml)

### Run Quinzical

Run bash script when using 206 VM2 with atleast 4GB RAM
```bash
$ ./Quinzical.sh # or bash Quinzical.sh
```
Run on .jar by add arguments (replace `/usr/share/java/lib` to javafx sdk)
```bash
java --module-path /usr/share/java/lib --add-modules javafx.controls,javafx.media,javafx.base,javafx.fxml,javafx.web,javafx.graphics -jar Quinzical.jar
```

#### Using login
If you do not want to create a login, our default login is as below:
**Username:** ```Bob ```
**Password:** ```password```

## Documentation

#### Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
    - `quinzical`: application package
        - `controllers`: controllers for views
            - `local`: controller for local games
            - `online`: controller for online games
            - `util`: controller utils for buttons and alerts etc.
        - `resources`: includes fxml, css, font
            - `images`: for all iamges
        - `util`: models, sql and api
            - `api`: used to access api
            - `models`: models for accessing data
            - `processes`: task/thread related processes
            - `socket`: socket io handlers
            - `sql`: used to access sqlite
- `data`: the folder to maintain data
    - `data.db`: sqlite file
    - `hash.txt`: checksum is used to check if quinzical.txt is changed
    - `auth.txt`: saves jwt token for auto login
    
        
#### Questions
Questions are in the [file](/quinzical.txt) ```quinzical.txt``` in the format:
```
CATEGORY_NAME
QUESTION\ (What is) ANSWER
```
Example:
```
NZ Life
This is the name for a traditional fenced Māori village\ (What is) a Pā
This is the Māori word for stomach\ (What is) Puku
```


#### User Data
Local userdata is stored in sqlite and on cloud using the [endpoint](https://quinzical-api.herokuapp.com/)

## Code Attribution and Resources used
- [Styling pop ups](https://stackoverflow.com/a/37368747/13876862)
- [Scene Manager Concept](https://stackoverflow.com/a/52932422/13876862)
- [Learning more about JavaFX Animations](https://www.genuinecoder.com/javafx-animation-tutorial/)
- [Socket io usage](https://github.com/socketio/socket.io-client-java#usage)
- [Using org.json](https://stackoverflow.com/a/19018552/13876862)
- [Idea of using Timelines to animate](https://stackoverflow.com/questions/20022889/how-to-make-the-ball-bounce-off-the-walls-in-javafx)
- [TilesFX demo project was referenced to use library](https://github.com/HanSolo/tilesfxdemo) (bad docs)
- [Native HttpClient was used](https://openjdk.java.net/groups/net/httpclient/intro.html)

Most code follow java and library documentations.

