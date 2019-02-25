public class KnightBoard {
  public KnightBoard(int startingRows,int startingCols) {
    if (startingRows <= 0 || startingCols <= 0) {
      throw new IllegalArgumentException("Both parameters must be positive, you nutcase.");
    }
    int[][] board = new int[startingRows][startingCols]; //Create board with appropriate dimensions.
  }
  public boolean addKnight(int row, int col, int level) { //Return yes if knight is added.
    if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) { //The knight is not on the board.
      return false;
    }
    if (board[row][col] == 0) { //The spot is clean.
      board[row][col] = level;
      return true;
    }
    return false;
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
    return solveH(startingRow, startingCol, 2);
  }
  private boolean solveH(int row ,int col, int level) {
    if (level == board.length * board[0].length) { //If the level is equal to the total area of the board plus 1.
      return true;
    }
    if (row > -1 && row < board.length && col > -1 && col < board[0].length && board[row][col] == 0) { //If the spot is available.
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
  public int countSolutions(int startingRow, int startingCol) {
    if (!check) { //The board is not clean.
      throw new IllegalStateException("Dude, the board isn't even clean.");
    }
    if (startingRow < 0 || startingCol < 0 || startingRow > board.length - 1 || startingCol > board[0].length - 1) { //Faulty parameters.
      throw new IllegalArgumentException("Invalid parameters.")
    }
    return countH(0, 0, 1);
  }
  private int countH(int row, int col, int level) {
    int ans = 0; //This variable will be the number of solutions.
    if (addKnight(row, col, level)) { //If the knight can be placed.
      if (level == board.length * board[0].length) { //Has the knight been to every single location?
        removeKnight()
      }
    }
