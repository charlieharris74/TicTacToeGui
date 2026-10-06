public class TicTacToeGame
{
    private final char[][] board = new char[3][3];
    private char currentPlayer;
    private int moveCount;

    public TicTacToeGame()
    {
        reset();
    }

    public void reset()
    {
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
                board[row][col] = ' ';
        currentPlayer = 'X';
        moveCount = 0;
    }

    public char getCurrentPlayer() { return currentPlayer; }
    public int getMoveCount() { return moveCount; }

    public boolean makeMove(int row, int col)
    {
        if (row < 0 || row > 2 || col < 0 || col > 2 || board[row][col] != ' ')
            return false;
        board[row][col] = currentPlayer;
        moveCount++;
        return true;
    }

    public void switchPlayer()
    {
        currentPlayer = currentPlayer == 'X' ? 'O' : 'X';
    }

    public boolean isWin(char player)
    {
        for (int i = 0; i < 3; i++)
        {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player)
                return true;
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player)
                return true;
        }
        return (board[0][0] == player && board[1][1] == player && board[2][2] == player)
                || (board[0][2] == player && board[1][1] == player && board[2][0] == player);
    }

    public boolean isTie()
    {
        if (moveCount < 7 || isWin('X') || isWin('O'))
            return false;
        return !canStillWin('X') && !canStillWin('O');
    }

    // A player can still win if at least one line has no opponent marks.
    private boolean canStillWin(char player)
    {
        char opponent = player == 'X' ? 'O' : 'X';
        for (int i = 0; i < 3; i++)
        {
            if (board[i][0] != opponent && board[i][1] != opponent && board[i][2] != opponent)
                return true;
            if (board[0][i] != opponent && board[1][i] != opponent && board[2][i] != opponent)
                return true;
        }
        return (board[0][0] != opponent && board[1][1] != opponent && board[2][2] != opponent)
                || (board[0][2] != opponent && board[1][1] != opponent && board[2][0] != opponent);
    }
}
