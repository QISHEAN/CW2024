
# COMP2042 Developing Maintainable Software

## 1.0 My GitHub
- **Name**: OH QI SHEAN
- **Student ID**: 20512381
- **Repository Link**: [GitHub Repository](https://github.com/QISHEAN/CW2024)

---

## 2.0 Setup and Compilation Instructions

### 2.1 Prerequisites
- **Java Development Kit (JDK)**: Version 21 or higher
- **Apache Maven**: Version 3.8.0 or higher
- **JavaFX SDK**: Version 21.0.5
- **Git**: Installed and configured

### Recommended IDEs
- **IntelliJ IDEA**: Version 2023.3 or higher
- **Eclipse**: Version 2023-12 or higher
- **VS Code**: With Java Extension Pack and JavaFX Extension installed

---

## Environment Setup

### **1. Install Java Development Kit (JDK)**
1. Download JDK 21 from [Oracle](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html) or [OpenJDK](https://openjdk.org/).
2. Set up the `JAVA_HOME` environment variable:
    - **Windows**:
      ```powershell
      [Environment]::SetEnvironmentVariable("JAVA_HOME", "C:\Program Files\Java\jdk-21", "Machine")
      [Environment]::SetEnvironmentVariable("Path", $env:Path + ";%JAVA_HOME%\bin", "Machine")
      ```
    - **Unix/MacOS**:
      ```bash
      echo 'export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-21.jdk/Contents/Home' >> ~/.bash_profile
      echo 'export PATH=$JAVA_HOME/bin:$PATH' >> ~/.bash_profile
      source ~/.bash_profile
      ```
3. Verify installation:
   ```bash
   java --version


### **2. Install JavaFX SDK**
1. Download JavaFX SDK 21 from [OpenJFX](https://openjfx.io/).
2. Extract it to a preferred location.
3. Optionally, add it to your PATH (although Maven will handle dependencies automatically):
    - **Windows**:
      ```powershell
      [Environment]::SetEnvironmentVariable("PATH_TO_FX", "C:\Path\To\javafx-sdk-21\lib", "Machine")
      ```
    - **Unix/MacOS**:
      ```bash
      echo 'export PATH_TO_FX=/path/to/javafx-sdk-21/lib' >> ~/.bash_profile
      source ~/.bash_profile
      ```

###  **3. Install Git**
1. Install Git for your operating system:
    - **Windows**: Download and install from [Git for Windows](https://git-scm.com/).
    - **macOS**: Install via Homebrew:
      ```bash
      brew install git
      ```
    - **Linux (Ubuntu/Debian)**:
      ```bash
      sudo apt-get install git
      ```
    - **Linux (Fedora)**:
      ```bash
      sudo dnf install git
      ```

2. Verify Git installation:
   ```bash
   git --version


### **4. Install Apache Maven**
1. Download Apache Maven from the [Maven Official Site](https://maven.apache.org/download.cgi).
2. Add Maven to your system PATH:
    - **Windows**:
      ```powershell
      [Environment]::SetEnvironmentVariable("Path", $env:Path + ";C:\Path\To\Maven\bin", "Machine")
      ```
    - **Unix/MacOS**:
      ```bash
      echo 'export PATH=/path/to/maven/bin:$PATH' >> ~/.bash_profile
      source ~/.bash_profile
      ```

3.  Verify Maven Installation
- To confirm Maven is installed correctly, run the following command:
    ```bash
    mvn --version

## **2.2 Project Setup**

1. Clone the project repository:
   ```bash
   git clone https://github.com/QISHEAN/CW2024.git
   cd CW2024

2. IDE setup

- Eclipse
1. Open Eclipse and import the project as a Maven project:
    - Go to **File** → **Import** → **Existing Maven Projects**.
    - Select the project folder and click **Finish**.
2. Configure the Java Build Path if necessary:
    - Right-click the project in the Project Explorer → **Properties** → **Java Build Path**.
    - Add JavaFX libraries if they are missing:
        - Go to the **Libraries** tab.
        - Click **Add External JARs...** and select the JavaFX JAR files from your JavaFX SDK.


- IntelliJ IDEA
1. Open IntelliJ IDEA and import the project as a Maven project:
    - Select **Open** from the IntelliJ start screen and choose the project folder.
    - IntelliJ will detect the `pom.xml` and set up the Maven configuration.
2. Verify the Project SDK:
    - Go to **File** → **Project Structure** → **Project Settings** → **Project SDK**.
    - Ensure the SDK is set to JDK 21.
3. Refresh Maven dependencies:
    - Open the Maven tool window (usually on the right-hand side).
    - Click **Reload All Maven Projects** to load all dependencies.
4. Wait for Maven to sync and ensure there are no errors in the dependencies.



- VS Code
1. Install the required extensions from the Visual Studio Code Marketplace:
    - **Java Extension Pack**.
    - **JavaFX Extension** (optional for JavaFX development).
2. Open the project folder in VS Code:
    - Select **File** → **Open Folder...** and choose the project directory.
3. Use Maven commands in the integrated terminal to build and run the project:
    - For example:
      ```bash
      mvn javafx:run
      ```
4. Alternatively, set up a launch configuration to run the project directly:
    - Go to the **Run and Debug** panel → **Create a Launch Configuration** → Configure it for Maven.


#### 3. Compile the Project
- Clean and compile the project using Maven:
    ```bash
    mvn clean compile
    ```
- Run the application:
    ```bash
    mvn javafx:run
    ```
- Package the application:
    ```bash
    mvn package
    ```
- Run the packaged JAR file:
    ```bash
    java -jar target/CW2024-1.0-SNAPSHOT.jar
    ```

## **2.3 Common Build Commands**

**1. Clean Build Files**
- Removes all previously generated build files:
    ```bash
    mvn clean
    ```

**2. Compile the Project**
- Compiles the source code of the project:
    ```bash
    mvn compile
    ```

**3. Run Tests**
- Executes all test cases in the project:
    ```bash
    mvn test
    ```

**4. Package the Application**
- Packages the application into a JAR file:
    ```bash
    mvn package
    ```

**5. Install to Local Repository**
- Installs the application into the local Maven repository:
    ```bash
    mvn install
    ```

**6. Run the Application**
- Runs the application using the Maven JavaFX plugin:
    ```bash
    mvn javafx:run
    ```

## 3.0 Features

### 3.1 Implemented and Working Properly
1. **User Plane Movement**: Added left and right movement controls for the user plane.
    - **Classes Involved**:
        - `UserPlane` (actor)
        - `InputManager` (manager)
    - **Key Methods**:
        - `UserPlane.moveLeft()`
        - `UserPlane.moveRight()`
        - `UserPlane.stopHorizontalMovement()`
        - `InputManager.createKeyPressedHandler()`
        - `InputManager.createKeyReleasedHandler()`

2. **Main Menu**: Implemented a main menu that appears upon game startup.
    - **Classes Involved**:
        - `MenuController` (controller)
    - **Key Methods**:
        - `MenuController.handleStartButton()`
        - `MenuController.startEndlessMode()`
        - `MenuController.showLeaderboard()`
        - `MenuController.openSoundSettings()`
        - `MenuController.handleExitButton()`

3. **Pause Menu**: Created a pause menu along with its handler for game interruption.
    - **Classes Involved**:
        - `PauseMenuController` (controller)
        - `PauseManager` (manager)
    - **Key Methods**:
        - `PauseManager.pauseGame()`
        - `PauseManager.resumeGame()`
        - `PauseManager.exitToMainMenu()`
        - `PauseMenuController.onResumeClicked()`
        - `PauseMenuController.onRestartClicked()`
        - `PauseMenuController.onExitToMainMenuClicked()`

4. **Kill Count Display**: Added a kill count text to track the number of enemies defeated.
    - **Classes Involved**:
        - `EndlessLevelView` (levelview)
        - `KillCountDisplay` (levelview)
    - **Key Methods**:
        - `EndlessLevelView.initializeKillCountDisplay()`
        - `EndlessLevelView.updateKillCount(int killCount)`
        - `KillCountDisplay.updateDisplayText()`

5. **Visual and Audio Enhancements**: Changed background images and incorporated sound effects and background music.
    - **Classes Involved**:
        - `SoundManager` (manager)
        - `GameOverImage`, `WinImage`, `HeartDisplay`, `ShieldImage` (ui)
    - **Key Methods**:
        - `SoundManager.playSoundEffect()`
        - `SoundManager.playBackgroundMusic()`
        - `SoundManager.setBackgroundMusicVolume()`

6. **Additional Level**: Added an extra level (Level 2 and changed the original Level 2 to `BossLevel`) to increase game complexity.
    - **Classes Involved**:
        - `BossLevel` (level)
        - `LevelTwo` (level)
        - `LevelParent` (level)
    - **Key Methods**:
        - `BossLevel.initializeScene()`
        - `BossLevel.startGame()`
        - `BossLevel.updateLevelView()`
        - `BossLevel.checkIfGameOver()`
        - `LevelTwo.incrementKillCount()`
        - `LevelTwo.initializeKillCountDisplay()`

7. **Endless Mode and Leaderboard**: Implemented endless mode along with leaderboard mechanics to track high scores.
    - **Classes Involved**:
        - `EndlessController` (controller)
        - `EndlessMode` (level)
        - `LeaderboardController` (controller)
        - `LeaderboardManager` (manager)
    - **Key Methods**:
        - `EndlessController.startEndlessMode()`
        - `EndlessMode.instantiateLevelView()`
        - `EndlessMode.spawnEnemyUnits()`
        - `EndlessMode.incrementKillCount()`
        - `EndlessMode.restartLevel()`
        - `LeaderboardManager.addScore()`
        - `LeaderboardManager.getTopScores()`
        - `LeaderboardManager.saveScores()`
        - `LeaderboardController.initialize()`
        - `LeaderboardController.backToMenu()`

8. **Game Controls**: Enabled users to restart, resume, and exit to the main menu at every level.
    - **Classes Involved**:
        - `PauseMenuController` (controller)
        - `PauseManager` (manager)
        - `NavigationManager` (manager)
    - **Key Methods**:
        - `PauseMenuController.onRestartClicked()`
        - `PauseMenuController.onResumeClicked()`
        - `PauseMenuController.onExitToMainMenuClicked()`
        - `PauseManager.restartLevel()`
        - `NavigationManager.notifyRestartLevel()`

9. **Leaderboard Display**: Added a leaderboard that sorts and showcases the highest scores.
    - **Classes Involved**:
        - `LeaderboardController` (controller)
        - `LeaderboardManager` (manager)
    - **Key Methods**:
        - `LeaderboardController.initialize()`
        - `LeaderboardController.backToMenu()`
        - `LeaderboardManager.addScore()`
        - `LeaderboardManager.getTopScores()`

10. **Sound Effects**: Included sound effects for projectiles, game win and loss events, and enemy planes when damaged by projectiles.
    - **Classes Involved**:
        - `SoundManager` (manager)
    - **Key Methods**:
        - `SoundManager.playSoundEffect()`

11. **Boss Plane Health**: Added a health text for the boss plane.
    - **Classes Involved**:
        - `BossLevel` (level)
        - `LevelViewBoss` (levelview)
    - **Key Methods**:
        - `BossLevel.initializeScene()`
        - `BossLevel.updateLevelView()`
        - `BossLevel.checkIfGameOver()`
        - `LevelViewBoss.showShield()`
        - `LevelViewBoss.hideShield()`

12. **Settings Page**: Implemented a settings page allowing users to adjust music and sound effects volume.
    - **Classes Involved**:
        - `SoundSettingsController` (controller)
        - `SoundManager` (manager)
    - **Key Methods**:
        - `SoundSettingsController.initializeSliders()`
        - `SoundSettingsController.addSliderListeners()`
        - `SoundSettingsController.applyVolumeSettings()`

13. **Warning Label**: Added a warning label in Level 2 to alert players of specific conditions or events.
    - **Classes Involved**:
        - `LevelTwo` (level)
        - `LevelView` (levelview)
    - **Key Methods**:
        - `LevelTwo.incrementKillCount()`
        - `EndlessLevelView.showWarningLabel()`

14. **Enemy Penetration Handling**: Introduced a feature where enemy planes crossing the screen boundary reduce the player's health while ensuring the kill count remains unaffected, maintaining accurate gameplay statistics.
    - **Classes Involved**:
        - `CollisionManager` (manager)
        - `UserPlane` (actor)
    - **Key Methods**:
        - `CollisionManager.handleEnemyPenetration()`
        - `UserPlane.reduceHealth(int amount)`

15. **Bounding Box Feature**: Integrated dynamic bounding boxes for all destructible actors to improve collision detection and handling, ensuring accurate hit detection and damage calculations.
    - **Classes Involved**:
        - `ActiveActorDestructible` (actor)
        - `CollisionManager` (manager)
    - **Key Methods**:
        - `ActiveActorDestructible.getBoundingBox()`
        - `ActiveActorDestructible.updateBoundingBox()`
        - `CollisionManager.handlePlaneCollisions()`
        - `CollisionManager.handleUserProjectileCollisions()`
        - `CollisionManager.handleEnemyProjectileCollisions()`

### 3.2 Implemented but Not Working Properly
1. **Pause Menu Glitch**: When the game is lost and the game over screen appears, pressing Esc to open the pause menu and then selecting "Resume" causes the plane, projectiles, and other elements in the level to briefly move before stopping, even though the game is already over.
2. **Unlimited Projectiles**: Users can fire projectiles indefinitely without the need to reload, leading to potential balance issues.
3. **Projectile Sound Effects Issue**: The unlimited projectile feature causes the sound effects to stop playing when the fire button is held down continuously.
4. **Sound Settings Mute Issue**: Unable to completely mute the sound using the sound settings sliders because setting the minimum value to `0` in the FXML causes warnings.
   - **Solution**: I have adjusted the sliders' minimum values to `0.01`, allowing users to reduce the volume close to mute without triggering warnings.
   
### 3.3 Not Implemented
1. **Tutorial Section**: A tutorial to teach beginners how to play the game.
2. **In-Game Power-Ups**: Implementation of power-ups that provide temporary advantages during gameplay.
3. **Final Score Display**: Displaying the user's final score on the game over screen during endless mode.

### 3.4 How to Fix Identified Issues

1. **Pause Menu Glitch**
    - **Validate Game State**: Ensure the game cannot resume if it's already in a game-over state.
    - **Manage Timelines**: Stop all active game timelines when the game ends to prevent unintended movements.
    - **Cleanup Pause Menu**: Disable or hide the pause menu upon triggering the game-over state to prevent further interactions.

2. **Unlimited Projectiles**
    - **Cooldown Timer**: Add a cooldown period between successive projectile firings to control the rate of fire.
    - **Ammo System**: Implement an ammo counter that decreases with each shot and requires reloading after depletion.
    - **UI Integration**: Display the current ammo count and cooldown status to inform players about their firing capabilities.

### 3.5 How to Add Missing Features

1. **Tutorial Section**
    - **Create `TutorialLevel` Class**: Develop a simplified level that introduces basic controls and mechanics.
    - **Add Instructional UI**: Use tooltips or dialog boxes to provide real-time guidance (e.g., "Press SPACE to shoot").
    - **Include a Skip Option**: Add a "Skip Tutorial" button in the main menu for experienced players.

2. **In-Game Power-Ups**
    - **Develop `PowerUp` Class**: Define different types of power-ups with specific effects and durations.
    - **Spawn Power-Ups**: Integrate power-up spawning logic in the `EntityManager` to appear at random intervals or after certain events.
    - **Handle Collection and Effects**: Update the `CollisionManager` to detect power-up pickups and apply their effects to the player.
    - **Display Active Power-Ups**: Show active power-ups and their remaining durations in the game UI.

3. **Final Score Display**
    - **Update `EndlessLevelView`**: Enhance the game-over screen to include the final score.
    - **Integrate with Leaderboard**: Automatically save the final score to the leaderboard upon game over.
    - **UI Enhancements**: Use `Text` elements to prominently display the final score alongside the game-over image.
---

## 4.0 Refactoring Process

### 4.1 New Java Classes

# controller

| **Class Name**                | **Description**                                                                                                                                                                                                                                                                                               |
|-------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **`CustomFormatter`**         | This class customizes the format of log messages in the application. It formats timestamps, log levels, and messages in a structured way for readability. It also includes exception details and stack traces in the log output when applicable.                                                              |
| **Key Methods**:              | - **`format(LogRecord record)`**: Formats log messages with timestamps, log levels, and exception stack traces (if any).                                                                                                                                                                                      |
| **`EndlessController`**       | This class manages the control flow for the endless mode of the game. It initializes and starts endless mode by setting up the scene, displaying it on the stage, and starting the game logic.                                                                                                                |
| **Key Methods**:              | - **`startEndlessMode()`**: Initializes the endless mode scene and starts the gameplay loop.                                                                                                                                                                                                                  |
| **`LeaderboardController`**   | This class controls the leaderboard functionality, including displaying the top scores in a `ListView`. It ensures smooth navigation back to the main menu and updates the leaderboard dynamically.                                                                                                           |
| **Key Methods**:              | - **`initialize()`**: Loads the top scores when the leaderboard is displayed. <br> - **`backToMenu()`**: Navigates back to the main menu.                                                                                                                                                                     |
| **`MenuController`**          | This class handles the interactions and control flow from the main menu. It manages navigation to various game modes, the leaderboard, sound settings, and application exit.                                                                                                                                  |
| **Key Methods**:              | - **`handleStartButton()`**: Launches the standard game mode. <br> - **`startEndlessMode()`**: Starts endless mode. <br> - **`showLeaderboard()`**: Displays the leaderboard screen. <br> - **`openSoundSettings()`**: Opens the sound settings menu. <br> - **`handleExitButton()`**: Exits the application. |
| **`PauseMenuController`**     | This class manages the behavior of the pause menu, allowing the user to resume, restart, or exit the game. Actions are configured via `Runnable` functions.                                                                                                                                                   |
| **Key Methods**:              | - **`onResumeClicked()`**: Resumes the game. <br> - **`onRestartClicked()`**: Restarts the current level. <br> - **`onExitToMainMenuClicked()`**: Returns to the main menu.                                                                                                                                   |
| **`SoundSettingsController`** | This class provides a user interface for adjusting music and sound effects volume. It synchronizes slider values with the `SoundManager` for real-time updates.                                                                                                                                               |
| **Key Methods**:              | - **`initializeSliders()`**: Initializes slider values to match current volume settings. <br> - **`addSliderListeners()`**: Adds listeners to sliders for dynamic volume adjustments. <br> - **`applyVolumeSettings()`**: Saves and applies updated volume settings.                                          |

# level

| **Class Name**      | **Description**                                                                                                                                                                                                                                                                                                                                                                    |
|---------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **`BossLevel`**     | This class represents the boss level in the game. It initializes a unique boss with a shield mechanic and custom animations, plays boss-specific background music, and handles the win/lose conditions specific to the level.                                                                                                                                                      |
| **Key Methods**:    | - **`initializeScene()`**: Sets up the boss-specific scene and adds the boss health display. <br> - **`startGame()`**: Starts the boss level with background music and game loop. <br> - **`updateLevelView()`**: Manages shield visibility and blinking animation for the boss. <br> - **`checkIfGameOver()`**: Ends the game if the player is destroyed or the boss is defeated. |
| **`EndlessMode`**   | This class represents the endless mode of the game. It spawns increasing waves of enemies, dynamically adjusts the difficulty, updates the kill count, and integrates leaderboard functionality to track player scores.                                                                                                                                                            |
| **Key Methods**:    | - **`instantiateLevelView()`**: Initializes the endless level view. <br> - **`spawnEnemyUnits()`**: Spawns enemies dynamically based on the current difficulty. <br> - **`incrementKillCount()`**: Updates the kill count display as the player defeats enemies. <br> - **`restartLevel()`**: Resets the game state and restarts the endless mode.                                 |
| **`LevelListener`** | This interface defines the contract for handling level-specific events such as changing to the next level, restarting the current level, or exiting to the main menu. It is used to ensure consistent communication between different game levels and the controller.                                                                                                              |
| **Key Methods**:    | - **`onLevelChange(String nextLevelClassName)`**: Handles transitions to the next level. <br> - **`exitToMainMenu()`**: Exits the current level and navigates to the main menu. <br> - **`restartLevel()`**: Restarts the current level.                                                                                                                                           |

# levelview

| **Class Name**         | **Description**                                                                                                                                                                                    | **Key Methods**                                                                                                                                                                                                                                                                                                                                                                                     |
|------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **`EndlessLevelView`** | Represents the visual elements of the endless mode level. It includes a kill count display, handles the game over image, and navigates to the leaderboard after the game ends.                     | - **`initializeKillCountDisplay()`**: Sets up the kill count display on the screen. <br> - **`updateKillCount(int killCount)`**: Dynamically updates the kill count display during gameplay. <br> - **`showGameOverImage()`**: Displays a game over image and navigates to the leaderboard after a delay. <br> - **`resetKillCountDisplay()`**: Resets the kill count display to its initial value. |
| **`KillCountDisplay`** | Provides a reusable component for displaying the kill count on the screen. It dynamically updates the score display and allows for customization of its position and style.                        | - **`updateDisplayText()`**: Updates the displayed kill count. <br> - **`getDisplayText()`**: Returns the formatted kill count text. <br> - **Constructor**: Accepts position and initial kill count to initialize the display.                                                                                                                                                                     |
| **`LevelViewBoss`**    | Handles the visual elements specific to the boss level. This includes a shield image for the boss, which can be shown or hidden dynamically, and integrates all elements into the level's root UI. | - **`showShield()`**: Displays the shield image when the boss's shield is active. <br> - **`hideShield()`**: Hides the shield image when the shield is inactive. <br> - **Constructor**: Initializes the visual components and adds the shield image to the level's root.                                                                                                                           |

# manager

| **Class Name**           | **Description**                                                                                                                                                                                                                                                                                                                                                                                                                                                    |
|--------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **`CollisionManager`**   | This class handles collision detection and resolution between different types of entities, such as planes, projectiles, and the user. It ensures damage is applied when collisions occur and manages enemy penetration logic.                                                                                                                                                                                                                                      |
| **Key Methods**:         | - **`handlePlaneCollisions()`**: Detects and resolves collisions between friendly and enemy planes. <br> - **`handleUserProjectileCollisions()`**: Manages collisions between user projectiles and enemy units. <br> - **`handleEnemyProjectileCollisions()`**: Resolves collisions between enemy projectiles and friendly units. <br> - **`handleEnemyPenetration()`**: Handles scenarios where enemy planes penetrate the screen boundary, reducing user health. |
| **`EntityManager`**      | This class manages the lifecycle and interactions of game entities (friendly units, enemies, and projectiles). It is responsible for adding, updating, and removing entities, as well as ensuring their representation in the UI.                                                                                                                                                                                                                                  |
| **Key Methods**:         | - **`addFriendlyUnit()`**: Adds a friendly unit to the game. <br> - **`addEnemyUnit()`**: Adds an enemy unit to the game. <br> - **`removeAllDestroyedActors()`**: Cleans up all destroyed entities from the game scene. <br> - **`clearAllProjectiles()`**: Clears all active projectiles from the game.                                                                                                                                                          |
| **`GameInitializer`**    | This class handles the initialization of the game components, including setting up the background, adding friendly units to the scene, and configuring the main game loop using a `Timeline`.                                                                                                                                                                                                                                                                      |
| **Key Methods**:         | - **`initializeBackground()`**: Sets up the game background and ensures it is focusable. <br> - **`initializeFriendlyUnits()`**: Adds the user's plane to the scene. <br> - **`initializeTimeline()`**: Configures the main game loop for updating the game state.                                                                                                                                                                                                 |
| **`InputManager`**       | This class manages user input and keybindings, allowing the player to control the user plane's movements and actions (e.g., firing projectiles). It integrates seamlessly with the `PauseManager` to disable input when paused.                                                                                                                                                                                                                                    |
| **Key Methods**:         | - **`initializeInputHandling()`**: Sets up key event handlers for user input. <br> - **`createKeyPressedHandler()`**: Handles key presses for movement and firing. <br> - **`createKeyReleasedHandler()`**: Stops movement when keys are released.                                                                                                                                                                                                                 |
| **`LeaderboardManager`** | This class manages the leaderboard functionality, including storing and retrieving player scores. It handles file operations for persistence and ensures the leaderboard always contains the top scores.                                                                                                                                                                                                                                                           |
| **Key Methods**:         | - **`addScore()`**: Adds a new score to the leaderboard and persists it. <br> - **`getTopScores()`**: Retrieves the top N scores from the leaderboard. <br> - **`saveScores()`**: Saves the current scores to a file.                                                                                                                                                                                                                                              |
| **`NavigationManager`**  | This class facilitates communication between different levels and game components. It allows for notifying listeners of level changes, restarts, or navigation back to the main menu.                                                                                                                                                                                                                                                                              |
| **Key Methods**:         | - **`notifyLevelChange()`**: Notifies listeners of a level transition. <br> - **`notifyExitToMainMenu()`**: Signals exit to the main menu. <br> - **`notifyRestartLevel()`**: Notifies listeners to restart the current level.                                                                                                                                                                                                                                     |
| **`PauseManager`**       | This class manages the pause functionality in the game. It handles pausing and resuming the game, displaying the pause menu, and providing options to restart the game or exit to the main menu.                                                                                                                                                                                                                                                                   |
| **Key Methods**:         | - **`pauseGame()`**: Pauses the game and displays the pause menu. <br> - **`resumeGame()`**: Resumes the game and removes the pause menu. <br> - **`exitToMainMenu()`**: Navigates to the main menu from the pause menu.                                                                                                                                                                                                                                           |
| **`SoundManager`**       | This class manages the game's audio, including playing sound effects and background music. It supports volume adjustments, looping background music, and dynamically loading sound resources.                                                                                                                                                                                                                                                                      |
| **Key Methods**:         | - **`playSoundEffect()`**: Plays a specified sound effect. <br> - **`playBackgroundMusic()`**: Plays looping background music. <br> - **`setBackgroundMusicVolume()`**: Adjusts the volume of background music.                                                                                                                                                                                                                                                    |

### 4.2 Modified Java Classes

- actor

| **Class Name**            | **Description**                                                                                                                                                  | **Changes**                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        |
|---------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `ActiveActor`             | This abstract class represents an active game entity with an image. It includes methods for movement and position updates.                                       | 1. **Null Safety for Image Resource**: Added `Objects.requireNonNull` to ensure that the image resource is valid before usage.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     |
|                           |                                                                                                                                                                  | 2. **Code Cleanup**: Replaced hardcoded image path loading with more robust error handling to prevent runtime exceptions.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          |
| `ActiveActorDestructible` | This abstract class extends `ActiveActor` and represents a destructible game entity. It includes methods for managing destruction, position updates, and health. | 1. **Bounding Box Integration**: Added a `Rectangle` as a bounding box for collision detection, which is initialized and updated dynamically based on the actor's position.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        |
|                           |                                                                                                                                                                  | 2. **Health Attribute**: Introduced a `health` attribute with getter and setter methods to track and manage the entity's health.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   |
|                           |                                                                                                                                                                  | 3. **removePlane Method**: Added a method to cleanly remove the actor and its bounding box from the game root, ensuring proper cleanup during destruction.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         |
|                           |                                                                                                                                                                  | 4. **Debugging Enhancements**: Made the bounding box visible during debugging by adding a red stroke and transparent fill.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         |
|                           |                                                                                                                                                                  | 5. **Code Refactoring**: Replaced `setDestroyed(boolean)` with a simplified `setDestroyed()` method for better readability and consistency.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        |
|                           |                                                                                                                                                                  | 6. **Collision Handling Ready**: Bounding box and related methods (`getBoundingBox`, `updateBoundingBox`) prepare the class for efficient integration with collision managers or game mechanics requiring spatial interactions.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    |
| `Boss`                    | Represents the boss enemy in the game, with unique mechanics such as movement patterns, projectile firing, and shield activation.                                | 1. **Health Management**: Added a `currentHealth` attribute to dynamically track the boss's health, along with logic to handle health reduction and destruction when health reaches zero. <br> 2. **Shield Enhancements**: Increased shield activation probability (`BOSS_SHIELD_PROBABILITY` from 0.002 to 0.05) and reduced shield duration (`MAX_FRAMES_WITH_SHIELD` from 500 to 100). <br> 3. **Bounding Box Adjustments**: Updated the bounding box for better collision detection, with customizable offsets and scaling parameters.                                                                                                                                                                                         |
|                           |                                                                                                                                                                  | 4. **Improved Movement Patterns**: Enhanced `initializeMovePattern` and `getNextMove` logic for smoother and more randomized movement. <br> 5. **Firing Logic Refinement**: Added `bossFiresInCurrentFrame` method to control projectile firing based on a fixed rate. <br> 6. **Refactored Shield Logic**: Improved `updateShield` method to manage activation, duration, and deactivation in a more readable and efficient way. <br> 7. **Collision Handling**: Improved `takeDamage` to incorporate shield behavior and adjust health dynamically.                                                                                                                                                                              |
|                           |                                                                                                                                                                  | 8. **Game Integration**: Added methods to retrieve shield status (`getIsShield`) and current health (`getHealth`) for integration with the level view and UI updates.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              |
| `EnemyPlane`              | Represents an enemy plane in the game, which moves horizontally, fires projectiles, and has customizable speed and health.                                       | 1. **Health Parameterization**: Added a `health` parameter to the constructor, allowing for customization of enemy health. <br> 2. **Dynamic Speed**: Introduced methods `setSpeed` and `getSpeed` to adjust and retrieve the horizontal movement speed of the enemy dynamically. <br> 3. **Updated Bounding Box**: Refined bounding box calculations with customizable offsets (`offsetX` and `offsetY`) and scaling factors (`scaleWidth` and `scaleHeight`) for better collision detection.                                                                                                                                                                                                                                     |
|                           |                                                                                                                                                                  | 4. **Improved Fire Logic**: Maintained firing rate (`FIRE_RATE`) while refining projectile firing logic for better integration with the game mechanics. <br> 5. **Horizontal Velocity Adjustment**: The horizontal velocity can now be adjusted dynamically via `setSpeed`, and its value is retrieved as a positive number for clarity. <br> 6. **Reduced Image Height**: The default image height was reduced from `150` to `120` for smaller visual representation.                                                                                                                                                                                                                                                             |
| `FighterPlane`            | An abstract class representing a fighter plane in the game, capable of firing projectiles and taking damage.                                                     | 1. **Health Validation**: Added `Math.max(0, health)` to the constructor to ensure health is never initialized with a negative value. <br> 2. **Enhanced Damage Logic**: Added a conditional check in `takeDamage` to prevent health from being reduced below zero and avoid unnecessary calls to `destroy()`.                                                                                                                                                                                                                                                                                                                                                                                                                     |
| `UserPlane`               | Represents the player's plane in the game, capable of firing projectiles and moving in vertical and horizontal directions.                                       | 1. **Added Horizontal Movement**: Introduced `horizontalVelocityMultiplier` and related methods (`moveLeft`, `moveRight`, `stopHorizontalMovement`) for horizontal movement.<br> 2. **Bounding Box Update**: Enhanced `updateBoundingBox` with custom offsets and scaling for better collision detection.<br> 3. **Reset Functionality**: Added `reset` and `resetKillCount` methods to reset the player's state, including position and kill count.<br>4.**Kill Count Tracking**: Improved kill count management with `incrementKillCount(int count)` to increment by a specified value and `getKillCount()` for retrieval.<br> 5. **Adjusted Speeds**: Updated vertical and horizontal velocity constants for smoother gameplay. |

- controller

| **Class Name** | **Description**                                                                                                                 | **Changes**                                                                                                                                                                                                                                    |
|----------------|---------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `Controller`   | This class acts as the primary game controller, managing level transitions, game initialization, and error handling.            | 1. **SoundManager Integration**: Added `SoundManager` to handle background music and sound effects. <br>2. **Stage Integration**: Used `Stage` to support better level transitions and UI management.                                          |
|                |                                                                                                                                 | 3. **LevelListener Interface**: Refactored the class to implement the `LevelListener` interface instead of `Observer`, allowing for cleaner level transitions and event handling.                                                              |
|                |                                                                                                                                 | 4. **Enhanced Error Handling**: Added `showErrorAlert()` method to handle exceptions and show user-friendly alerts.                                                                                                                            |
|                |                                                                                                                                 | 5. **Logging Improvements**: Integrated a logger with a custom formatter for detailed error and event tracking. <br>6. **Level Cleanup**: Added `removeLevelListener()` to cleanly stop levels and remove references during transitions.       |
|                |                                                                                                                                 | 7. **Main Menu Navigation**: Added `loadMainMenu()` method to allow navigation back to the main menu. <br>8. **Platform.runLater**: Used `Platform.runLater` for thread-safe updates during level transitions and error handling.              |
|                |                                                                                                                                 | 9. **Restart Level Functionality**: Introduced `restartLevel()` to allow restarting the current level. <br>10. **Dynamic Level Loading**: Refactored `goToLevel()` to dynamically load levels with new constructors supporting `SoundManager`. |
| `Main`         | This class serves as the entry point for the application, responsible for initializing the primary stage and starting the game. | 1. **SoundManager Integration**: Introduced `SoundManager` to manage background music and sound effects, passing it to the `MenuController`.                                                                                                   |
|                |                                                                                                                                 | 2. **FXML Loading**: Refactored to load the main menu screen (`MenuScreen.fxml`) using `FXMLLoader`, allowing better modularity and integration with JavaFX scenes.                                                                            |
|                |                                                                                                                                 | 3. **Dynamic Scene Management**: Configured the scene dynamically based on the loaded FXML and attached it to the primary stage, improving scalability and readability.                                                                        |
|                |                                                                                                                                 | 4. **Logging Enhancements**: Added a `configureLogging()` method to set up a custom logger with improved formatting and to suppress unnecessary JavaFX logs for better debugging and performance tracking.                                     |
|                |                                                                                                                                 | 5. **Error Handling**: Improved error handling for FXML loading, providing more informative messages for debugging and runtime issues.                                                                                                         |
|                |                                                                                                                                 | 6. **Code Cleanup**: Removed the `Controller` object initialization and direct level launching in favor of starting from the main menu, aligning with the updated application flow.                                                            |

- level

| **Class Name** | **Description**                                                                                                                                                                    | **Changes**                                                                                                                                                                                                                                                                                                                                                                                  |
|----------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `LevelOne`     | This class represents the first level in the game. It features an introductory gameplay experience with manageable difficulty.                                                     | 1. **Kill Count Tracking**: Added `currentKills` variable and `incrementKillCount()` method to dynamically track and update the player's kills.<br>2. **Kill Count Display**: Updated kill count display using `getLevelView().updateKillCountDisplay()`.                                                                                                                                    |
|                |                                                                                                                                                                                    | 3. **SoundManager Integration**: Added `SoundManager` to handle sounds during the level.<br>4. **Scene Enhancements**: Displayed the kill count using `getLevelView().showKillCountDisplay()` in the scene initialization.                                                                                                                                                                   |
|                |                                                                                                                                                                                    | 5. **Enemy Plane Updates**: Modified enemy planes to have a health attribute (`EnemyPlane` instantiated with `health = 1`).<br>6. **Improved LevelView Initialization**: Used refactored `LevelView` class with new customization parameters.                                                                                                                                                |
|                |                                                                                                                                                                                    | 7. **Kill Target Update**: Changed the kill target condition to `TOTAL_KILLS_TO_WIN = 5` instead of a hard-coded check.<br>8. **Stage Integration**: Incorporated `Stage` for better level management and transitions.                                                                                                                                                                       |
| `LevelParent`  | This abstract class serves as the base class for all levels in the game. It manages game entities, interactions, and user input while providing hooks for level-specific behavior. | 1. **Enhanced Sound Management**: Integrated `SoundManager` for background music and sound effects. <br>2. **Stage Integration**: Added `Stage` for better level transitions. <br>3. **Improved Collision Handling**: Leveraged `CollisionManager` for managing collisions between entities. <br>4. **Game Initialization**: Refactored initialization logic into a `GameInitializer` class. |
|                |                                                                                                                                                                                    | 5. **Entity Management**: Incorporated `EntityManager` to handle entities like user projectiles, enemy units, and friendly units. <br>6. **Pause Mechanism**: Introduced `PauseManager` to handle game pausing and resuming with relevant actions. <br>7. **Level Navigation**: Added `NavigationManager` to facilitate level changes and restarts.                                          |
|                |                                                                                                                                                                                    | 8. **Boss Integration**: Added support for managing boss mechanics, including health tracking and shield handling. <br>9. **Improved Kill Count Handling**: Enhanced kill count tracking and updates in the level view. <br>10. **Updated Timeline Handling**: Added methods to cleanly pause, resume, and stop the game timeline.                                                           |
|                |                                                                                                                                                                                    | 11. **Input Handling**: Integrated `InputManager` to streamline handling of user input, including movement (`UP`, `DOWN`, `LEFT`, `RIGHT`), projectile firing (`SPACE`), and pause control (`ESCAPE`). This modularized the input logic for easier maintenance and extensibility.                                                                                                            |
| `LevelTwo`     | This class represents the second level in the game. It features increased difficulty with stronger enemies and higher kill count requirements.                                     | 1. **Enhanced Enemy Attributes**: Enemies now have increased health (`setHealth(5)`) and higher speed (`setSpeed()`).<br>2. **Kill Count Target**: Updated kill target for advancing to the next level (`TOTAL_KILLS_TO_WIN = 10`).<br>3. **Dynamic Kill Count Tracking**: Added `incrementKillCount()` method to track kills dynamically.                                                   |
|                |                                                                                                                                                                                    | 4. **Custom Warning Message**: Added a warning label specific to Level Two (`"Enemies are stronger! Stay alert!"`) via `LevelView`.<br>5. **SoundManager Integration**: Added `SoundManager` to manage sounds in the level.<br>6. **Boss Handling Removed**: Boss mechanics replaced with stronger enemy waves.                                                                              |
|                |                                                                                                                                                                                    | 7. **Improved Initialization**: Used a refactored `LevelView` class with additional parameters for customization.<br>8. **Scene Enhancements**: Displayed kill count and warning label using `getLevelView().showKillCountDisplay()` and `getLevelView().updateKillCountDisplay()`.                                                                                                          |

- levelview

| **Class Name** | **Description**                                                                                                                                        | **Changes**                                                                                                                                                                                                                                                              |
|----------------|--------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `LevelView`    | Represents the UI components and visual feedback for a game level, including heart displays, win/loss screens, and optional elements like boss health. | 1. **Shield Integration**: Added `ShieldImage` with `showShield` and `hideShield` methods to display and manage a shield icon for boss.<br>2. **Boss Health Display**: Added `Text` and `Pane` components to display and dynamically update the boss's health.           |
|                |                                                                                                                                                        | 3. **Kill Count Integration**: Introduced `killCountText` for tracking and displaying kill counts. Added methods `showKillCountDisplay` and `updateKillCountDisplay` to manage this feature.<br>4. **Warning Label**: Added optional warning labels for specific levels. |
|                |                                                                                                                                                        | 5. **Improved Image Management**: Ensured unique addition of `WinImage` and `GameOverImage` to avoid duplicate entries in the game root.<br>6. **Dynamic Feedback**: Updated warning labels dynamically based on progress towards kill targets.                          |

- projectile

| **Class Name**    | **Description**                                                                                                                | **Changes**                                                                                                                                                                                                                                  |
|-------------------|--------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `BossProjectile`  | Represents the projectiles fired by the boss, moving horizontally across the screen with a predefined velocity and image size. | 1. **Image Height Adjustment**: Reduced `IMAGE_HEIGHT` from `75` to `50` for smaller visual representation.                                                                                                                                  |
| `EnemyProjectile` | Represents the projectiles fired by enemy planes, moving horizontally with a predefined velocity and image size.               | 1. **Image Height Adjustment**: Reduced `IMAGE_HEIGHT` from `50` to `30` for smaller visual representation. <br>2. **Bounding Box Update**: Added `updateBoundingBox` method to dynamically update the bounding box for collision detection. |
| `Projectile`      | An abstract class representing projectiles fired by various entities, inheriting from `ActiveActorDestructible`.               | 1. **Package Relocation**: Moved from the default package (`com.example.demo`) to `com.example.demo.projectiles` for better organization.                                                                                                    |
| `UserProjectile`  | Represents the projectiles fired by the player's plane, inheriting from `Projectile`.                                          | 1. **Package Relocation**: Moved from the default package (`com.example.demo`) to `com.example.demo.projectiles` for better organization.                                                                                                    |
|                   |                                                                                                                                | 2. **Bounding Box Implementation**: Added `updateBoundingBox` method to define precise collision boundaries using offsets and scaling factors.                                                                                               |
|                   |                                                                                                                                | 3. **Horizontal Velocity Increase**: Updated `HORIZONTAL_VELOCITY` from `15` to `20` for faster projectile movement.                                                                                                                         |

- ui

| **Class Name**  | **Description**                                                            | **Changes**                                                                                                                                                        |
|-----------------|----------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `GameOverImage` | Represents the "Game Over" image displayed when the game ends.             | 1. **Package Relocation**: Moved from the default package (`com.example.demo`) to `com.example.demo.ui` for improved organization.                                 |
|                 |                                                                            | 2. **Default Width Added**: Introduced `DEFAULT_WIDTH` (500) and used `setFitWidth(DEFAULT_WIDTH)` to define a default size for the image.                         |
|                 |                                                                            | 3. **Null Safety**: Added `Objects.requireNonNull` to ensure the image resource is valid before loading.                                                           |
|                 |                                                                            | 4. **Preserve Aspect Ratio**: Added `setPreserveRatio(true)` to maintain the original aspect ratio of the image when resized.                                      |
| `HeartDisplay`  | Manages the display of hearts representing player health in the game UI.   | 1. **Package Relocation**: Moved from the default package (`com.example.demo`) to `com.example.demo.ui` for improved organization.                                 |
|                 |                                                                            | 2. **Null Safety**: Added `Objects.requireNonNull` to ensure the image resource is valid before being loaded, preventing potential runtime exceptions.             |
|                 |                                                                            | 3. **Final Field Attributes**: Marked `containerXPosition`, `containerYPosition`, and `numberOfHeartsToDisplay` as `final` for immutability and clarity.           |
| `ShieldImage`   | Represents a visual shield indicator in the game, toggled during gameplay. | 1. **Package Relocation**: Moved from the default package (`com.example.demo`) to `com.example.demo.ui` for improved organization.                                 |
|                 |                                                                            | 2. **Static Shield Size**: Adjusted `SHIELD_SIZE` from 200 to 150 for a smaller visual representation.                                                             |
|                 |                                                                            | 3. **Default Positioning**: Added default `LayoutX` (1150) and `LayoutY` (5) positions for the shield image in the constructor.                                    |
|                 |                                                                            | 4. **Improved Null Safety**: Added `Objects.requireNonNull` to ensure that the image resource path is valid, preventing potential runtime errors during execution. |
|                 |                                                                            | 5. **File Path Update**: Corrected the shield image path to `/com/example/demo/images/shield.png` to reflect the correct resource file.                            |
| `WinImage`      | Represents the "You Win" image displayed on game victory.                  | 1. **Package Relocation**: Moved from the default package (`com.example.demo`) to `com.example.demo.ui` for improved organization.                                 |
|                 |                                                                            | 2. **Improved Null Safety**: Added `Objects.requireNonNull` to ensure the image resource path is valid, preventing potential runtime errors during execution.      |
|                 |                                                                            | 3. **File Path Retention**: Retained the correct resource path for the "You Win" image: `/com/example/demo/images/youwin.png`.                                     |
|                 |                                                                            | 4. **No Visual Changes**: Maintained the same dimensions (`HEIGHT = 500`, `WIDTH = 600`) and layout positioning logic as the original class.                       |

## 5.0 Summary

### 5.1 Additional Features

1. **Endless Mode and Leaderboard**
    - **Description**: Introduced an endless mode with continuously increasing waves of enemies.
    - **Leaderboard**: Implemented a leaderboard system to track and display high scores.
    - **Classes Involved**:
        - `EndlessController` (controller)
        - `EndlessMode` (level)
        - `LeaderboardController` (controller)
        - `LeaderboardManager` (manager)
    - **Key Methods**:
        - `EndlessController.startEndlessMode()`
        - `EndlessMode.instantiateLevelView()`
        - `EndlessMode.spawnEnemyUnits()`
        - `LeaderboardManager.addScore()`
        - `LeaderboardManager.getTopScores()`

2. **Boss Level**
    - **Description**: Developed a challenging boss level featuring a unique boss with shield mechanics and custom animations.
    - **Classes Involved**:
        - `BossLevel` (level)
        - `LevelViewBoss` (levelview)
    - **Key Methods**:
        - `BossLevel.initializeScene()`
        - `BossLevel.startGame()`
        - `BossLevel.updateLevelView()`
        - `BossLevel.checkIfGameOver()`
        - `LevelViewBoss.showShield()`
        - `LevelViewBoss.hideShield()`

3. **Settings Page**
    - **Description**: Created a settings page allowing users to adjust music and sound effects volume.
    - **Classes Involved**:
        - `SoundSettingsController` (controller)
        - `SoundManager` (manager)
    - **Key Methods**:
        - `SoundSettingsController.initializeSliders()`
        - `SoundSettingsController.addSliderListeners()`
        - `SoundSettingsController.applyVolumeSettings()`

4. **Warning Labels**
    - **Description**: Added warning labels in Level 2 to alert players of specific conditions, such as increased enemy strength.
    - **Classes Involved**:
        - `LevelTwo` (level)
        - `LevelView` (levelview)
    - **Key Methods**:
        - `LevelTwo.incrementKillCount()`
        - `EndlessLevelView.showWarningLabel()`

5. **Enhanced Collision Detection**
    - **Description**: Integrated dynamic bounding boxes for all destructible actors to improve collision detection accuracy.
    - **Classes Involved**:
        - `ActiveActorDestructible` (actor)
        - `CollisionManager` (manager)
    - **Key Methods**:
        - `ActiveActorDestructible.getBoundingBox()`
        - `ActiveActorDestructible.updateBoundingBox()`
        - `CollisionManager.handlePlaneCollisions()`
        - `CollisionManager.handleUserProjectileCollisions()`
        - `CollisionManager.handleEnemyProjectileCollisions()`

6. **Pause Menu**
    - **Description**: Implemented a pause menu that allows players to pause the game, resume gameplay, restart the current level, or exit to the main menu.
    - **Classes Involved**:
        - `PauseMenuController` (controller)
        - `PauseManager` (manager)
    - **Key Methods**:
        - `PauseMenuController.onResumeClicked()`
        - `PauseMenuController.onRestartClicked()`
        - `PauseMenuController.onExitToMainMenuClicked()`
        - `PauseManager.pauseGame()`
        - `PauseManager.resumeGame()`
        - `PauseManager.exitToMainMenu()`

### 5.2 Refactoring

1. **Package Organization**
    - **Description**: Reorganized the project structure into specific packages (`actor`,`controller`, `level`, `levelview`, `manager`, `projectiles`, `ui`) to enhance code maintainability and readability.
    - **Benefits**:
        - Improved modularity.
        - Easier navigation and management of classes.
        - Enhanced scalability for future developments.

2. **Sound Management Enhancement**
    - **Description**: Centralized audio functionalities within the `SoundManager` class for consistent handling of sound effects and background music.
    - **Key Changes**:
        - Added methods like `playSoundEffect()`, `playBackgroundMusic()`, and `setBackgroundMusicVolume()`.
        - Enabled dynamic and overlapping sound effects without interruption.

3. **Collision Handling Optimization**
    - **Description**: Enhanced collision detection mechanisms by integrating bounding boxes within destructible actors and delegating collision logic to the `CollisionManager`.
    - **Benefits**:
        - Increased accuracy in collision detection.
        - Simplified interaction management between different game entities.

4. **Game State Management**
    - **Description**: Refactored game state transitions and pause/resume functionalities to ensure smooth and error-free gameplay, especially during critical states like game-over.
    - **Key Changes**:
        - Implemented state checks within `PauseManager` to prevent unintended game resumes.
        - Ensured timelines are appropriately managed during state transitions.

5. **FXML Integration for UI Design**
    - **Description**: Utilized FXML to separate UI layout from application logic, enhancing code organization and maintainability.
    - **Benefits**:
        - Clear separation of concerns between UI and business logic.
        - Easier to design and modify UI components using JavaFX Scene Builder.
        - Improved readability and manageability of UI-related code.

6. **Consolidation of `LevelViewLevelTwo` into `LevelView`**
    - **Description**: Removed the `LevelViewLevelTwo` class and integrated its functionalities into the `LevelView` class by adding `showShield()` and `hideShield()` methods.
    - **Solution**: Removed redundant methods and unused code to improve code readability and maintainability.

7. **Fixed Shield Display in Boss Level**
    - **Description**: The shield was not displaying correctly in the boss level.
    - **Solution**: Moved the shield showing functionality to the `LevelBoss` class for better separation of concerns.

8. **Refactored `LevelParent` Class**
    - **Description**: The `LevelParent` class had multiple responsibilities, violating the single responsibility principle.
    - **Solution**: Introduced manager classes to handle specific responsibilities like entity management, input handling, and collision detection.

9. **Fixed Kill Count Issue with Collisions and Penetrations**
    - **Issue**: The kill count was not updating correctly when enemies were destroyed, regardless of destruction cause (penetration, collision, and projectile kill).
    - **Solution**: Updated `CollisionManager` to handle different destruction types. When an enemy plane penetrates the screen boundary without being destroyed by the user plane, it decreases the user's health without increasing the kill count.

    - **Modified Classes**:

      | **Class Name**     | **Key Methods**                                      |
      |--------------------|------------------------------------------------------|
      | `CollisionManager` | `handleCollisions()`, `handleBoundaryPenetrations()` |

10. **Tested and Verified Refactored Codebase Functionality and Performance**
    - **Issue**: Refactored code could introduce new bugs or performance issues.
    - **Solution**: Performed extensive Java unit and integration testing using a test-driven development (TDD) approach to ensure the refactored codebase operates correctly and maintains optimal performance.

### 5.3 Design Patterns Used

1. **Model-View-Controller (MVC)**
    - **Description**: Adopted the MVC architectural pattern to separate concerns within the application, enhancing code organization and scalability.
    - **Components**:
        - **Model**: Represents the game data and logic (e.g., `UserPlane`, `EnemyPlane`).
        - **View**: Handles the UI elements (e.g., `EndlessLevelView`, `LevelViewBoss`).
        - **Controller**: Manages user interactions and updates the model/view accordingly (e.g., `MenuController`, `PauseMenuController`).

2. **Singleton Pattern**
    - **Description**: Utilized the Singleton pattern for manager classes to ensure a single, globally accessible instance throughout the application.
    - **Classes Implemented**:

      | **Class Name**       | **Description**                                                                                                                 | **Key Methods**                                                                                         |
      |----------------------|---------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------|
      | `SoundManager`       | Manages all sound-related functionalities, including playing sound effects and background music.                                | `getInstance()`, `playSoundEffect()`, `playBackgroundMusic()`                                           |
      | `CollisionManager`   | Handles collision detection and resolution between game entities.                                                               | `getInstance()`, `handleCollisions()`                                                                   |
      | `LeaderboardManager` | Manages the leaderboard, including adding, retrieving, and persisting player scores.                                            | `getInstance()`, `addScore()`, `getTopScores()`, `saveScores()`                                         |
      | `EntityManager`      | Manages the lifecycle and interactions of game entities (friendly units, enemies, and projectiles).                             | `getInstance()`, `addEntity()`, `removeEntity()`                                                        |
      | `GameInitializer`    | Handles the initialization of game components, setting up the background, adding friendly units, and configuring the game loop. | `getInstance()`, `initializeBackground()`, `initializeFriendlyUnits()`, `initializeTimeline()`          |
      | `InputManager`       | Manages user input and keybindings, allowing the player to control the user plane's movements and actions.                      | `getInstance()`, `initializeInputHandling()`, `createKeyPressedHandler()`, `createKeyReleasedHandler()` |
      | `NavigationManager`  | Facilitates communication between different levels and game components.                                                         | `getInstance()`, `notifyLevelChange()`, `notifyExitToMainMenu()`, `notifyRestartLevel()`                |
      | `PauseManager`       | Manages the pause functionality in the game, including pausing/resuming the game and handling the pause menu.                   | `getInstance()`, `pauseGame()`, `resumeGame()`, `exitToMainMenu()`                                      |

    - **Implementation Details**:
        - **`SoundManager`**:
            - **Method**: `getInstance()`
            - **Purpose**: Provides a global point of access to the `SoundManager` instance.
        - **`CollisionManager`**:
            - **Method**: `getInstance()`
            - **Purpose**: Ensures only one instance of `CollisionManager` exists to manage all collision-related logic.
        - **`LeaderboardManager`**:
            - **Method**: `getInstance()`
            - **Purpose**: Maintains a single instance responsible for managing leaderboard data and operations.
        - **`EntityManager`**:
            - **Method**: `getInstance()`
            - **Purpose**: Manages all game entities, ensuring centralized control over their lifecycle.
        - **`GameInitializer`**:
            - **Method**: `getInstance()`
            - **Purpose**: Handles the initialization process of game components, ensuring consistent setup across levels.
        - **`InputManager`**:
            - **Method**: `getInstance()`
            - **Purpose**: Centralizes input handling, ensuring consistent user interactions throughout the game.
        - **`NavigationManager`**:
            - **Method**: `getInstance()`
            - **Purpose**: Manages navigation between different game states and levels.
        - **`PauseManager`**:
            - **Method**: `getInstance()`
            - **Purpose**: Controls game pausing and resuming, ensuring a single point of management for pause-related functionalities.

    - **Benefits**:
        - Controlled access to shared resources.
        - Prevented the creation of multiple conflicting instances.
        - Enhanced consistency and reliability across the application.

3. **Observer Pattern (Refactored to LevelListener Interface)**
    - **Description**: Initially implemented using the Observer pattern, it was refactored to use a `LevelListener` interface to handle level-specific events, promoting a cleaner and more flexible event-handling mechanism.
    - **Usage**:
        - Facilitates communication between different game levels and the controller.
        - Enables dynamic response to level transitions and user actions.

4. **Factory Pattern (Potential Usage)**
    - **Description**: Although not yet implemented, the structured creation of game entities like projectiles and enemy planes suggests the potential use of the Factory pattern to manage object instantiation systematically.
    - **Benefits**:
        - Centralized object creation.
        - Enhanced flexibility in managing different types of game entities.

### 5.4 Achievements

1. **Successful Implementation of Endless Mode with Leaderboard**
    - **Description**: Developed and integrated an endless gameplay mode that challenges players with continuous waves of enemies. Coupled with a dynamic leaderboard, it provides players with a platform to compete for high scores, significantly enhancing the game's replayability and engagement.
    - **Impact**:
        - Increased player retention through competitive elements.
        - Enhanced game complexity and depth.

2. **Development of a Comprehensive Boss Level**
    - **Description**: Designed and implemented a challenging boss level featuring a unique boss with shield mechanics, custom animations, and specific win/lose conditions. This addition introduced a new layer of difficulty and excitement, offering players a formidable challenge and a sense of accomplishment upon victory.
    - **Impact**:
        - Elevated the overall game difficulty and player satisfaction.
        - Showcased advanced game design and development capabilities.

3. **Implementation of Sound Settings**
    - **Description**: Created a comprehensive settings page that allows users to adjust the volume of music and sound effects, providing a personalized audio experience.
    - **Impact**:
        - Enhanced user experience through customizable audio options.
        - Improved accessibility for players with varying audio preferences and requirements.

4. **Implementation of Pause Menu**
    - **Description**: Developed and integrated a pause menu feature that allows players to pause the game, resume gameplay, restart the current level, or exit to the main menu. This feature enhances the user experience by providing flexible control over the game state.
    - **Impact**:
        - Improved user experience by allowing players to manage game flow effectively.
        - Enhanced accessibility and control, catering to player needs during gameplay.

---

## 6.0 Unexpected Problems

### 1. **Sound Settings Mute Issue**
- **Issue**: Unable to completely mute the sound using the sound settings sliders because setting the minimum value to `0` in the FXML causes warnings.
- **Solution**: Adjusted the sliders' minimum values to `0.01`, allowing users to reduce the volume close to mute without triggering warnings.

### 2. **Integration Issues After Refactoring**
- **Issue**: Refactoring to introduce new manager classes caused unexpected integration bugs between game levels and manager functionalities.
- **Solution**:
    - Conducted incremental refactoring with continuous testing to identify and resolve integration issues promptly.
    - Utilized unit tests to ensure that each manager class interacted correctly with game levels.
    - Collaborated with team members to review changes and maintain code consistency.

### 3. **Performance Degradation Due to Collision Handling Enhancements**
- **Issue**: Optimizing collision detection mechanisms led to increased computational overhead, causing noticeable lag during intensive gameplay moments.
- **Solution**:
    - Optimized collision algorithms to reduce unnecessary calculations.
    - Implemented spatial partitioning techniques to limit collision checks to nearby entities.
    - Profiled the game to identify and address specific performance bottlenecks.

### 4. **UI Rendering Glitches After Consolidating Level Views**
- **Issue**: Removing the `LevelViewLevelTwo` class and consolidating its functionalities into the `LevelView` class resulted in UI inconsistencies, such as the shield not displaying correctly in the boss level.
- **Solution**:
    - Moved the shield display methods (`showShield()`, `hideShield()`) to the `LevelBoss` class to ensure proper separation of concerns.
    - Conducted thorough UI testing across all levels to identify and fix rendering issues.
    - Adjusted FXML layouts and styles to maintain consistent UI behavior after consolidation.

---
