package components.chessboard;

/**
 * Layered implementations of secondary methods for {@code ChessBoard}.
 */
public abstract class ChessBoardSecondary implements ChessBoard {

    @Override
    public boolean isBoardEmpty() {
        boolean empty = true;
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (!this.isSquareEmpty(r, c)) {
                    empty = false;
                }
            }
        }
        return empty;
    }

    @Override
    public int pieceCount() {
        int count = 0;
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (!this.isSquareEmpty(r, c)) {
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public void removeAllPieces() {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (!this.isSquareEmpty(r, c)) {
                    this.clearSquare(r, c);
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ChessBoard [Turn: ").append(this.turn()).append("]\n");
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (this.isSquareEmpty(r, c)) {
                    sb.append(". ");
                } else {
                    ChessPiece p = this.pieceAt(r, c);
                    char symbol = p.type().name().charAt(0);
                    if (p.color() == ChessPiece.Color.WHITE) {
                        sb.append(Character.toLowerCase(symbol)).append(" ");
                    } else {
                        sb.append(symbol).append(" ");
                    }
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
