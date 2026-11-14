package components.chessboard;

/**
 * {@code ChessBoard} represented as an 8x8 grid of pieces and an explicit boolean
 * occupancy grid, avoiding null values in accordance with strict component discipline.
 *
 * @convention |pieces| = 8 and |occupied| = 8, with each row of length 8. 
 *             Every element in pieces is non-null, and occupancy is tracked 
 *             via the boolean grid. currentTurn is non-null.
 * @correspondence this = (pieces, currentTurn)
 */
public final class ChessBoard1L extends ChessBoardSecondary {

    private ChessPiece[][] pieces;
    private boolean[][] occupied;
    private ChessPiece.Color currentTurn;
    private static final int BOARD_SIZE = 8;

    /**
     * Creator of initial perennial state.
     */
    private void createNewRep() {
        this.currentTurn = ChessPiece.Color.WHITE;
        this.pieces = new ChessPiece[BOARD_SIZE][BOARD_SIZE];
        this.occupied = new boolean[BOARD_SIZE][BOARD_SIZE];
        for (int r = 0; r < BOARD_SIZE; r++) {
            for (int c = 0; c < BOARD_SIZE; c++) {
                this.pieces[r][c] = new ChessPiece(ChessPiece.Color.WHITE, ChessPiece.Type.PAWN);
                this.occupied[r][c] = false;
            }
        }
    }

    /**
     * Default no-argument constructor.
     */
    public ChessBoard1L() {
        this.createNewRep();
    }

    @Override
    public final ChessBoard newInstance() {
        try {
            return this.getClass().getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type " + this.getClass());
        }
    }

    @Override
    public final void clear() {
        this.createNewRep();
    }

    @Override
    public final void transferFrom(ChessBoard source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof ChessBoard1L : ""
                + "Violation of: source is of dynamic type ChessBoard1L";

        ChessBoard1L localSource = (ChessBoard1L) source;
        this.pieces = localSource.pieces;
        this.occupied = localSource.occupied;
        this.currentTurn = localSource.currentTurn;
        localSource.createNewRep();
    }

    @Override
    public final void setPiece(int row, int col, ChessPiece piece) {
        assert row >= 0 && row < BOARD_SIZE : "Violation of: row out of bounds";
        assert col >= 0 && col < BOARD_SIZE : "Violation of: col out of bounds";
        assert piece != null : "Violation of: piece is not null";

        this.pieces[row][col] = piece;
        this.occupied[row][col] = true;
    }

    @Override
    public final void clearSquare(int row, int col) {
        assert row >= 0 && row < BOARD_SIZE : "Violation of: row out of bounds";
        assert col >= 0 && col < BOARD_SIZE : "Violation of: col out of bounds";

        this.occupied[row][col] = false;
    }

    @Override
    public final ChessPiece pieceAt(int row, int col) {
        assert row >= 0 && row < BOARD_SIZE : "Violation of: row out of bounds";
        assert col >= 0 && col < BOARD_SIZE : "Violation of: col out of bounds";
        assert this.occupied[row][col] : "Violation of: square is not empty";

        return this.pieces[row][col];
    }

    @Override
    public final ChessPiece.Color turn() {
        assert this.currentTurn != null : "Violation of: currentTurn is not null";
        return this.currentTurn;
    }

    @Override
    public final void toggleTurn() {
        if (this.currentTurn == ChessPiece.Color.WHITE) {
            this.currentTurn = ChessPiece.Color.BLACK;
        } else {
            this.currentTurn = ChessPiece.Color.WHITE;
        }
    }

    @Override
    public final boolean isSquareEmpty(int row, int col) {
        assert row >= 0 && row < BOARD_SIZE : "Violation of: row out of bounds";
        assert col >= 0 && col < BOARD_SIZE : "Violation of: col out of bounds";

        return !this.occupied[row][col];
    }
}
