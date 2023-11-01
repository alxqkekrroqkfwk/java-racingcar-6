package racingcar.service;

import racingcar.domain.Car;
import racingcar.domain.CarName;
import racingcar.domain.Winner;
import racingcar.utils.RandomNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GameService {

    private final List<Car> cars = new ArrayList<>();
    private int maxPosition;

    public GameService(List<String> carNames) {
        carNames.forEach(carName -> cars.add(new Car(new CarName(carName))));
    }

    public List<Car> getCars() {
        return cars;
    }

    public void moveCar() {
        cars.forEach(car -> car.move(RandomNumber.createRandomNumber()));
    }

    public Winner findWinners() {
        findMaxPosition();
        return findWinnerCar();
    }

    private void findMaxPosition() {
        this.maxPosition = cars.stream()
                .map(Car::getPosition)
                .max(Integer::compareTo)
                .orElse(Integer.MIN_VALUE); // 예외처리하기
    }

    private Winner findWinnerCar() {
        List<String> winnerCarNames = cars.stream()
                .filter(car -> car.getPosition() == maxPosition)
                .map(Car::getName)
                .collect(Collectors.toList());

        return new Winner(winnerCarNames);
    }
}
