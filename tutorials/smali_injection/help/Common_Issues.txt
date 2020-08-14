Known issue 1: Register invalid error (Only 0-15)

Possible solution: If your smali method contains a line of code similar to "move-object/from16 v1, p0", use v1 instead of p0, then paste the caller UNDER the move-object code.
Examples:
1. move-object/from16 v0, p0 -> use v0
2. move-object/from16 v3, p0 -> use v3
