import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

public class DemoPanel extends JPanel {

    // Screen Settings
    final int maxCol = 15;
    final int maxRow = 10;
    final int nodeSize = 70;
    final int screenWidth = nodeSize * maxCol;
    final int screenHeight = nodeSize * maxRow;

    // Node
    Node[][] node = new Node[maxCol][maxRow];
    Node startNode, goalNode, currentNode;
    ArrayList<Node> openList = new ArrayList<>();
    ArrayList<Node> checkedList = new ArrayList<>();

    // Others
    boolean goalReached = false;
    int step = 0;

    public DemoPanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setLayout(new GridLayout(maxRow, maxCol));
        this.addKeyListener(new KeyHandler(this));
        this.setFocusable(true);

        // Place Nodes
        int col = 0;
        int row = 0;

        while (col < maxCol && row < maxRow) {
            node[col][row] = new Node(col, row);
            this.add(node[col][row]);

            col++;
            if (col == maxCol) {
                col = 0;
                row++;
            }
        }

        // Set start and goal node
        setStartNode(3, 6);
        setGoalNode(11, 3);

        // Place solid nodes
        setSolidNode(10, 2);
        setSolidNode(10, 3);
        setSolidNode(10, 4);
        setSolidNode(10, 5);
        setSolidNode(10, 6);
        setSolidNode(10, 7);
        setSolidNode(6, 2);
        setSolidNode(7, 2);
        setSolidNode(8, 2);
        setSolidNode(9, 2);
        setSolidNode(11, 7);
        setSolidNode(12, 7);
        setSolidNode(6, 1);

        // Set cost
        setCostOnNodes();
    }

    private void setStartNode(int col, int row) {
        node[col][row].setAsStart();
        startNode = node[col][row];
        currentNode = startNode;
    }

    private void setGoalNode(int col, int row) {
        node[col][row].setAsGoal();
        goalNode = node[col][row];
    }

    private void setSolidNode(int col, int row) {
        node[col][row].setAsSolid();
    }

    private void setCostOnNodes() {
        int col = 0;
        int row = 0;

        while (col < maxCol && row < maxRow) {
            getCost(node[col][row]);
            col++;
            if (col == maxCol) {
                col = 0;
                row++;
            }
        }
    }

    private void getCost(Node node) {
        // Get G cost
        int xDistance = Math.abs(node.col - startNode.col);
        int yDistance = Math.abs(node.row - startNode.row);
        node.gCost = xDistance + yDistance;

        // Get H cost
        xDistance = Math.abs(node.col - goalNode.col);
        yDistance = Math.abs(node.row - goalNode.row);
        node.hCost = xDistance + yDistance;

        // Get F cost
        node.fCost = node.gCost + node.hCost;

        // Display the cost on node
        if (node != startNode && node != goalNode) {
            node.setText("<html>F:" + node.fCost + "<br>G:" + node.gCost + "</html>");
        }
    }

    public void autoSearch() {
        while (!goalReached && step < 300) {
            int col = currentNode.col;
            int row = currentNode.row;

            currentNode.setAsChecked();
            checkedList.add(currentNode);
            openList.remove(currentNode);

            // Open the up node
            if (row - 1 >= 0) {
                openNode(node[col][row - 1]);
            }
            // Open the left node
            if (col - 1 >= 0) {
                openNode(node[col - 1][row]);
            }
            // Open the down node
            if (row + 1 < maxRow) {
                openNode(node[col][row + 1]);
            }
            // Open the right node
            if (col + 1 < maxCol) {
                openNode(node[col + 1][row]);
            }

            // Find the best node
            int bestNodeIndex = 0;
            int bestNodeFCost = 999;

            for (int i = 0; i < openList.size(); i++) {
                // Check if the node's F cost is better
                if (openList.get(i).fCost < bestNodeFCost) {
                    bestNodeIndex = i;
                    bestNodeFCost = openList.get(i).fCost;
                }
                // If the F cost is equal, check the G cost
                else if (openList.get(i).fCost == bestNodeFCost) {
                    if (openList.get(i).fCost < openList.get(bestNodeIndex).gCost) {
                        bestNodeIndex = i;
                    }
                }
            }
            // After the loop, we get the best node which is our next step
            currentNode = openList.get(bestNodeIndex);

            if (currentNode == goalNode) {
                goalReached = true;
            trackThePath();
            }
            step++;
        }
    }

    public void openNode(Node node) {
        if (!node.open && !node.checked && !node.solid) {
            // If the node is not open yet, add it to the open list
            node.setAsOpen();
            node.parent = currentNode;
            openList.add(node);
        }
    }

    private void trackThePath() {
        Node current =  goalNode;
        while (current != startNode){
            current = current.parent;
            if (current != startNode){
                current.setAsPath();
            }
        }
    }
}
