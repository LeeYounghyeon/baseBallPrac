package com.company;

import java.util.ArrayList;
import java.util.List;

public class Ball {
    List<Integer> ballList;

    Ball(List<Integer> ballList) {
        this.ballList = ballList;
    }

    public final List<Integer> getBallList() {
        return ballList;
    }
}
