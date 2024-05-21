public class PieceFactory {

    public static Piece createPiece(String pieceType,boolean isWhite)
    {
        return switch (pieceType) {
            case "King" -> new King(isWhite);
            case "Knight" -> new Knight(isWhite);
            case "Queen" -> new Queen(isWhite);
            case "Bishop" -> new Bishop(isWhite);
            case "Rook" -> new Rook(isWhite);
            case "Pawn" -> new Pawn(isWhite);
            default -> throw new IllegalArgumentException("Not a piece type");
        };
    }
}
