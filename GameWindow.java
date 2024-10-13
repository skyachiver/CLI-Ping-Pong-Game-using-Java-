import javax.swing.JFrame;

public class GameWindow {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Ping Pong Game");
        GamePanel panel = new GamePanel();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }
}
