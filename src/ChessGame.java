import java.util.ArrayList;
import java.util.List;

public class ChessGame {

    private ChessBoard chessBoard;
    private List<Player> players;
    private Player currentTurn;
    private GameStatus gameStatus;
    private List<Move> movesPlayed;
    private ChessMoveController chessMoveController;

    public ChessGame(Player player1,Player player2)
    {
        players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

        currentTurn = player1.isWhiteSide() ? player1 : player2;
        chessBoard = new ChessBoard();
        gameStatus = GameStatus.ACTIVE;
        movesPlayed = new ArrayList<>();
        this.chessMoveController = new ChessMoveController();
    }

    public ChessGame(){

    }
    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public void setChessBoard(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Player getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(Player currentTurn) {
        this.currentTurn = currentTurn;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public List<Move> getMovesPlayed() {
        return movesPlayed;
    }

    public void setMovesPlayed(List<Move> movesPlayed) {
        this.movesPlayed = movesPlayed;
    }

    public boolean playerMove(Player player, int startX, int startY, int endX, int endY) {
        if(player != currentTurn)
        {
            System.out.println("Not " + player.getPerson().getName() + "'s turn");
            return false;
        }

        Box startBox = chessBoard.getBox(startX,startY);
        Box endBox = chessBoard.getBox(endX,endY);
        Move move = new Move(player,startBox,endBox);
        if(makeMove(move,player)) {
            checkGameStatus();
            return true;
        }
        return false;
    }
    private boolean makeMove(Move move, Player player) {
        Piece sourcePiece = move.getStart().getPiece();
        //source piece is null or its not the players turn
        if(sourcePiece == null || !(sourcePiece.isWhite() == currentTurn.isWhiteSide()))
        {
            System.out.println("Invalid move : No piece or wrong color at source");
        }

        if(!chessMoveController.validateMove(chessBoard,move))
        {
            return false;
        }

        move.getEnd().setPiece(move.getPieceMoved());
        move.getStart().setPiece(null);

        movesPlayed.add(move);
        checkGameStatus();
        currentTurn = currentTurn == players.get(0) ? players.get(1) : players.get(0);

        return true;
    }
    public boolean isOver(){
        return gameStatus!=GameStatus.ACTIVE;
    }
    private void checkGameStatus() {
        if (isCheckmate()) {
            gameStatus = currentTurn.isWhiteSide() ? GameStatus.BLACKWIN : GameStatus.WHITEWIN;
        } else if (isStalemate()) {
            gameStatus = GameStatus.STALEMATE;
        }  else if (isInsufficientMaterial()) {
            gameStatus = GameStatus.DRAW_INSUFFICIENT_MATERIAL;
        }
    }

    private boolean isCheckmate() {
        Box currentKing = getCurrentKingBox();
        return isInCheck(currentKing) && !hasAnyLegalMoves(currentTurn);
    }

    private boolean isStalemate() {
       return false;
    }

    private boolean isInsufficientMaterial() {
        List<Piece> pieces = chessBoard.getPieces();
        boolean hasMinorPiece = false;

        for (Piece piece : pieces) {
            if (piece instanceof Queen || piece instanceof Rook || piece instanceof Pawn) {
                return false;
            }
            if (piece instanceof Bishop || piece instanceof Knight) {
                if (hasMinorPiece) {
                    return false;
                }
                hasMinorPiece = true;
            }
        }
        return true;
    }

    private boolean isInCheck(Box box) {
        int kingX = box.getX();
        int kingY = box.getY();
        return chessBoard.isSquareUnderAttack(kingX, kingY, currentTurn.isWhiteSide());
    }

    private boolean hasAnyLegalMoves(Player player) {
        for (Box[] row : chessBoard.getBoxes()) {
            for (Box box : row) {
                Piece piece = box.getPiece();
                if (piece != null && piece.isWhite()==(player.isWhiteSide())) {
               //     List<Move> legalMoves = piece.getLegalMoves(chessBoard);
                    List<Move> legalMoves = new ArrayList<>();
                    for (Move move : legalMoves) {
                        Piece capturedPiece = move.getEnd().getPiece();
                        makeMove(move, player);
                        boolean isLegal = !isInCheck(getCurrentKingBox());
                     //   undoMove(move, capturedPiece);
                        if (isLegal) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private Box getCurrentKingBox() {
        for (Box[] row : chessBoard.getBoxes()) {
            for (Box box : row) {
                Piece piece = box.getPiece();
                if (piece instanceof King && piece.isWhite() == (currentTurn.isWhiteSide())) {
                    return box;
                }
            }
        }
        return null; // Should never reach here
    }


}
