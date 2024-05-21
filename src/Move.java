public class Move {
    private Box start;
    private Box end;
    private Piece pieceKilled;
    private Piece pieceMoved;
    private Player player;
    private boolean castlingMove;

    public Move(Player player, Box startBox, Box endBox) {
        this.player = player;
        this.start = startBox;
        this.end = endBox;
        pieceKilled = endBox.getPiece();
        pieceMoved = startBox.getPiece();
        this.castlingMove = false;
    }

    public Box getStart() {
        return start;
    }

    public void setStart(Box start) {
        this.start = start;
    }

    public Box getEnd() {
        return end;
    }

    public void setEnd(Box end) {
        this.end = end;
    }

    public Piece getPieceKilled() {
        return pieceKilled;
    }

    public void setPieceKilled(Piece pieceKilled) {
        this.pieceKilled = pieceKilled;
    }

    public Piece getPieceMoved() {
        return pieceMoved;
    }

    public void setPieceMoved(Piece pieceMoved) {
        this.pieceMoved = pieceMoved;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean isCastlingMove() {
        return castlingMove;
    }

    public void setCastlingMove(boolean castlingMove) {
        this.castlingMove = castlingMove;
    }
}
