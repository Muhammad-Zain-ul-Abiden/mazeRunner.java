package MazeRunner;
import java.util.Scanner;
public class game {
	 private static char[][] mazeMap;
	    private static int rows, columns;
	    private static int steps;
	    private static int highScore;

	    
	    public static void main(String[] args) {
	    	Scanner scan = new Scanner(System.in);

      System.out.println("Welcome to Maze Runner!");
      char choice;
      // Main Menu
      do {
          System.out.println("Main Menu:");
          System.out.println("1. Enter the Maze.");
          System.out.println("2. Instructions.");
          System.out.println("3. Leader Board.");
          System.out.println("4. Credits.");
          System.out.println("5. Exit.");

          System.out.print("Enter 1-5 to make your choice: ");
          choice = scan.next().charAt(0);
          scan.nextLine();
          // Switch Cases to select one from the options in Menu
          switch (Character.toUpperCase(choice)) {
              case '1':
                  newGame();
                  break;
              case '2':
                  gamePlayInstructions();
                  break;
              case '3':
                  leaderBoard();
                  break;
              case '4':
                  gameDevCredits();
                  break;
              case '5':
                  System.out.println("Closing the Game. Thank You For Playing!");
                  System.exit(0);
                  break;
              default:
                  System.out.println("Invalid choice. Use the Options from the Menu.");
                  break;
          }
      } while (true);
  }
	    // Method for New Game
  private static void newGame() {
      initializeMaze();
      gamePlay();
     finalResults();
  }
  		// Method for High Scores
  private static void leaderBoard() {
      System.out.println("\n___\nHigh Score: " + highScore + "\n___\n");
  }
  		// Method for Credits information
  private static void gameDevCredits() {
      System.out.println("\n_______\nGame developed by Zain.\nTakes donation in $ only. :) \n_______\n");
  }
  		// Method to initialize maze
  private static void initializeMaze() {
      mazeMap = new char[][]{
              {'#','#','#', '#', '#', '#', '#', '#', '#', '#'}, // 2D Array to create maze
              {'#','P','.', '.', '.', '.', '.', '.', '.', '#'},
              {'#','#','#', '#', '#', '#', '.', '#', '#', '#'},
              {'#','.','.', '.', '.', '.', '.', '.', '.', '#'},
              {'#','#','#', '#', '#', '.', '#', '#', '.', '#'},
              {'#','.','.', '.', '.', '.', '.', '#', '.', '#'},
              {'#','#','#', '#', '#', '#', '.', '#', '#', '#'},
              {'#','.','.', '.', '#', '.', '.', '.', '.', '#'},
              {'#','.','#', '.', '.', '.', '#', '#', '.', '#'},
              {'#','.','#', '#', '#', '#', '#', '#', '#', '#'},
              {'#','.','.', '#', '.', '#', '.', '.', '.', '#'},
              {'#','#','.', '.', '.', '.', '.', '#', 'E', '#'},
              {'#','#','#', '#', '#', '#', '#', '#', '#', '#'}};

            // Getting the starting point 
  	        for (int row = 0; row < mazeMap.length; row++) {
  	            for (int col = 0; col < mazeMap[0].length; col++) {
  	                if (mazeMap[row][col] == 'P') {
  	                    rows = row;
  	                    columns = col;
  	                }
  	            }
  	        }
  	    }
  // Display instructions on the main user menu
  private static void gamePlayInstructions() {
      System.out.println("\n__________\nHow to play: ");
      System.out.println("Find your way through The Impassable Maze. \nYou are here at the starting position P and you need to get to the exit point E. \nWhile avoiding walls SPIKY WALLS! # , get to E before the time runs out. \nAND SAVE THE WORLD!.\n");
      System.out.println("Instructions:\nUse W, A, S, D keys to move up, left, down, and right, respectively.");
      System.out.println("Good luck!\n__________\n");
  }
  //  Printing the current state of the maze on the user screen while playing
  private static void printMaze() {
      for (int i = 0; i < mazeMap.length; i++) {
          char[] row = mazeMap[i];
          for (int j = 0; j < row.length; j++) {
              char cell = row[j];
              System.out.print(cell + " ");
          }
          System.out.println();
      }
  }
  // Checking method for the valid move '.' to follow and '#' to avoid
  private static boolean isValidMove(int mazeRow, int mazeCol) {
      return mazeRow >= 0 && mazeRow < mazeMap.length && mazeCol >= 0 && mazeCol < mazeMap[0].length && mazeMap[mazeRow][mazeCol] != '#';
  }
  // Player movement from user input directions
  private static void movePlayer(char direction) {
      int newRow = rows;
      int newCol = columns;
      // Switch cases to differentiate up, down, left and right movement
      switch (Character.toUpperCase(direction)) {
          case 'W':
              newRow--;
              break;
          case 'A':
              newCol--;
              break;
          case 'S':
              newRow++;
              break;
          case 'D':
              newCol++;
              break;
          default:
              System.err.println("Invalid move. Please use W, A, S, or D.");
              return;
      }
      // Checking for moves and their validity
      if (isValidMove(newRow, newCol)) {
          mazeMap[rows][columns] = '.';
          rows = newRow;
          columns = newCol;
          mazeMap[rows][columns] = 'P';
          steps++;
      } else {
          System.err.println("Invalid move. You have lost health! :(");
      }
  }
  //  Check for the player has won the game
  private static boolean hasPlayerWon() {
	  return mazeMap[rows][columns] == 'E';
  }
  
  private static void gamePlay() {

	// Time Function set for 20 seconds
      final int TIME_LIMIT_SECONDS = 20;
      long startTime = System.currentTimeMillis();

      Scanner scan = new Scanner(System.in);
      String moves;
      printMaze();

      while (!hasPlayerWon()) {
          System.out.println("\n__________\nEnter your moves (W/A/S/D): ");
          moves = scan.nextLine();
          for (char move : moves.toCharArray()) {
              movePlayer(move);
              printMaze();
              if (hasPlayerWon()) {
                  System.out.println("Congratulations! You Saved The World! :D");
                  System.out.println("Number of steps taken: " + steps);
                  System.exit(0);
              }
          }

          // Check if time is up
          long currentTime = System.currentTimeMillis();
          long elapsedTime = (currentTime - startTime) / 1000; // Convert to seconds
          if (elapsedTime >= TIME_LIMIT_SECONDS) {
              System.out.println("Time's up! The world is not saved! :(");
              System.exit(0);
          }
      }
  }
// Final game display and High Score updates
  private static void finalResults() {
      System.out.println("You did not reach the exit. The world is not saved! :(");
      System.out.println("Number of steps taken: " + steps);

      if (steps < highScore || highScore == 0) {
          highScore = steps;
      }

      System.out.println("Your current high score: " + highScore);
  }
}