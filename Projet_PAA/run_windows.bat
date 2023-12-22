@echo off
set /p JAVAFX_SDK_PATH="Entrez le chemin vers le SDK JavaFX (ex. C:\path\to\javafx-sdk\lib): "

echo Compilation des fichiers Java...
javac --module-path "%JAVAFX_SDK_PATH%" --add-modules javafx.controls,javafx.fxml -d bin src\up\mi\*.java
if NOT %ERRORLEVEL% == 0 goto error

echo Execution de la classe principale...
java --module-path "%JAVAFX_SDK_PATH%" --add-modules javafx.controls,javafx.fxml -cp bin up.mi.Menu ville.txt
goto end

:error
echo Erreur lors de la compilation ou de l'execution.

:end
pause

