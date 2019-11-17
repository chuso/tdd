# Draughts week 4

We need to add new functinalities to our game. In order to do this, we will follow a test-driven design approach, so for each new behaviour we will:

1. Create a single test that check it.
2. Add a minimum maybe dirty code covering to pass the test.
3. Refactor if needed.

Each one of these steps will contain its own commit, just for educational purpose. 

Main additions:
* Builder to be able to test without mocking a lot of classes (whitebox test: fragile) and without defining the steps to reach that state (easier to read and maintain).
* Create a Pawn class with its own validations. Piece is now abstract.
* Move Draught specific validation to its class.
* Cover and implement a `between()` method to get the list of Coordinates between two pieces.
* Create an abstract method `getEatenCoordinate()` in Piece. The logic to know which piece should be eaten depends on its specific implementation of Piece.

Missing actionable: implement the blocking logic (i.e. when an player is not able to neither move nor eat any piece).
