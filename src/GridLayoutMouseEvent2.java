import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GridLayoutMouseEvent2 extends JFrame implements MouseListener {

    private JLabel jlabels[];
    private int clickEventCounter;
    private int result=-1;
    private boolean gameOver=false;
    private Color defaultColour = getBackground();

    private ImageIcon xImage = new ImageIcon("labsheet12/sampleprogram2/images/cross.gif");
    private ImageIcon oImage = new ImageIcon("labsheet12/sampleprogram2/images/nought.gif");

    public GridLayoutMouseEvent2() {
        super("Demonstrating GridLayout & MouseEvent");

        GridLayout layout = new GridLayout(3, 3, 0,0);
        setLayout(layout);

        jlabels = new JLabel[9];

        for (int i = 0; i < jlabels.length; i++) {
            jlabels[i] = new JLabel(""+(i+1),SwingConstants.CENTER);
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

            for (int i = 0; i < jlabels.length; i++) {
                jlabels[i].setIcon(null);
                jlabels[i].setText(""+ (i+1));

            }
            gameOver=false;
            clickEventCounter=0;
            result=-1;
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
        for(int i=0;i<9;i+=3) {
            Icon labelA = jlabels[i].getIcon();
            Icon labelB = jlabels[i+1].getIcon();
            Icon labelC = jlabels[i+2].getIcon();

            if(labelA==null || labelB==null || labelC == null) {
                //continue on to the next iteration of the loop
            }

            else if (labelA.equals(xImage) && labelB.equals(xImage) && labelC.equals(xImage))
                return 1;
            else if (labelA.equals(oImage) && labelB.equals(oImage) && labelC.equals(oImage))
                return 2;
        }

        for(int i=0;i<3;i+=1) {
            Icon labelA = jlabels[i].getIcon();
            Icon labelB = jlabels[i+3].getIcon();
            Icon labelC = jlabels[i+6].getIcon();

            if(labelA==null || labelB==null || labelC == null) {
                //continue on to the next iteration of the loop
            }

            else if (labelA.equals(xImage) && labelB.equals(xImage) && labelC.equals(xImage))
                return 1;
            else if (labelA.equals(oImage) && labelB.equals(oImage) && labelC.equals(oImage))
                return 2;
        }

        if(jlabels[0].getIcon() == null || jlabels[4].getIcon() == null || jlabels[8].getIcon() == null) {
            //do nothing, just move on to check other diagonal
        }

        else if(jlabels[0].getIcon().equals(xImage) && jlabels[4].getIcon().equals(xImage) && jlabels[8].getIcon().equals(xImage))
            return 1;
        else if(jlabels[0].getIcon().equals(oImage) && jlabels[4].getIcon().equals(oImage) && jlabels[8].getIcon().equals(oImage))
            return 2;

        if(jlabels[2].getIcon() == null || jlabels[4].getIcon() == null || jlabels[6].getIcon() == null) {
            return 0;
        }

        if(jlabels[2].getIcon().equals(xImage) && jlabels[4].getIcon().equals(xImage) && jlabels[6].getIcon().equals(xImage))
            return 1;
        else if(jlabels[2].getIcon().equals(oImage) && jlabels[4].getIcon().equals(oImage) && jlabels[6].getIcon().equals(oImage))
            return 2;

        return 0;
    }
}
