package ingraph.debugger.backend

final case class RegisterSuccessMessage(id: String)

final case class ErrorMessage(exception: String)
