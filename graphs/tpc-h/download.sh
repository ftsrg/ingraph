#!/bin/bash

mysql -h relational.fit.cvut.cz -u guest -prelational -e 'select * from tpch.customers' | sed  's/\t/,/g' > customers.csv
mysql -h relational.fit.cvut.cz -u guest -prelational -e 'select * from tpch.orders' | sed  's/\t/,/g' > orders.csv
