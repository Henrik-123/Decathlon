package com.example.decathlon.heptathlon;

import com.example.decathlon.common.CalcTrackAndField;
import com.example.decathlon.common.InputResult;
import com.example.decathlon.common.InvalidResultException;

public class Hep100MHurdles {

	private int score;
	private double A = 9.23076;
	private double B = 26.7;
	private double C = 1.835;
	CalcTrackAndField calc = new CalcTrackAndField();
	InputResult inputResult = new InputResult();

	// Calculate the score based on time. All running events.
	public int calculateResult(double runningTime) throws InvalidResultException {

				// Acceptable values.
				if (runningTime < 10) {
                    throw new InvalidResultException("Value too low");
				} else if (runningTime > 30) {
                    throw new InvalidResultException("Value too high");
				} else {
					score = calc.calculateTrack(A, B, C, runningTime);
				}

		System.out.println("The result is " + score);
		return score;
	}

}
