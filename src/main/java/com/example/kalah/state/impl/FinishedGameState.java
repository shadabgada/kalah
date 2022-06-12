package com.example.kalah.state.impl;


import com.example.kalah.model.GameBoard;
import com.example.kalah.model.Side;
import com.example.kalah.model.State;
import com.example.kalah.state.AbstractGameState;

public class FinishedGameState extends AbstractGameState {
    @Override
    public State getCurrentState() {
        return State.FINISHED;
    }

    @Override
    public GameBoard initializeGame() {
        return new GameBoard();
    }

    @Override
    public Side[] declareWinner(GameBoard gameBoard) {
        int[] board = gameBoard.getBoard();

        if (board[SOUTH_KALAH] > board[NORTH_KALAH]) {
            return new Side[]{Side.SOUTH};
        } else if (board[NORTH_KALAH] > board[SOUTH_KALAH]) {
            return new Side[]{Side.NORTH};
        } else {
            return new Side[]{Side.SOUTH, Side.NORTH};
        }
    }
}
