import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements Runnable, KeyListener {

    private Paddle leftPaddle, rightPaddle;
    private Ball ball;
    private Thread gameThread;
    private boolean running, paused;
    private int leftScore, rightScore;

    public GamePanel() {
        this.setPreferredSize(new Dimension(800, 600));
        this.setBackground(Color.BLACK);
        this.addKeyListener(this);
        this.setFocusable(true);

        leftPaddle = new Paddle(20, 250);  // Left side paddle
        rightPaddle = new Paddle(760, 250); // Right side paddle
        ball = new Ball(400, 300); // Start ball in center

        startGame();
    }

    public void startGame() {
        running = true;
        paused = false;
        leftScore = 0;
        rightScore = 0;
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (running) {
            if (!paused) {
                update();
                repaint();
            }
            try {
                Thread.sleep(16); // Approx 60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void update() {
        leftPaddle.move();
        rightPaddle.move();
        ball.move(leftPaddle, rightPaddle);

        // Check for scoring
        if (ball.getX() <= 0) { // Right player scores
            rightScore++;
            ball.reset();
        }
        if (ball.getX() >= 780) { // Left player scores
            leftScore++;
            ball.reset();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw paddles and ball
        leftPaddle.draw(g);
        rightPaddle.draw(g);
        ball.draw(g);

        // Draw Scoreboard
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("Mario | " + leftScore, 50, 30);
        g.drawString("Luigi | " + rightScore, 600, 30);

        // Display pause state if paused
        if (paused) {
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.drawString("PAUSED", 300, 300);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) leftPaddle.setUp(true);
        if (e.getKeyCode() == KeyEvent.VK_S) leftPaddle.setDown(true);
        if (e.getKeyCode() == KeyEvent.VK_UP) rightPaddle.setUp(true);
        if (e.getKeyCode() == KeyEvent.VK_DOWN) rightPaddle.setDown(true);

        // Pause/Resume with Space Bar
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            paused = !paused;
        }

        // Restart the game with R key
        if (e.getKeyCode() == KeyEvent.VK_R) {
            leftScore = 0;
            rightScore = 0;
            ball.reset();
            paused = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) leftPaddle.setUp(false);
        if (e.getKeyCode() == KeyEvent.VK_S) leftPaddle.setDown(false);
        if (e.getKeyCode() == KeyEvent.VK_UP) rightPaddle.setUp(false);
        if (e.getKeyCode() == KeyEvent.VK_DOWN) rightPaddle.setDown(false);
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}
