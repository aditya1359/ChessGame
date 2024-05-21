import java.util.ArrayList;
import java.util.List;

public class King extends Piece{

    private boolean castlingDone;

    public King(boolean isWhite) {
        this.setWhite(isWhite);
    }
    public King(){

    }

    @Override
    public boolean canMove(ChessBoard chessBoard, Box start, Box end) {

        if (end.getPiece() != null && end.getPiece().isWhite() == this.isWhite()) {
            return false; // Cannot attack own piece
        }

        int xMovement = Math.abs(start.getX() - end.getX());
        int yMovement = Math.abs(start.getY() - end.getY());

        if (xMovement + yMovement == 1) {
            // Moving one square in any direction
            return true;
        }
        return false;

        //castling to be implemented also

    }

    public boolean isCastlingDone() {
        return castlingDone;
    }

    public void setCastlingDone(boolean castlingDone) {
        this.castlingDone = castlingDone;
    }
}
