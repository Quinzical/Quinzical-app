# Wiki for SOFTENG 206 Team 44 Assignment Three, Four and Project
## This wiki contains meeting notes from Zoom meetings that were held twice weekly over the course of the entire design process

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
  * Decided upons scripts, but if there are not alot of commands needed, then just use ProcessBuilder.

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

### Meeting 5: 25/09/2020
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

### Meeting 6: 28/09/2020
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

### Meeting 7: 2/10/2020
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

---

### Meeting 8: 5/10/2020
#### Meeting Plan
##### Presentation
* Background needs work
* Use git GIFs to show branches, commits etc
* Replace our common vision with team agreement
* Show team agreement
* Git, agile etc

* Design choices:
*  GUI design 
*   NZ themed
*    Logo is another subtle nod to NZ
*   Colour theme black and yellow buttons are easy to read
*   Target user - adult
*   Show responsiveness using GIF
*  Code design
*   data structure by user - future proofing for final project
*   MVC - one per scene - easy to remove single scene (no dependency)
*   SceneManager loader for faster load time
*   Packages makes our code easier to manage
*   Github PR code is of higher quality as each persons code gets reviewed

* Live Demo
*  Show main features, games and practice module
*  CZ to do the live demo

* GIFs to make:
*  Team agreement
*  Git branching
*  Feedback design
*  Finishing the game with reward screen
*  Package structure
*  Responsive GUI

* Test run on zoom before presentation

##### Final project
* The ability to add more Clues and Answers to the database
* Help on how to use the different features of the tool
* User choosing 5 categories 
*  So multiple users can compare their performance
* Email nasser for firebase or sql based on what he says
* International question section 
* Custom exceptions?


#### Next meeting plan
* Next meeting: Wednesday afternoon (7/10/2020).
*  Practice presentation
* Get presentation slides ready
* Script on google docs
* Email to nasser

---

### Meeting 9: 7/10/20
#### Meeting Plan
* Check script
* Do presentation run
* Time the presentation

##### SQL
* REST API - javascript, nodejs, mongodb, heroku, CI/CD, mongodb atlas
* Backend will have leaderboard, additional questions
* Post score into backend - leaderboard (table)
* Get the leaderboard table
* Put (add questions) to question bank (table)
* Get query question bank 
*Wrote out tables*


#### Next meeting plan
* Next meeting: Friday afternoon (9/10/2020)
* Setup sqlite package
* Create new scenes
* Create rest api repo
* 9:45am  presentation on Friday
* 2 hr 40 min meeting, well done

---

### Meeting 10: 9/10/20
#### Meeting Plan
* Meeting was carried out while waiting for our presentation, we created the rest api
* Maggie learnt what monogDB is


#### Next meeting plan
* Next meeting: Monday morning (12/10/2020)

---

### Meeting 11: 12/10/20
#### Meeting Plan
* Need scene for adding pages for adding question/categories
* Leaderboard
* Completed - category every question has been attempted
* You have unlocked the international section, go out to the main menu to play
* End of the game after the five categories have been completed


#### Next meeting plan
* Next meeting: Thursday afternoon (15/10/2020)
* Repeated question fix 
* Test SQL speed

---

### Meeting 12: 15/10/20
#### Meeting Plan
* Pair programming for 2.5 hours


#### Next meeting plan
* Next meeting: Satruday afternoon (17/10/2020)
* Add login
* Add leaderboard

---

### Meeting 13: 17/10/2020
#### Meeting Plan
* Do rest api over zoom
* Cheng-Zhen guided Maggie through the process, good teacher

##### TODO
* Fix question answer (what is)
*  Goes incorrect if you put what is etc before the answer
* Adding questions
* Add UI for user to add questions
*  Drop down for prompt
*  Multiple answers
*  Maybe add to cloud if we have time
* Fix getting the same question in a category
* International Section
*  Delete users fields, add unlock
*  Add international button into game module view
*  International score would effect game score
*  Limited number of attempts for international section?
*  Score is the same as game_session
* Chart - javafx
* User Manual (docs)
* User Login API
*  ID
*  Username - no spaces
*  Password - at least 6 letters?
* Add timeout/server check before login 
* Local login - users - id, api_id, username
* Submit multiple 
* Insert, query, leaderboard all online
* Offline mode? Only able to play practice module

##### Login
* Username - no spaces
* Password - at least 6 letters?
* Add timeout/server check before login 
* Local login though db 

#### Next meeting plan
* Next meeting: Tuesday afternoon (20/10/2020)
* Do finishing touches before A4 submission

---

### Meeting 14: 20/10/2020
#### Meeting Plan
* Go over new features we want to add for the competition/final project
* Everything that is required is added for the final project

##### Things that must be completed
* Restructure code packages
* User manual
* Add macron buttons for user when answering questions
* UI upgrade to make it a bit more visually appealing
* Home menu music and sound effects?

##### Things that could be added
* Multiplayer
* User stats using charts
* Convert quinzical.txt to quinzical.json

#### Multiplayer design
* Lobby - lobby link and code
* Host game
*  Different game options
*   Can pick timer length
*   Can pick question type, New Zealand or International
*  qcode for online play?

* As a host, you can do the following:
*  Change game options
* Start game
*  Leave lobby/end game

#### Next meeting plan
* No next meeting planned, depends on how everything goes as there is not alot more to implement. 
