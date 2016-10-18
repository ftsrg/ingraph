#!/usr/bin/env python3

import glob

import os
import re
from collections import defaultdict

import sys

def indent(lines):
    padding = '        '
    return ('\n' + padding).join(lines.split('\n'))

filenames = glob.glob('*.feature')
for filename in filenames:
    filename_without_extension = os.path.splitext(filename)[0]
    test_file = open("../ingraph-cypher2relalg/src-test/ingraph/cypher2relalg/tck/%sParserTest.xtend" % filename_without_extension, "w")

    test_header = """package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.Cypher2RelAlg
import ingraph.cypherparser.CypherParser
import ingraph.cypherparser.CypherUtil

class %sParserTest {
    """ % filename_without_extension

    test_file.write(test_header)

    with open(filename, 'r') as file:
        content = file.read()

    indentation_pattern = re.compile('^\s*', re.MULTILINE)
    query_pattern = re.compile('(Scenario: .*?)When executing query:$\s*"""(.*?)\s*"""$\s*Then (.*?)$', re.MULTILINE | re.DOTALL)
    matches = re.findall(query_pattern, content)

    i = 0
    for match in matches:
        scenario = match[0]
        query = indentation_pattern.sub("", match[1])
        then = match[2]
        if "SyntaxError" in then:
            continue

        i += 1

        if ("CREATE " in query) or ("DELETE " in query) or ("MERGE " in query) or ("REMOVE " in query) or ("SET " in query):
            continue

        with open("../queries/tck/%s_%02d.cyp" % (filename_without_extension, i), "w") as query_file:
            query_file.write(query + "\n")

        test_name = "%s_%02d" % (filename_without_extension, i)
        test_case = """
    /*
    %s*/
    @Test
    def void test%s() {
        val cypher = CypherParser.parseString('''
        %s
        ''')
        CypherUtil.save(cypher, "../cypxmi/%s")
        Cypher2RelAlg.processCypher(cypher)
    }
""" % (scenario, test_name, indent(query), test_name)
        test_file.write(test_case)

    test_footer = """
}
"""
    test_file.write(test_footer)
    test_file.close()
