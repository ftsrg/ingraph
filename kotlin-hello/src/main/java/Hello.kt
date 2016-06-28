import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph

fun main(args: Array<String>) {
    println("Hello")
    val g = TinkerGraph.open()
    g.addVertex("hello")

}
