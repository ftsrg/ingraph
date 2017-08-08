package ingraph.relalg2tex.constants;

import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Functions.Function0;

@SuppressWarnings("all")
public class RelNullConstants {
  public final static String relNull = new Function0<String>() {
    public String apply() {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("\\relnull");
      return _builder.toString();
    }
  }.apply();
  
  public final static String isNotNull = new Function0<String>() {
    public String apply() {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("\\neq ");
      _builder.append(RelNullConstants.relNull, "");
      return _builder.toString();
    }
  }.apply();
  
  public final static String isNull = new Function0<String>() {
    public String apply() {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("= ");
      _builder.append(RelNullConstants.relNull, "");
      return _builder.toString();
    }
  }.apply();
}
