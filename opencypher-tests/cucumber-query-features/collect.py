#!/usr/bin/env python3
import glob

import os
import re
from collections import defaultdict

import sys

filenames = glob.glob('*.feature')
for filename in filenames:
    filename_without_extension = os.path.splitext(filename)[0]
    test_file = open("../../ingraph-cypher2relalg/src-test/ingraph/cypher2relalg/tck/%sTest.java" % filename_without_extension, "w")

    test_header = """package ingraph.cypher2relalg.tck;

import org.junit.Test;

import ingraph.cypher2relalg.RelalgParser;

public class %sTest {
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
        match_without_indentation = indentation_pattern.sub("", match)
        match_file = open("../../queries/tck/%s_%02d.cyp" % (filename_without_extension, i), "w")
        match_file.write(match_without_indentation + "\n")
        match_file.close()

        test_case = """
    @Test
    public void test%s_%02d() {
        RelalgParser.parse("tck/%s_%02d");
    }
    """ % (filename_without_extension, i, filename_without_extension, i)
        test_file.write(test_case)

    test_footer = """
}
    """
    test_file.write(test_footer)
    test_file.close()
