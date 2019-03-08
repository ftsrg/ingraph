package ingraph.debugger.backend

final case class RegisterMessage(definition: String)

final case class RegisterSuccessMessage(status: String, id: String)

final case class ErrorMessage(status: String, exception: String)

final case class DeltaMessage()
