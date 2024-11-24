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
    public void PutStone(int row, int col)
    {
        board[row][col] = order;
        prevPutRow = row;
        prevPutCol = col;
    }
    public void Undo()
    {
        board[prevPutRow][prevPutCol] = ePlayerType.End;
        players[order.ordinal()].undoCount -= 1;
    }
    public void ChangeOrder() { order = order == ePlayerType.First ? ePlayerType.Second : ePlayerType.First;}
    public boolean PutPossible(int row, int col)
    {
        return board[row][col] == ePlayerType.End;
    }

    public boolean Is33(int row, int col)
    {
        if(order != ePlayerType.First)
            return false;

        board[row][col] = ePlayerType.First;

        int black3Count = 0;
        if(oneLineCheck(row, col, 1, 0))
            black3Count += 1;
        if(oneLineCheck(row, col, 0, 1))
            black3Count += 1;
        if(oneLineCheck(row, col, 1, 1))
            black3Count += 1;
        if(oneLineCheck(row, col, 1, -1))
            black3Count += 1;

        board[row][col] = ePlayerType.End;

        return black3Count >= 2;
    }
    private boolean oneLineCheck(int row, int col, int dr, int dc)
    {
        int[] dp = new int[7];
        int _dr = -3 * dr;
        int _dc = -3 * dc;

        int r = row + _dr;
        int c = col + _dc;

        int whiteCount = 0;
        if(r >= 0 && r < MAXROW && c >= 0 && c < MAXCOL)
        {
            if(board[r][c] == ePlayerType.First)
                dp[0] = 1;
            else if(board[r][c] == ePlayerType.Second)
                whiteCount += 1;
        }

        _dr += dr;
        _dc += dc;


        int maxContinuesBlackCount = 0;
        for(int i = 1; i < 7; ++i)
        {
            r = row + _dr;
            c = col + _dc;
            if(r >= 0 && r < MAXROW && c >= 0 && c < MAXCOL)
            {
                if(board[r][c] == ePlayerType.Second)
                {
                    dp[i] = 0;
                    whiteCount += 1;
                }
                else if(board[r][c] == ePlayerType.First)
                    dp[i] = dp[i - 1] + 1;
                else
                    dp[i] = dp[i - 1];
            }

            maxContinuesBlackCount = Math.max(maxContinuesBlackCount, dp[i]);

            _dr += dr;
            _dc += dc;
        }
        return maxContinuesBlackCount == 3 && whiteCount < 2;
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
