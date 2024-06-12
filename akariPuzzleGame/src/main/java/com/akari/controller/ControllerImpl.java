package com.akari.controller;

import com.akari.model.CellType;
import com.akari.model.Model;
import com.akari.model.Puzzle;
import com.akari.model.PuzzleLibrary;


public class ControllerImpl implements AlternateMvcController {
  Model model;

  public ControllerImpl(Model model) {

    this.model = model;
  }

  @Override
  public void clickNextPuzzle() {

    int currentIndex = model.getActivePuzzleIndex();
    int librarySize = model.getPuzzleLibrarySize();
    if (librarySize > 0) {
      // Increment the index and wrap around using modulo if it reaches the end of the list
      int nextIndex = (currentIndex + 1) % librarySize;
      model.setActivePuzzleIndex(nextIndex);
    }
  }

  @Override
  public void clickPrevPuzzle() {
    int currentIndex = model.getActivePuzzleIndex();
    int librarySize = model.getPuzzleLibrarySize();
    if (librarySize > 0) {
      // Decrement the index and wrap around using modulo to handle the transition from the first to the last puzzle
      int prevIndex = (currentIndex - 1 + librarySize) % librarySize;
      model.setActivePuzzleIndex(prevIndex);
    }
  }

  @Override
  public void clickRandPuzzle() {
    int index = model.getActivePuzzleIndex();
    while (index == model.getActivePuzzleIndex()) {
      index = (int) Math.floor(Math.random() * ((model.getPuzzleLibrarySize() - 1) + 1));
    }
    model.setActivePuzzleIndex(index);
  }

  @Override
  public void clickResetPuzzle() {
    model.resetPuzzle();
  }

  @Override
  public void clickCell(int r, int c) {
    if (model.getActivePuzzle().getCellType(r, c) != CellType.CORRIDOR) {
      return;
    }
    if (model.isLamp(r, c)) {
      model.removeLamp(r, c);
    } else {
      model.addLamp(r, c);
    }
  }

  @Override
  public boolean isLit(int r, int c) {
    return model.isLit(r, c);
  }

  @Override
  public boolean isLamp(int r, int c) {
    return model.isLamp(r, c);
  }

  @Override
  public boolean isClueSatisfied(int r, int c) {
    return model.isClueSatisfied(r, c);
  }

  @Override
  public boolean isSolved() {
    return model.isSolved();
  }

  @Override
  public Puzzle getActivePuzzle() {
    return model.getActivePuzzle();
  }

  public boolean isLampIllegal(int r, int c) {
    return model.isLampIllegal(r, c);
  }

  public int getNumberOfPuzzles() {
    return model.getPuzzleLibrarySize();
  }

  public int getIndex() {
    return model.getActivePuzzleIndex();
  }
}
