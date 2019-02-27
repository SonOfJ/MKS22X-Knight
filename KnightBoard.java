public class KnightBoard {
  private int[][] board;
  private int[][] moves = {{-1,  2},
  {1,   2},
  {-2,  1},
  {-2, -1},
  {-1, -2},
  {1,  -2},
  {2,   1},
  {2,  -1}};
  private int level = 1;
  private int sols = 0;
  public KnightBoard(int startingRows,int startingCols) {
    if (startingRows <= 0 || startingCols <= 0) {
      throw new IllegalArgumentException("Both parameters must be positive, you nutcase.");
    }
    board = new int[startingRows][startingCols]; //Create board with appropriate dimensions.
  }
  public boolean addKnight(int row, int col) { //Return yes if knight is added.
    if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) { //The knight is not on the board.
      return false;
    }
    if (board[row][col] == 0) { //The spot is clean.
      board[row][col] = level; //Place the knight.
      level = level + 1; //Next level.
      return true;
    }
    return false;
  }
  public boolean removeKnight(int row, int col) {
    if (board[row][col] == 0) { //The spot is clean.
      return false;
    }
    board[row][col] = 0; //Empty the spot.
    level = level - 1; //Decrease the level.
    return true;
  }
  public String toString() {
    String display = "";
    for(int i = 0; i < board.length; i = i + 1) {
      for(int j = 0; j < board[0].length; j = j + 1) {
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
      for(int j = 0; j < board[0].length; j = j + 1) {
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
    if (startingRow < 0 || startingCol < 0 || startingRow > board.length - 1 || startingCol > board[0].length - 1) { //Faulty parameters.
      throw new IllegalArgumentException("Invalid parameters.")
    }
    board[startingRow][startingCol] = 1; //The first step.
    return solveH(startingRow, startingCol);
  }
  private boolean solveH(int row ,int col, int level) {
    if (level == board.length * board[0].length) { //If the level is equal to the total area of the board plus 1.
      return true;
    }
    for(int i = 0; i < 8; i = i + 1) { //Only 8 possible moves for a knight.
      if (addKnight(row, col)) { //Can be added?
        if (solveH(row + moves[i][0], col + moves[i][1])) {
          return true;
        }
        removeKnight(row, col); //Go back and try another spot.
      }
    }
    return false; //Can't be solved.
  }
  public int countSolutions(int startingRow, int startingCol) {
    if (!check) { //The board is not clean.
      throw new IllegalStateException("Dude, the board isn't even clean.");
    }
    if (startingRow < 0 || startingCol < 0 || startingRow > board.length - 1 || startingCol > board[0].length - 1) { //Faulty parameters.
      throw new IllegalArgumentException("Invalid parameters.")
    }
    return countH(startingRow, startingCol);
  }
  private int countH(int row, int col) {
    if (level == board.length * board[0].length) { //If the level is equal to the total area of the board plus 1.
      sols = sols + 1; //Gained a solution.
    }
    for(int i = 0; i < 8; i = i + 1) { //Only 8 possible moves for a knight.
      if (addKnight(row, col)) { //Can be added?
        countH(row + moves[i][0], col + moves[i][1]);
        removeKnight(row, col); //Go back and try another spot.
      }
    }
    return sols;
  }
}
