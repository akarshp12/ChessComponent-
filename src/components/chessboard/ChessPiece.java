package components.chessboard;

/**
 * Represents an individual chess piece with color and type.
 */
public final class ChessPiece {

    public enum Color {
        WHITE, BLACK
    }

    public enum Type {
        PAWN, KNIGHT, BISHOP, ROOK, QUEEN, KING
    }

    private final Color color;
    private final Type type;

    /**
     * Constructor for a chess piece.
     * 
     * @param color
     *            the piece color (WHITE or BLACK)
     * @param type
     *            the piece type (PAWN, KNIGHT, etc.)
     */
    public ChessPiece(Color color, Type type) {
        assert color != null : "Violation of: color is not null";
        assert type != null : "Violation of: type is not null";
        this.color = color;
        this.type = type;
    }

    /**
     * Returns the color of this piece.
     * 
     * @return the piece color
     */
    public Color color() {
        return this.color;
    }

    /**
     * Returns the type of this piece.
     * 
     * @return the piece type
     */
    public Type type() {
        return this.type;
    }
}
