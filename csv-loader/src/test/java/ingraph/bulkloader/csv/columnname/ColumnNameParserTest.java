package ingraph.bulkloader.csv.columnname;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ColumnNameParserTest {

	@Test
	public void testId() {
		ColumnNameParser cnp = new ColumnNameParser(":ID");
		assertEquals("_internal_id", cnp.getName());
		assertEquals(ColumnType.ID, cnp.getType());
	}

	@Test
	public void testBasic() {
		ColumnNameParser cnp = new ColumnNameParser("hello");
		assertEquals("hello", cnp.getName());
		assertEquals(ColumnType.STRING, cnp.getType());
	}

	@Test
	public void testString() {
		ColumnNameParser cnp = new ColumnNameParser("hello:STRING");
		assertEquals("hello", cnp.getName());
		assertEquals(ColumnType.STRING, cnp.getType());
	}

	@Test
	public void testStringArray() {
		ColumnNameParser cnp = new ColumnNameParser("hello:STRING[]");
		assertEquals("hello", cnp.getName());
		assertEquals(ColumnType.STRING_ARRAY, cnp.getType());
	}

	@Test
	public void testInt() {
		ColumnNameParser cnp = new ColumnNameParser("hello:INT");
		assertEquals("hello", cnp.getName());
		assertEquals(ColumnType.INT, cnp.getType());
	}

	@Test(expected = IllegalArgumentException.class)
	public void test2() {
		new ColumnNameParser("hello:INT2");
	}

	@Test
	public void test3() {
		ColumnNameParser cnp = new ColumnNameParser(":ID(Person)");
		assertEquals(ColumnType.ID, cnp.getType());
	}

	@Test
	public void testLabels() {
		ColumnNameParser cnp = new ColumnNameParser(":LABEL");
		assertEquals(ColumnType.LABEL, cnp.getType());
	}


}
