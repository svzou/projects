package com.akari.view;

import com.akari.controller.AlternateMvcController;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

public class View implements FXComponent {
  private AlternateMvcController controller;

  public View(AlternateMvcController controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    VBox layout = new VBox();
    ControlView controlView = new ControlView(controller);
    PuzzleView puzzleView = new PuzzleView(controller);
    MessageView messageView = new MessageView(controller);
    IndexView indexView = new IndexView(controller);
    layout.getChildren().add(controlView.render());
    layout.getChildren().add(puzzleView.render());
    layout.getChildren().add(messageView.render());
    layout.getChildren().add(indexView.render());
    return layout;
  }
}
