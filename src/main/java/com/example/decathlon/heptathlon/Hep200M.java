package com.example.decathlon.heptathlon;

import com.example.decathlon.common.CalcTrackAndField;
import com.example.decathlon.common.InputResult;
import com.example.decathlon.common.InvalidResultException;

public class Hep200M {

	private int score;
	private double A = 4.99087;
	private double B = 42.5;
	private double C = 1.81;
	boolean active = true;
	CalcTrackAndField calc = new CalcTrackAndField();
	InputResult inputResult = new InputResult();

	// Calculate the score based on time. All running events.
	public int calculateResult(double runningTime) throws InvalidResultException {

		// Acceptable values.
        if (runningTime < 20) {
            throw new InvalidResultException("Value too low");
        } else if (runningTime > 100) {
            throw new InvalidResultException("Value too high");
        } else {
            score = calc.calculateTrack(A, B, C, runningTime);
        }

		System.out.println("The result is " + score);
		return score;
	}

}
