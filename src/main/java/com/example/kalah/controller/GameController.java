package com.example.kalah.controller;

import com.example.kalah.exception.KalahException;
import com.example.kalah.exception.UnsupportedKalahOperationException;
import com.example.kalah.service.GameService;
import com.example.kalah.state.GameState;
import com.example.kalah.state.impl.InProgressGameState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RestController
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/")
    public @ResponseBody
    ModelAndView getBoard() {
        Map<String, Object> model = new HashMap<>();
        GameState gameState = gameService.getCurrentState();
        model.put("state", gameState.getCurrentState().name());

        if (gameState instanceof InProgressGameState)
            model.put("side", ((InProgressGameState) gameState).getSide().name());

        try {
            model.put("board", gameService.getBoard());
        } catch (UnsupportedKalahOperationException e) {
            model.put("board", null);
        }
        try {
            model.put("winner", gameService.declareWinner());
        } catch (UnsupportedKalahOperationException e) {
            model.put("winner", null);
        }

        return new ModelAndView("home", model);
    }

    @PostMapping("/new")
    public @ResponseBody
    ResponseEntity newGame() {
        try {
            gameService.startNewGame();
            return ResponseEntity.ok().build();
        } catch (UnsupportedKalahOperationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/play/{pit}")
    public @ResponseBody
    ResponseEntity play(@PathVariable("pit") int pit) {
        try {
            gameService.play(pit);
            return ResponseEntity.ok().build();
        } catch (KalahException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}