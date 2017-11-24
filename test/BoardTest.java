import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {

    @Test
    public void Dimension_3by3() {
        int[][] blocks = new int[][] { {0, 1, 2}, {3, 4, 5}, {6, 7, 8} };
        Board b = new Board(blocks);
        assertEquals(3, b.dimension());
    }

    @Test
    public void Dimension_2by2() {
        int[][] blocks = new int[][] { {0, 1,}, {2, 3,}, {4, 5} };
        Board b = new Board(blocks);
        assertEquals(2, b.dimension());
    }

    @Test
    public void ToString() {
        int[][] blocks = new int[][] { {1, 2}, {3, 4}, {5, 0} };
        Board b = new Board(blocks);
        assertEquals("2\n 1 2\n 3 4\n", b.toString());
    }

    @Test
    public void Hamming_NoneInWrongPosition_NoMoves() {
        int[][] blocks = new int[][] { {1, 2, 3}, {4, 5, 6}, {7, 8, 0} };
        Board b = new Board(blocks);
        assertEquals(0, b.hamming(), "all blocks are in final position, hamming score is 0" );
    }

    @Test
    public void Hamming_SomeBlocksInWrongPosition_NoMoves() {
        int[][] blocks = new int[][] { {8, 1, 3}, {4, 0, 2}, {7, 6, 5} };
        Board b = new Board(blocks);
        assertEquals(5, b.hamming(), "Blocks in wrong positions: 8, 1, 0, 2, 5, 6: hamming score is 5" );
    }

    @Test
    public void IsGoal_NoneInWrongPosition() {
        int[][] blocks = new int[][] { {1, 2, 3}, {4, 5, 6}, {7, 8, 0} };
        Board b = new Board(blocks);
        assertEquals(true, b.isGoal(), "all blocks are in final position, isGoal is true" );
    }

    @Test
    public void IsGoal_SomeBlocksInWrongPosition() {
        int[][] blocks = new int[][] { {8, 1, 3}, {4, 0, 2}, {7, 6, 5} };
        Board b = new Board(blocks);
        assertEquals(false, b.isGoal(), "any block not in final positions, isGoal is false. " );
    }

    @Test
    public void Manhattan_CellIsAboveAndLeftOfEndPosition() {
        int dimension = 3;
        int index = 1;
        int value = 8;
        int row = Math.abs((value - index) / dimension);
        assertEquals(2,  row , "how many rows does value need to move");

        int col = (value - index) - (dimension * row);
        assertEquals(1, col, "how many cols does value need to move");

        int manhattan = row + col;
        assertEquals(3, manhattan, "manhattan is how many rows/cols off from desired location");
    }

    @Test
    public void Manhattan_CellIsBelowAndRightOfEndPosition() {
        int dimension = 3;
        int index = 6;
        int value = 2;
        int row = Math.abs((value - index) / dimension);
        assertEquals(1, row, "2 is one row below where it ought to be");

        int col = Math.abs(value-index) - (dimension * row);
        assertEquals(1, col, "2 is one col right of where it ought to be");

        int manhattan = row + col;
        assertEquals(2, manhattan, "manhattan is how many rows/cols off from desired location");
    }

    @Test
    public void Manhattan_NoneInWrongPosition_NoMoves() {
        int[][] blocks = new int[][] { {1, 2, 3}, {4, 5, 6}, {7, 8, 0} };
        Board b = new Board(blocks);
        assertEquals(0, b.manhattan(), "all blocks are in final position, manhattan score is 0" );
    }

    @Test
    public void Manhattan_SomeBlocksInWrongPosition_NoMoves() {
        int[][] blocks = new int[][] { {8, 1, 3}, {4, 0, 2}, {7, 6, 5} };
        Board b = new Board(blocks);
        assertEquals(10, b.manhattan(), "Blocks in wrong positions: 8, 1, 0, 2, 5, 6: manhattan score is 10" );
    }
}
