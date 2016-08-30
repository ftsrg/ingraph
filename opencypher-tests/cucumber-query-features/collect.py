#!/usr/bin/env python3
import glob

import re
from collections import defaultdict

import sys

filenames = glob.glob('*.feature')
for filename in filenames:
    print(filename)
    with open(filename, 'r') as file:
        content = file.read()

    query_pattern = re.compile('When executing query:$\s*"""(.*?)\s*"""', re.MULTILINE | re.DOTALL)
    matches = re.findall(query_pattern, content)
    i = 0
    for match in matches:
        i += 1
        indentation_pattern = re.compile('^\s*', re.MULTILINE)
        match_without_indentation = indentation_pattern.sub("", match)
        match_file = open("queries/%s-%02d.cyp" % (filename, i), "w")
        match_file.write(match_without_indentation)
        match_file.close()
