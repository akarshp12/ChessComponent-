import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.chessboard.ChessBoard;
import components.chessboard.ChessBoard1L;
import components.chessboard.ChessPiece;

/**
 * Comprehensive JUnit test suite for the ChessBoard component.
 */
public class ChessBoardComponentTest {

    private ChessBoard constructorTest() {
        return new ChessBoard1L();
    }

    @Test
    public void testConstructorTurn() {
        ChessBoard board = this.constructorTest();
        assertEquals(ChessPiece.Color.WHITE, board.turn());
    }

    @Test
    public void testConstructorBoardEmpty() {
        ChessBoard board = this.constructorTest();
        assertTrue(board.isBoardEmpty());
    }

    @Test
    public void testConstructorPieceCount() {
        ChessBoard board = this.constructorTest();
        assertEquals(0, board.pieceCount());
    }

    @Test
    public void testConstructorAllSquaresEmpty() {
        ChessBoard board = this.constructorTest();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                assertTrue(board.isSquareEmpty(r, c));
            }
        }
    }

    @Test
    public void testSetAndGetPieceTopLeft() {
        ChessBoard board = this.constructorTest();
        ChessPiece rook = new ChessPiece(ChessPiece.Color.WHITE,
                ChessPiece.Type.ROOK);
        board.setPiece(0, 0, rook);
        assertFalse(board.isSquareEmpty(0, 0));
        assertEquals(ChessPiece.Type.ROOK, board.pieceAt(0, 0).type());
        assertEquals(ChessPiece.Color.WHITE, board.pieceAt(0, 0).color());
    }

    @Test
    public void testSetAndGetPieceBottomRight() {
        ChessBoard board = this.constructorTest();
        ChessPiece king = new ChessPiece(ChessPiece.Color.BLACK,
                ChessPiece.Type.KING);
        board.setPiece(7, 7, king);
        assertFalse(board.isSquareEmpty(7, 7));
        assertEquals(ChessPiece.Type.KING, board.pieceAt(7, 7).type());
        assertEquals(ChessPiece.Color.BLACK, board.pieceAt(7, 7).color());
    }

    @Test
    public void testSetAndGetPieceCenter() {
        ChessBoard board = this.constructorTest();
        ChessPiece queen = new ChessPiece(ChessPiece.Color.WHITE,
                ChessPiece.Type.QUEEN);
        board.setPiece(4, 4, queen);
        assertFalse(board.isSquareEmpty(4, 4));
        assertEquals(ChessPiece.Type.QUEEN, board.pieceAt(4, 4).type());
    }

    @Test
    public void testClearSquare() {
        ChessBoard board = this.constructorTest();
        ChessPiece pawn = new ChessPiece(ChessPiece.Color.WHITE,
                ChessPiece.Type.PAWN);
        board.setPiece(3, 3, pawn);
        assertFalse(board.isSquareEmpty(3, 3));
        board.clearSquare(3, 3);
        assertTrue(board.isSquareEmpty(3, 3));
    }

    @Test
    public void testToggleTurnOnce() {
        ChessBoard board = this.constructorTest();
        board.toggleTurn();
        assertEquals(ChessPiece.Color.BLACK, board.turn());
    }

    @Test
    public void testToggleTurnTwice() {
        ChessBoard board = this.constructorTest();
        board.toggleTurn();
        board.toggleTurn();
        assertEquals(ChessPiece.Color.WHITE, board.turn());
    }

    @Test
    public void testClearMethod() {
        ChessBoard board = this.constructorTest();
        board.setPiece(1, 1,
                new ChessPiece(ChessPiece.Color.WHITE, ChessPiece.Type.BISHOP));
        board.toggleTurn();
        board.clear();
        assertTrue(board.isBoardEmpty());
        assertEquals(0, board.pieceCount());
        assertEquals(ChessPiece.Color.WHITE, board.turn());
    }

    @Test
    public void testNewInstanceMethod() {
        ChessBoard board = this.constructorTest();
        board.setPiece(2, 2,
                new ChessPiece(ChessPiece.Color.WHITE, ChessPiece.Type.KNIGHT));
        ChessBoard fresh = board.newInstance();
        assertTrue(fresh.isBoardEmpty());
        assertEquals(0, fresh.pieceCount());
        assertEquals(ChessPiece.Color.WHITE, fresh.turn());
    }

    @Test
    public void testTransferFromMethod() {
        ChessBoard source = this.constructorTest();
        source.setPiece(5, 5,
                new ChessPiece(ChessPiece.Color.BLACK, ChessPiece.Type.ROOK));
        ChessBoard recipient = this.constructorTest();
        recipient.transferFrom(source);
        assertFalse(recipient.isBoardEmpty());
        assertEquals(1, recipient.pieceCount());
        assertEquals(ChessPiece.Type.ROOK, recipient.pieceAt(5, 5).type());
        assertTrue(source.isBoardEmpty());
        assertEquals(0, source.pieceCount());
    }

    @Test
    public void testOverwritePiece() {
        ChessBoard board = this.constructorTest();
        board.setPiece(0, 0,
                new ChessPiece(ChessPiece.Color.WHITE, ChessPiece.Type.PAWN));
        board.setPiece(0, 0,
                new ChessPiece(ChessPiece.Color.BLACK, ChessPiece.Type.QUEEN));
        assertEquals(1, board.pieceCount());
        assertEquals(ChessPiece.Type.QUEEN, board.pieceAt(0, 0).type());
        assertEquals(ChessPiece.Color.BLACK, board.pieceAt(0, 0).color());
    }

    @Test
    public void testMultiplePiecesPlacement() {
        ChessBoard board = this.constructorTest();
        board.setPiece(0, 0,
                new ChessPiece(ChessPiece.Color.WHITE, ChessPiece.Type.ROOK));
        board.setPiece(0, 1,
                new ChessPiece(ChessPiece.Color.WHITE, ChessPiece.Type.KNIGHT));
        board.setPiece(0, 2,
                new ChessPiece(ChessPiece.Color.WHITE, ChessPiece.Type.BISHOP));
        assertEquals(3, board.pieceCount());
        assertFalse(board.isBoardEmpty());
    }

    @Test
    public void testToggleTurnMultipleCycles() {
        ChessBoard board = this.constructorTest();
        for (int i = 0; i < 5; i++) {
            board.toggleTurn();
        }
        assertEquals(ChessPiece.Color.BLACK, board.turn());
    }

    @Test
    public void testClearSquareOnEmptySquare() {
        ChessBoard board = this.constructorTest();
        assertTrue(board.isSquareEmpty(4, 4));
        board.clearSquare(4, 4);
        assertTrue(board.isSquareEmpty(4, 4));
        assertEquals(0, board.pieceCount());
    }

    @Test
    public void testPieceCountAfterOperations() {
        ChessBoard board = this.constructorTest();
        board.setPiece(1, 1,
                new ChessPiece(ChessPiece.Color.WHITE, ChessPiece.Type.PAWN));
        board.setPiece(2, 2,
                new ChessPiece(ChessPiece.Color.BLACK, ChessPiece.Type.PAWN));
        assertEquals(2, board.pieceCount());
        board.clearSquare(1, 1);
        assertEquals(1, board.pieceCount());
    }

    @Test
    public void testIsBoardEmptyTrueInitially() {
        ChessBoard board = this.constructorTest();
        assertTrue(board.isBoardEmpty());
    }

    @Test
    public void testIsBoardEmptyFalseWhenPieceAdded() {
        ChessBoard board = this.constructorTest();
        board.setPiece(0, 0,
                new ChessPiece(ChessPiece.Color.WHITE, ChessPiece.Type.KING));
        assertFalse(board.isBoardEmpty());
    }

    @Test
    public void testIsBoardEmptyTrueAfterClearingAll() {
        ChessBoard board = this.constructorTest();
        board.setPiece(0, 0,
                new ChessPiece(ChessPiece.Color.WHITE, ChessPiece.Type.KING));
        board.removeAllPieces();
        assertTrue(board.isBoardEmpty());
    }

    @Test
    public void testPieceCountZeroInitially() {
        ChessBoard board = this.constructorTest();
        assertEquals(0, board.pieceCount());
    }

    @Test
    public void testPieceCountMultiplePieces() {
        ChessBoard board = this.constructorTest();
        board.setPiece(0, 0,
                new ChessPiece(ChessPiece.Color.WHITE, ChessPiece.Type.ROOK));
        board.setPiece(0, 7,
                new ChessPiece(ChessPiece.Color.WHITE, ChessPiece.Type.ROOK));
        board.setPiece(7, 0,
                new ChessPiece(ChessPiece.Color.BLACK, ChessPiece.Type.ROOK));
        assertEquals(3, board.pieceCount());
    }

    @Test
    public void testRemoveAllPiecesFunctionality() {
        ChessBoard board = this.constructorTest();
        board.setPiece(3, 3,
                new ChessPiece(ChessPiece.Color.WHITE, ChessPiece.Type.QUEEN));
        board.setPiece(4, 4,
                new ChessPiece(ChessPiece.Color.BLACK, ChessPiece.Type.QUEEN));
        assertEquals(2, board.pieceCount());
        board.removeAllPieces();
        assertEquals(0, board.pieceCount());
        assertTrue(board.isBoardEmpty());
    }

    @Test
    public void testToStringContainsTurn() {
        ChessBoard board = this.constructorTest();
        String s = board.toString();
        assertTrue(s.contains("Turn: WHITE"));
    }

    @Test
    public void testToStringContainsPieceSymbols() {
        ChessBoard board = this.constructorTest();
        board.setPiece(0, 0,
                new ChessPiece(ChessPiece.Color.WHITE, ChessPiece.Type.ROOK));
        String s = board.toString();
        assertTrue(s.contains("r"));
    }

    @Test
    public void testToStringBlackPieceSymbol() {
        ChessBoard board = this.constructorTest();
        board.setPiece(0, 0,
                new ChessPiece(ChessPiece.Color.BLACK, ChessPiece.Type.QUEEN));
        String s = board.toString();
        assertTrue(s.contains("Q"));
    }

    @Test
    public void testIsBoardEmptyFullBoard() {
        ChessBoard board = this.constructorTest();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                board.setPiece(r, c, new ChessPiece(ChessPiece.Color.WHITE,
                        ChessPiece.Type.PAWN));
            }
        }
        assertFalse(board.isBoardEmpty());
        assertEquals(64, board.pieceCount());
    }

    @Test
    public void testRemoveAllPiecesOnFullBoard() {
        ChessBoard board = this.constructorTest();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                board.setPiece(r, c, new ChessPiece(ChessPiece.Color.WHITE,
                        ChessPiece.Type.PAWN));
            }
        }
        board.removeAllPieces();
        assertTrue(board.isBoardEmpty());
        assertEquals(0, board.pieceCount());
    }

    @Test
    public void testPieceCountAfterSingleRemoval() {
        ChessBoard board = this.constructorTest();
        board.setPiece(0, 0,
                new ChessPiece(ChessPiece.Color.WHITE, ChessPiece.Type.KING));
        board.setPiece(0, 1,
                new ChessPiece(ChessPiece.Color.WHITE, ChessPiece.Type.QUEEN));
        board.clearSquare(0, 0);
        assertEquals(1, board.pieceCount());
    }

    @Test
    public void testToStringEmptyBoardGrid() {
        ChessBoard board = this.constructorTest();
        String s = board.toString();
        assertTrue(s.contains(". . . . . . . ."));
    }

    @Test
    public void testToStringAfterTurnToggle() {
        ChessBoard board = this.constructorTest();
        board.toggleTurn();
        String s = board.toString();
        assertTrue(s.contains("Turn: BLACK"));
    }

    @Test
    public void testRemoveAllPiecesOnAlreadyEmpty() {
        ChessBoard board = this.constructorTest();
        board.removeAllPieces();
        assertTrue(board.isBoardEmpty());
        assertEquals(0, board.pieceCount());
    }

    @Test
    public void testPieceCountMaxCapacity() {
        ChessBoard board = this.constructorTest();
        int count = 0;
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                board.setPiece(r, c, new ChessPiece(ChessPiece.Color.BLACK,
                        ChessPiece.Type.PAWN));
                count++;
            }
        }
        assertEquals(count, board.pieceCount());
    }

    @Test
    public void testIsBoardEmptySinglePieceRemoval() {
        ChessBoard board = this.constructorTest();
        board.setPiece(7, 7,
                new ChessPiece(ChessPiece.Color.WHITE, ChessPiece.Type.KING));
        board.clearSquare(7, 7);
        assertTrue(board.isBoardEmpty());
    }

    @Test
    public void testToStringMultiplePieceLayout() {
        ChessBoard board = this.constructorTest();
        board.setPiece(0, 4,
                new ChessPiece(ChessPiece.Color.WHITE, ChessPiece.Type.KING));
        board.setPiece(7, 4,
                new ChessPiece(ChessPiece.Color.BLACK, ChessPiece.Type.KING));
        String s = board.toString();
        assertTrue(s.contains("k"));
        assertTrue(s.contains("K"));
    }

    @Test
    public void testPieceCountMixedOperations() {
        ChessBoard board = this.constructorTest();
        board.setPiece(0, 0,
                new ChessPiece(ChessPiece.Color.WHITE, ChessPiece.Type.ROOK));
        board.setPiece(0, 1,
                new ChessPiece(ChessPiece.Color.WHITE, ChessPiece.Type.KNIGHT));
        board.clearSquare(0, 0);
        board.setPiece(0, 2,
                new ChessPiece(ChessPiece.Color.WHITE, ChessPiece.Type.BISHOP));
        assertEquals(2, board.pieceCount());
    }

    @Test
    public void testSecondaryMethodsConsistency() {
        ChessBoard board = this.constructorTest();
        assertTrue(board.isBoardEmpty());
        assertEquals(0, board.pieceCount());
        board.setPiece(1, 1,
                new ChessPiece(ChessPiece.Color.WHITE, ChessPiece.Type.PAWN));
        assertFalse(board.isBoardEmpty());
        assertEquals(1, board.pieceCount());
        board.removeAllPieces();
        assertTrue(board.isBoardEmpty());
        assertEquals(0, board.pieceCount());
    }
}
