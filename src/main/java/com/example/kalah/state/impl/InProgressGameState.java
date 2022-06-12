package com.example.kalah.state.impl;

import com.example.kalah.exception.IllegalKalahMoveException;
import com.example.kalah.exception.UnsupportedKalahOperationException;
import com.example.kalah.model.GameBoard;
import com.example.kalah.model.Side;
import com.example.kalah.model.State;
import com.example.kalah.state.AbstractGameState;
import com.example.kalah.state.GameState;
import com.example.kalah.state.GameStateFactory;

public class InProgressGameState extends AbstractGameState {

    Side side;

    public InProgressGameState(Side side) {
        this.side = side;
    }

    @Override
    public State getCurrentState() {
        return State.INPROGRESS;
    }

    public Side getSide() {
        return side;
    }

    @Override
    public GameState play(GameBoard gameBoard, int pit) throws IllegalKalahMoveException, UnsupportedKalahOperationException {
        int index;
        switch (side) {
            case NORTH:
                validateMove(Side.NORTH, gameBoard, pit);
                index = sow(gameBoard, SOUTH_KALAH, pit);
                if (index == NORTH_KALAH) {
                    return isGameOver(gameBoard) ? GameStateFactory.getGameState(State.END) : GameStateFactory.getGameState(Side.NORTH);
                } else if (index > SOUTH_KALAH) {
                    int[] board = gameBoard.getBoard();
                    if (board[index] == 1) {
                        int stolenStones = board[12 - index];
                        board[12 - index] = 0;
                        board[index] = 0;
                        board[NORTH_KALAH] = board[NORTH_KALAH] + stolenStones + 1;
                        gameBoard.setBoard(board);
                    }
                    return isGameOver(gameBoard) ? GameStateFactory.getGameState(State.END) : GameStateFactory.getGameState(Side.SOUTH);
                } else {
                    return isGameOver(gameBoard) ? GameStateFactory.getGameState(State.END) : GameStateFactory.getGameState(Side.SOUTH);
                }
            case SOUTH:
                validateMove(Side.SOUTH, gameBoard, pit);
                index = sow(gameBoard, NORTH_KALAH, pit);
                if (index == SOUTH_KALAH) {
                    return isGameOver(gameBoard) ? GameStateFactory.getGameState(State.END) : GameStateFactory.getGameState(Side.SOUTH);
                } else if (index < SOUTH_KALAH) {
                    int[] board = gameBoard.getBoard();
                    if (board[index] == 1) {
                        int stolenStones = board[12 - index];
                        board[12 - index] = 0;
                        board[index] = 0;
                        board[SOUTH_KALAH] = board[SOUTH_KALAH] + stolenStones + 1;
                        gameBoard.setBoard(board);
                    }
                    return isGameOver(gameBoard) ? GameStateFactory.getGameState(State.END) : GameStateFactory.getGameState(Side.NORTH);
                } else {
                    return isGameOver(gameBoard) ? GameStateFactory.getGameState(State.END) : GameStateFactory.getGameState(Side.NORTH);
                }
            default:
                throw new IllegalKalahMoveException("Illegal move");
        }

    }
}
