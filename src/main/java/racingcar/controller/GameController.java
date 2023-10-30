package racingcar.controller;

import racingcar.domain.GameManager;
import racingcar.domain.Winner;
import racingcar.view.OutputView;

public class GameController {

    private GameManager gameManager;
    private int tryCount;

    public void gameSetInput() {
        this.gameManager = new GameManager(ViewController.setCars());
        this.tryCount = ViewController.setTryCount();
    }

    public void playRacing() {
        for (int i=0; i<tryCount; i++) {
            gameManager.moveCar();
            OutputView.showCarNameAndPosition(gameManager.getCars());
        }
    }

    public void showWinnerCars() {
        Winner winner = gameManager.findWinnerCar();
        OutputView.finalWinnerMessage(winner);
    }
}