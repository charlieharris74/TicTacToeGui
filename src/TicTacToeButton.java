import javax.swing.JButton;
import java.awt.Font;

public class TicTacToeButton extends JButton
{
    private final int row;
    private final int col;
    private char mark = ' ';

    public TicTacToeButton(int row, int col)
    {
        this.row = row;
        this.col = col;
        setFont(new Font("SansSerif", Font.BOLD, 64));
        setFocusPainted(false);
    }

    public int getRow() { return row; }
    public int getCol() { return col; }
    public char getMark() { return mark; }

    public void setMark(char mark)
    {
        this.mark = mark;
        setText(mark == ' ' ? "" : String.valueOf(mark));
    }
}
