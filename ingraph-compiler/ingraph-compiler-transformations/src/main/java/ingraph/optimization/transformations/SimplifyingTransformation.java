package ingraph.optimization.transformations;

import ingraph.logger.IngraphLogger;
import ingraph.optimization.patterns.LeftOuterJoinAndSelectionMatch;
import ingraph.optimization.patterns.LeftOuterJoinAndSelectionMatcher;
import ingraph.optimization.patterns.UnnecessaryAllDifferentOperatorMatch;
import ingraph.optimization.patterns.UnnecessaryAllDifferentOperatorMatcher;
import ingraph.optimization.patterns.UnnecessaryJoinMatch;
import ingraph.optimization.patterns.UnnecessaryJoinMatcher;
import ingraph.optimization.transformations.AbstractRelalgTransformation;
import org.eclipse.viatra.query.runtime.api.IMatchProcessor;
import org.eclipse.viatra.query.runtime.api.IQuerySpecification;
import org.eclipse.viatra.transformation.runtime.emf.rules.batch.BatchTransformationRule;
import org.eclipse.viatra.transformation.runtime.emf.rules.batch.BatchTransformationRuleFactory;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import relalg.AllDifferentOperator;
import relalg.EquiJoinLikeOperator;
import relalg.Operator;
import relalg.RelalgContainer;

@SuppressWarnings("all")
public class SimplifyingTransformation extends AbstractRelalgTransformation {
  @Extension
  private IngraphLogger logger = new IngraphLogger(SimplifyingTransformation.class.getName());
  
  public SimplifyingTransformation(final RelalgContainer container) {
    super(container);
  }
  
  public RelalgContainer simplify() {
    this.logger.info("Simplifying relational algebra expression");
    if ((this.container.isSimplifiedPlan() || this.container.isIncrementalPlan())) {
      throw new IllegalStateException(
        "The query plan is already simplified or incremental. Simplfication transformation should be invoked on a non-simplified search plan");
    }
    BatchTransformationRule<UnnecessaryJoinMatch, UnnecessaryJoinMatcher> _unnecessaryJoinOperatorRule = this.unnecessaryJoinOperatorRule();
    this.statements.<UnnecessaryJoinMatch>fireWhilePossible(_unnecessaryJoinOperatorRule);
    BatchTransformationRule<UnnecessaryAllDifferentOperatorMatch, UnnecessaryAllDifferentOperatorMatcher> _unnecessaryAllDifferentOperatorRule = this.unnecessaryAllDifferentOperatorRule();
    this.statements.<UnnecessaryAllDifferentOperatorMatch>fireWhilePossible(_unnecessaryAllDifferentOperatorRule);
    BatchTransformationRule<LeftOuterJoinAndSelectionMatch, LeftOuterJoinAndSelectionMatcher> _leftOuterJoinAndSelectionRule = this.leftOuterJoinAndSelectionRule();
    this.statements.<LeftOuterJoinAndSelectionMatch>fireWhilePossible(_leftOuterJoinAndSelectionRule);
    this.container.setSimplifiedPlan(true);
    return this.container;
  }
  
  /**
   * [a] Remove unnecessary JoinOperators
   */
  protected BatchTransformationRule<UnnecessaryJoinMatch, UnnecessaryJoinMatcher> unnecessaryJoinOperatorRule() {
    try {
      BatchTransformationRuleFactory.BatchTransformationRuleBuilder<UnnecessaryJoinMatch, UnnecessaryJoinMatcher> _createRule = this.ruleFactory.<UnnecessaryJoinMatch, UnnecessaryJoinMatcher>createRule();
      IQuerySpecification<UnnecessaryJoinMatcher> _querySpecification = UnnecessaryJoinMatcher.querySpecification();
      BatchTransformationRuleFactory.BatchTransformationRuleBuilder<UnnecessaryJoinMatch, UnnecessaryJoinMatcher> _precondition = _createRule.precondition(_querySpecification);
      final IMatchProcessor<UnnecessaryJoinMatch> _function = (UnnecessaryJoinMatch it) -> {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("unnecessaryJoinOperatorRule fired for ");
        EquiJoinLikeOperator _equiJoinLikeOperator = it.getEquiJoinLikeOperator();
        _builder.append(_equiJoinLikeOperator, "");
        this.logger.info(_builder.toString());
        Operator _parentOperator = it.getParentOperator();
        EquiJoinLikeOperator _equiJoinLikeOperator_1 = it.getEquiJoinLikeOperator();
        Operator _otherInputOperator = it.getOtherInputOperator();
        this.changeChildOperator(_parentOperator, _equiJoinLikeOperator_1, _otherInputOperator);
      };
      BatchTransformationRuleFactory.BatchTransformationRuleBuilder<UnnecessaryJoinMatch, UnnecessaryJoinMatcher> _action = _precondition.action(_function);
      return _action.build();
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * [b] Remove unnecessary AllDifferentOperators
   */
  protected BatchTransformationRule<UnnecessaryAllDifferentOperatorMatch, UnnecessaryAllDifferentOperatorMatcher> unnecessaryAllDifferentOperatorRule() {
    try {
      BatchTransformationRuleFactory.BatchTransformationRuleBuilder<UnnecessaryAllDifferentOperatorMatch, UnnecessaryAllDifferentOperatorMatcher> _createRule = this.ruleFactory.<UnnecessaryAllDifferentOperatorMatch, UnnecessaryAllDifferentOperatorMatcher>createRule();
      IQuerySpecification<UnnecessaryAllDifferentOperatorMatcher> _querySpecification = UnnecessaryAllDifferentOperatorMatcher.querySpecification();
      BatchTransformationRuleFactory.BatchTransformationRuleBuilder<UnnecessaryAllDifferentOperatorMatch, UnnecessaryAllDifferentOperatorMatcher> _precondition = _createRule.precondition(_querySpecification);
      final IMatchProcessor<UnnecessaryAllDifferentOperatorMatch> _function = (UnnecessaryAllDifferentOperatorMatch it) -> {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("unnecessaryAllDifferentOperatorRule fired for ");
        AllDifferentOperator _allDifferentOperator = it.getAllDifferentOperator();
        _builder.append(_allDifferentOperator, "");
        this.logger.info(_builder.toString());
        Operator _parentOperator = it.getParentOperator();
        AllDifferentOperator _allDifferentOperator_1 = it.getAllDifferentOperator();
        Operator _inputOperator = it.getInputOperator();
        this.changeChildOperator(_parentOperator, _allDifferentOperator_1, _inputOperator);
      };
      BatchTransformationRuleFactory.BatchTransformationRuleBuilder<UnnecessaryAllDifferentOperatorMatch, UnnecessaryAllDifferentOperatorMatcher> _action = _precondition.action(_function);
      return _action.build();
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
