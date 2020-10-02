# Quinzical

## Introduction

Quinzical is a quiz platform written in java and designed for players to test their knowledge of New Zealand through multiple categories. The quiz platform contains a game module and a practice module for the players to practice and measure their knowledge. The game module contains 25 random questions (5 questions per category) each with a score ranging from $100 to $500. The practice module contains all categories and the user will be given a random question from the category chosen. Each question is read out aloud using TTS.

### Features

- New Zealand themed
- TTS for reading aloud questions using [espeak](http://espeak.sourceforge.net/)
- Autosaving game state and score
- Responsive UI design with [fxml](https://openjfx.io/javadoc/12/javafx.fxml/javafx/fxml/doc-files/introduction_to_fxml.html)
- Settings menu to change volume and TTS speed

### Images
|   ![](/images/HomeMenu.png)   | ![](/images/GameMenu.png) |
| :---------------------------: | :-----------------------: |
| ![](/images/PracticeMenu.png) | ![](/images/Question.png) |

## How to Run

### Requirements

- Linux or Unix system (designed for ubuntu)
- [espeak](http://espeak.sourceforge.net/) installed (for TTS support)
- [JavaFX 11](https://openjfx.io/) sdk installed
- Java installed (Java 11 recommended)

### Run Quinzical

Run bash script when using 206 VM
```bash
$ ./Quinzical.sh # or bash Quinzical.sh
```
Run on .jar by add arguments (replace `/home/se2062020/javafx-sdk-11.0.2/lib` to javafx sdk)
```bash
java --module-path /home/se2062020/javafx-sdk-11.0.2/lib --add-modules javafx.controls,javafx.media,javafx.base,javafx.fxml -jar Quinizical.jar
```

## Documentation

#### Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
    - `application`: application package
        - `controllers`: controllers for views
        - `helper`: helpers shared in multiple packages
        - `models`: models for controllers
        - `processes`: background processes
        - `resources`: resources includes fxml, css and images
- `data`: the folder to maintain data
    - `users`: for multiple users
        - `default`: current default user
    - `categories`: processed quinzical.txt
        
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
User data is stored into a folder called ```./data/user/default```. (designed for multiple users TODO)
- ```./data/user/default/score.txt``` stores the score in a single line
- ```./data/user/default/category/CATEGORY.txt``` stores unanswered questions using category as filename.

