public class ChessMoveController {

    public boolean validateMove(ChessBoard chessBoard, Move move) {
        Piece piece = move.getPieceMoved();
        if (piece == null) {
            return false;
        }

        Box start = move.getStart();
        Box end = move.getEnd();

        // General checks
        if (start.equals(end)) {
            return false; // Can't move to the same position
        }
        if (!isInBounds(end.getX(), end.getY())) {
            return false; // Out of bounds
        }

        // Check if the move is valid for the specific piece
        if (!piece.canMove(chessBoard, start, end)) {
            return false;
        }

        // Check if the path is clear (except for knights)
        if (!(piece instanceof Knight) && !isPathClear(chessBoard, start, end)) {
            return false;
        }

        return true;
    }

    private boolean isInBounds(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }

    private boolean isPathClear(ChessBoard chessBoard, Box start, Box end) {
        int xDirection = Integer.signum(end.getX() - start.getX());
        int yDirection = Integer.signum(end.getY() - start.getY());

        int x = start.getX() + xDirection;
        int y = start.getY() + yDirection;

        while (x != end.getX() || y != end.getY()) {
            if (chessBoard.getBox(x, y).getPiece() != null) {
                return false;
            }
            x += xDirection;
            y += yDirection;
        }

        return true;
    }
}
