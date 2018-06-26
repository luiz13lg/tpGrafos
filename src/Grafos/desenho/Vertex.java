/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafos.desenho;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 *
 * @author Danilo Medeiros Eler
 */
public class Vertex {
    private float x;
    private float y;
    private int ray = 8;
    private Boolean selected = true;
    private Color color;// = Color.RED;
    private int ID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void draw(java.awt.Graphics2D g2) {
        if (this.selected) {
            g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 1.0f));
            g2.setStroke(new java.awt.BasicStroke(3.0f));
        } else { //not selected and there is not a global vertex selected
            g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 0.2f));
            g2.setStroke(new java.awt.BasicStroke(1.5f));
        }
        
        g2.setColor(this.color);        
        g2.fillOval(((int) this.x) - this.getRay(), ((int) this.y)
                - this.getRay(), this.getRay() * 2, this.getRay() * 2);

        g2.setColor(Color.BLACK);
        g2.drawOval(((int) this.x) - this.getRay(), ((int) this.y)
                - this.getRay(), this.getRay() * 2, this.getRay() * 2);
        
        drawText(g2, new Point((int) x, (int) y),   //peso
        new Point((int) x, (int) y),                //
        String.valueOf(ID), 70); 
        
        g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 1.0f));      
    }

    public float getX() {
        return x;
    }

    public void setX(float X) {
        this.x = X;
    }

    public float getY() {
        return y;
    }

    public void setY(float Y) {
        this.y = Y;
    }

    public int getRay() {
        return ray;
    }

    public void setRay(int ray) {
        this.ray = ray;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Boolean isSelected() {
        return selected;
    }

    public void setSelected(Boolean flag) {
        this.selected = flag;
    }
    
    ///////////////////////////////////////////////
    private void drawText(Graphics2D g2, Point s, Point t, String text, int deslocamento) {
        g2.setFont(new Font("Verdana", Font.BOLD, 12));
        
        g2.setColor(Color.WHITE);
        g2.drawString(text, x - 4, y + 4);
    }
    //////////////////////////////////////////////
}
