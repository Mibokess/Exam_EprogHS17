package solutions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by mikeb on 04-Dec-18
 */
public class PrimeFactorization {
	public static void main(String[] args) {
		var factorization = new PrimeFactorization();

		factorization.primeFactors(-13).forEach(System.out::println);
	}

	List<Integer> primeFactors(Integer number) {
		var primesFactors = new ArrayList<Integer>();

		if (number > 0) {
			primesFactors.add(1);
		} else if (number < 0) {
			primesFactors.add(-1);
			number = -number;
		}

		int possiblePrimeFactor = 2;
		int dividedNumber = number;
		while (possiblePrimeFactor <= number) {
			if (isPrime(possiblePrimeFactor) && dividedNumber % possiblePrimeFactor == 0) {
				primesFactors.add(possiblePrimeFactor);
				dividedNumber /= possiblePrimeFactor;
			} else {
				++possiblePrimeFactor;
			}

		}

		// Collections.sort(primesFactors); Not needed as the primes are added in order to the list.
		return primesFactors;
	}

	private boolean isPrime(int possiblePrime) {
		for (int i = 2; i < possiblePrime; i++) {
			if (possiblePrime % i == 0) {
				return false;
			}
		}
		return true;
	}
}
