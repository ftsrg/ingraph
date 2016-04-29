package hu.bme.mit.ingraph.algebra.operators.util;

import java.util.List;

import org.eclipse.collections.impl.factory.Lists;

public class AlgebraUtil {

	public static List<Integer> L(Integer... items) {
		return Lists.mutable.of(items);
	}

}
