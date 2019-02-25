package ingraph.compiler.sql.driver

import ingraph.compiler.sql.GTopExtension
import org.cytosm.common.gtop.GTop

object LdbcTestResources {
  val gTop: GTop = GTopExtension.loadFromResource("/gtop/ldbc.gtop")
  val url = "jdbc:postgresql://localhost:5432/ldbcsf1?user=postgres&password=foo"
}
