import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();                          // Instanciação da janela
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Permite a janela fechar
        window.setResizable(true);                             // Permite que a janela possa ser maximizada
        window.setTitle("A* Pathfinding Algorithm");           // Define o nome da janela
        window.add(new DemoPanel());                           // Adiciona o DemoPanel na janela

        window.pack(); //
        window.setLocationRelativeTo(null); //
        window.setVisible(true); //
    }
}