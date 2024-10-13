import java.awt.*;

public class Ball {

    private int x, y;
    private int size = 20;
    private int xVelocity = 4, yVelocity = 4;

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, size, size);
    }

    public void move(Paddle leftPaddle, Paddle rightPaddle) {
        x += xVelocity;
        y += yVelocity;

        // Collision with top and bottom walls
        if (y <= 0 || y >= 580) {
            yVelocity = -yVelocity;
        }

        // Collision with paddles
        if (getBounds().intersects(leftPaddle.getBounds()) || getBounds().intersects(rightPaddle.getBounds())) {
            xVelocity = -xVelocity;
        }
    }

    public void reset() {
        x = 400;
        y = 300;
        xVelocity = -xVelocity; // Ball moves toward the last scorer
    }

    public int getX() {
        return x;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, size, size);
    }
}
