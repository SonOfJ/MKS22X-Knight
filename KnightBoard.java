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
  if (!check) { //The board is not clean.
    throw new IllegalStateException("Dude, the board isn't even clean.");
  }
  if (startingRow < 0 || startingCol < 0 || startingRow > board.length - 1 || startingCol > board[].length - 1) { //Faulty parameters.
    throw new IllegalArgumentException("Invalid parameters.")
  }
  board[startingRow][startingCol] = 1; //The first step.
  return solveH(startingRow, startingCol, 2);
}
private boolean solveH(int row ,int col, int level) {
  if (level == board.length * board[].length + 1) { //If the level is equal to the total area of the board plus 1.
    return true;
  }
  if (row > -1 && row < board.length && col > -1 && col < board[].length && board[row][col] == 0) { //If the spot is available.
    board[row][col] = level; //Put the number.
    if (solveH(row - 2, col + 1, level + 1) || //All the possible positions.
    solveH(row - 1, col + 2, level + 1) ||
    solveH(row + 1, col + 2, level + 1) ||
    solveH(row + 2, col + 1, level + 1) ||
    solveH(row + 2, col - 1, level + 1) ||
    solveH(row + 1, col - 2, level + 1) ||
    solveH(row - 1, col - 2, level + 1) ||
    solveH(row - 2, col - 1, level + 1)) {
      return true;
    }
    board[row][col] = 0; //Reset the current spot.
  }
  return false;
}
@returns the number of solutions from the starting position specified
public int countSolutions(int startingRow, int startingCol) {
  if (!check) { //The board is not clean.
    throw new IllegalStateException("Dude, the board isn't even clean.");
  }
  if (startingRow < 0 || startingCol < 0 || startingRow > board.length - 1 || startingCol > board[].length - 1) { //Faulty parameters.
    throw new IllegalArgumentException("Invalid parameters.")
  }
  return countH(0, 0, 1);
}
private int countH(int row, int col, int level) {

}
