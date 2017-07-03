package ingraph.search2tasks.tasks.unary;

import ingraph.search2tasks.tasks.Task;
import org.eclipse.xtend.lib.annotations.Data;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;
import relalg.AbstractEdgeVariable;
import relalg.VertexVariable;

@Data
@SuppressWarnings("all")
public class ExpandBothTask implements Task {
  private final VertexVariable sourceVar;
  
  private final AbstractEdgeVariable edgeVar;
  
  private final VertexVariable targetVar;
  
  public ExpandBothTask(final VertexVariable sourceVar, final AbstractEdgeVariable edgeVar, final VertexVariable targetVar) {
    super();
    this.sourceVar = sourceVar;
    this.edgeVar = edgeVar;
    this.targetVar = targetVar;
  }
  
  @Override
  @Pure
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.sourceVar== null) ? 0 : this.sourceVar.hashCode());
    result = prime * result + ((this.edgeVar== null) ? 0 : this.edgeVar.hashCode());
    result = prime * result + ((this.targetVar== null) ? 0 : this.targetVar.hashCode());
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
    ExpandBothTask other = (ExpandBothTask) obj;
    if (this.sourceVar == null) {
      if (other.sourceVar != null)
        return false;
    } else if (!this.sourceVar.equals(other.sourceVar))
      return false;
    if (this.edgeVar == null) {
      if (other.edgeVar != null)
        return false;
    } else if (!this.edgeVar.equals(other.edgeVar))
      return false;
    if (this.targetVar == null) {
      if (other.targetVar != null)
        return false;
    } else if (!this.targetVar.equals(other.targetVar))
      return false;
    return true;
  }
  
  @Override
  @Pure
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("sourceVar", this.sourceVar);
    b.add("edgeVar", this.edgeVar);
    b.add("targetVar", this.targetVar);
    return b.toString();
  }
  
  @Pure
  public VertexVariable getSourceVar() {
    return this.sourceVar;
  }
  
  @Pure
  public AbstractEdgeVariable getEdgeVar() {
    return this.edgeVar;
  }
  
  @Pure
  public VertexVariable getTargetVar() {
    return this.targetVar;
  }
}
