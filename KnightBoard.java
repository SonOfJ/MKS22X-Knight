public class KnightBoard {
  public KnightBoard(int startingRows,int startingCols) {
    if (startingRows <= 0 || startingCols <= 0) {
      throw new IllegalArgumentException("Both parameters must be positive, you nutcase.");
    }
    int[][] board = new int[startingRows][startingCols]; //Create board with appropriate dimensions.
  }
  public String toString() {
    String display = "";
    for(int i = 0; i < board.length; i = i + 1) {
      for(int j = 0; j < board[].length; j = j + 1) {
        if (board[i][j] == 0) { //If the value is 0.
          display = display + " _ ";
        }
        if (board[i][j] % 10 >= 1) { //If the value is a two-digit number.
          display = display + board[i][j] + " ";
        } else {
          display = display + " " + board[i][j] + " "; //If the value is a one-digit number.
        }
      }
      display = display + "\n";
    }
    return display;
  }
Modifies the board by labeling the moves from 1 (at startingRow,startingCol) up to the area of the board in proper knight move steps.
@throws IllegalStateException when the board contains non-zero values.
@throws IllegalArgumentException when either parameter is negative
 or out of bounds.
@returns true when the board is solvable from the specified starting position
private boolean check() { //Checks to see if the board only has 0s.
  for(int i = 0; i < board.length; i = i + 1) {
    for(int j = 0; j < board[].length; j = j + 1) {
      if (board[i][j] != 0) {
        return false;
      }
    }
  }
  return true;
}
public boolean solve(int startingRow, int startingCol) {
  if (!check) {
    throw new IllegalStateException("Dude, the board isn't even clean.");
  }
}



@throws IllegalStateException when the board contains non-zero values.
@throws IllegalArgumentException when either parameter is negative
 or out of bounds.
@returns the number of solutions from the starting position specified
public int countSolutions(int startingRow, int startingCol)

Suggestion:
private boolean solveH(int row ,int col, int level)
// level is the # of the knight
}
