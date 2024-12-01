package com.example.demo.managers;

import com.example.demo.actor.UserPlane;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class GameInitializer {

    private final Group root;
    private final ImageView background;
    private final UserPlane user;
    private final Timeline timeline;
    private final int millisecondDelay;

    public GameInitializer(Group root, ImageView background, UserPlane user, Timeline timeline, int millisecondDelay) {
        this.root = root;
        this.background = background;
        this.user = user;
        this.timeline = timeline;
        this.millisecondDelay = millisecondDelay;
    }

    public void initializeBackground() {
        background.setFocusTraversable(true);
        root.getChildren().add(0, background);
    }

    public void initializeFriendlyUnits() {
        root.getChildren().add(user);
        root.getChildren().add(user.getBoundingBox());
    }

    public void initializeTimeline(Runnable updateScene) {
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame gameLoop = new KeyFrame(Duration.millis(millisecondDelay), e -> updateScene.run());
        timeline.getKeyFrames().add(gameLoop);
    }
}
