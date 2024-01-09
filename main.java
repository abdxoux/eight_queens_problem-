// Une classe pour représenter une reine
class Queen {
  // La rangée et la colonne de la reine
  private int row;
  private int col;

  // Un constructeur pour créer une reine avec une ligne et une colonne données
  public Queen(int row, int col) {
    this.row = row;
    this.col = col;
  }

  // Une méthode getter pour renvoyer la ligne de la reine
  public int getRow() {
    return row;
  }

  // Une méthode getter pour renvoyer la colonne de la reine
  public int getCol() {
    return col;
  }

  // Une méthode pour vérifier si la reine est attaquée par une autre reine
 public boolean isUnderAttack(Queen other) {
    // Vérifiez s'ils sont dans la même ligne, colonne ou diagonale
    return this.row == other.row ||    // Added logical OR operator
           this.col == other.col ||
           Math.abs(this.row - other.row) == Math.abs(this.col - other.col);
}

}

// Une classe pour représenter un échiquier
class Board {
  // La taille du tableau
  public static final int SIZE = 8;

  // Un tableau pour stocker les reines sur le plateau
  private Queen[] queens;

  // Un constructeur pour créer un tableau vide 
  public Board() {
    queens = new Queen[SIZE];
  }

  // Une méthode pour placer une reine sur le plateau
  public void placeQueen(Queen queen, int col) {
    queens[col] = queen;
  }

  // Une méthode pour retirer une reine du plateau
  public void removeQueen(int col) {
    queens[col] = null;
  }

  // Une méthode pour vérifier si le tableau est valide, c'est-à-dire qu'aucune reine n'est attaquée
  public boolean isValid() {
    for (int i = 0; i < SIZE; i++) {
      for (int j = i + 1; j < SIZE; j++) {
        if (queens[i] != null && queens[j] != null && queens[i].isUnderAttack(queens[j])) {
          return false;
        }
      }
    }
    return true;
  }

  // Une méthode pour afficher le tableau
  public void display() {
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        if (queens[j] != null && queens[j].getRow() == i) {
          System.out.print("Q ");
        } else {
          System.out.print(". ");
        }
      }
      System.out.println();
    }
  }
}

// Un cours pour résoudre le problème des Huit Reines en utilisant le backtracking
class Solver {
  // Une méthode pour trouver une solution au problème
  public static boolean solve(Board board, int col) {
    // Si toutes les colonnes sont remplies, le problème est résolu
    if (col == Board.SIZE) {
      return true;
    }
    // Try each row in the current column
    for (int row = 0; row < Board.SIZE; row++) {
      // Create a new queen
      Queen queen = new Queen(row, col);
      // Place the queen on the board
      board.placeQueen(queen, col);
      // Check if the board is valid
      if (board.isValid()) {
        // Recursively try the next column
        if (solve(board, col + 1)) {
          return true;
        }
      }
      // Remove the queen from the board
      board.removeQueen(col);
    }
    // No solution found in this column
    return false;
  }

  // A main method to test the solver
  public static void main(String[] args) {
    // Create a new board
    Board board = new Board();
    // Try to find a solution
    if (solve(board, 0)) {
      // Display the solution
      board.display();
    } else {
      // No solution found
      System.out.println("No solution found");
    }
  }
}
