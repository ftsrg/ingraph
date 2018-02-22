package ingraph.util


object PlanPrettyPrinter {

  def clean(plan: String): String = {
    plan
      .replaceAll("Some", "")
      .replaceAll("vertexattribute", "v")
      .replaceAll("edgeattribute", "e")
      .replaceAll("propertyattribute", "p")
      .replaceAll("expressionattribute", "expr")
      .replaceAll("vertexlabelset\\((.*?)\\)", "{$1}")
      .replaceAll("edgelabelset\\((.*?)\\)", "{$1}")
      .replaceAll(", NonEmpty\\}", "}")
      .replaceAll("\\{Empty\\}", "{}")
      .replaceAll("functioninvocation", "fun")
      .replaceAll("returnitem", "ret")
      .replaceAll("(\\w+) '\\1", "$1")
  }

}
