package Info;

import javax.swing.*;

public class GameInfo
{
    public static final int MAXROW = 15;
    public static final int MAXCOL = 15;
    public ePlayerType[][] board = new ePlayerType[MAXROW][MAXCOL];
    public PlayerInfo[] players = new PlayerInfo[ePlayerType.End.ordinal()];
    public ePlayerType order = ePlayerType.End;
    public int prevPutRow = -1;
    public int prevPutCol = -1;

    public GameInfo()
    {
        ResetBoard();
    }
    public void ResetBoard()
    {
        for(int i = 0; i < MAXROW; ++i)
            for(int j = 0; j < MAXCOL;++ j)
                board[i][j] = ePlayerType.End;
    }
    public boolean PutStone(int row, int col)
    {
        if(putPossible(row, col) == false)
            return false;

        board[row][col] = order;
        prevPutRow = row;
        prevPutCol = col;
        return true;
    }
    public void Undo()
    {
        board[prevPutRow][prevPutCol] = ePlayerType.End;
        players[order.ordinal()].undoCount -= 1;
    }
    public void ChangeOrder() { order = order == ePlayerType.First ? ePlayerType.Second : ePlayerType.First;}
    private boolean putPossible(int row, int col)
    {
        return board[row][col] == ePlayerType.End;
    }

    // 승리 조건 검사
    public boolean CheckWin(int row, int col)
    {
        return checkDirection(row, col, 1, 0) || // 가로
                checkDirection(row, col, 0, 1) || // 세로
                checkDirection(row, col, 1, 1) || // 대각선 ↘
                checkDirection(row, col, 1, -1);  // 대각선 ↙
    }

    private boolean checkDirection(int row, int col, int dr, int dc)
    {
        int count = 1;
        count += countStones(row, col, dr, dc);
        count += countStones(row, col, -dr, -dc);
        return count >= 5;
    }

    private int countStones(int row, int col, int dr, int dc)
    {
        int r = row + dr, c = col + dc;
        int count = 0;

        while (r >= 0 && r < MAXROW && c >= 0 && c < MAXCOL &&
                board[r][c] == order)
        {
            count++;
            r += dr;
            c += dc;
        }
        return count;
    }
}
