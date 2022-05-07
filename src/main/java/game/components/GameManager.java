package game.components;

import javax.swing.JPanel;
import javax.swing.Timer;

import graphics.Window;

import java.awt.Graphics;   
import java.awt.Point;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

import static utils.UtilLogger.log;
import static java.util.logging.Level.INFO;

public class GameManager extends JPanel implements Input
{
    public GameManager() {
        paddle = new Paddle(new Point(Window.getWidth() / 2 - Paddle.xOffset, Window.getHeight() - Paddle.yOffset), new Dimension(60, 10));
        ball = new Projectile(new Point(paddle.x + Projectile.xOffset , paddle.y - Projectile.yOffset), new Dimension(10, 10));
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        timer = new Timer(delay, this);
		timer.start(); // Sends action events
    }

    public void paint(Graphics g) {
        final Graphics graphics = g;
        super.paint(g);

        try { paddle.draw(graphics); } catch (IOException e) { e.printStackTrace(); }
        try { ball.draw(graphics); } catch (IOException e) { e.printStackTrace(); }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            movePaddle(Direction.RIGHT);
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT){
            movePaddle(Direction.LEFT);
        }
        
        if (e.getKeyCode() == KeyEvent.VK_SPACE 
         || e.getKeyCode() == KeyEvent.VK_RIGHT
         || e.getKeyCode() == KeyEvent.VK_LEFT 
         && Projectile.isIdle) {
            isPlaying = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE && Projectile.isIdle)
            ball.randomize();
        
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) { log(INFO, Character.toString(e.getKeyChar())); }

    @Override
    public void keyReleased(KeyEvent e) { log(INFO, Character.toString(e.getKeyChar())); }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(isPlaying) { 
            repaint();
            ball.update();
            if (Projectile.isIdle){
                ball.x = paddle.x + Projectile.xOffset;
                ball.y = paddle.y - Projectile.yOffset;
            }
            if(ball.x > Window.dimension.width - (ball.width * 2) || ball.x <= 0) { ball.dispX = -ball.dispX; }
            else if (ball.y <= 0 - ball.height || (ball.intersects(paddle))) ball.dispY = -ball.dispY;
            else if (ball.y > Window.dimension.height) Projectile.isIdle = true; 
            repaint();
        }
    }

    private void movePaddle(Direction direction) {
        repaint(paddle.x, paddle.y, paddle.width, paddle.height);
        paddle.move(direction);
        repaint(paddle.x, paddle.y, paddle.width, paddle.height);
    }

    private final int delay = 5;
    private boolean isPlaying = false;
    private Timer timer;
    private Paddle paddle; 
    private Projectile ball; 
}
