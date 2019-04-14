package com.company;

import java.util.*;

public class Computer {
    public static final String STRIKE = "strike";
    public static final String BALL = "ball";
    public static final String NOTHING = "nothing";
    public static final int INITNUMBER = 0;

    private Ball ball;

    Computer(Ball ball) {
        this.ball = ball;
    }

    public final Ball getBall() {
        return ball;
    }

    public Map<String, Integer> checkResult(Ball userBall) {
        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put(STRIKE, INITNUMBER);
        resultMap.put(BALL, INITNUMBER);
        resultMap.put(NOTHING, INITNUMBER);

        for (int i = 0; i < userBall.ballList.size(); i++) {
            getUpdateCount(resultMap, getUpdateKey(userBall.ballList, i, ball.ballList.get(i)));
        }

        return resultMap;
    }

    private String getUpdateKey(List<Integer> ballList, int index, int ball) {
        if (ballList.get(index).equals(ball)) {
            return STRIKE;
        }

        if (ballList.contains(ball)) {
            return BALL;
        }

        return NOTHING;
    }

    private void getUpdateCount(Map<String, Integer> resultMap, String key) {
        resultMap.put(key, resultMap.get(key) + 1);
    }
}

