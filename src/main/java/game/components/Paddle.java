package game.components;

import game.Player;

import java.awt.Point;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

import java.io.IOException;

public class Paddle extends GameObject
{
    public static int displacement = 10;
    public static int yOffset = 10;
    public static int xOffset = 30;

    Paddle(Point point, Dimension dimension) { super(point, dimension); }

    /**
     * Move the paddle with safe guard
     * @param direction
     */
    public void move(Direction direction, double panelWidth) {
        if (Direction.LEFT == direction) {
            if (x - displacement >= 0 )
                x -= displacement;
        } 
        else if (Direction.RIGHT == direction) {
            if ((x + width + displacement) <= panelWidth) 
                x += displacement;
        }
    }

    /**
     * Draws the paddle
     * @param g
     * @throws IOException
     */
    public void draw(Graphics g) throws IOException {
        // g.setColor(Color.RED);
        g.drawImage(loadBufferedImage("paddle.png"), x, y, dimension.width, dimension.height, null); 
        g.setColor(Color.RED);
        g.setFont(new Font(Font.MONOSPACED, Font.ITALIC, 10));
        g.drawString(Player.name, x+12, y+7);
    }

    /**
     * Change the displacement to control the paddle speed
     * @param displacement
     */
    public void setDisplacement(int displacement) { Paddle.displacement = displacement; }
    
    /**
     * Changes the width to control the game difficulty
     * @param width
     */
    public void setWidth(int width) { dimension.width = width; }
}
