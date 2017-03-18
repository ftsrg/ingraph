package ingraph.bulkloader.csv.columnname;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ingraph.bulkloader.csv.columnname.ColumnNameParser;
import ingraph.bulkloader.csv.columnname.ColumnType;

public class ColumnNameParserTest {

	@Test
	public void testId() {
		ColumnNameParser cnp = new ColumnNameParser(":ID");
		assertEquals("", cnp.getName());
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
	public void testInt() {
		ColumnNameParser cnp = new ColumnNameParser("hello:INT");
		assertEquals("hello", cnp.getName());
		assertEquals(ColumnType.INT, cnp.getType());
	}

	@Test(expected = IllegalArgumentException.class)
	public void test2() {
		new ColumnNameParser("hello:INT2");
	}

}
