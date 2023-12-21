# Optimizing Charging Station Installation Costs in Urban Communities
[![forthebadge](https://forthebadge.com/images/badges/made-with-java.svg))](http://forthebadge.com)  [![forthebadge](https://forthebadge.com/images/badges/powered-by-coffee.svg)](http://forthebadge.com)

## This program aim to solve and display, using different algorithms, what could be the minimum number of charging stations needed for a community of agglomerations to minimize the cost of electrification of the road network


This project, fully functional, was carried out as a part of our third licence year in CS for the Advanced Programming and Applications course.
The program solves and displays, using different algorithms, what could be the minimum number of charging stations needed for a community of agglomerations, for each city to have one or to be directly connected to a city possessing one.
it does the following:
*takes a user's file as an argument that contains a CA with or without a solution (if the file doesn't contain any, using a standard one to begin with) for the placement of charging stations.
*the progam proposes to the user an interface with multiple choices:
-the first one permits to solve the problem manually.
-the second to solve it automatically through implemented algorithms.
-the third to save the solution.
-the fourth to represent it graphically.
-the fifth to stop the program.


## This is what our program looks like
### during the execution:
![Execution of the program](https://github.com/Muzza1103/PROJET_PAA/tree/main/Projet_PAA/img/bash.png)
### for option number 4, how it represents the solution graphically:
![Graphic representation](https://github.com/Muzza1103/PROJET_PAA/tree/main/Projet_PAA/img/javafx.png)

# How to install and run this project

first, make sure you have a javaFX installed on your computer, and that it's compatible with the java version you're running.
[Link for javaFx](https://gluonhq.com/products/javafx/)
Then, clone the repository or download it and unzip it at the location of your choice

After that, if you want to use it directly from the bash terminal on linux follow these steps:

   Start by compiling it with the following command : 


    "javac --module-path [your/path/to/sdk]/lib --add-modules javafx.controls,javafx.fxml -d bin [path/to/your/project]/src/up/mi/*.java"
    
   *you can run it with: 
    
    "java --module-path [your/path/to/sdk]/lib --add-modules javafx.controls,javafx.fxml -cp bin up.mi.Menu [path/to/your/file]"      

    
   on windows cmd or powerShell:

   Start by compiling it with the following command : 
    
    "javac --module-path [your\path\to\sdk]\lib --add-modules javafx.controls,javafx.fxml -d bin [path\to\your\project]\src\up\mi\*.java"
    
   you can run it with:


    "java --module-path [your\path\to\sdk]\lib --add-modules javafx.controls,javafx.fxml -cp bin up.mi.Menu [path\to\your\file]"


**notice that you can use just the name of the file if you place it in the same level containing src and bin folders

#### feel free to try the different options of your program and enjoy !




## Tools used during development


* [VsCode](https://code.visualstudio.com/) - code editor
* [Eclipse](https://eclipseide.org) - code editor


## Auteurs
Zaidi Rayan

Mougamadou Muzzammil

Guelai Adam





 
