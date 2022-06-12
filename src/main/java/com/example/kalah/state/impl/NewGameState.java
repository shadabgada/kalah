package com.example.kalah.state.impl;


import com.example.kalah.model.GameBoard;
import com.example.kalah.model.State;
import com.example.kalah.state.AbstractGameState;

public class NewGameState extends AbstractGameState {

    @Override
    public State getCurrentState() {
        return State.NEW;
    }

    @Override
    public GameBoard initializeGame() {
        return new GameBoard();
    }
}
