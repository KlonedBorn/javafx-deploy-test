package com.kloneborn.github;

import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.Random;

public class PrimaryController {

    @FXML
    private Label title;

    @FXML
    private Pane particlePane;

    private ParallelTransition celebration; // Parallel transition for wobble and confetti

    private SequentialTransition wobbleTransition;

    private Random random = new Random();

    private static final double WOBBLE_ANGLE = 10;

    private static final double CONFETTI_BLAST_RADIUS = 60.0;

    private static final int CONFETTI_PARTICLES = 25;

    private static final int CONFETTI_BURSTS = 8; // 5 random bursts

    private static final Duration WOBBLE_DURATION = Duration.millis(80.0);


    @FXML
    void onActionCelebrate(ActionEvent event) {
        celebration = new ParallelTransition();

        // Create and add wobble transition to parallel celebration sequence
        if (wobbleTransition == null) {
            createWobbleTransition(title, WOBBLE_ANGLE); // Default deflection angle
        }
        celebration.getChildren().add(wobbleTransition);

        // Create confetti bursts and add them to parallel celebration sequence
        for (int i = 0; i < CONFETTI_BURSTS; i++) {
            // Generate random x and y coordinates within the bounds of the particlePane
            double x = random.nextDouble() * particlePane.getWidth();
            double y = random.nextDouble() * particlePane.getHeight();

            // Create a timeline for the offset between bursts (20ms for each burst)
            Timeline delay = new Timeline(new KeyFrame(Duration.millis(i * 20))); // 20ms between each burst

            // Add a confetti effect at each random position after the delay
            delay.setOnFinished(e -> createConfettiEffect(x, y));

            // Add each confetti burst to the parallel transition
            celebration.getChildren().add(delay);
        }

        // Play the wobble and confetti bursts in sync
        celebration.play();
    }

    // Create a wobble transition with a customizable angle of deflection
    private void createWobbleTransition(Node target, double angle) {
        // Rotate the title by the specified angle to the right
        RotateTransition rotateRight = new RotateTransition(WOBBLE_DURATION, target);
        rotateRight.setByAngle(angle);

        // Rotate the title by the negative of the angle to the left
        RotateTransition rotateLeft = new RotateTransition(WOBBLE_DURATION, target);
        rotateLeft.setByAngle(-2 * angle); // Twice the angle for reverse motion

        // Rotate the title back to neutral (0 degrees)
        RotateTransition rotateBack = new RotateTransition(WOBBLE_DURATION, target);
        rotateBack.setToAngle(0);

        // Chain the rotations into a SequentialTransition and repeat it
        wobbleTransition = new SequentialTransition(rotateRight, rotateLeft, rotateBack);
        wobbleTransition.setCycleCount(5); // Repeat the wobble effect 5 times
    }

    // Create the confetti effect at a given x and y position
    private void createConfettiEffect(double x, double y) {
        for (int i = 0; i < CONFETTI_PARTICLES; i++) {
            // Create a confetti label
            Label confetti = createConfettiLabel();

            // Set the initial position of the confetti particle
            confetti.setLayoutX(x);
            confetti.setLayoutY(y);

            // Add the confetti to the pane
            particlePane.getChildren().add(confetti);

            // Calculate the angle for the current confetti particle
            double angle = (360.0 / CONFETTI_PARTICLES) * i;
            double targetX = x + CONFETTI_BLAST_RADIUS * Math.cos(Math.toRadians(angle));
            double targetY = y + CONFETTI_BLAST_RADIUS * Math.sin(Math.toRadians(angle));

            // Create a transition from the start point to the polar coordinates
            TranslateTransition transition = new TranslateTransition(Duration.seconds(0.45), confetti);
            transition.setToX(targetX - x); // Movement relative to current x position
            transition.setToY(targetY - y); // Movement relative to current y position
            transition.setOnFinished(e -> particlePane.getChildren().remove(confetti)); // Remove the confetti after
                                                                                        // animation

            transition.play();
        }
    }

    // Utility method to create a confetti label with random color
    private Label createConfettiLabel() {
        Label confetti = new Label("*");
        confetti.setStyle("-fx-font-size: 12px;");

        // Randomly choose a color from the CONFETTI_COLOR_PALETTE
        Color randomColor = CONFETTI_COLOR_PALETTE[random.nextInt(CONFETTI_COLOR_PALETTE.length)];
        confetti.setTextFill(randomColor);

        return confetti;
    }

    private static final Color[] CONFETTI_COLOR_PALETTE = new Color[] {
            Color.web("#a864fd"),
            Color.web("#29cdff"),
            Color.web("#78ff44"),
            Color.web("#ff718d"),
            Color.web("#fdff6a")
    };
}
