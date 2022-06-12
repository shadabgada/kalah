package com.example.kalah.service.impl;

import com.example.kalah.exception.IllegalKalahMoveException;
import com.example.kalah.exception.UnsupportedKalahOperationException;
import com.example.kalah.model.GameBoard;
import com.example.kalah.model.Side;
import com.example.kalah.model.State;
import com.example.kalah.service.GameService;
import com.example.kalah.state.GameState;
import com.example.kalah.state.GameStateFactory;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {

    private GameState gameState;
    private GameBoard gameBoard;

    public GameServiceImpl() throws UnsupportedKalahOperationException {
        gameState = GameStateFactory.getGameState(State.NEW);
    }

    @Override
    public GameState getCurrentState() {
        return gameState;
    }

    @Override
    public int[] getBoard() throws UnsupportedKalahOperationException {
        if (gameBoard == null) {
            throw new UnsupportedKalahOperationException("Game isn't started yet");
        } else {
            return gameBoard.getBoard();
        }
    }

    @Override
    public synchronized void startNewGame() throws UnsupportedKalahOperationException {
        gameBoard = gameState.initializeGame();
        gameState = GameStateFactory.getGameState(Side.SOUTH);
    }

    @Override
    public synchronized void play(int pit) throws IllegalKalahMoveException, UnsupportedKalahOperationException {
        gameState = gameState.play(gameBoard, pit);
        if (gameState.getCurrentState() == State.END) {
            endGame();
        }
    }

    @Override
    public Side[] declareWinner() throws UnsupportedKalahOperationException {
        return gameState.declareWinner(gameBoard);
    }

    /**
     * Since State.END is a transition state before State.FINISHED, there is no direct access to this method
     *
     * @throws UnsupportedKalahOperationException thrown when there are stones in both sides
     */
    private void endGame() throws UnsupportedKalahOperationException {
        gameBoard = gameState.endGame(gameBoard);
        gameState = GameStateFactory.getGameState(State.FINISHED);
    }
}
