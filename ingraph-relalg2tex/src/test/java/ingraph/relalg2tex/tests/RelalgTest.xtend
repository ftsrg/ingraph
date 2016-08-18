package ingraph.relalg2tex.tests

import ingraph.relalg2tex.AlgebraTreeDrawer
import ingraph.relalg2tex.ExpressionSerializer
import org.junit.Test
import relalg.Direction
import relalg.RelalgFactory

class RelalgTest {

	val static ExpressionSerializer serializer = new ExpressionSerializer(false, false)
	val static AlgebraTreeDrawer drawer = new AlgebraTreeDrawer(true)
	val extension static RelalgFactory factory = RelalgFactory.eINSTANCE

	@Test
	def void test1() {
		val r = createInputRelation => [type = "r"]
		val s = createInputRelation => [type = "s"]
		val t = createInputRelation => [type = "t"]
		val join1 = createJoinOperation => [name = "Join1"; leftParent = r; rightParent = s]
		val join2 = createJoinOperation => [name = "Join2"; leftParent = join1; rightParent = t]

		val expression = join2
		print(serializer.serialize(expression))
//		print(drawer.serialize(expression))
	}

	@Test
	def void test2() {
		val follows_r = createAttribute => [name = "r"]
		val follows_swP = createAttribute => [name = "swP"]

		val target_swP = createAttribute => [name = "swP"]
		val target_sw = createAttribute => [name = "sw"]

		val monitoredBy_te = createAttribute => [name = "te"]
		val monitoredBy_sen = createAttribute => [name = "sen"]

		val gathers_r = createAttribute => [name = "r"]
		val gathers_sen = createAttribute => [name = "sen"]

		// input relations
		val follows = createInputRelation => [type = "follows"; attributes += #[follows_r, follows_swP]]
		val target = createInputRelation => [type = "target"; attributes += #[target_swP, target_sw]]
		val monitoredBy = createInputRelation =>
			[type = "monitoredBy"; attributes += #[monitoredBy_te, monitoredBy_sen]]
		val gathers = createInputRelation => [type = "gathers"; attributes += #[gathers_r, gathers_sen]]

		// joins
		val join1 = createJoinOperation => [
			name = "Join1";
			leftParent = follows;
			rightParent = target;
			bindings += #[
				createJoinBinding => [leftAttribute = follows_swP; rightAttribute = target_swP]
			]
		]
		val join2 = createJoinOperation => [
			name = "Join2";
			leftParent = join1;
			rightParent = monitoredBy;
			bindings += #[
				createJoinBinding => [leftAttribute = target_sw; rightAttribute = monitoredBy_te]
			]
		]
		val antijoin = createAntiJoinOperation => [
			name = "Antijoin";
			leftParent = join2;
			rightParent = gathers;
			bindings += #[
				createJoinBinding => [leftAttribute = follows_r; rightAttribute = gathers_r],
				createJoinBinding => [leftAttribute = monitoredBy_sen; rightAttribute = gathers_sen]
			]
		]

		val expression = antijoin
		print(serializer.serialize(expression))
	}

	@Test
	def void test3() {
		val getNodesR = createGetNodesOperation => [attribute = createAttribute => [name = "r"]]
		val expandIn = createExpandOperation => [direction = Direction.IN; parent = getNodesR]
		
		val getNodesS = createGetNodesOperation => [attribute = createAttribute => [name = "s"]]		
		val join = createJoinOperation => [name = "Join1"; leftParent = expandIn; rightParent = getNodesS]
		
		val expression = join
//		print(serializer.serialize(expression))
		print(drawer.serialize(expression))
	}

}
