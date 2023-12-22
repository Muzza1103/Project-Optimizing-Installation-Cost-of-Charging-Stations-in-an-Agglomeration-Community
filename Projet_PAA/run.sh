#!/bin/bash

# Demande à l'utilisateur d'entrer le chemin vers le SDK JavaFX
read -p "Entrez le chemin vers le SDK JavaFX (par ex. /home/user/javafx-sdk/lib) : " JAVAFX_SDK_PATH

echo "Compilation des fichiers Java..."
javac --module-path "$JAVAFX_SDK_PATH" --add-modules javafx.controls,javafx.fxml -d bin src/up/mi/*.java
if [ $? -ne 0 ]; then
    echo "Erreur lors de la compilation."
    exit 1
fi

echo "Execution de la classe principale..."
java --module-path "$JAVAFX_SDK_PATH" --add-modules javafx.controls,javafx.fxml -cp bin up.mi.Menu ville.txt

