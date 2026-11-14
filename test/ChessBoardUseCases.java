package test;

import components.chessboard.ChessBoard;
import components.chessboard.ChessBoard1L;
import components.chessboard.ChessPiece;

public final class ChessBoardUseCases {

    private ChessBoardUseCases() {
    }

    public static void main(String[] args) {
        ChessBoard endgameBoard = new ChessBoard1L();
        ChessPiece whiteKing = new ChessPiece(ChessPiece.Color.WHITE, ChessPiece.Type.KING);
        ChessPiece blackKing = new ChessPiece(ChessPiece.Color.BLACK, ChessPiece.Type.KING);
        ChessPiece whiteQueen = new ChessPiece(ChessPiece.Color.WHITE, ChessPiece.Type.QUEEN);

        endgameBoard.setPiece(7, 4, whiteKing);
        endgameBoard.setPiece(0, 4, blackKing);
        endgameBoard.setPiece(6, 3, whiteQueen);

        System.out.println("=== Use Case 1: Endgame Setup ===");
        System.out.println("Total active pieces: " + endgameBoard.pieceCount());
        System.out.println("Is board empty? " + endgameBoard.isBoardEmpty());
        System.out.println(endgameBoard.toString());

        ChessBoard puzzleBoard = new ChessBoard1L();
        ChessPiece knight = new ChessPiece(ChessPiece.Color.WHITE, ChessPiece.Type.KNIGHT);

        puzzleBoard.setPiece(2, 2, knight);
        puzzleBoard.toggleTurn();

        System.out.println("=== Use Case 2: Custom Puzzle State ===");
        System.out.println("Current Turn: " + puzzleBoard.turn());
        System.out.println("Is square (2,2) empty? " + puzzleBoard.isSquareEmpty(2, 2));
        System.out.println("Piece at (2,2) type: " + puzzleBoard.pieceAt(2, 2).type());
        System.out.println(puzzleBoard.toString());
    }
}
