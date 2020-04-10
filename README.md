# Seng 300 Project - Group 39

## Group Members

Martin Guillen - Tut T08

James Lowther - Tut T09

Brayden Schmaltz-Campbell - Tut T09

Andre Staffa - Tut T08

Igor Zayarny - Tut T09

## Requirements

* Java 8
* JSON.simple library
* JavaFX (included in Java 8)

## Getting Started

To get started, copy the `Journals.json` and `Users.json` files from `test-data/` into the project's root directory.
It includes a two starter editor accounts with username `editor-1` and `editor-2` each with the password `1234`.
Each of these accounts has its own journal, and each journal has two volumes.

Other user accounts (researchers and reviewers) can be generated at runtime.

## Code Structure

For our project we used JavaFX to handle our GUI and JSON.simple to handle the persistant storage of data. The JSON files are divided into `Journals.json`, `Papers.json`, and `Users.json`. The `Users.json` file stores all of the user accounts, their user type, name, hashed password, etc. The `Papers.json` file stores all existing papers and their attributes such as title, author, deadline, reviewers, etc. The `Journals.json` file stores all of the journals included in our system and their respective volumes.

The code is partitioned into different packages to try and keep things organized. Within the GUI package, related classes are split between user roles. Each class represents a particular scene. The `getScene()` method in each class returns a scene object which can then be passed to the `GUIController.changeScene()` method to actually change the displayed scene. Objects needed for some scene's tableviews are also included in the GUI packages. They are mainly used to allow the tableview to properly format the papers, reviewers, journals, etc.

The `jsonparsing` contains code to parse the different json files using JSON.simple. It allows us to query for certain objects in the storage and also update their values. The class used to handle password hashing is included here as well.

The `login` package contains the code to handle the login and registration of users to the system. These methods are generally called from the `LoginScene` and `RegistrationScene` classes from the GUI. When a user logs in their information is stored the `Globals` class which is accessed by a number of different methods throughtout the code.

The `shared` package contains code that is used by multiple different classes and therefore is difficult to assign to a single grouping. This code generally includes higher-level methods which call lower-level methods in the `jsonparing` package to handle functions like the addition of a new paper or change of a deadline.

The actual storing of paper PDF files is handled by saving them in a folder called `submissions` in the project's root directory. Within the `submissions` folder are a bunch of sub-folders which are named corresponding to a researchers unique UID. Within each sub-folder is the PDF file, named after the paper's unique PID. Overall the structure looks like `./submissions/<researcher UID>/<paper PID>.pdf`

Because the project specification didn't say that we needed to implement functionality to actually create new journals, journals and their volumes need to be added manually. That is why test json files are provided in the `test-data` folder. Theoretically, there would be an `admin` account which would manage journals and their editors, however we were told not to implement this functionality.

## Known Issues

* The UI for the project does not display properly on Windows when windows display scaling is greater than 100% (I suspect this is an issue with JavaFX 8)
