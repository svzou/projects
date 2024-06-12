package com.akari.view;

import com.akari.controller.AlternateMvcController;

import com.akari.model.CellType;
import com.akari.model.Puzzle;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.control.Label;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class PuzzleView implements FXComponent {
  private AlternateMvcController controller;

  public PuzzleView(AlternateMvcController controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    Puzzle puzzle = controller.getActivePuzzle();
    GridPane gridPane = new GridPane();
    gridPane.setGridLinesVisible(true);
    gridPane.setVgap(1);
    gridPane.setHgap(1);
    int numRows = puzzle.getHeight();
    int numCols = puzzle.getWidth();
    for (int i = 0; i < numCols; i++) {
      ColumnConstraints colConst = new ColumnConstraints();
      colConst.setMaxWidth(100);
      colConst.setMinWidth(40);
      gridPane.getColumnConstraints().add(colConst);
    }
    for (int i = 0; i < numRows; i++) {
      RowConstraints rowConst = new RowConstraints();
      rowConst.setMaxHeight(80);
      rowConst.setMinHeight(40);
      gridPane.getRowConstraints().add(rowConst);
    }
    for (int row = 0; row < numRows; row++) {
      for (int col = 0; col < numCols; col++) {
        StackPane cell = new StackPane();
        configureCell(cell, puzzle.getCellType(row, col), puzzle, row, col);
        gridPane.add(cell, col, row);
      }
    }
    return gridPane;
  }

  private void configureCell(StackPane cell, CellType type, Puzzle puzzle, int row, int col) {
    switch (type) {
      case CORRIDOR:
        cell.setMaxSize(40, 40);
        cell.setMinSize(40, 40);
        cell.setStyle("-fx-background-color: white;");
        cell.setOnMouseClicked(e -> controller.clickCell(row, col));
        if (controller.isLamp(row, col)) {
          Image img = new Image("light-bulb.png");
          ImageView imgView = new ImageView(img);
          imgView.setFitWidth(20);
          imgView.setFitHeight(20);
          if (controller.isLampIllegal(row, col)) {
            cell.getChildren().add(new Label("X"));
          } else {
            cell.getChildren().add(imgView);
          }
        }
        if (controller.isLit(row, col)) {
          cell.setStyle("-fx-background-color: lightyellow;");
        }
        break;
      case WALL:
        cell.setStyle("-fx-background-color: black;");
        break;
      case CLUE:
        int clue = puzzle.getClue(row, col);
        Text title = new Text(Integer.toString(clue));
        cell.getChildren().add(title);
        cell.setStyle("-fx-background-color: violet;");
        if (controller.isClueSatisfied(row, col)) {
          cell.setStyle("-fx-background-color: green;");
        }
        break;
    }
  }
}