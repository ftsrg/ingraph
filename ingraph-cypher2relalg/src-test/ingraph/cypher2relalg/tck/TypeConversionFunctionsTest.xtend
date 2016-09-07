package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser

class TypeConversionFunctionsTest {
    
    @Test
    def void testTypeConversionFunctions_01() {
        RelalgParser.parse('''
        RETURN toBoolean('true') AS b
        ''')
    }
        
    @Test
    def void testTypeConversionFunctions_02() {
        RelalgParser.parse('''
        UNWIND [true, false] AS b
        RETURN toBoolean(b) AS b
        ''')
    }
        
    @Test
    def void testTypeConversionFunctions_03() {
        RelalgParser.parse('''
        UNWIND ['true', 'false'] AS s
        RETURN toBoolean(s) AS b
        ''')
    }
        
    @Test
    def void testTypeConversionFunctions_04() {
        RelalgParser.parse('''
        UNWIND [null, '', ' tru ', 'f alse'] AS things
        RETURN toBoolean(things) AS b
        ''')
    }
        
    @Test
    def void testTypeConversionFunctions_05() {
        RelalgParser.parse('''
        WITH [true, <invalid>] AS list
        RETURN toBoolean(list[1]) AS b
        ''')
    }
        
    @Test
    def void testTypeConversionFunctions_06() {
        RelalgParser.parse('''
        MATCH (p:Person { age: '42' })
        WITH *
        MATCH (n)
        RETURN toInteger(n.age) AS age
        ''')
    }
        
    @Test
    def void testTypeConversionFunctions_07() {
        RelalgParser.parse('''
        WITH 82.9 AS weight
        RETURN toInteger(weight)
        ''')
    }
        
    @Test
    def void testTypeConversionFunctions_08() {
        RelalgParser.parse('''
        WITH 'foo' AS foo_string, '' AS empty_string
        RETURN toInteger(foo_string) AS foo, toInteger(empty_string) AS empty
        ''')
    }
        
    @Test
    def void testTypeConversionFunctions_09() {
        RelalgParser.parse('''
        WITH [2, 2.9] AS numbers
        RETURN [n IN numbers | toInteger(n)] AS int_numbers
        ''')
    }
        
    @Test
    def void testTypeConversionFunctions_10() {
        RelalgParser.parse('''
        WITH [2, 2.9, '1.7'] AS things
        RETURN [n IN things | toInteger(n)] AS int_numbers
        ''')
    }
        
    @Test
    def void testTypeConversionFunctions_11() {
        RelalgParser.parse('''
        WITH ['2', '2.9', 'foo'] AS numbers
        RETURN [n IN numbers | toInteger(n)] AS int_numbers
        ''')
    }
        
    @Test
    def void testTypeConversionFunctions_12() {
        RelalgParser.parse('''
        MATCH p = (n)-[r:T]->()
        RETURN [x IN [1, <invalid>] | toInteger(x) ] AS list
        ''')
    }
        
    @Test
    def void testTypeConversionFunctions_13() {
        RelalgParser.parse('''
        MATCH (m:Movie { rating: 4 })
        WITH *
        MATCH (n)
        RETURN toFloat(n.rating) AS float
        ''')
    }
        
    @Test
    def void testTypeConversionFunctions_14() {
        RelalgParser.parse('''
        WITH [3.4, 3] AS numbers
        RETURN [n IN numbers | toFloat(n)] AS float_numbers
        ''')
    }
        
    @Test
    def void testTypeConversionFunctions_15() {
        RelalgParser.parse('''
        WITH 'foo' AS foo_string, '' AS empty_string
        RETURN toFloat(foo_string) AS foo, toFloat(empty_string) AS empty
        ''')
    }
        
    @Test
    def void testTypeConversionFunctions_16() {
        RelalgParser.parse('''
        WITH [3.4, 3, '5'] AS numbers
        RETURN [n IN numbers | toFloat(n)] AS float_numbers
        ''')
    }
        
    @Test
    def void testTypeConversionFunctions_17() {
        RelalgParser.parse('''
        WITH ['1', '2', 'foo'] AS numbers
        RETURN [n IN numbers | toFloat(n)] AS float_numbers
        ''')
    }
        
    @Test
    def void testTypeConversionFunctions_18() {
        RelalgParser.parse('''
        MATCH p = (n)-[r:T]->()
        RETURN [x IN [1.0, <invalid>] | toFloat(x) ] AS list
        ''')
    }
        
    @Test
    def void testTypeConversionFunctions_19() {
        RelalgParser.parse('''
        MATCH (m:Movie { rating: 4 })
        WITH *
        MATCH (n)
        RETURN toString(n.rating)
        ''')
    }
        
    @Test
    def void testTypeConversionFunctions_20() {
        RelalgParser.parse('''
        MATCH (m:Movie)
        RETURN toString(m.watched)
        ''')
    }
        
    @Test
    def void testTypeConversionFunctions_21() {
        RelalgParser.parse('''
        RETURN toString(1 < 0) AS bool
        ''')
    }
        
    @Test
    def void testTypeConversionFunctions_22() {
        RelalgParser.parse('''
        RETURN toString(true) AS bool
        ''')
    }
        
    @Test
    def void testTypeConversionFunctions_23() {
        RelalgParser.parse('''
        RETURN [x IN [1, 2.3, true, 'apa'] | toString(x) ] AS list
        ''')
    }
        
    @Test
    def void testTypeConversionFunctions_24() {
        RelalgParser.parse('''
        WITH [1, 2, 3] AS numbers
        RETURN [n IN numbers | toString(n)] AS string_numbers
        ''')
    }
        
    @Test
    def void testTypeConversionFunctions_25() {
        RelalgParser.parse('''
        MATCH p = (n)-[r:T]->()
        RETURN [x IN [1, '', <invalid>] | toString(x) ] AS list
        ''')
    }
        
}
    