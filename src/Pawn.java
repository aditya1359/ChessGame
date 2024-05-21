public class Pawn extends Piece{
    public Pawn(boolean isWhite) {
        this.setWhite(isWhite);
    }

    public Pawn(){

    }

    @Override
    public boolean canMove(ChessBoard chessBoard, Box start, Box end) {
        if (end.getPiece() != null && end.getPiece().isWhite() == this.isWhite()) {
            return false; // Cannot move to a square occupied by a piece of the same color
        }

        int x = start.getX() - end.getX();
        int y = start.getY() - end.getY();

        int direction = this.isWhite() ? -1 : 1;
        int startRow = this.isWhite() ? 6 : 1;

        if (x == 0 && y == direction) {
            return end.getPiece() == null; // Single step forward
        }

        if (x == 0 && y == 2 * direction && start.getY() == startRow) {
            Box inBetween = chessBoard.getBox(start.getX(), start.getY() + direction);
            return end.getPiece() == null && inBetween.getPiece() == null; // Double step forward
        }

        if (Math.abs(x) == 1 && y == direction) {
            return end.getPiece() != null && end.getPiece().isWhite() != this.isWhite(); // Capture
        }

        return false;
    }
}
