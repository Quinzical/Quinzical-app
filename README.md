# Quinzical

## Introduction

Quinzical is a quiz platform written in java and designed for players to test their knowledge of New Zealand through multiple categories. The quiz platform contains a game module and a practice module for the players to practice and measure their knowledge. The game module contains 25 random questions (5 questions per category) each with a score ranging from $100 to $500. The practice module contains all categories and the user will be given a random question from the category chosen. Each question is read out aloud using TTS.

### Features

- New Zealand themed
- TTS for reading aloud questions using [espeak](http://espeak.sourceforge.net/)
- SQLite database
- Cloud based User login/register using REST API backend
- Responsive UI design with [fxml](https://openjfx.io/javadoc/12/javafx.fxml/javafx/fxml/doc-files/introduction_to_fxml.html)
- Settings menu to change volume and TTS speed
- Github workflows to check code style and lint
- Online Multiplayer Quinzical

### Images
| ![](/images/user-manual/login-screen.png)  | ![](/images/user-manual/opening-menu.png)     |
| :---------------------------: | :---------------------------: |
| ![](/images/user-manual/game-questions.png)     | ![](/images/user-manual/practice-category.png) |
| ![](/images/user-manual/local-leaderboard.png)  | ![](/images/user-manual/game-question.png)     |

## How to Run

Check our server status [here](https://quinzical.gq)

### Requirements

- Linux or Unix system (designed for ubuntu)
- [espeak](http://espeak.sourceforge.net/) installed (for TTS support)
- [JavaFX 11](https://openjfx.io/) sdk installed
- Java installed (Java 14 recommended)

##### Recommendations

- Eclipse IDE
- Checkstyle installed and use [checkstyle.xml](/checkstyle.xml)
- Eclipse formatter setup using [eclipse-formatter.xml](/eclipse-formatter.xml)

### Run Quinzical

Run bash script when using 206 VM2
```bash
$ ./Quinzical.sh # or bash Quinzical.sh
```
Run on .jar by add arguments (replace `/usr/share/java/lib` to javafx sdk)
```bash
java --module-path /usr/share/java/lib --add-modules javafx.controls,javafx.media,javafx.base,javafx.fxml -jar Quinzical.jar
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
        - `processes`: includes models for controllers, helper classes shared in multiple sub-packages
        - `resources`: resources includes fxml, css and images
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
