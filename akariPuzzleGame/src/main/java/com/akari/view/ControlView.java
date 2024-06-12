package com.akari.view;

import com.akari.controller.AlternateMvcController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
// import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class ControlView implements FXComponent {
  private AlternateMvcController controller;

  public ControlView(AlternateMvcController controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    VBox layout = new VBox();
    layout.setAlignment(Pos.CENTER); // Central alignment within the VBox
    layout.setSpacing(10.0); // Vertical spacing between buttons
    layout.setPadding(new Insets(20, 20, 20, 20)); // Padding around the VBox
    // Randomize button
    Button randomize = new Button("Random");
    randomize.setOnAction(
        event -> {
          controller.clickRandPuzzle();
        });
    layout.getChildren().add(randomize);
    // Previous button
    Button previous = new Button("Previous");
    previous.setOnAction(
        event -> {
          controller.clickPrevPuzzle();
        });
    layout.getChildren().add(previous);
    // Next button
    Button next = new Button("Next ");
    next.setOnAction(
        event -> {
          controller.clickNextPuzzle();
        });
    layout.getChildren().add(next);
    // Reset button
    Button reset = new Button("Reset ");
    reset.setOnAction(
        event -> {
          controller.clickResetPuzzle();
        });
    layout.getChildren().add(reset);
    layout.setSpacing(10.0);
    layout.setPadding(new Insets(20, 20, 20, 20));
    return layout;
  }
}
