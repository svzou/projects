package com.akari.view;

import com.akari.SamplePuzzles;
import com.akari.controller.AlternateMvcController;
import com.akari.controller.ControllerImpl;
import com.akari.model.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppLauncher extends Application {
  @Override
  public void start(Stage stage) {
    PuzzleLibrary library = new PuzzleLibraryImpl();
    library.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_01));
    library.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_02));
    library.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_03));
    library.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_04));
    Model model = new ModelImpl(library);
    AlternateMvcController controller = new ControllerImpl(model);
    View view = new View(controller);
    Scene scene = new Scene(view.render(), 700, 700);
    stage.setTitle("AKARI :)");
    stage.setScene(scene);
    model.addObserver(
        (Model m) -> {
          scene.setRoot(view.render());
          stage.sizeToScene();
        });
    stage.show();
  }
}
