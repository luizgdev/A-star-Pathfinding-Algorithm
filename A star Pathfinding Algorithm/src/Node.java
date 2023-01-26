import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Node extends JButton implements ActionListener {
    Node parent;
    int col; // Coluna
    int row; // Linha
    int gCost; // Custo entre a posição inicial e a atual
    int hCost; // Custo entre a posição atual e o objetivo
    int fCost; // Custo total (gCost + hCost)
    boolean start;
    boolean goal;
    boolean solid;
    boolean open;
    boolean checked;


    public Node(int col, int row) {
        this.col = col;
        this.row = row;

        setBackground(Color.white);
        setForeground(Color.black);
        addActionListener(this);
    }

    public void setAsStart() {
        setBackground(Color.blue);
        setForeground(Color.white);
        setText("Start");
        start = true;
    }

    public void setAsGoal() {
        setBackground(Color.yellow);
        setForeground(Color.black);
        setText("Goal");
        goal = true;
    }

    public void setAsSolid() {
        setBackground(Color.black);
        setForeground(Color.black);
        solid = true;
    }

    public void setAsOpen() {
        open = true;
    }

    public void setAsChecked() {
        if (!start && !goal) {
            setBackground(Color.orange);
            setForeground(Color.black);
        }
        checked = true;
    }

    public void setAsPath() {
        setBackground(Color.green);
        setForeground(Color.black);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (!start) {
            setAsStart();
        } else {
            setAsGoal();
        }
    }

}
