package ingraph.bulkloader.csv.columnname;

public class ColumnConstants {

	public static final String INTERNAL_LABEL = "_internal_label";
	public static final String INTERNAL_ID = "_internal_id";
	public static final String INTERNAL_START_ID = "_internal_start_id";
	public static final String INTERNAL_END_ID = "_internal_end_id";

	public static boolean isInternal(String s) {
		return s.startsWith("_internal_");
	}

}
