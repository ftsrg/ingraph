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
    test_file = open("../../ingraph-cypher2relalg/src-test/ingraph/cypher2relalg/tck/%sTest.xtend" % filename_without_extension, "w")

    test_header = """package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser

class %sTest {
    """ % filename_without_extension

    test_file.write(test_header)

    with open(filename, 'r') as file:
        content = file.read()

    query_pattern = re.compile('When executing query:$\s*"""(.*?)\s*"""', re.MULTILINE | re.DOTALL)
    matches = re.findall(query_pattern, content)
    i = 0
    for match in matches:
        i += 1
        indentation_pattern = re.compile('^\s*', re.MULTILINE)
        query = indentation_pattern.sub("", match)

        if "CREATE " in query:
            continue

        with open("../../queries/tck/%s_%02d.cyp" % (filename_without_extension, i), "w") as query_file:
            query_file.write(query + "\n")

        test_case = """
    @Test
    def void test%s_%02d() {
        RelalgParser.parse('''
        %s
        ''')
    }
        """ % (filename_without_extension, i, indent(query))
        test_file.write(test_case)

    test_footer = """
}
    """
    test_file.write(test_footer)
    test_file.close()
