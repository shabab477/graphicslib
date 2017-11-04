/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;

/**
 *
 * @author Shabab
 */
public class Main {
    
    
    private static final int SIZE_X = 1000;
    private static final int SIZE_Y = 1000;
    
    public static void main(String[] args) {
        
        GLCapabilities capabilities = new GLCapabilities(GLProfile.get(GLProfile.GL2));
        final GLCanvas glcanvas = new GLCanvas(capabilities);
        
        //This is where we put graphics event listener. 
        //Implement the Event Listener class to use a more object
        //oriented approach
        glcanvas.addGLEventListener(new SlopeIndependent());
        
        glcanvas.setSize(SIZE_X, SIZE_Y);
        JFrame frame = new JFrame("My GL canvas");
        //Close on frame close button
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        
        frame.getContentPane().add(glcanvas);
        frame.setSize(SIZE_X, SIZE_Y);
        frame.setVisible(true);
    }
    
    static class ConvertedPoint
    {
        int x;
        int y;

        public ConvertedPoint(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
        
        
    }
    
    static interface ZoneCalculator
    {
        ConvertedPoint calculate(int x, int y); 
    }
    
    
    
    static class Point
    {
        private float x1;
        private float x2;
        private float y1;
        private float y2;
        private final GL2 canvas;
        
        private final ZoneCalculator calculator;
        private static final float FACTOR = 0.01f;
        
        public Point(float x1, float x2, float y1, float y2, GL2 canvas) {
            this.x1 = x1;
            this.x2 = x2;
            this.y1 = y1;
            this.y2 = y2;
            this.canvas = canvas;
            calculator = findZone();
        }
        
        
        private ZoneCalculator findZone()
        {
            float dx = x2 - x1;
            float dy = y2 - y1;
            
            if(dx >= 0 && dy >= 0)
            {
                //z0 or z1
                if(Math.abs(dx) >= Math.abs(dy))
                {
                    //z0
                    return new ZoneCalculator() {
                        @Override
                        public ConvertedPoint calculate(int x, int y) {
                            return new ConvertedPoint(x, y);
                        }
                    };
                }
                else
                {
                    //z1
                    float temp = x1;
                    x1 = y1;
                    y1 = temp;
                    
                    temp = x2;
                    x2 = 
                    
                    return new ZoneCalculator() {
                        @Override
                        public ConvertedPoint calculate(int x, int y) {
                            
                        }
                    }
                }
            }
            else if(dx < 0 && dy >= 0)
            {
                //z2 or z3
            }
            else if(dx < 0 && dy < 0)
            {
                //z4 or z5
            }
            else
            {
                //z6 or z7
            }
        }
        
             
    }
    
    static class SlopeIndependent implements GLEventListener
    {

        @Override
        public void init(GLAutoDrawable glad) {
        
        }

        @Override
        public void dispose(GLAutoDrawable glad) {
        }

        @Override
        public void display(GLAutoDrawable drawable) {
            GL2 gl = drawable.getGL().getGL2();
            gl.glBegin(GL2.GL_POINTS);
            BufferedReader reader = null;
            
            
            gl.glVertex2i(1, 1);
            
            try {
                reader = new BufferedReader(new FileReader(new File("slope_points")));
                String line = null;
                while((line = reader.readLine()) != null)
                {
                    String points[] = line.split("\\s+");
                    float x1 = Float.parseFloat(points[0]) / SIZE_X;
                    float y1 = Float.parseFloat(points[1]) / SIZE_Y;
                    float x2 = Float.parseFloat(points[2]) / SIZE_X;
                    float y2 = Float.parseFloat(points[3]) / SIZE_Y;
                    //new Point(x1, y1, x2, y2).draw(gl);
                    
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally
            {
                try {
                    reader.close();
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                finally
                {
                    gl.glEnd();
                }
                
            }
            
        }

        @Override
        public void reshape(GLAutoDrawable glad, int i, int i1, int i2, int i3) {
        
        }
        
    }
}
