public class Queen extends Piece{
    public Queen(boolean isWhite) {
        this.setWhite(isWhite);
    }

    public Queen(){

    }

    @Override
    public boolean canMove(ChessBoard chessBoard, Box start, Box end) {
        if (end.getPiece() != null && end.getPiece().isWhite() == this.isWhite()) {
            return false; // Cannot move to a square occupied by a piece of the same color
        }

        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());

        return isStraightOrDiagonalMove(start, end);
    }

    private boolean isStraightOrDiagonalMove(Box start, Box end) {
        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());
        return x == y || x == 0 || y == 0;
    }
}
