/**
 * generated by Xtext 2.10.0
 */
package ingraph.report.featuregrammar.feature;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Then Step</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link ingraph.report.featuregrammar.feature.ThenStep#getTags <em>Tags</em>}</li>
 *   <li>{@link ingraph.report.featuregrammar.feature.ThenStep#getRows <em>Rows</em>}</li>
 * </ul>
 *
 * @see ingraph.report.featuregrammar.feature.FeaturePackage#getThenStep()
 * @model
 * @generated
 */
public interface ThenStep extends Step
{
  /**
   * Returns the value of the '<em><b>Tags</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Tags</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Tags</em>' attribute list.
   * @see ingraph.report.featuregrammar.feature.FeaturePackage#getThenStep_Tags()
   * @model unique="false"
   * @generated
   */
  EList<String> getTags();

  /**
   * Returns the value of the '<em><b>Rows</b></em>' containment reference list.
   * The list contents are of type {@link ingraph.report.featuregrammar.feature.ExampleRow}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Rows</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Rows</em>' containment reference list.
   * @see ingraph.report.featuregrammar.feature.FeaturePackage#getThenStep_Rows()
   * @model containment="true"
   * @generated
   */
  EList<ExampleRow> getRows();

} // ThenStep
