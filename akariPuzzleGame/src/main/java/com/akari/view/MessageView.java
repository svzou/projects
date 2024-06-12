package com.akari.view;

import com.akari.controller.AlternateMvcController;
import com.akari.model.Puzzle;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class MessageView implements FXComponent {
  private AlternateMvcController controller;
  private Label message;

  public MessageView(AlternateMvcController controller) {
    this.controller = controller;
    this.message = new Label();
  }

  @Override
  public Parent render() {
    HBox layout = new HBox();
    if (controller.isSolved()) {
      Text title = new Text("SUCCESS! YOU SOLVED AKARI!");
      title.setFont(javafx.scene.text.Font.font("times"));
      layout.getChildren().add(title);
    }
    if (!controller.isSolved()) {
      Text title = new Text("not solved yet");
      title.setFont(javafx.scene.text.Font.font("times"));
      layout.getChildren().add(title);
    }
    layout.setAlignment(Pos.TOP_CENTER);
    return layout;
  }
}
