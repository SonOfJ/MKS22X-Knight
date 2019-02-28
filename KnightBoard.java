public class KnightBoard {
  private int[][] board;
  private int[][] canMove; //Array for optimization that contains the number of outgoing moves.
  private int[] moves = {2, 1, 1, 2, -2, -1, -1, -2, -2, 1, 2, -1, -1, 2, 1,-2}; //Arrray containing coordinates for movement.
  public KnightBoard(int startingRows, int startingCols) {
    if (startingRows <= 0 || startingCols <= 0) {
      throw new IllegalArgumentException("Both parameters must be positive, you nutcase.");
    }
    board = new int[startingRows][startingCols]; //Create board with appropriate dimensions.
    canMove = new int[startingRows][startingCols]; //Set up optimization array.
    for (int i = 0; i < startingRows; i = i + 1) {
      for (int j = 0; j < startingCols; j = j + 1) {
        if (i == 0 || i == startingRows - 1) { //At the very top or bottom.
          if (j == 0 || j == startingCols -1) { //At the very left or right.
            canMove[i][j] = 2;
          } else if (j == 1 || j == startingCols - 2) {
            canMove[i][j] = 3;
          } else {
            canMove[i][j] = 4;
          }
        } else if (i == 1 || i == startingRows - 2) { //Moving towards the middle.
          if (j == 0 || j == startingCols - 1) {
            canMove[i][j] = 3;
          } else if (j == 1 || j == startingCols - 2) {
            canMove[i][j] = 4;
          } else {
            canMove[i][j] = 6;
          }
        } else {
          if (j == 0 || j == startingCols - 1) {
            canMove[i][j] = 4;
          } else if (j == 1 || j == startingCols - 2) {
            canMove[i][j] = 6;
          } else {
            canMove[i][j] = 8;
          }
        }
      }
    }
  }
  public String toString() {
    String display = "";
    for (int i = 0; i < board.length; i = i + 1) {
      for (int j = 0; j < board[0].length; j = j + 1) {
        if (board[i][j] == 0) {
          display = display + " _ ";
        } else {
          if (board[i][j] / 10 == 0) { //Single-digit values.
            display = display + " " + board[i][j] + " ";
          } else { //Double-digit values.
            display = display + board[i][j] + " ";
          }
        }
      }
      display = display + "\n";
    }
    return display;
  }
  private boolean check() { //Checks to see if the board only has 0s.
    for (int i = 0; i < board.length; i = i + 1) {
      for (int j = 0; j < board[0].length; j = j + 1) {
        if (board[i][j] != 0) {
          return false;
        }
      }
    }
    return true;
  }
  public boolean solve(int startingRow, int startingCol) {
    if (!check()) { //The board is not clean.
      throw new IllegalStateException("Dude, the board isn't even clean.");
    }
    if (startingRow < 0 || startingCol < 0 || startingRow > board.length - 1 || startingCol > board[0].length - 1) { //Faulty parameters.
      throw new IllegalArgumentException("Invalid parameters.");
    }
    return solveH(startingRow, startingCol, 1);
  }
  public void sort(int row, int col) {
    int[] moveNums = new int[8]; //Array containing all outgoing values.
    for (int i = 0; i < 8; i = i + 1) { //The first coordinates are already sorted.
      if (row + moves[i * 2] >= 0 && row + moves[i * 2] < board.length && col + moves[i * 2 + 1] >= 0 && col + moves[i * 2 + 1] < board[0].length && board[row + moves[i * 2]][col + moves[i * 2 + 1]] == 0) { //The spot is on the board and it is clean.
        moveNums[i] = canMove[row + moves[i * 2]][col + moves[i * 2 + 1]];
      } else { //The spot is either not on the board or the knight was there.
        moveNums[i] = 9; //The highest outgoing number is 8. This will make the invalid spot go to the very end of the array.
      }
    }
    int outValue; //Number of outgoing moves.
    int rowNum;
    int colNum;
    for (int i = 1; i < 8; i = i + 1) { //First value is already sorted.
      outValue = moveNums[i];
      rowNum = moves[i * 2];
      colNum = moves[i * 2 + 1];
      int j = i;
      while (j > 0 && moveNums[j - 1] > outValue) { //Not at the beginning and the previous value is larger.
        moveNums[j] = moveNums[j - 1];
        moves[j * 2] = moves[j * 2 - 2];
        moves[j * 2 + 1] = moves[j * 2 - 1];
        j = j - 1;
      }
      moveNums[j] = outValue;
      moves[j * 2] = rowNum;
      moves[j * 2 + 1] = colNum;
    }
  }
  public boolean solveH(int row, int col, int level) {
    if (level > board[0].length * board.length) { //Done with all spots?
      return true;
    } else {
      if (addKnight(row, col, level)) {
        sort(row, col);
        for (int i = 0; i < 15; i = i + 2) { //The array has been optimized. Order MATTERS.
          if (solveH(row + moves[i], col + moves[i + 1], level + 1)) {
            return true;
          }
        }
        removeKnight(row, col); //Try another path.
      }
      return false;
    }
  }
  public int countSolutions(int startingRow, int startingCol) {
    if (!check()) { //The board is not clean.
      throw new IllegalStateException("Dude, the board isn't even clean.");
    }
    if (startingRow < 0 || startingCol < 0 || startingRow > board.length - 1 || startingCol > board[0].length - 1) { //Faulty parameters.
      throw new IllegalArgumentException("Invalid parameters.");
    }
    return countH(startingRow, startingCol, 1);
  }
  public int countH(int row, int col, int level) {
    int sols = 0; //Number of solutions.
    if (addKnight(row, col, level)) {
      if (level == board[0].length * board.length) { //Done with all spots?
        removeKnight(row, col);
        return 1;
      } else {
        for (int i = 0; i < 15; i = i + 2) { //Go through the list of coordinates.
          sols = sols + countH(row + moves[i], col + moves[i + 1], level + 1);
        }
      }
      removeKnight(row, col); //Try another spot.
    }
    return sols; //Return the number of solutions.
  }
  public boolean addKnight(int row, int col, int level) {
    if (row >= board.length || row < 0 || col >= board[0].length || col < 0 || board[row][col] != 0) { //The spot is either off the board or there is already a knight.
      return false;
    } else {
      board[row][col] = level; //Place knight.
      for (int i = 0; i < 15; i = i + 2) { //Optimization that reduces the number of moves on every possible spot by 1.
        if (row + moves[i] >= 0 && row + moves[i] < board.length && col + moves[i + 1] >= 0 && col + moves[i + 1] < board[0].length) { //Is the spot on the board?
          canMove[row + moves[i]][col + moves[i + 1]] = canMove[row + moves[i]][col + moves[i + 1]] - 1; //Reduce that spot.
        }
      }
      return true;
    }
  }
  public boolean removeKnight(int row, int col) {
    if (row >= board.length || row < 0 || col >= board[0].length || col < 0 || board[row][col] == 0) { //The spot is either off the board or there is no knight.
      return false;
    } else {
      board[row][col] = 0; //Remove knight.
      for (int i = 0; i < 15; i = i + 2) { //Optimization that increases the number of moves on every possible spot by 1.
        if (row + moves[i] >= 0 && row + moves[i] < board.length && col + moves[i + 1] >= 0 && col + moves[i + 1] < board[0].length) { //Is the spot on the board?
          canMove[row + moves[i]][col + moves[i + 1]] = canMove[row + moves[i]][col + moves[i + 1]] + 1; //Increase that spot.
        }
      }
      return true;
    }
  }
}
