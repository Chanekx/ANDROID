import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// Pacman
public class Pacman extends JFrame implements KeyListener {

    PacmanCanvas canvas;
    Timer timer;

    public Pacman() {
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Circulado Pacman v1.0");
        //
        canvas = new PacmanCanvas(this);
        timer = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                canvas.movePacmen();
                canvas.repaint();
            }
        });

        add(canvas);
        //
        setVisible(true);
        addKeyListener(this);

        timer.start();
    }

    public void keyTyped(KeyEvent k) {
    }

    public void keyReleased(KeyEvent k) {
    }

    public void keyPressed(KeyEvent k) {
        int keycode = k.getKeyCode();
        switch (keycode) {
            // Player 1 controls
            case KeyEvent.VK_RIGHT:
                canvas.direction = 0;
                break;
            case KeyEvent.VK_LEFT:
                canvas.direction = 1;
                break;
            case KeyEvent.VK_DOWN:
                canvas.direction = 2;
                break;
            case KeyEvent.VK_UP:
                canvas.direction = 3;
                break;

            // Player 2 controls (using AWSD keys)
            case KeyEvent.VK_D:
                canvas.direction2 = 0;
                break;
            case KeyEvent.VK_A:
                canvas.direction2 = 1;
                break;
            case KeyEvent.VK_S:
                canvas.direction2 = 2;
                break;
            case KeyEvent.VK_W:
                canvas.direction2 = 3;
                break;
        }
    }

    //
    static public void main(String... args) {
        new Pacman();
    }
}

class PacmanCanvas extends JPanel {

    private Pacman pacman;
    int x, y;
    int startAngle, arcLen;
    int direction;

    // Second Pacman variables
    int x2, y2;
    int startAngle2, arcLen2;
    int direction2;

    public PacmanCanvas(Pacman pacman) {
        this.pacman = pacman;
        startAngle = 30;
        arcLen = 300;
        direction = 0;

        // Initialize second Pacman variables
        x2 = 100;
        y2 = 100;
        startAngle2 = 120;
        arcLen2 = 300;
        direction2 = 2;
    }

    public void movePacmen() {
        // First Pacman movement
        switch (direction) {
            case 0: // right
                if (x < getWidth() - 50) {
                    x += 5;
                    startAngle = 30;
                    arcLen = 300;
                }
                break;
            case 1: // left
                if (x > 0) {
                    x -= 5;
                    startAngle = 150;
                    arcLen = -300;
                }
                break;
            case 2: // down
                if (y < getHeight() - 50) {
                    y += 5;
                    startAngle = -60;
                    arcLen = 300;
                }
                break;
            case 3: // up
                if (y > 0) {
                    y -= 5;
                    startAngle = 60;
                    arcLen = -300;
                }
        }

        // Second Pacman movement
        switch (direction2) {
            case 0: // right
                if (x2 < getWidth() - 50) {
                    x2 += 5;
                    startAngle2 = 30;
                    arcLen2 = 300;
                }
                break;
            case 1: // left
                if (x2 > 0) {
                    x2 -= 5;
                    startAngle2 = 150;
                    arcLen2 = -300;
                }
                break;
            case 2: // down
                if (y2 < getHeight() - 50) {
                    y2 += 5;
                    startAngle2 = -60;
                    arcLen2 = 300;
                }
                break;
            case 3: // up
                if (y2 > 0) {
                    y2 -= 5;
                    startAngle2 = 60;
                    arcLen2 = -300;
                }
        }

        // Check for collision
        if (collidesWith(x, y, x2, y2)) {
            // Pacman 1 wins
            System.out.println("Pacman 1 wins!");
            resetPositions();
        } else if (collidesWith(x2, y2, x, y)) {
            // Pacman 2 wins
            System.out.println("Pacman 2 wins!");
            resetPositions();
        }
    }

    private boolean collidesWith(int x1, int y1, int x2, int y2) {
        int dx = x1 - x2;
        int dy = y1 - y2;
        int distance = (int) Math.sqrt(dx * dx + dy * dy);
        return distance < 50;
    }

    private void resetPositions() {
        x = getWidth() - 50;
        y = 0;
        direction = 1;

        x2 = 0;
        y2 = 0;
        direction2 = 0;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, getWidth(), getHeight());
        // the first pacman
        g.setColor(Color.YELLOW);
        g.fillArc(x, y, 50, 50, startAngle, arcLen);
        // the second pacman
        g.setColor(Color.RED);
        g.fillArc(x2, y2, 50, 50, startAngle2, arcLen2);
    }
}
