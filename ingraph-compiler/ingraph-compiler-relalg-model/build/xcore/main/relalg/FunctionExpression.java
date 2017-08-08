/**
 */
package relalg;

import org.eclipse.emf.common.util.EList;

import relalg.function.Function;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Function Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * FunctionExpression is a generic container for a function call descriptor,
 * i.e. the functor and its argument expressions.
 *  * Various sub-types like FunctionComparableExpression, FunctionArithmeticExpression
 * exists to allow type-safe building of expressions. However keep in mind, that
 * it is not guaranteed that it will refer to a function that is e.g.
 * comparable in case of a FunctionComparableExpression.
 * Type check is always needed in query execution time!
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link relalg.FunctionExpression#getFunctor <em>Functor</em>}</li>
 *   <li>{@link relalg.FunctionExpression#getArguments <em>Arguments</em>}</li>
 * </ul>
 *
 * @see relalg.RelalgPackage#getFunctionExpression()
 * @model
 * @generated
 */
public interface FunctionExpression extends Expression {
	/**
	 * Returns the value of the '<em><b>Functor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Functor</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Functor</em>' attribute.
	 * @see #setFunctor(Function)
	 * @see relalg.RelalgPackage#getFunctionExpression_Functor()
	 * @model unique="false" dataType="relalg.Function"
	 * @generated
	 */
	Function getFunctor();

	/**
	 * Sets the value of the '{@link relalg.FunctionExpression#getFunctor <em>Functor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Functor</em>' attribute.
	 * @see #getFunctor()
	 * @generated
	 */
	void setFunctor(Function value);

	/**
	 * Returns the value of the '<em><b>Arguments</b></em>' reference list.
	 * The list contents are of type {@link relalg.Expression}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Arguments</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Arguments</em>' reference list.
	 * @see relalg.RelalgPackage#getFunctionExpression_Arguments()
	 * @model
	 * @generated
	 */
	EList<Expression> getArguments();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='<%relalg.function.Function%> _functor = this.getFunctor();\n<%java.lang.String%> _plus = (_functor + \"(\");\n<%org.eclipse.emf.common.util.EList%><<%relalg.Expression%>> _arguments = this.getArguments();\nfinal <%org.eclipse.xtext.xbase.lib.Functions.Function1%><<%relalg.Expression%>, <%java.lang.String%>> _function = new <%org.eclipse.xtext.xbase.lib.Functions.Function1%><<%relalg.Expression%>, <%java.lang.String%>>()\n{\n\tpublic <%java.lang.String%> apply(final <%relalg.Expression%> it)\n\t{\n\t\treturn it.fullName();\n\t}\n};\n<%org.eclipse.emf.common.util.EList%><<%java.lang.String%>> _map = <%org.eclipse.emf.ecore.xcore.lib.XcoreEListExtensions%>.<<%relalg.Expression%>, <%java.lang.String%>>map(_arguments, _function);\n<%java.lang.String%> _join = <%org.eclipse.xtext.xbase.lib.IterableExtensions%>.join(_map, \", \");\n<%java.lang.String%> _plus_1 = (_plus + _join);\nreturn (_plus_1 + \")\");'"
	 * @generated
	 */
	String fullName();

} // FunctionExpression
