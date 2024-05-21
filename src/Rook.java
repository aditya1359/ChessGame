public class Rook extends Piece{
    public Rook(boolean isWhite) {
        this.setWhite(isWhite);
    }

    public Rook(){

    }

    @Override
    public boolean canMove(ChessBoard chessBoard, Box start, Box end) {
        if (end.getPiece() != null && end.getPiece().isWhite() == this.isWhite()) {
            return false; // Cannot move to a square occupied by a piece of the same color
        }

        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());

        return x == 0 || y == 0; // Rook moves straight in any direction
    }
}
