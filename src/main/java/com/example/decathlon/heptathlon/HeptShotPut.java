package com.example.decathlon.heptathlon;

import com.example.decathlon.common.CalcTrackAndField;
import com.example.decathlon.common.InputResult;
import com.example.decathlon.common.InvalidResultException;

public class HeptShotPut {

	private int score;
	private double A = 56.0211;
	private double B = 1.5;
	private double C = 1.05;
	CalcTrackAndField calc = new CalcTrackAndField();
	InputResult inputResult = new InputResult();

	// Calculate the score based on distance and height. Measured in meters.
	public int calculateResult(double distance) throws InvalidResultException {
				// Acceptable values.
        if (distance < 0) {
            throw new InvalidResultException("Value too low");
        } else if (distance > 30) {
            throw new InvalidResultException("Value too high");
        } else {
            score = calc.calculateField(A, B, C, distance);
        }
		System.out.println("The result is: " + score);
		return score;
	}

}
