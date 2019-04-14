package com.company;

import java.util.*;
import java.util.regex.Pattern;

import static com.company.Computer.*;

public class BaseBallGame {
    private static final Scanner SCAN = new Scanner(System.in);
    private static final String INPUT_BOUND_ERROR_MESSAGE = "1-9사이의 범위만 입력해주세요.";
    private static final String INPUT_LENGTH_ERROR_MESSAGE = "3개의 숫자만 입력해주세요.";
    private static final String INPUT_INTEGER_ERROR_MESSAGE = "문자가 입력되었습니다.";
    private static final String INPUT_EMPTY_ERROR_MESSAGE = "값이 입력되지 않았습니다.";
    private static final String INPUT_CONTAIN_ERROR_MESSAGE = "값이 중복되었습니다.";
    private static final String WINNER_MESSAGE = "3개의 숫자를 모두 맞히셨습니다! 게임 종료";
    private static final String SELECT_MESSAGE = "게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.";
    private static final String INPUT_MESSAGE = "숫자를 입력해주세요 : ";
    private static final String STRIKE_MESSAGE = "스트라이크";
    private static final String BALL_MESSAGE = "볼";
    private static final String NOTHING_MESSAGE = "낫싱";
    private static final int REPLAY = 1;
    private static final String PATTERN = "^[0-9]*$";
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 9;
    private static final int ONE_ROUND_BALL_LENGTH = 3;

    public void run() {
        while (round()) ;
    }

    private boolean round() {
        Computer computer = new Computer(new Ball(getComputerBallList()));

        oneRound(computer);

        return isRelpay();
    }

    private void oneRound(Computer computer) {
        while (true) {
            Ball ball = new Ball(getUserList());
            Map<String, Integer> map = computer.checkResult(ball);

            printResult(map);

            if (getWin(map)) {
                break;
            }
        }
    }

    private boolean checkNumber(String input) {
        if (Integer.parseInt(input) != 1 && Integer.parseInt(input) != 2) {
            System.err.println("0과 1만 입력해주세요.");
            return true;
        }

        return false;
    }

    private boolean isRelpay() {
        System.out.println(SELECT_MESSAGE);
        String selectNumber = SCAN.nextLine();

        if (isChar(selectNumber) || isEmpty(selectNumber) || checkNumber(selectNumber)) {
            return isRelpay();
        }

        return Integer.parseInt(selectNumber) == REPLAY;
    }

    private boolean getWin(Map<String, Integer> map) {
        if (map.get(STRIKE) == 3) {
            System.out.println(WINNER_MESSAGE);
            return true;
        }

        return false;
    }

    private boolean checkNothing(Map<String, Integer> resultMap) {
        return resultMap.get(NOTHING) == ONE_ROUND_BALL_LENGTH;
    }

    private void printResult(Map<String, Integer> resultMap) {
        if (checkNothing(resultMap)) {
            System.out.println(NOTHING_MESSAGE);
            return;
        }

        getScore(resultMap);
    }

    private void getScore(Map<String, Integer> resultMap) {
        if (resultMap.get(STRIKE) > 0) {
            System.out.print(getMapValue(resultMap, STRIKE) + STRIKE_MESSAGE + " ");
        }

        if (resultMap.get(BALL) > 0) {
            System.out.print(getMapValue(resultMap, BALL) + BALL_MESSAGE);
        }

        System.out.println();
    }

    private int getMapValue(Map<String, Integer> map, String key) {
        if (Objects.isNull(map.get(key))) {
            return 0;
        }

        return map.get(key);
    }

    private boolean isNotBound(String input) {
        if (input.contains("0")) {
            System.err.println(INPUT_BOUND_ERROR_MESSAGE);
            return true;
        }

        return false;
    }

    private boolean isNotThreeLength(List<Integer> ballList) {
        if (ballList.size() != ONE_ROUND_BALL_LENGTH) {
            System.err.println(INPUT_LENGTH_ERROR_MESSAGE);
            return true;
        }

        return false;
    }

    private boolean isChar(String input) {
        if (!Pattern.matches(PATTERN, input)) {
            System.err.println(INPUT_INTEGER_ERROR_MESSAGE);
            return true;
        }

        return false;
    }

    private boolean isEmpty(String input) {
        if (input.isEmpty()) {
            System.err.println(INPUT_EMPTY_ERROR_MESSAGE);
            return true;
        }

        return false;
    }

    private boolean isContains(List<Integer> ballList) {
        Set<Integer> ballSet = new HashSet<>(ballList);

        if (ballSet.size() != ONE_ROUND_BALL_LENGTH) {
            System.err.println(INPUT_CONTAIN_ERROR_MESSAGE);
            return true;
        }

        return false;
    }

    private List<Integer> addUserList() {
        List<Integer> uesrBallList = new ArrayList<>();
        String[] numberArr = inputNumber().split("");

        for (String number : numberArr) {
            uesrBallList.add(Integer.parseInt(number));
        }

        return uesrBallList;
    }

    private boolean checkValid(List<Integer> userBallList) {
        return isNotThreeLength(userBallList) || isContains(userBallList);
    }

    private List<Integer> getUserList() {
        List<Integer> userBallList = addUserList();

        if (checkValid(userBallList)) {
            return getUserList();
        }

        return userBallList;

    }

    private String inputNumber() {
        System.out.print(INPUT_MESSAGE);
        String number = SCAN.nextLine();

        if (isEmpty(number) || isChar(number) || isNotBound(number)) {
            return inputNumber();
        }

        return number;
    }

    private Set<Integer> addSet() {
        Set<Integer> ballSet = new HashSet<>();

        while (ballSet.size() != ONE_ROUND_BALL_LENGTH) {
            ballSet.add(getRandomNumber());
        }

        return ballSet;
    }

    private List<Integer> getComputerBallList() {
        List<Integer> computerBallList = new ArrayList<>(addSet());
        return computerBallList;
    }

    private int getRandomNumber() {
        Random random = new Random();
        return random.nextInt(MAX_NUMBER) + MIN_NUMBER;
    }

}

