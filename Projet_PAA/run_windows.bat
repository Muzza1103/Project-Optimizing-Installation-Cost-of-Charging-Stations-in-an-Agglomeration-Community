@echo off
SET /P JAVAFX_SDK_PATH="Entrez le chemin vers le SDK JavaFX (par ex. C:\javafx-sdk\lib): "
SET /P FILE_PATH="Entrez le chemin du fichier à utiliser (par ex. C:\chemin\vers\votre\fichier.txt): "

echo Compilation des fichiers Java...
javac --module-path "%JAVAFX_SDK_PATH%" --add-modules javafx.controls,javafx.fxml -d bin src\up\mi\*.java
if %ERRORLEVEL% NEQ 0 (
    echo Erreur lors de la compilation.
    exit /b 1
)

echo Execution de la classe principale...
java --module-path "%JAVAFX_SDK_PATH%" --add-modules javafx.controls,javafx.fxml -cp bin up.mi.Menu "%FILE_PATH%"
