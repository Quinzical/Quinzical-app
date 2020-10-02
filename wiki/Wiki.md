# Wiki for team-44 assignment three
## This wiki contains meeting notes from Zoom meetings that were held twice weekly over the course of this assignment construction
---
### Meeting 1: 07/09/2020
#### Meeting Plan
* Go through available meeting times - 2 meetings a week? And what days?
  * Not Tuesday and Thursdays.
* Team Agreement
  * Maggie to complete before the next meeting.
* Git
* Agile, MVP
  * Could try to use agile during the design process.
* JavaFX or Swing?
  * Both agreed to JavaFX.
  * We can use scene builder and fxml to create our GUI look and feels.
* Design plan - paper, scan it in.

#### Next meeting plan
* Each to create a GUI design, two for each target user (as specified in the project description).
* Next meeting: Friday evening (11/09/2020).

---

### Meeting 2: 11/09/2020
#### Meeting Plan
* GUI design
* View designs - likes/dislikes
  * Both gave feedback on one anothers designs on a separate document
* Choose target user
  * Agreed upon adult
* Colour theme
  * Decided upon light blue and yellow, blackish for font from the designs we had created
* Game implementation
  * How to play - clue + questions
  * Settings - audio settings
  * Pages
    * Category practice page
    * Category page game
    * Question page
    * High score/record

#### Next meeting plan
* Ask if qunizical has an I?
* Scene builder - final design homepage and practice module.
* Change speech speed (research)
* Next meeting: Wednesday evening (16/09/2020).

---

### Meeting 3: 16/09/2020
#### Meeting Plan
* Fxml 
* General
* Stylesheet - one stylesheet
* Project structure
  * Decided upon Model, View, Controller
* How are we going to read files?
  * Read files using java
* How to we want to do Bash commands?
  * Decidede upons scripts, but if there are not alot of commands needed, then just use ProcessBuilder.

##### How do we want to store game data?
As files, like so:
/data/categories/<categories>
/data/categories/Places.txt
/data/categories/...

For assignment three, since there is no 'users' function, we will have:
CONST user = “default”
And
/data/users/default/categories/Places.txt
/data/users/default/categories/Places.txt
/data/users/default/score.txt


##### How is the GameModel meant to work?
* Game - 5 categories - 5 questions (order) (read from the file)
  * Stored as /categories/ 5 files - files by category name - space replaced with hyphen.
* Score
* Delete question by line after it has been answered.
* Reset - delete game folder and reload it with new data

We should have Category Objects which are an array of Question Object.

##### QuestionModel 
* Category Class 
* Question Class 

##### Getters
//read lines
//save games on exit

Case Study:
https://github.com/AidenBurgess/VARpedia/blob/842863105dfdf34996103eb65644710eda6e692a/src/app/VideoManager.java

#### Next meeting plan
* Start coding
  * CZ to work mostly on frontend and Maggie mostly on backend.
* Next meeting: Monday afternoon (21/09/2020).

---

### Meeting 4: 21/09/2020
#### Meeting Plan
* Any issues? How’s it going?
* Package names plural or singular?
  * https://softwareengineering.stackexchange.com/questions/75919/should-package-names-be-singular-or-plural
* Global helper package? Should SceneManager be in this helper package?
  * Decided yes upon this. 
* If you want to merge master into a branch, do the following:
  * ***git fetch origin***
  * ***git merge origin/master***
* Comment Style https://docs.google.com/document/d/1zCIS133gM_0B14iHaRcd83pp6HIwysXAXqQrV_UZ9To/edit 
  * How many comments?
    * Not too many, but the nessecary ones.
  * When to comment?
    * Comment at the top of every class and method, and any extra comments for code that doesn't make sense.
  * How to comment?
    * Comments for classes and methods in JavaDoc, other comments using //.
* Kanban
  * https://github.com/SOFTENG206-2020/assignment-3-and-project-team-44/projects/1 
  * Add stuff to it as you go along

##### PracticeMenu
* category buttons handler - goes to question page
* QuestionModel.setCategory, setPractice = true
* switchScene(questionPage)
* questionPage - questionController()

Controller currently does not know which category. To fix this implement:
* get QuestionModel 
* get which category
* practice 

QuestionModels - singleton
* fields - selectedCategory, practice true/false

#### Next meeting plan
* Add pages and controllers
* .classpath remove from master and add .gitigno
* Next meeting: Friday evening (25/09/2020).

---

### Meeting 4: 21/09/2020
#### Meeting Plan
##### TODO
* Wiki
  * Use our meeting notes from past meetings, as stored on our shared goole drive
* SettingsMenuController
  * Reload page or adapters
  * Reset button listener
* QuestionController
  * Answer, clue
  * Don’t know - leave it in?
  * Display right or wrong
  * Correct/incorrect text add to fxml and different color
* GameMenu
  * Try inverted look
  * Grids auto fill buttons
  * Connect model to controller
  * Fix don’t know button
  * Question is correct or not? Change to true/false instead of string.
  * Add Reset button to user view
  * User can only access lowest value
  * TTS system (no text of the question is given).
  * Current winnings, add as yellow box down on the bottom left hand corner.
* PracticeMenu
  * Padding in question text on GUI
  * 3rd attempt first letter
* SettingsMenu
  * Connect model to controller and process
* Reward Screen

#### Next meeting plan
* Next meeting: Monday evening (28/09/2020).
* Final testing on last meeting before the deadline, probably next friday.

---

### Meeting 5: 28/09/2020
#### Meeting Plan
##### TODO
* SettingsMenu
 * Settings don’t change volume
* Question
 * Category doesn’t change
 * Who is/What is doesn’t change
 * Clue remove ‘the’
 * add back button practice module //not necessary
* GameMenu
 * BUG: first load doesn’t work
 * add reset game
 * force user to choose lowest value
 * Add a reward screen for the user, showing their score and ask if they want to play again (another option is exit i assume) and the score is set to 0. 
* Reward


#### Next meeting plan
* Next meeting: Friday afternoon (2/10/2020).
* Sort out all final details.

---

### Meeting 5: 2/10/2020
#### Meeting Plan
##### TODO
* Compile application
*  README - how to run
* Update wiki add today's meeting
* add alert to closing stage
* Bug hunting
* Reformat files/comments
* Slideshow and draft script for Monday meeting

#### Next meeting plan
* Next meeting: Monday afternoon (5/10/2020).
* Sort out presentation and begin project.
