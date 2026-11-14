package components.chessboard;

import components.standard.Standard;

/**
 * {@code ChessBoard} enhanced component interface representing an 8x8 chess board 
 * state, player turn, and piece placements.
 * 
 * @mathsubtypes <p>
 *               {@code ChessBoard} modeled as a board of size 8x8 containing optional 
 *               {@code ChessPiece} entries and an active player turn {@code Color}.
 *               </p>
 * @mathmodel <p>
 *            this = (grid: string[8][8] of ChessPiece, turn: Color)
 *            </p>
 */
public interface ChessBoard extends Standard<ChessBoard> {

    /**
     * Places a piece on the board at the specified coordinate.
     * 
     * @param row
     *            the row index (0 to 7)
     * @param col
     *            the column index (0 to 7)
     * @param piece
     *            the chess piece to set
     * @updates this
     * @requires 0 <= row and row < 8 and 0 <= col and col < 8 and piece is not null
     * @ensures pieceAt(row, col) = piece and !isSquareEmpty(row, col)
     */
    void setPiece(int row, int col, ChessPiece piece);

    /**
     * Clears any piece at the specified board coordinate.
     * 
     * @param row
     *            the row index (0 to 7)
     * @param col
     *            the column index (0 to 7)
     * @updates this
     * @requires 0 <= row and row < 8 and 0 <= col and col < 8
     * @ensures isSquareEmpty(row, col)
     */
    void clearSquare(int row, int col);

    /**
     * Returns the piece at the specified coordinate.
     * 
     * @param row
     *            the row index (0 to 7)
     * @param col
     *            the column index (0 to 7)
     * @return the chess piece at the location
     * @requires 0 <= row and row < 8 and 0 <= col and col < 8 and !isSquareEmpty(row, col)
     * @ensures pieceAt = [piece at (row, col)]
     */
    ChessPiece pieceAt(int row, int col);

    /**
     * Returns the current player turn color.
     * 
     * @return the current turn color (WHITE or BLACK)
     * @ensures turn = [active player color]
     */
    ChessPiece.Color turn();

    /**
     * Switches the active player turn.
     * 
     * @updates this
     * @ensures turn = [opposite of old turn]
     */
    void toggleTurn();

    /**
     * Reports whether the specified square is empty.
     * 
     * @param row
     *            the row index (0 to 7)
     * @param col
     *            the column index (0 to 7)
     * @return true if empty, false otherwise
     * @requires 0 <= row and row < 8 and 0 <= col and col < 8
     * @ensures isSquareEmpty = [true if square has no piece, false otherwise]
     */
    boolean isSquareEmpty(int row, int col);

    /**
     * Reports whether the entire board has zero pieces.
     * 
     * @return true if board is empty, false otherwise
     * @ensures isBoardEmpty = [true if all squares are empty]
     */
    boolean isBoardEmpty();

    /**
     * Returns the total count of active pieces on the board.
     * 
     * @return the piece count
     * @ensures pieceCount = [number of non-empty squares]
     */
    int pieceCount();

    /**
     * Clears all pieces from the board.
     * 
     * @updates this
     * @ensures isBoardEmpty() and pieceCount() = 0
     */
    void removeAllPieces();
}
