package ingraph.report.tests;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public class PrettyPrinter {
	private static final String INDENT = "    "; //$NON-NLS-1$

	public static String prettyPrint(EObject object) {
		List<String> lines = prettyPrintAny(object, ""); //$NON-NLS-1$
		StringBuilder sb = new StringBuilder();
		for (String line : lines) {
			sb.append(line).append('\n');
		}
		return sb.toString();
	}

	private static List<String> prettyPrintAny(Object o, String indent) {
		List<String> lines = new ArrayList<>();
		if (o instanceof EObject) {
			EObject object = (EObject) o;
			EClass eClass = object.eClass();
			lines.add(eClass.getName() + " [" + o.getClass().getCanonicalName() + "] {"); //$NON-NLS-1$ //$NON-NLS-2$
			lines.addAll(prettyPrintRecursive(object, INDENT));
			lines.add("}"); //$NON-NLS-1$
		} else if (o instanceof Iterable) {
			lines.add("["); //$NON-NLS-1$
			for (Object obj : (Iterable<?>) o) {
				lines.addAll(prettyPrintAny(obj, INDENT));
			}
			lines.add("]"); //$NON-NLS-1$
		} else {
			String line = String.valueOf(o) + ' ';
			if (o != null) {
				line += '[' + o.getClass().getCanonicalName() + ']';
			}
			lines.add(line);
		}
		return indentLines(lines, indent);
	}

	private static List<String> indentLines(List<String> lines, String indent) {
		List<String> result = new ArrayList<>();
		for (String l : lines) {
			result.add(indent + l);
		}
		return result;
	}

	private static List<String> prettyPrintRecursive(EObject o, String indent) {
		EClass eClass = o.eClass();
		List<String> result = new ArrayList<>();
		for (EStructuralFeature feature : eClass.getEAllStructuralFeatures()) {
			Object value = o.eGet(feature);
			String line = feature.getName() + " = "; //$NON-NLS-1$
			List<String> list = prettyPrintAny(value, INDENT);
			list.set(0, list.get(0).trim());
			result.add(line + list.get(0));
			list.remove(0);
			result.addAll(list);
		}
		return indentLines(result, indent);
	}
}
