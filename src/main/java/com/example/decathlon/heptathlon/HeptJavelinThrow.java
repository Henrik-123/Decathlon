package com.example.decathlon.heptathlon;

import com.example.decathlon.common.CalcTrackAndField;
import com.example.decathlon.common.InputResult;
import com.example.decathlon.common.InvalidResultException;

public class HeptJavelinThrow {

	private int score;
	private double A = 15.9803;
	private double B = 3.8;
	private double C = 1.04;

	CalcTrackAndField calc = new CalcTrackAndField();
	InputResult inputResult = new InputResult();

	// Calculate the score based on distance and height. Measured in metres.
	public int calculateResult(double distance) throws InvalidResultException {

        if (distance < 0) {
            throw new InvalidResultException("Value too low");
        } else if (distance > 110) {
            throw new InvalidResultException("Value too high");
        } else {
            score = calc.calculateField(A, B, C, distance);
        }

		System.out.println("The result is: " + score);
		return score;
	}

}
