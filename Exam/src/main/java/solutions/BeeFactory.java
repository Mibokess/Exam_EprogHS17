package solutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by mikeb on 04-Dec-18
 */
public class BeeFactory {
	public static void main(String[] args) throws FileNotFoundException {
		String dateiName = "src\\main\\java\\bienen.txt";
		Scanner scanner = new Scanner(new File(dateiName));
		PrintStream output = new PrintStream(System.out);

		analyzeButStreams(scanner, output);

		output.close();
		scanner.close();
	}

	/**
	 * Ändern Sie nicht die Signatur der Methode
	 */
	public static void analyze(Scanner input, PrintStream output) {
		List<Person> people = readData(input);

		TreeMap<String, Integer> countryToSpecialSumMap = new TreeMap<>();
		Person maxTotalPerson = people.get(0), maxSpecialPercentagePerson = people.get(0);

		for (Person person : people) {
			if (maxTotalPerson.getTotalPayment() < person.getTotalPayment()) {
				maxTotalPerson = person;
			}

			if (maxSpecialPercentagePerson.getPercentageSpecial() < person.getPercentageSpecial()) {
				maxSpecialPercentagePerson = person;
			}

			if (countryToSpecialSumMap.containsKey(person.getCountry())) {
				countryToSpecialSumMap.put(person.getCountry(), countryToSpecialSumMap.get(person.getCountry()) + person.getSpecialPayments());
			} else {
				countryToSpecialSumMap.put(person.getCountry(), person.getSpecialPayments());
			}
		}

		Map.Entry<String, Integer> maxCountry = countryToSpecialSumMap.firstEntry();
		for (Map.Entry<String, Integer> country : countryToSpecialSumMap.entrySet()) {
			if (maxCountry.getValue() < country.getValue()) maxCountry = country;
		}

		output.print(maxTotalPerson.getName() + " " + maxTotalPerson.getTotalPayment() + " ");
		output.print(maxSpecialPercentagePerson.getName() + " " + Math.round(100 * maxSpecialPercentagePerson.getPercentageSpecial()) + " ");
		output.println(maxCountry.getKey() + " " + maxCountry.getValue());
	}

	public static void analyzeButCollections(Scanner input, PrintStream output) {
		var people = readData(input);

		var maxTotalPerson = Collections.max(people, (p1, p2) -> p1.getTotalPayment() >= p2.getTotalPayment() ? 1 : -1);

		var maxSpecialPercentagePerson = Collections.max(people, (p1, p2) -> p1.getPercentageSpecial() >= p2.getPercentageSpecial() ? 1 : -1);

		var countryMap = new HashMap<String, List<Person>>();
		people.forEach(p -> countryMap.computeIfAbsent(p.getCountry(), k -> new ArrayList<>()).add(p));

		int maxSpecialCountry = 0;
		String maxCountry = "";
		for (Map.Entry<String, List<Person>> countryInhabitants : countryMap.entrySet()) {
			int sumSpecialPayments = 0;

			for (Person person : countryInhabitants.getValue()) {
				sumSpecialPayments += person.getSpecialPayments();
			}

			if (maxSpecialCountry < sumSpecialPayments) {
				maxCountry = countryInhabitants.getKey();
				maxSpecialCountry = sumSpecialPayments;
			}
		}

		output.print(maxTotalPerson.getName() + " " + maxTotalPerson.getTotalPayment() + " ");
		output.print(maxSpecialPercentagePerson.getName() + " " + Math.round(100 * maxSpecialPercentagePerson.getPercentageSpecial()) + " ");
		output.println(maxCountry + " " + maxSpecialCountry);
	}

	public static void analyzeButStreams(Scanner input, PrintStream output) {
		var people = readData(input);

		var maxTotalPerson = people.stream().max(Comparator.comparing(Person::getTotalPayment)).get();

		var maxSpecialPercentagePerson = people.stream().max(Comparator.comparing(Person::getPercentageSpecial)).get();

		var countrySumSpecialMap = people.stream().collect(Collectors.groupingBy(Person::getCountry, Collectors.summingInt(Person::getSpecialPayments)));
		var maxSpecialCountry = Collections.max(countrySumSpecialMap.entrySet(), Comparator.comparing(Map.Entry::getValue));

		output.print(maxTotalPerson.getName() + " " + maxTotalPerson.getTotalPayment() + " ");
		output.print(maxSpecialPercentagePerson.getName() + " " + Math.round(100 * maxSpecialPercentagePerson.getPercentageSpecial()) + " ");
		output.println(maxSpecialCountry.getKey() + " " + maxSpecialCountry.getValue());
	}

	private static List<Person> readData(Scanner input) {
		var people = new ArrayList<Person>();

		while (input.hasNextLine()) {
			people.add(new Person(input.next(), input.next(), input.nextInt(), input.nextInt()));
		}

		return people;
	}
}


class Person {
	private final String name;
	private final String country;
	private final int attendanceFees;
	private final int specialPayments;

	private final int totalPayment;
	private final double percentageSpecial;

	public Person(String name, String country, int attendanceFees, int specialPayments) {
		this.name = name;
		this.country = country;
		this.attendanceFees = attendanceFees;
		this.specialPayments = specialPayments;

		totalPayment = attendanceFees + specialPayments;
		percentageSpecial = ((double) specialPayments) / totalPayment;
	}

	public String getName() {
		return name;
	}

	public String getCountry() {
		return country;
	}

	public int getAttendanceFees() {
		return attendanceFees;
	}

	public int getSpecialPayments() {
		return specialPayments;
	}

	public int getTotalPayment() {
		return totalPayment;
	}

	public double getPercentageSpecial() {
		return percentageSpecial;
	}
}