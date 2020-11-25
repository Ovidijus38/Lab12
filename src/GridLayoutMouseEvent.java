import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GridLayoutMouseEvent extends JFrame implements MouseListener {

    private JLabel jlabels[];
    private int clickEventCounter;
    private int result=-1;
    private boolean gameOver=false;
    private Color defaultColour = getBackground();

    private ImageIcon xImage = new ImageIcon("D:\\OOP_Java\\Sem3Lab12\\Lab12/images/cross.gif");
    private ImageIcon oImage = new ImageIcon("D:\\OOP_Java\\Sem3Lab12\\Lab12/images/nought.gif");

    public GridLayoutMouseEvent() {
        super("Demonstrating GridLayout & MouseEvent");

        GridLayout layout = new GridLayout(3, 3, 0,0);
        setLayout(layout);

        jlabels = new JLabel[9];

        for (int i = 0; i < jlabels.length; i++) {
            jlabels[i] = new JLabel(""+ (i+1),SwingConstants.CENTER);
            jlabels[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            add(jlabels[i]);
            jlabels[i].addMouseListener(this);
        }

        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
    }


    public static void main(String args[]) {
        GridLayoutMouseEvent b = new GridLayoutMouseEvent();
    }


    public void mouseClicked(MouseEvent e) {
        clickEventCounter++;

        JLabel label = (JLabel) e.getSource();

        if(!label.getText().equals("")){
            label.setText("");
            if(clickEventCounter%2==1)
                label.setIcon(xImage);
            else
                label.setIcon(oImage);
        }
        else {
            JOptionPane.showMessageDialog(null, "This cell has already been clicked!", "Error!!",
                    JOptionPane.ERROR_MESSAGE);
            clickEventCounter--;
        }



        if(clickEventCounter>=5&&clickEventCounter<=9) {
            result = checkForWinner();

            if (result == 1)
                JOptionPane.showMessageDialog(null, "Player 1 wins!", "Player 1 wins!!",
                        JOptionPane.INFORMATION_MESSAGE);
            else if (result == 2)
                JOptionPane.showMessageDialog(null, "Player 2 wins!", "Player 2 wins!!",
                        JOptionPane.INFORMATION_MESSAGE);
        }

        if(clickEventCounter==9 && result==0) {
            JOptionPane.showMessageDialog(null, "Neither player wins!", "A Tie!!",
                    JOptionPane.INFORMATION_MESSAGE);

            gameOver = true;
        }

        if(result==1 || result==2 || (result==0 && gameOver)) {
            JOptionPane.showMessageDialog(null, "Game Over", "Game Over!!",
                    JOptionPane.INFORMATION_MESSAGE);

             /*I have omitted about 6/7 lines of code here which reset the game back to its original state
             so the user can play again. This is for an exercise that follows this */
        }


    }


    public void mousePressed(MouseEvent e) {
        System.out.println("Mouse was pressed");
    }


    public void mouseReleased(MouseEvent e) {
        System.out.println("Mouse was released");
    }


    public void mouseEntered(MouseEvent e) {
        JLabel label = (JLabel) e.getSource();
        label.setOpaque(true);
        label.setBackground(Color.ORANGE);
    }


    public void mouseExited(MouseEvent e) {
        JLabel label = (JLabel) e.getSource();
        label.setBackground(defaultColour);
    }

    public int checkForWinner(){
        /*I have omitted about 50 lines of code here which check to see whether
        there was a winner and returns the winner. Returning 1 means player 1 wins,
        returning 2 means player 2 wins and returning 0 means neither player wins.
        This is for an exercise that follows this sample program*/
        return 0;
    }
}
