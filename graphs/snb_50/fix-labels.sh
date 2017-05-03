#!/bin/bash

sed -i "s/|city$/|City/" place$POSTFIX
sed -i "s/|country$/|Country/" place$POSTFIX
sed -i "s/|continent$/|Continent/" place$POSTFIX
sed -i "s/|company|/|Company|/" organisation$POSTFIX
sed -i "s/|university|/|University|/" organisation$POSTFIX
