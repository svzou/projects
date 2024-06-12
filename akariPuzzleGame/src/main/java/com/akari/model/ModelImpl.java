package com.akari.model;

import java.util.ArrayList;
import java.util.List;

public class ModelImpl implements Model {
  private PuzzleLibrary library;
  private List<ModelObserver> observers;
  private int index;
  private Puzzle currentPuzzle;
  private boolean[][] lampLocations;

  public ModelImpl(PuzzleLibrary library) {
    this.library = library;
    this.observers = new ArrayList<>();
    this.index = 0;
    this.currentPuzzle = library.getPuzzle(0);
    this.lampLocations = new boolean[currentPuzzle.getHeight()][currentPuzzle.getWidth()];
  }

  @Override
  public void addLamp(int r, int c) {
    if (r >= currentPuzzle.getHeight() || c >= currentPuzzle.getWidth() || r < 0 || c < 0) {
      throw new IndexOutOfBoundsException();
    }
    if (currentPuzzle.getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    lampLocations[r][c] = true;
    notify(this);
  }

  @Override
  public void removeLamp(int r, int c) {
    if (r >= currentPuzzle.getHeight() || c >= currentPuzzle.getWidth() || r < 0 || c < 0) {
      throw new IndexOutOfBoundsException();
    }
    if (getActivePuzzle().getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    lampLocations[r][c] = false;
    notify(this);
  }

  @Override
  public boolean isLit(int r, int c) {
    if (r >= currentPuzzle.getHeight() || c >= currentPuzzle.getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    if (currentPuzzle.getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    if (lampLocations[r][c] == true) {
      return true;
    }
    for (int i = r; i < currentPuzzle.getHeight(); i++) {
      if (lampLocations[i][c]) {
        return true;
      } else if (currentPuzzle.getCellType(i, c) != CellType.CORRIDOR) {
        break;
      }
    }
    for (int i = r; i >= 0; i--) {
      if (lampLocations[i][c]) {
        return true;
      } else if (currentPuzzle.getCellType(i, c) != CellType.CORRIDOR) {
        break;
      }
    }
    for (int j = c; j < currentPuzzle.getWidth(); j++) {
      if (lampLocations[r][j]) {
        return true;
      } else if (currentPuzzle.getCellType(r, j) != CellType.CORRIDOR) {
        break;
      }
    }
    for (int j = c; j >= 0; j--) {
      if (lampLocations[r][j]) {
        return true;
      } else if (currentPuzzle.getCellType(r, j) != CellType.CORRIDOR) {
        break;
      }
    }
    return false;
  }

  @Override
  public boolean isLamp(int r, int c) {
    if (r >= currentPuzzle.getHeight() || c >= currentPuzzle.getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    if (currentPuzzle.getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    if (lampLocations[r][c] == true) {
      return true;
    }
    return false;
  }

  @Override
  public boolean isLampIllegal(int r, int c) {
    if (r >= currentPuzzle.getHeight() || c >= currentPuzzle.getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    if (!isLamp(r, c)) {
      throw new IllegalArgumentException();
    }
    for (int i = r + 1; i < currentPuzzle.getHeight(); i++) {
      if (lampLocations[i][c]) {
        return true;
      } else if (currentPuzzle.getCellType(i, c) != CellType.CORRIDOR) {
        break;
      }
    }
    for (int i = r - 1; i >= 0; i--) {
      if (lampLocations[i][c]) {
        return true;
      } else if (currentPuzzle.getCellType(i, c) != CellType.CORRIDOR) {
        break;
      }
    }
    for (int j = c + 1; j < currentPuzzle.getWidth(); j++) {
      if (lampLocations[r][j]) {
        return true;
      } else if (currentPuzzle.getCellType(r, j) != CellType.CORRIDOR) {
        break;
      }
    }
    for (int j = c - 1; j >= 0; j--) {
      if (lampLocations[r][j]) {
        return true;
      } else if (currentPuzzle.getCellType(r, j) != CellType.CORRIDOR) {
        break;
      }
    }
    return false;
  }

  @Override
  public Puzzle getActivePuzzle() {
    return library.getPuzzle(index);
  }

  @Override
  public int getActivePuzzleIndex() {
    return this.index;
  }

  @Override
  public void setActivePuzzleIndex(int index) {
    if (index >= library.size() || index < 0) {
      throw new IndexOutOfBoundsException();
    }
    this.index = index;
    currentPuzzle = library.getPuzzle(index);
    resetPuzzle();
  }

  @Override
  public int getPuzzleLibrarySize() {
    return library.size();
  }

  @Override
  public void resetPuzzle() {
    lampLocations = new boolean[currentPuzzle.getHeight()][currentPuzzle.getWidth()];
    notify(this);
  }

  @Override
  public boolean isSolved() {
    for (int i = 0; i < currentPuzzle.getHeight(); i++) {
      for (int j = 0; j < currentPuzzle.getWidth(); j++) {
        if (currentPuzzle.getCellType(i, j) == CellType.CLUE) {
          if (!isClueSatisfied(i, j)) {
            return false;
          }
        }
        if (currentPuzzle.getCellType(i, j) == CellType.CORRIDOR) {
          if (!isLit(i, j)) {
            return false;
          }
          if (isLamp(i, j) && isLampIllegal(i, j)) {
            return false;
          }
        }
      }
    }
    return true;
  }

  @Override
  public boolean isClueSatisfied(int r, int c) {
    if (r >= currentPuzzle.getHeight() || c >= currentPuzzle.getWidth() || r < 0 || c < 0) {
      throw new IndexOutOfBoundsException();
    }
    if (currentPuzzle.getCellType(r, c) != CellType.CLUE) {
      throw new IllegalArgumentException();
    }
    int count = 0;
    if (r > 0 && currentPuzzle.getCellType(r - 1, c) == CellType.CORRIDOR) {
      if (isLamp(r - 1, c)) {
        count++;
      }
    }
    if (r < currentPuzzle.getHeight() - 1
        && currentPuzzle.getCellType(r + 1, c) == CellType.CORRIDOR) {
      if (isLamp(r + 1, c)) {
        count++;
      }
    }
    if (c > 0 && currentPuzzle.getCellType(r, c - 1) == CellType.CORRIDOR) {
      if (isLamp(r, c - 1)) {
        count++;
      }
    }
    if (c < currentPuzzle.getWidth() - 1
        && currentPuzzle.getCellType(r, c + 1) == CellType.CORRIDOR) {
      if (isLamp(r, c + 1)) {
        count++;
      }
    }
    if (count == currentPuzzle.getClue(r, c)) {
      return true;
    }
    return false;
  }

  @Override
  public void addObserver(ModelObserver observer) {
    observers.add(observer);
  }

  @Override
  public void removeObserver(ModelObserver observer) {
    observers.remove(observer);
  }

  private void notify(ModelImpl model) {
    for (ModelObserver o : observers) {
      o.update(model);
    }
  }
}
