package com.example.kalah.state;

import com.example.kalah.exception.IllegalKalahMoveException;
import com.example.kalah.exception.UnsupportedKalahOperationException;
import com.example.kalah.model.GameBoard;
import com.example.kalah.model.Side;

import java.util.Arrays;
import java.util.stream.IntStream;

public abstract class AbstractGameState implements GameState {
    protected final int SOUTH_KALAH = 6;
    protected final int NORTH_KALAH = 13;

    @Override
    public GameBoard initializeGame() throws UnsupportedKalahOperationException {
        throw new UnsupportedKalahOperationException("You can't initialize a new game now.");
    }

    @Override
    public GameState play(GameBoard gameBoard, int pit) throws UnsupportedKalahOperationException, IllegalKalahMoveException {
        throw new UnsupportedKalahOperationException("You can't play now.");
    }

    @Override
    public GameBoard endGame(GameBoard gameBoard) throws UnsupportedKalahOperationException {
        throw new UnsupportedKalahOperationException("You can't end game now. There are still stones in both sides.");
    }

    @Override
    public Side[] declareWinner(GameBoard gameBoard) throws UnsupportedKalahOperationException {
        throw new UnsupportedKalahOperationException("Game isn't finished yet.");
    }

    protected void validateMove(Side side, GameBoard gameBoard, int pit) throws IllegalKalahMoveException {
        if (pit < 0 || pit == SOUTH_KALAH || pit >= NORTH_KALAH) {
            throw new IllegalKalahMoveException("Invalid pit value");
        } else if (pit < SOUTH_KALAH && side == Side.NORTH || (pit > SOUTH_KALAH && side == Side.SOUTH)) {
            throw new IllegalKalahMoveException("You can't play from your opponent's pits.");
        } else {
            int[] board = gameBoard.getBoard();
            if (board[pit] == 0) {
                throw new IllegalKalahMoveException("There's no stone in pit " + pit + ".");
            }
        }
    }

    /**
     * Sows stones counter clockwise.
     *
     * @param gameBoard          Current game instance.
     * @param opponentKalah Opponent's kalah ID.
     * @param pit           Which pit player is starting to sow stones from.
     * @return Returns the ID of the pit where it puts its last stone.
     */
    protected int sow(GameBoard gameBoard, int opponentKalah, int pit) {
        int[] board = gameBoard.getBoard();
        int stoneCount = board[pit];
        int currentIndex = pit;

        board[pit] = 0;
        while (stoneCount > 0) {
            currentIndex = (currentIndex + 1) % 14;
            if (currentIndex != opponentKalah) {
                board[currentIndex]++;
                stoneCount--;
            }
        }
        gameBoard.setBoard(board);
        return currentIndex;
    }

    /**
     * Returns if game is over. Checks if there are stones either sides.
     *
     * @param gameBoard Current game instance.
     * @return Returns if the game is over or not.
     */
    protected boolean isGameOver(GameBoard gameBoard) {
        int[] board = gameBoard.getBoard();
        int[] south = Arrays.copyOfRange(board, 0, SOUTH_KALAH);
        int[] north = Arrays.copyOfRange(board, 7, NORTH_KALAH);
        return IntStream.of(south).allMatch(x -> x == 0) || IntStream.of(north).allMatch(x -> x == 0);
    }
}
