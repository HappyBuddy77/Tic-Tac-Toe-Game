package eecs1022.lab8.tictactoe.model;

public class Game {
    private String nameX;
    private String nameO;
    private String first;
    private char playfirst;
    private String second;
    private int turn;
    private final int end = 9;
    private char[][] board;
    private String invalidRow;
    private String invalidColumn;
    private String invalidPosition;
    private String invalidTie;
    private String invalidWin;

    public Game(String nameX, String nameO) {
        this.nameX = nameX;
        this.nameO = nameO;
        turn = 0;
        first = nameX;
        second = nameO;
        playfirst = 'x';
        invalidRow = "";
        invalidColumn = "";
        invalidPosition = "";
        invalidTie = "";
        invalidWin = "";
        board = new char[][] {
                {'_', '_', '_'},
                {'_', '_', '_'},
                {'_', '_', '_'}
        };
    }
    public void setWhoPlaysFirst(char c) {
        if(c == 'o') {
            first = nameO;
            second = nameX;
            playfirst = 'o';
        }
        else if(c == 'x') {
            first = nameX;
            second = nameO;
            playfirst = 'x';
        }

    }
    public int Winner() {
        int samer = 0;
        int samec = 0;
        int win = 0;
        for(int row = 0; row < board.length; row ++) {
            for(int col = 0; col < board[row].length - 1; col ++) {
                if(board[row][col] != '_' && board[row][col] == board[row][col + 1]) {
                    samer += 1;
                }
            }
            if(samer == 2) {
                win = 1;
            }
            samer = 0;

        }
        for(int col = 0; col < board.length; col ++) {
            for(int row = 0; row < board[col].length - 1; row ++) {
                if(board[row][col] != '_' && board[row][col] == board[row + 1][col]) {
                    samec += 1;
                }
            }
            if(samec == 2) {
                win = 1;
            }
            samec = 0;

        }
        if(board[0][0] != '_' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            win = 1;
        }
        if(board[0][2] != '_' &&board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            win = 1;
        }
        return win;
    }
    public String getCurrentPlayer() {
        String current;
        int win = Winner();
        if(win != 1 && turn < end) {
            if (turn % 2 == 1) {
                current = second;
            } else {
                current = first;
            }
        }
        else {
            current = null;
        }
        return current;
    }
    public String getStatus() {
        String stat;
        int win = Winner();
        if(!invalidWin.equals("")) {
            stat = invalidWin;
            invalidWin = "";
        }
        else if(!invalidTie.equals("")) {
            stat = invalidTie;
            invalidTie = "";
        }
        else if(!invalidRow.equals("")) {
            stat = invalidRow;
            invalidRow = "";
        }
        else if(!invalidColumn.equals("")) {
            stat = invalidColumn;
            invalidColumn = "";
        }
        else if(!invalidPosition.equals("")) {
            stat = invalidPosition;
            invalidPosition = "";
        }
        else if(win == 1) {
            if (turn % 2 == 1) {
                stat = "Game is over with " + first + " being the winner.";
            } else {
                stat = "Game is over with " + second + " being the winner.";
            }

        }
        else if(win != 1 && turn >= end) {
            stat = "Game is over with a tie between " + nameX + " and " + nameO + ".";
        }
        else {
            if(turn % 2 == 1) {
                stat = second + "'s turn to play...";
            }
            else {
                stat = first + "'s turn to play...";
            }
        }
        return stat;
    }
    public char[][] getBoard() {
        return board;
    }
    public void move(int r, int c) {
        int win = Winner();
        if(win == 1) {
            invalidWin = "Error: game is already over with a winner.";
        }
        else if(turn >= end) {
            invalidTie = "Error: game is already over with a tie.";
        }
        else if(r > 3 || r < 1) {
            invalidRow = "Error: row " + r + " is invalid.";
        }
        else if(c > 3 || c < 1) {
            invalidColumn = "Error: col " + c + " is invalid.";
        }
        else {
            if(board[r - 1][c - 1] != '_') {
                invalidPosition = "Error: slot @ (" + r + ", " + c + ") is already occupied.";
            }
            else {
                    if(playfirst == 'x') {
                        if (turn % 2 == 1) {
                            board[r - 1][c - 1] = 'o';
                        }
                        else {
                            board[r - 1][c - 1] = 'x';
                        }
                    }
                    else {
                        if (turn % 2 == 1) {
                            board[r - 1][c - 1] = 'x';
                        }
                        else {
                            board[r - 1][c - 1] = 'o';
                        }
                    }
                    turn += 1;
            }
        }
    }
}
