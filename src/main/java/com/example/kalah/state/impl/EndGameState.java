package com.example.kalah.state.impl;

import com.example.kalah.model.GameBoard;
import com.example.kalah.model.State;
import com.example.kalah.state.AbstractGameState;

import java.util.Arrays;
import java.util.stream.IntStream;

public class EndGameState extends AbstractGameState {

    @Override
    public State getCurrentState() {
        return State.END;
    }

    @Override
    public GameBoard endGame(GameBoard gameBoard) {
        int[] board = gameBoard.getBoard();

        int southLeftovers = IntStream.of(Arrays.copyOfRange(board, 0, SOUTH_KALAH)).sum();
        int northLeftovers = IntStream.of(Arrays.copyOfRange(board, 7, NORTH_KALAH)).sum();

        int northScore = board[NORTH_KALAH] + northLeftovers;
        int southScore = board[SOUTH_KALAH] + southLeftovers;

        board = new int[]{0, 0, 0, 0, 0, 0, southScore, 0, 0, 0, 0, 0, 0, northScore};
        gameBoard.setBoard(board);
        return gameBoard;
    }
}
