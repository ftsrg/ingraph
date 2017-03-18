package ingraph.bulkloader.csv.columnname;

public class ColumnConstants {

	public static final String INTERNAL_ID = "_internal_id";
	public static final String INTERNAL_START_ID = "_internal_start_id";
	public static final String INTERNAL_END_ID = "_internal_end_id";

	public static boolean isId(String s) {
		return INTERNAL_ID.equals(s) || INTERNAL_START_ID.equals(s) || INTERNAL_END_ID.equals(s);
	}

}
