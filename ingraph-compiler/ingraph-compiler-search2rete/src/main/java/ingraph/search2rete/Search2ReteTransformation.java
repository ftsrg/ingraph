package ingraph.search2rete;

import ingraph.optimization.patterns.DefaultExpandOperatorMatch;
import ingraph.optimization.patterns.DefaultExpandOperatorMatcher;
import ingraph.optimization.patterns.GetVerticesAndExpandOperatorMatch;
import ingraph.optimization.patterns.GetVerticesAndExpandOperatorMatcher;
import ingraph.optimization.patterns.LeftOuterJoinAndSelectionMatch;
import ingraph.optimization.patterns.LeftOuterJoinAndSelectionMatcher;
import ingraph.optimization.patterns.SortAndTopOperatorMatch;
import ingraph.optimization.patterns.SortAndTopOperatorMatcher;
import ingraph.optimization.patterns.TransitiveExpandOperatorMatch;
import ingraph.optimization.patterns.TransitiveExpandOperatorMatcher;
import ingraph.optimization.transformations.AbstractRelalgTransformation;
import org.eclipse.viatra.transformation.runtime.emf.rules.batch.BatchTransformationRule;
import relalg.RelalgContainer;

@SuppressWarnings("all")
public class Search2ReteTransformation extends AbstractRelalgTransformation {
  public Search2ReteTransformation(final RelalgContainer container) {
    super(container);
  }
  
  public RelalgContainer transformToRete() {
    this.logger.info("Transforming relational algebra expression to Rete network");
    boolean _isIncrementalPlan = this.container.isIncrementalPlan();
    if (_isIncrementalPlan) {
      throw new IllegalStateException(
        "The relalg plan is already a Rete plan. Search2ReteTransformation should be invoked on a (non-incremental) search plan");
    }
    BatchTransformationRule<GetVerticesAndExpandOperatorMatch, GetVerticesAndExpandOperatorMatcher> _verticesAndExpandOperatorRule = this.getVerticesAndExpandOperatorRule();
    this.statements.<GetVerticesAndExpandOperatorMatch>fireWhilePossible(_verticesAndExpandOperatorRule);
    BatchTransformationRule<DefaultExpandOperatorMatch, DefaultExpandOperatorMatcher> _defaultExpandOperatorRule = this.defaultExpandOperatorRule();
    this.statements.<DefaultExpandOperatorMatch>fireWhilePossible(_defaultExpandOperatorRule);
    BatchTransformationRule<TransitiveExpandOperatorMatch, TransitiveExpandOperatorMatcher> _transitiveExpandOperatorRule = this.transitiveExpandOperatorRule();
    this.statements.<TransitiveExpandOperatorMatch>fireWhilePossible(_transitiveExpandOperatorRule);
    BatchTransformationRule<SortAndTopOperatorMatch, SortAndTopOperatorMatcher> _sortAndTopOperatorRule = this.sortAndTopOperatorRule();
    this.statements.<SortAndTopOperatorMatch>fireWhilePossible(_sortAndTopOperatorRule);
    BatchTransformationRule<LeftOuterJoinAndSelectionMatch, LeftOuterJoinAndSelectionMatcher> _leftOuterJoinAndSelectionRule = this.leftOuterJoinAndSelectionRule();
    this.statements.<LeftOuterJoinAndSelectionMatch>fireWhilePossible(_leftOuterJoinAndSelectionRule);
    this.container.setIncrementalPlan(true);
    return this.container;
  }
}
