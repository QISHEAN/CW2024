
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


## **2. Install JavaFX SDK**
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

---

## **3. Install Git**
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


## **4. Install Apache Maven**
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

---

### Verify Maven Installation
- To confirm Maven is installed correctly, run the following command:
    ```bash
    mvn --version


# Project Setup Guide

Follow the steps below to set up and run the project on your local environment.

---

## **2.2 Project Setup**

### Clone the Repository
1. Clone the project repository:
   ```bash
   git clone https://github.com/QISHEAN/CW2024.git
   cd CW2024

## **IDE Setup**

### **Eclipse**
1. Open Eclipse and import the project as a Maven project:
    - Go to **File** → **Import** → **Existing Maven Projects**.
    - Select the project folder and click **Finish**.
2. Configure the Java Build Path if necessary:
    - Right-click the project in the Project Explorer → **Properties** → **Java Build Path**.
    - Add JavaFX libraries if they are missing:
        - Go to the **Libraries** tab.
        - Click **Add External JARs...** and select the JavaFX JAR files from your JavaFX SDK.

---

### **IntelliJ IDEA**
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

---

### **VS Code**
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

---

## **Compile and Run the Application**

### **Compile the Project**
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

# Common Build Commands

Use the following Maven commands to build, test, package, and run the project.

---
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
2. **Main Menu**: Implemented a main menu that appears upon game startup.
3. **Pause Menu**: Created a pause menu along with its handler for game interruption.
4. **Kill Count Display**: Added a kill count text to track the number of enemies defeated.
5. **Visual and Audio Enhancements**: Changed background images and incorporated sound effects and background music.
6. **Additional Level**: Added an extra level (Level 2 and change the original Level 2 to BossLevel) to increase game complexity.
7. **Endless Mode and Leaderboard**: Implemented endless mode along with leaderboard mechanics to track high scores.
8. **Game Controls**: Enabled users to restart, resume, and exit to the main menu at every level.
9. **Leaderboard Display**: Added a leaderboard that sorts and showcases the highest scores.
10. **Sound Effects**: Included sound effects for projectiles, game win and loss events, and enemy planes when damaged by projectiles.
11. **Boss Plane Health**: Added a health indicator for the boss plane.
12. **Settings Page**: Implemented a settings page allowing users to adjust music and sound effects volume.
13. **Warning Label**: Added a warning label in Level 2 to alert players of specific conditions or events.
14. **Enemy Penetration Handling**: Introduced a feature where enemy planes crossing the screen boundary reduce the player's health while ensuring the kill count remains unaffected, maintaining accurate gameplay statistics.
15. **Bounding Box Feature**: Integrated dynamic bounding boxes for all destructible actors to improve collision detection and handling, ensuring accurate hit detection and damage calculations.

### 3.2 Implemented but Not Working Properly
1. **Pause Menu Glitch**: When the game is lost and the game over screen appears, pressing Esc to open the pause menu and then selecting "Resume" causes the plane, projectiles, and other elements in the level to briefly move before stopping, even though the game is already over.
2. **Unlimited Projectiles**: Users can fire projectiles indefinitely without the need to reload, leading to potential balance issues.
3. **Unlimited Projectiles**: The unlimited projectile feature causes the sound effects to stop playing when the fire button is held down continuously.

### 3.3 Not Implemented
1. **Tutorial Section**: A tutorial to teach beginners how to play the game.
2. **In-Game Power-Ups**: Implementation of power-ups that provide temporary advantages during gameplay.
3. **Final Score Display**: Displaying the user's final score on the game over screen during endless mode.

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


### 4.3 Summary and Additional Notes of Refactoring Process

## 1. Modularization
The codebase has been reorganized into structured packages for improved clarity and maintainability. The following packages were introduced:
- **`controller`**: Manages game flow, menu interactions, and user controls.
- **`level`**: Handles level-specific logic, transitions, and gameplay elements.
- **`levelview`**: Manages UI components for individual levels, including dynamic visual feedback.
- **`managers`**: Centralizes responsibilities for input, collisions, entities, navigation, sound, and game initialization.
- **`projectiles`**: Defines projectile behavior for player and enemy entities.
- **`ui`**: Manages reusable UI components such as game over and win screens.

This modular structure simplifies maintenance, enables efficient debugging, and facilitates scalability by isolating concerns.

---

## 2. Enhanced Feature Set
Several new features and improvements were introduced to enhance gameplay and user experience:

### **2.1 User Interface Enhancements**
- **Kill Count Display**: Dynamically tracks and displays the number of enemies defeated.
- **Shield Image**: Displays the boss's shield status during gameplay.
- **Warning Labels**: Alerts players to specific conditions or events.

### **2.2 Audio Integration**
- **SoundManager**: Manages background music, sound effects, and real-time volume adjustments for immersive gameplay.

### **2.3 Level Transitions and Flow**
- **NavigationManager**: Streamlines level transitions and ensures smooth integration with menus.
- **LevelListener**: Defines consistent interfaces for handling level-specific events (e.g., transitions, restarts).

### **2.4 Dynamic Gameplay**
- **Endless Mode**: Adds replayability by introducing infinite enemy waves and a leaderboard to track high scores.
- **Boss Mechanics**: Incorporates strategic depth with boss health tracking, shield mechanics, and custom animations.

### **2.5 Code Simplification**
- **Removed `LevelViewLevelTwo`**: Consolidated its functionality into the `LevelView` class to reduce redundancy and improve code maintainability.

---

## 3. Improved Code Quality
### **3.1 Null Safety**
- Added `Objects.requireNonNull` to safeguard against potential runtime exceptions (e.g., invalid image paths).

### **3.2 Bounding Boxes**
- Introduced dynamic bounding boxes for improved collision detection of entities like planes and projectiles.

### **3.3 Standardized Logging**
- A custom logger with a unified format was implemented for easier debugging and event tracking.

### **3.4 Reduced Coupling**
- Centralized responsibilities into dedicated managers (e.g., `EntityManager`, `CollisionManager`, `InputManager`), reducing code duplication and improving maintainability.

---

## 4. Improved Visuals and Gameplay
### **4.1 Responsive UI**
- Enhanced UI components such as health displays, shield indicators, and kill count trackers provide dynamic visual feedback.

### **4.2 Custom Animations**
- Refined gameplay elements like boss movement patterns, projectile velocities, and shield activation for smoother player experience.

### **4.3 Level-Specific Enhancements**
- Each level now includes tailored challenges and UI configurations to align with its unique gameplay mechanics.

---

## 5. Scalability
The introduction of reusable components (e.g., `LevelParent`, `LevelListener`, `LevelView`) ensures:
- Easier addition of new levels, mechanics, or UI features.
- Minimal changes required in the existing codebase for updates.

---

## 6. Additional Notes

### **6.1 Identified Issues**
1. **Pause Menu Glitch**: When the game ends, resuming from the pause menu causes unintended behavior, such as brief movement of entities. Further debugging is required to synchronize game state and UI components.
2. **Unlimited Projectiles**: Players can fire projectiles without restriction, potentially unbalancing gameplay. A reload or cool down mechanic is recommended.

### **6.2 Missing Features**
1. **Tutorial Section**: A tutorial for beginners would improve accessibility.
2. **In-Game Power-Ups**: Temporary power-ups like shields or health boosts could add variety to gameplay.
3. **Final Score Display**: Including a final score on the game over screen in endless mode would enhance engagement.

### **6.3 How to Fix Identified Issues**

#### **1. Pause Menu Glitch**
- **Problem**: When the game ends, resuming from the pause menu causes entities (e.g., plane, projectiles) to briefly move before stopping.

- **Fix**:
    1. **Game State Validation**:
        - Update the `PauseManager` to check the current game state (e.g., `isGameOver`) before allowing the game to resume.
        - Add a conditional statement in the `resumeGame` method to ensure the game cannot be resumed if it has already ended.

    2. **Timeline Management**:
        - Ensure the game timeline is properly paused or stopped when the game ends.
        - In the `LevelParent` or related base class:
            - Stop all active timelines in the `gameOver` method to prevent further updates.
            - Use `Platform.runLater` to synchronize UI updates and ensure thread safety.

    3. **Pause Menu Cleanup**:
        - Modify the `showGameOver` method to automatically hide or disable the pause menu when the game-over state is triggered.
        - Ensure the pause menu is not accessible after the game ends to prevent further interaction.

**Expected Outcome**:
- The game properly handles the pause menu during the game-over state.
- Players cannot resume gameplay after the game ends, ensuring smooth state transitions.
- The pause menu is removed or disabled once the game enters the game-over state.
---

#### **2. Unlimited Projectiles**
- **Problem**: Players can fire projectiles without restriction, leading to balance issues.

- **Fix**:
    1. **Add a Cooldown Timer**:
        - Implement a cooldown period between successive projectile firings to limit the rate of fire.
        - Add a `boolean canFire` attribute in the `UserPlane` or `InputManager` class to track firing eligibility.
        - Use a `Timeline` or `Timer` to reset `canFire` after a predefined interval.

       **Key Methods**:
        - `fireProjectile`: Checks the `canFire` state before firing a projectile.
        - `startCooldown`: Starts a cooldown timer to reset the `canFire` state.

    2. **Ammo System**:
        - Introduce an ammo counter in the `UserPlane` class that decrements with each fired projectile.
        - Add logic to prevent firing when ammo is depleted.
        - Provide a reload mechanism that replenishes ammo after a delay or upon user action (e.g., pressing a reload button).

       **Key Methods**:
        - `fireProjectile`: Decrements the ammo count on each successful shot.
        - `reload`: Refills the ammo counter and optionally displays a progress indicator in the UI.

    3. **UI Integration**:
        - Display the remaining ammo count on the screen using a `Text` element.
        - Add a visual or sound cue to indicate when ammo is low or when reloading is in progress.

**Expected Outcome**:
- Players can only fire projectiles at a controlled rate, ensuring balanced gameplay.
- The addition of an ammo system introduces strategic elements, requiring players to manage resources during combat.
- The user interface clearly communicates ammo status and cooldown states, enhancing player feedback.

### **3 Addressing Projectile Sound Effects Issue**

**Problem**: The unlimited projectile feature causes the sound effects to stop playing when the fire button is held down continuously.

**Fix**:
1. **Modify the SoundManager**:
    - Ensure that each projectile sound effect is handled by a separate instance of the `MediaPlayer`.
    - Create new `MediaPlayer` instances dynamically for each projectile sound to avoid overlapping or cutting off ongoing sound effects.

   **Key Methods**:
    - **`playProjectileSound`**: Creates and plays a new `MediaPlayer` instance for each projectile sound.
    - **`disposeMediaPlayer`**: Cleans up `MediaPlayer` resources once the sound effect completes playback.

2. **Add a Cooldown Mechanism**:
    - Limit the firing rate of projectiles using a cooldown timer, reducing the frequency of sound effect triggers.
    - This indirectly ensures the audio system has enough time to process and play each sound effect properly.

   **Key Methods**:
    - **`startCooldown`**: Implements a timer to reset the ability to fire projectiles.
    - **`fireProjectile`**: Checks the cooldown state before firing a projectile and triggering its sound effect.

3. **Avoid Sound Overlap**:
    - Use a queue to manage sound effects, ensuring that new sounds do not interrupt ongoing playback.
    - Allow queued sound effects to play sequentially once the previous sound completes.

   **Key Methods**:
    - **`queueProjectileSound`**: Adds new sounds to a queue when multiple sounds are triggered.
    - **`playNextSound`**: Plays the next sound in the queue after the current sound finishes.

4. **Volume Control**:
    - Dynamically adjust the volume of projectile sound effects based on the current firing rate.
    - Ensure consistent volume levels across all instances of projectile sound effects.

   **Key Methods**:
    - **`setSoundEffectsVolume`**: Adjusts the volume of all active sound effects dynamically.

**Expected Outcome**:
- Each projectile sound effect plays without interruptions, even when the fire button is held continuously.
- The sound system avoids overlapping or suppressed sound effects, maintaining immersive and clear audio feedback.
- Players experience consistent and polished gameplay audio without glitches caused by rapid projectile firing.

---

# Missing Features Implementation Guide

## **6.4 How to Add Missing Features**

### **1. Tutorial Section**

**Steps**:
1. **Create a `TutorialLevel` class**:
    - Extend the `LevelParent` class to inherit core level functionality.
    - Introduce simplified gameplay mechanics to teach basic controls like:
        - Moving the plane using arrow keys.
        - Shooting projectiles using the SPACE key.
        - Avoiding obstacles or enemies.

2. **Add instructional tooltips or dialog boxes**:
    - Use UI elements to provide real-time guidance, such as:
        - "Press SPACE to shoot."
        - "Use arrow keys to move."
        - "Avoid enemy planes to survive."

3. **Introduce simplified enemy interactions**:
    - Add stationary or slow-moving enemies to allow players to practice aiming and shooting.
    - Include visual cues or markers to highlight targets.

4. **Add a "Skip Tutorial" button**:
    - Modify the `MenuController` to include a "Tutorial" button on the main menu.
    - Add functionality to skip the tutorial and proceed directly to the main game levels for experienced players.

5. **UI Integration**:
    - Display instructions prominently on the screen during the tutorial.
    - Add a "Quit Tutorial" button to return to the main menu if the player chooses to exit early.

**Expected Outcome**:
- The tutorial provides new players with an accessible introduction to the game's controls and mechanics.
- Players can practice without time pressure or overwhelming challenges.
- Experienced players have the option to skip the tutorial and start the main game directly.
---

### **2. In-Game Power-Ups**

**Steps**:
1. **Create a `PowerUp` class**:
    - Define attributes for `type` (e.g., shield, health boost, ammo refill) and `duration` (for time-limited effects).
    - Use this class to represent collectible power-ups in the game.

2. **Modify the `EntityManager`**:
    - Add a method to spawn power-ups at random intervals or after specific events (e.g., defeating an enemy).
    - Example methods:
        - `spawnPowerUp(String type, int duration, double x, double y)`: Spawns a new power-up and adds it to the game.

3. **Update `CollisionManager`**:
    - Detect collisions between the user plane and power-ups.
    - Example methods:
        - `handlePowerUpCollisions(UserPlane userPlane, List<PowerUp> powerUps, Group root)`: Handles interactions when the user collects a power-up.

4. **Add logic in the `UserPlane` class**:
    - Activate the power-up effects based on their type.
    - Example methods:
        - `activatePowerUp(String type)`: Activates the corresponding power-up effect.
        - `enableShield(int duration)`: Temporarily enables a shield.
        - `restoreHealth(int amount)`: Restores a portion of health.
        - `refillAmmo()`: Refills the player’s ammunition.

5. **Display active power-ups in the UI**:
    - Add visual indicators to show active power-ups and their remaining durations.
    - Example methods:
        - `displayActivePowerUp(String type, int duration)`: Updates the UI to display the active power-up status.

### **Expected Outcome**
- **Power-ups** will enhance gameplay by providing temporary advantages such as immunity to damage, health restoration, or extra ammo.
- **UI indicators** will help players track active power-ups and their durations.
- **Dynamic spawning** will make gameplay more engaging and reward player performance.
---

#### **3. Final Score Display**

**Steps**:
1. **Modify the `EndlessLevelView` class**:
    - Enhance the game-over screen to include a final score display for better player feedback.

2. **Update the `LeaderboardManager` class**:
    - Add a method to format the final score for display purposes.
    - Example method:
        - `getFormattedFinalScore(int score)`:
            - Formats and returns the final score as a string in the format: `"Final Score: [score]"`.

3. **Enhance the `showGameOverImage` method**:
    - Update this method in the `LevelView` or `EndlessLevelView` class to display the final score along with the game-over image.
    - Include a visual representation of the final score using UI elements like `Text`.

4. **Integrate the final score with the leaderboard**:
    - Save the player's final score to the leaderboard by calling the `LeaderboardManager.addScore(score)` method during the game-over sequence.

**Expected Outcome**:
- Players can view their final score prominently on the game-over screen.
- The score is automatically saved to the leaderboard, enhancing competitiveness and replayability.

**Key Methods Used**:
- `EndlessLevelView.showGameOverImage()`:
    - Displays the final score and the game-over image on the screen.
- `LeaderboardManager.addScore(int score)`:
    - Saves the player's score to the leaderboard for future reference.
- `LeaderboardManager.getFormattedFinalScore(int score)`:
    - Formats the score for consistent display.

This approach improves user experience by providing clear feedback on performance and integrating the score with the leaderboard for competitive tracking.

