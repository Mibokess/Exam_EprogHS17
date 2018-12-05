package solutions;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by mikeb on 04-Dec-18
 */
public class Interleavings {
	public static void main(String[] args) {
		var interleaving = new Interleavings();

		interleaving.generateInterleaving("12", "ab").forEach(System.out::println);
	}

	public Set<String> generateInterleaving(String s, String t) {
		return generateInterleaving(s, t, "");
	}

	private Set<String> generateInterleaving(String s, String t, String combined) {
		if (s.isEmpty() && t.isEmpty()) {
			return new TreeSet<String>();
		} else if (s.isEmpty()) {
			return new TreeSet<String>(List.of(combined + t));
		} else if (t.isEmpty()) {
			return new TreeSet<String>(List.of(combined + s));
		}

		var usingS = generateInterleaving(s.substring(1), t, combined + s.charAt(0));
		var usingT = generateInterleaving(s, t.substring(1), combined + t.charAt(0));
		usingS.addAll(usingT);

		return usingS;
	}
}
