package ingraph.relalg2tex.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser
import ingraph.optimization.transformations.SchemaInferencer
import ingraph.relalg2tex.RelalgTreeSerializer

class MergeNodeAcceptanceVisualizationTest {

    val RelalgTreeSerializer serializer = new RelalgTreeSerializer
    extension SchemaInferencer inferencer = new SchemaInferencer
    
}
