public class Main {
    public static void main(String[] args) {

        // Define first person
        Person person1 = new Person("Rahul", "Shyambazar", "Kolkata", "WB", 700004, "India");

        // Define second person
        Person person2 = new Person("Ujjwal", "Saltlake", "Kolkata", "WB", 700091, "India");

        // Define player 1
        Player player1 = new Player();
        player1.setPerson(person1);
        player1.setWhiteSide(true);
        player1.setId(123);
        player1.setPassword("abc");

        // Define player 2
        Player player2 = new Player();
        player2.setPerson(person2);
        player2.setWhiteSide(false);
        player2.setId(456);
        player2.setPassword("def");

        // Create the chessboard and initialize pieces
        ChessBoard chessBoard = new ChessBoard();


        // Create the chess game
        ChessGame chessGame = new ChessGame(player1,player2);
        chessGame.setChessBoard(chessBoard);
        // Create the game view
        ChessGameView chessGameView = new ChessGameView(chessGame);

        // Start the game
        chessGameView.playMove();


    }
}