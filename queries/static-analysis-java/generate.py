#!/usr/bin/env python3

import glob
import re
import sys
import xml.etree.ElementTree as ET

for xmlFileName in glob.glob("../../../jqa-java-plugin/src/main/resources/META-INF/jqassistant-rules/*.xml"):
    tree = ET.parse(xmlFileName)
    root = tree.getroot()

    for child in root:
        queryName = child.attrib['id'].replace(':', '-')
        queryDescription = child.find('description').text
        queryDescription = re.sub('\s+', ' ', queryDescription)
        cypherString = child.find('cypher').text.replace('            ', '')
        queryFileContent = "// {}\n{}".format(queryDescription, cypherString)

        cypherFileName = '{}.cypher'.format(queryName)
        with open(cypherFileName, 'w') as text_file:
            text_file.write(queryFileContent)
