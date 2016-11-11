# UNWIND

Let's use a simple example:

```
WITH "x" AS col1, [1,2,3] AS list, "y" AS col3
RETURN *

╒════╤════╤═════════╕
│col1│col3│list     │
╞════╪════╪═════════╡
│x   │y   │[1, 2, 3]│
└────┴────┴─────────┘
```

Using `UNWIND`, we can create a new for for each element of the `list` variable:

```
WITH "x" AS col1, [1,2,3] AS list, "y" AS col3
UNWIND list AS col2
RETURN *

╒════╤════╤════╤═════════╕
│col1│col2│col3│list     │
╞════╪════╪════╪═════════╡
│x   │1   │y   │[1, 2, 3]│
├────┼────┼────┼─────────┤
│x   │2   │y   │[1, 2, 3]│
├────┼────┼────┼─────────┤
│x   │3   │y   │[1, 2, 3]│
└────┴────┴────┴─────────┘
```

## Notes

* For incremental processing, an important design decision will be the *granularity* of notification, i.e. if a list is updated, can we get notifications for the individual elements or should we throw it (and the unwound tuples) away?

## References

* ["A Formal Presentation of MongoDB"](https://arxiv.org/abs/1603.09291)
