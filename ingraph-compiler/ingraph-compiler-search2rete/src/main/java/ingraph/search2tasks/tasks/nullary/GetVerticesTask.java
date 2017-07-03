package ingraph.search2tasks.tasks.nullary;

import ingraph.search2tasks.tasks.Task;
import org.eclipse.xtend.lib.annotations.Data;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;
import relalg.VertexVariable;

@Data
@SuppressWarnings("all")
public class GetVerticesTask implements Task {
  private final VertexVariable variable;
  
  public GetVerticesTask(final VertexVariable variable) {
    super();
    this.variable = variable;
  }
  
  @Override
  @Pure
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.variable== null) ? 0 : this.variable.hashCode());
    return result;
  }
  
  @Override
  @Pure
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    GetVerticesTask other = (GetVerticesTask) obj;
    if (this.variable == null) {
      if (other.variable != null)
        return false;
    } else if (!this.variable.equals(other.variable))
      return false;
    return true;
  }
  
  @Override
  @Pure
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("variable", this.variable);
    return b.toString();
  }
  
  @Pure
  public VertexVariable getVariable() {
    return this.variable;
  }
}
