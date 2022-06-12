package com.example.kalah.state;

import com.example.kalah.exception.UnsupportedKalahOperationException;
import com.example.kalah.model.Side;
import com.example.kalah.model.State;
import com.example.kalah.state.impl.EndGameState;
import com.example.kalah.state.impl.FinishedGameState;
import com.example.kalah.state.impl.InProgressGameState;
import com.example.kalah.state.impl.NewGameState;

public class GameStateFactory {

    public static GameState getGameState(State state) throws UnsupportedKalahOperationException {
        if (state != null) {
            switch (state) {
                case NEW:
                    return new NewGameState();
                case END:
                    return new EndGameState();
                case FINISHED:
                    return new FinishedGameState();
            }
        }
        throw new UnsupportedKalahOperationException("Requested an unknown state from GameStateFactory.");
    }

    public static GameState getGameState(Side side) throws UnsupportedKalahOperationException {
        if (side != null) {
            switch (side) {
                case SOUTH:
                    return new InProgressGameState(Side.SOUTH);
                case NORTH:
                    return new InProgressGameState(Side.NORTH);
            }
        }
        throw new UnsupportedKalahOperationException("Requested an unknown state from GameStateFactory.");
    }

}
