public class KnightBoard {
  private int[][] board;
  private int[][] canMove; //Array for optimization that contains the number of places the knight can move to.
  private int[] moves = {2, 1, 1, 2, -2, -1, -1, -2, -2, 1, 2, -1, -1, 2, 1,-2}; //Arrray containing coordinates for movement.
  public KnightBoard(int startingRows,int startingCols) {
    if (startingRows <= 0 || startingCols <= 0) {
      throw new IllegalArgumentException("Both parameters must be positive, you nutcase.");
    }
    board = new int[startingRows][startingCols]; //Create board with appropriate dimensions.
    canMove = new int[startingRows][startingCols]; //Set up optimization array.
    for(int i = 0; i < startingRows; i = i + 1) {
      for(int j = 0; j < startingCols; j = j + 1) {
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
    if (!check()) { //The board is not clean.
      throw new IllegalStateException("Dude, the board isn't even clean.");
    }
    if (startingRow < 0 || startingCol < 0 || startingRow > board.length - 1 || startingCol > board[0].length - 1) { //Faulty parameters.
      throw new IllegalArgumentException("Invalid parameters.");
    }
    return solveH(startingRow, startingCol, 1);
  }

 public boolean solveH(int row, int col, int level) {
   if(level > board[row].length * board.length) { //Done with all spots?
     return true;
   } else {
     for(int i = 0; i < 15; i = i + 2) { //Go through the list of coordinates.
       if(addKnight(row + moves[i], col + moves[i + 1], level + 1)){
         if(solveH(row + moves[i], col + moves[i + 1], level + 1)){
           return true;
         } else {
           removeKnight(row + moves[i], col + moves[i + 1]); //Try another spot.
         }
       }
     }
   }
   return false;
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
   if(board[row][col] == 0) { //Space is empty.
     if(level > board[row].length * board.length) { //Done with all spots?
     return 1;
   } else {
     for(int i = 0; i < 15; i = i + 2) { //Go through the list of coordinates.
       if(addKnight(row + moves[i], col + moves[i + 1], level + 1)){
         sols = sols + countH(row + moves[i], col + moves[i + 1], level + 1);
           removeKnight(row + moves[i], col + moves[i + 1]); //Try another spot.
         }
       }
     }
   }
   return sols;
 }
 public boolean addKnight(int row, int col, int level){
   if(row >= board.length || row < 0 || col >= board[0].length || col < 0 || board[row][col] != 0){ //The spot is either off the board or there is already a knight.
     return false;
   } else {
     board[row][col] = level; //Place knight.
     for(int i = 0; i < 15; i = i + 2) { //Optimization that reduces the number of moves on every possible spot by 1.
       if (row + moves[i] >= 0 && row + moves[i] < board.length && col + moves[i + 1] >= 0 && col + moves[i + 1] < board[0].length) { //Is the spot on the board?
         canMove[row + moves[i]][col + moves[i + 1]] = canMove[row + moves[i]][col + moves[i + 1]] - 1; //Reduce that spot.
       }
     }
     return true;
   }
 }
 public boolean removeKnight(int row, int col){
  if(row >= board.length || row < 0 || col >= board[0].length || col < 0 || board[row][col] == 0){ //The spot is either off the board or there is no knight.
    return false;
  }else{
    board[row][col] = 0; //Remove knight.
    for(int i = 0; i < 15; i = i + 2) { //Optimization that increases the number of moves on every possible spot by 1.
      if (row + moves[i] >= 0 && row + moves[i] < board.length && col + moves[i + 1] >= 0 && col + moves[i + 1] < board[0].length) { //Is the spot on the board?
        canMove[row + moves[i]][col + moves[i + 1]] = canMove[row + moves[i]][col + moves[i + 1]] + 1; //Increase that spot.
      }
    }
    return true;
  }
}
}
