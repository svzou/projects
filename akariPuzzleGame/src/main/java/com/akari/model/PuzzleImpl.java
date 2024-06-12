package com.akari.model;

public class PuzzleImpl implements Puzzle {
  private int width;
  private int height;
  private int[][] board;

  public PuzzleImpl(int[][] board) {
    if (board == null) {
      throw new IllegalArgumentException();
    }
    this.height = board.length;
    this.width = board[0].length;
    this.board = board;
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public CellType getCellType(int r, int c) {
    if (r > getWidth() - 1 || c > getHeight() - 1 || r < 0 || c < 0) {
      throw new IndexOutOfBoundsException();
    }
    if (board[r][c] == 5) {
      return CellType.WALL;
    }
    if (board[r][c] == 6) {
      return CellType.CORRIDOR;
    } else {
      return CellType.CLUE;
    }
  }

  @Override
  public int getClue(int r, int c) {
    if (r > width || c > height) {
      throw new IndexOutOfBoundsException();
    }
    if (getCellType(r, c) != CellType.CLUE) {
      throw new IllegalArgumentException();
    }
    if (board[r][c] == 0) {
      return 0;
    }
    if (board[r][c] == 1) {
      return 1;
    }
    if (board[r][c] == 2) {
      return 2;
    }
    if (board[r][c] == 3) {
      return 3;
    }
    if (board[r][c] == 4) {
      return 4;
    }
    return 0;
  }
}
