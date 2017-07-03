package ingraph.search2tasks;

import ingraph.logger.IngraphLogger;
import ingraph.optimization.patterns.GetVerticesAndExpandOperatorMatch;
import ingraph.optimization.patterns.GetVerticesAndExpandOperatorMatcher;
import ingraph.optimization.transformations.AbstractRelalgTransformation;
import ingraph.relalg.util.visitors.PostOrderTreeVisitor;
import org.eclipse.viatra.transformation.runtime.emf.rules.batch.BatchTransformationRule;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import relalg.Operator;
import relalg.RelalgContainer;

@SuppressWarnings("all")
public class Search2TasksTransformation extends AbstractRelalgTransformation {
  @Extension
  private IngraphLogger logger = new IngraphLogger(Search2TasksTransformation.class.getName());
  
  @Extension
  private PostOrderTreeVisitor treeVisitor = new PostOrderTreeVisitor();
  
  public Search2TasksTransformation(final RelalgContainer container) {
    super(container);
  }
  
  public RelalgContainer transformToTasks() {
    this.logger.info("Transforming relational algebra expression to tasks");
    BatchTransformationRule<GetVerticesAndExpandOperatorMatch, GetVerticesAndExpandOperatorMatcher> _verticesAndExpandOperatorRule = this.getVerticesAndExpandOperatorRule();
    this.statements.<GetVerticesAndExpandOperatorMatch>fireWhilePossible(_verticesAndExpandOperatorRule);
    Operator _rootExpression = this.container.getRootExpression();
    final Procedure1<Operator> _function = (Operator it) -> {
      InputOutput.<Operator>println(it);
    };
    this.treeVisitor.traverse(_rootExpression, _function);
    return this.container;
  }
}
