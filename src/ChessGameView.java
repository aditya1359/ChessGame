import java.util.*;

public class ChessGameView {
    private ChessGame chessGame;
    private Scanner scanner;

    public ChessGameView(ChessGame chessGame) {
        this.chessGame = chessGame;
        this.scanner = new Scanner(System.in);
    }

    public void playMove() {
        while (!chessGame.isOver()) {
            displayBoard();
            System.out.println(chessGame.getCurrentTurn().isWhiteSide()?"White":"Black" + "'s turn");
            System.out.println("Enter your move (e.g., 'e2 e4'):");
            String moveInput = scanner.nextLine();
            String[] moveParts = moveInput.split(" ");
            if (moveParts.length == 2) {
                int startX = moveParts[0].charAt(1) - '1';
                int startY = moveParts[0].charAt(0) - 'a';
                int endX = moveParts[1].charAt(1) - '1';
                int endY = moveParts[1].charAt(0) - 'a';

                boolean moveSuccessful = chessGame.playerMove(chessGame.getCurrentTurn(), startX, startY, endX, endY);
                if (!moveSuccessful) {
                    System.out.println("Invalid move. Try again.");
                }
            } else {
                System.out.println("Invalid input format. Please enter the move in 'e2 e4' format.");
            }
        }
        System.out.println("Game over! " + chessGame.getGameStatus());
    }

    private void displayBoard() {
        Box[][] boxes = chessGame.getChessBoard().getBoxes();
        for (int i = 7; i >= 0; i--) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < 8; j++) {
                Piece piece = boxes[i][j].getPiece();
                if (piece != null) {
                    System.out.print(piece.getClass().getSimpleName().charAt(0));
                    System.out.print(piece.isWhite()?"W":"B" + " ");
                } else {
                    System.out.print("-- ");
                }
            }
            System.out.println();
        }
        System.out.println("  a  b  c  d  e  f  g  h");
    }

    public static void main(String[] args) {
        ChessGame chessGame = new ChessGame();
        ChessGameView chessGameView = new ChessGameView(chessGame);
        chessGameView.playMove();
    }
}
