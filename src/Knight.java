public class Knight extends Piece{
    public Knight(boolean isWhite) {
       this.setWhite(isWhite);
    }
    public Knight(){

    }

    @Override
    public boolean canMove(ChessBoard chessBoard, Box start, Box end) {
        if (end.getPiece() != null && end.getPiece().isWhite() == this.isWhite()) {
            return false; // Cannot move to a square occupied by a piece of the same color
        }

        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());

        return (x == 2 && y == 1) || (x == 1 && y == 2); // Knight moves in an L shape
    }
}
