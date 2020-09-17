## spotify-communication-app
Java app used to interact with spotify api and bind those interactions to specific keys on the keyboard.
# Compilation instructions
To run the app, following dependencies are neccessary:
1. [ChromeDriver](https://chromedriver.chromium.org/) - it is used by selenium which automates the browser and allows to get the authorization code. Of course [Chrome](https://www.google.com/chrome/) browser is also needed.
2. [Java Development Kit](https://www.oracle.com/java/technologies/javase-downloads.html) - oracle one recommende, but other should work too.
3. [Apache-Maven](https://maven.apache.org/download.cgi) - used for comilation of project files and adding necessary dependencies listed in pom.xml file. It needs to be added to the sytem PATH variable.
# Compilation commands
After cloning the repository, open a Command Prompt in the spotify-communication-app folder. Use `mvn -B compile` to build the project. It will create necessary .class files in the target directory. Next, use `mvn exec:java` to run the program.