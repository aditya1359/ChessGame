import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChessBoard {

    private Box[][] boxes;
    private Date createdDate;

    public ChessBoard(){
        createdDate = new Date();
        boxes = new Box[8][8];
        initializeBoard();
    }
    public Box[][] getBoxes() {
        return boxes;
    }

    public void setBoxes(Box[][] boxes) {
        this.boxes = boxes;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public List<Piece> getPieces()
    {
        List<Piece> pieceList = new ArrayList<>();
        for(int i=0;i<8;i++)
        {
            for(int j=0;j<8;j++)
            {
                if(boxes[i][j].getPiece()!=null && !boxes[i][j].getPiece().isKilled())
                {
                    pieceList.add(boxes[i][j].getPiece());
                }
            }
        }
        return pieceList;
    }

    public void setupPieces(String color, int majorRow,int pawnRow)
    {
        boolean isWhite = color.equals("White");
        for(int i=0;i<8;i++)
        {
            boxes[pawnRow][i].setPiece(PieceFactory.createPiece("Pawn",isWhite));
        }

        boxes[majorRow][0].setPiece(PieceFactory.createPiece("Rook",isWhite));
        boxes[majorRow][1].setPiece(PieceFactory.createPiece("Knight",isWhite));
        boxes[majorRow][2].setPiece(PieceFactory.createPiece("Bishop",isWhite));
        boxes[majorRow][3].setPiece(PieceFactory.createPiece("Queen",isWhite));
        boxes[majorRow][4].setPiece(PieceFactory.createPiece("King",isWhite));
        boxes[majorRow][5].setPiece(PieceFactory.createPiece("Bishop",isWhite));
        boxes[majorRow][6].setPiece(PieceFactory.createPiece("Knight",isWhite));
        boxes[majorRow][7].setPiece(PieceFactory.createPiece("Rook",isWhite));

    }

    public void resetBoard(){
        initializeBoard();
    }

    public void updateBoard(){

    }

    public void initializeBoard(){

        for(int i=0;i<8;i++)
        {
            for(int j=0;j<8;j++)
            {
                boxes[i][j] = new Box(i,j,null);
            }
        }

        //Major row for white pieces is 7 and pawn row is 6
        setupPieces("White",7,6);
        setupPieces("Black",0,1);
    }

    public Box getBox(int x, int y) {
        if(isValid(x,y))
        {
            return boxes[x][y];
        }
        return null;
    }

    private boolean isValid(int x,int y)
    {
        if(x>=0 && x<8 && y>=0 && y<8)
            return true;

        return false;
    }

    public boolean isSquareUnderAttack(int x, int y, boolean isWhite) {
        for (Box[] row : boxes) {
            for (Box box : row) {
                Piece piece = box.getPiece();
                if (piece != null && !piece.isWhite()==(isWhite)) {
                    List<Move> moves = piece.getLegalMoves(this);
                    for (Move move : moves) {
                        if (move.getEnd().getX() == x && move.getEnd().getY() == y) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
