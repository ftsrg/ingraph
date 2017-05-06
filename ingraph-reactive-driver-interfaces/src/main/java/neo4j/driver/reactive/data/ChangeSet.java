package neo4j.driver.reactive.data;

import java.util.stream.Collectors;

import com.google.common.collect.Multiset;

public class ChangeSet<T> {

	final Multiset<T> positive;
	final Multiset<T> negative;

	public ChangeSet(Multiset<T> positive, Multiset<T> negative) {
		super();
		this.positive = positive;
		this.negative = negative;
	}

	public Multiset<T> getPositive() {
		return positive;
	}

	public Multiset<T> getNegative() {
		return negative;
	}

	@Override
	public String toString() {
		return String.format("ChangeSet [\n  positive = { %s }\n  negative = { %s }\n]", formatRecords(positive), formatRecords(negative));
	}

	private String formatRecords(Multiset<T> records) {
		return records.stream() //
				.map(r -> r.toString()) //
				.collect(Collectors.joining(", ")); //
	}

}
