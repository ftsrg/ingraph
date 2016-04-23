package hu.bme.mit.ingraph.tinkerpop;

import java.io.IOException;

import org.junit.Test;

public class JoinTest {

	@Test
	public void test() throws IOException, ClassNotFoundException {
//		final Indexer indexer = new Indexer();
//		indexer.reload(modelSize);
//		final long loadTime = stopWatch.elapsed(TimeUnit.MILLISECONDS);
//
//		final List<Integer> join1primaryMask = ImmutableList.of(0);
//		final List<Integer> join1secondaryMask = ImmutableList.of(1);
//		final EquiJoin join1 = new EquiJoin(join1primaryMask, join1secondaryMask);
//
//		final List<Integer> join2primaryMask = ImmutableList.of(1);
//		final List<Integer> join2secondaryMask = ImmutableList.of(0);
//		final EquiJoin join2 = new EquiJoin(join2primaryMask, join2secondaryMask);
//
//		final List<Integer> antiJoinPrimaryMask = ImmutableList.of(2, 3);
//		final List<Integer> antiJoinSecondaryMask = ImmutableList.of(0, 1);
//		final AntiJoin antiJoin = new AntiJoin(antiJoinPrimaryMask, antiJoinSecondaryMask);
//
//		final Stopwatch stopWatch2 = Stopwatch.createStarted();
//
//		final Stopwatch join1Watch = Stopwatch.createStarted();
//		final Collection<Tuple> join1Result = join1.join(indexer.getSwitches(), indexer.getFollowss());
//		final long join1Time = join1Watch.elapsed(TimeUnit.MILLISECONDS);
//
//		final Stopwatch join2Watch = Stopwatch.createStarted();
//		final Collection<Tuple> join2Result = join2.join(join1Result, indexer.getSensors());
//		final long join2Time = join2Watch.elapsed(TimeUnit.MILLISECONDS);
//
//		final Stopwatch antiJoinWatch = Stopwatch.createStarted();
//		final Collection<Tuple> result = antiJoin.join(join2Result, indexer.getDefinedBys());
//		final long antiJoinTime = antiJoinWatch.elapsed(TimeUnit.MILLISECONDS);
//
//		final long queryTime = stopWatch2.elapsed(TimeUnit.MILLISECONDS);
//
//		// System.out.println("join times: " + join1Time + "\t" + join2Time +
//		// "\t" + antiJoinTime);
//		System.out.println(modelSize + "\t" + result.size() + "\t" + loadTime + "\t" + queryTime);
	}
}
