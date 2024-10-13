import java.awt.*;

public class Paddle {

    private int x, y;
    private int width = 10, height = 100;
    private int speed = 5;
    private boolean up = false, down = false;

    public Paddle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);
    }

    public void move() {
        if (up && y > 0) y -= speed;
        if (down && y < 500) y += speed;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
