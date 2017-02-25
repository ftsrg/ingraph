package ingraph.relalg2tex.serializers

import ingraph.relalg2tex.converters.VariableConverter
import java.util.List
import relalg.Variable
import ingraph.relalg2tex.converters.StringEscaper

class SchemaSerializer {
 
  extension VariableConverter variableConverter = new VariableConverter
  extension StringEscaper stringEscaper = new StringEscaper

  /**
   * serializeSchema
   */  
  def serializeSchema(List<Variable> schema) {
    '''\langle \var{«schema.map[ serializeVariable.escape ].join(', ')»} \rangle'''
  } 
  
}