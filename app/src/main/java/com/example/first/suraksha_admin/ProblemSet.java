package com.example.first.suraksha_admin;

import java.io.Serializable;

/**
 * Created by hp on 9/10/17.
 */

public class ProblemSet implements Serializable {
    String Problem;
    String Time;

    public ProblemSet() {
    }

    public ProblemSet(String problem, String time) {
        Problem = problem;
        Time = time;
    }

    public String getProblem() {
        return Problem;
    }

    public void setProblem(String problem) {
        Problem = problem;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}
