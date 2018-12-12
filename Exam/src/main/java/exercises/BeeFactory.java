package exercises;

import org.w3c.dom.stylesheets.LinkStyle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

/**
 * Created by mikeb on 04-Dec-18
 */
public class BeeFactory {
	public static void main(String[] args) throws FileNotFoundException {
		String dateiName = "src\\main\\java\\bienen.txt";
		Scanner scanner = new Scanner(new File(dateiName));
		PrintStream output = new PrintStream(System.out);

		analyze(scanner, output);

		output.close();
		scanner.close();
	}

	/**
	 * Ändern Sie nicht die Signatur der Methode
	 */
	public static void analyze(Scanner input, PrintStream output) {
		// Todo: Implement
	}
}
