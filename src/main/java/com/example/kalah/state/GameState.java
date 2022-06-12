package com.example.kalah.state;

import com.example.kalah.exception.IllegalKalahMoveException;
import com.example.kalah.exception.UnsupportedKalahOperationException;
import com.example.kalah.model.GameBoard;
import com.example.kalah.model.Side;
import com.example.kalah.model.State;

public interface GameState {

    /**
     * Gets current state of the game
     *
     * @return returns @State enum, according to game's current state
     */
    State getCurrentState();

    /**
     * Initializes the game by creating a fresh @Game instance
     *
     * @return returns the instance created
     * @throws UnsupportedKalahOperationException Thrown when an unfinished game already exists.
     */
    GameBoard initializeGame() throws UnsupportedKalahOperationException;

    /**
     * Plays from the pit specified, on the game specified.
     *
     * @param gameBoard instance of the current game
     * @param pit  id of the pit
     * @return returns what comes next: South plays - North plays - or the game is over.
     * @throws UnsupportedKalahOperationException Thrown when game isn't in a state where you can make a move.
     * @throws IllegalKalahMoveException          Thrown when specified move is illegal.
     */
    GameState play(GameBoard gameBoard, int pit) throws UnsupportedKalahOperationException, IllegalKalahMoveException;

    /**
     * Finishes the game when no stone left in one of sides by counting leftover stones.
     *
     * @param gameBoard instance of the current game.
     * @return returns the finished game.
     * @throws UnsupportedKalahOperationException Thrown when game isn't ready to be finished.
     */
    GameBoard endGame(GameBoard gameBoard) throws UnsupportedKalahOperationException;

    /**
     * Returns the side of who won the game. Returns both sides if it's a draw.
     *
     * @return Side of the winner(s).
     * @throws UnsupportedKalahOperationException Thrown when the winner isn't decided yet.
     */
    Side[] declareWinner(GameBoard gameBoard) throws UnsupportedKalahOperationException;
}
