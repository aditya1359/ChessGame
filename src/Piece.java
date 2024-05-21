public abstract class Piece {

    private boolean isKilled;
    private boolean isWhite;


    public boolean isKilled() {
        return isKilled;
    }

    public void setKilled(boolean killed) {
        isKilled = killed;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public void setWhite(boolean white) {
        isWhite = white;
    }

    public abstract boolean canMove(ChessBoard chessBoard,Box start,Box end);
}

