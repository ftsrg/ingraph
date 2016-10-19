# Relational algebra model

ingraph defines the model for representing relational algebra expressions in [Xcore](https://wiki.eclipse.org/Xcore).

Note that the model is currently **not serializable**, due to various limitations:

* The relational algebra expression may not be a tree. For example, if we looking for potential triangles with a missing edge, the expression may look like this:

    ```
           AntiJoin
          /        \
     ExpandOut      |     
         |          |
     ExpandOut  ExpandOut
          \        /     
         GetVertices
    ```

    Upon visualization, the expression if unfolded to a tree:

    ```
            AntiJoin
           /        \
     ExpandOut       |     
         |           |
     ExpandOut   ExpandOut
         |           |
    GetVertices  GetVertices
    ```


* Some parts of the model are not constrained to a hierarchy, e.g. for variables it is not trivial to find which operator should be their container.
