package com.akari.view;

import com.akari.controller.AlternateMvcController;
import com.akari.model.Puzzle;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class IndexView implements FXComponent {
  private final AlternateMvcController controller;

  public IndexView(AlternateMvcController controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    HBox layout = new HBox();
    layout.setAlignment(Pos.TOP_CENTER);
    Text puzzleText = new Text("Puzzle: ");
    puzzleText.setFont(Font.font("times", FontWeight.BOLD, 15));
    layout.getChildren().add(puzzleText);
    puzzleText.setFill(Color.INDIGO);
    int index = controller.getIndex();
    String indexS = Integer.toString(index + 1);
    Text indexText = new Text(indexS);
    indexText.setFont(Font.font("times", FontWeight.BOLD, 15));
    layout.getChildren().add(indexText);
    indexText.setFill(Color.INDIGO);
    Text ofText = new Text(" of ");
    ofText.setFont(Font.font("times", FontWeight.BOLD, 15));
    layout.getChildren().add(ofText);
    ofText.setFill(Color.INDIGO);
    int numPuzzles = controller.getNumberOfPuzzles();
    String numPuzzlesS = Integer.toString(numPuzzles);
    Text totalNum = new Text(numPuzzlesS);
    totalNum.setFont(Font.font("times", FontWeight.BOLD, 15));
    layout.getChildren().add(totalNum);
    totalNum.setFill(Color.INDIGO);
    layout.setPadding(new Insets(10, 10, 10, 10));
    return layout;
  }
}
