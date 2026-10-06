import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TicTacToeFrame extends JFrame
{
    private final TicTacToeGame game = new TicTacToeGame();
    private final TicTacToeButton[][] buttons = new TicTacToeButton[3][3];
    private final JLabel turnLabel = new JLabel("Player X's turn", SwingConstants.CENTER);
    private boolean gameOver;

    public TicTacToeFrame()
    {
        super("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        turnLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        add(turnLabel, BorderLayout.NORTH);

        JPanel boardPanel = new JPanel(new GridLayout(3, 3, 5, 5));
        boardPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        ActionListener boardListener = event -> handleMove((TicTacToeButton) event.getSource());
        for (int row = 0; row < 3; row++)
        {
            for (int col = 0; col < 3; col++)
            {
                buttons[row][col] = new TicTacToeButton(row, col);
                buttons[row][col].addActionListener(boardListener);
                boardPanel.add(buttons[row][col]);
            }
        }
        add(boardPanel, BorderLayout.CENTER);

        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(event -> quitGame());
        add(quitButton, BorderLayout.SOUTH);
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent event)
            {
                quitGame();
            }
        });
        setSize(460, 520);
        setLocationRelativeTo(null);
    }

    private void handleMove(TicTacToeButton button)
    {
        if (gameOver)
            return;
        if (!game.makeMove(button.getRow(), button.getCol()))
        {
            JOptionPane.showMessageDialog(this, "That square is already taken. Choose an empty square.",
                    "Illegal move", JOptionPane.WARNING_MESSAGE);
            return;
        }

        char player = game.getCurrentPlayer();
        button.setMark(player);
        if (game.getMoveCount() >= 5 && game.isWin(player))
        {
            finishGame("Player " + player + " wins!");
        }
        else if (game.getMoveCount() >= 7 && game.isTie())
        {
            String message = game.getMoveCount() == 9
                    ? "It's a tie! The board is full."
                    : "It's a tie! Neither player can win, even with empty squares left.";
            finishGame(message);
        }
        else
        {
            game.switchPlayer();
            turnLabel.setText("Player " + game.getCurrentPlayer() + "'s turn");
        }
    }

    private void finishGame(String message)
    {
        gameOver = true;
        turnLabel.setText("Game over");
        JOptionPane.showMessageDialog(this, message, "Game result", JOptionPane.INFORMATION_MESSAGE);
        int answer = JOptionPane.showConfirmDialog(this, "Would you like to play again?",
                "Play again", JOptionPane.YES_NO_OPTION);
        if (answer == JOptionPane.YES_OPTION)
        {
            game.reset();
            for (TicTacToeButton[] row : buttons)
                for (TicTacToeButton button : row)
                    button.setMark(' ');
            gameOver = false;
            turnLabel.setText("Player X's turn");
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Thanks for playing!");
            dispose();
        }
    }

    private void quitGame()
    {
        int answer = JOptionPane.showConfirmDialog(this, "Are you sure you want to quit?",
                "Quit game", JOptionPane.YES_NO_OPTION);
        if (answer == JOptionPane.YES_OPTION)
        {
            JOptionPane.showMessageDialog(this, "Thanks for playing!");
            dispose();
        }
    }
}
