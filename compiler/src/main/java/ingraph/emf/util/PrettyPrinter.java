package ingraph.emf.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public class PrettyPrinter {

	private static final String INDENT = "    "; //$NON-NLS-1$

	public static String prettyPrint(final EObject object) {
		final String string = format(object);
		System.out.println(string);
		return string;
	}

    public static String format(final EObject object) {
        final List<String> lines = formatAny(object, ""); //$NON-NLS-1$
        final StringBuilder sb = new StringBuilder();
        for (final String line : lines) {
            sb.append(line).append('\n');
        }
        return sb.toString();
    }

    private static List<String> formatAny(final Object o, final String indent) {
        final List<String> lines = new ArrayList<>();
        if (o instanceof EObject) {
            final EObject object = (EObject) o;
            final EClass eClass = object.eClass();
            lines.add(eClass.getName() + " [" + o.getClass().getCanonicalName() + "] {"); //$NON-NLS-1$ //$NON-NLS-2$
            lines.addAll(formatRecursive(object, INDENT));
            lines.add("}"); //$NON-NLS-1$
        } else if (o instanceof Iterable) {
            lines.add("["); //$NON-NLS-1$
            for (final Object obj : (Iterable<?>) o) {
                lines.addAll(formatAny(obj, INDENT));
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

    private static List<String> indentLines(final List<String> lines, final String indent) {
        final List<String> result = new ArrayList<>();
        for (final String l : lines) {
            result.add(indent + l);
        }
        return result;
    }

    private static List<String> formatRecursive(final EObject o, final String indent) {
        final EClass eClass = o.eClass();
        final List<String> result = new ArrayList<>();
        for (final EStructuralFeature feature : eClass.getEAllStructuralFeatures()) {
            final Object value = o.eGet(feature);
            final String line = feature.getName() + " = "; //$NON-NLS-1$
            final List<String> list = formatAny(value, INDENT);
            list.set(0, list.get(0).trim());
            result.add(line + list.get(0));
            list.remove(0);
            result.addAll(list);
        }
        return indentLines(result, indent);
    }
}
