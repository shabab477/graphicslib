/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1;

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
 * @author 14101138
 */
public class Main {
    
    private static final int SIZE_X = 300;
    private static final int SIZE_Y = 300;
    
    public static void main(String[] args) {
        GLCapabilities capabilities = new GLCapabilities(GLProfile.get(GLProfile.GL2));
        final GLCanvas glcanvas = new GLCanvas(capabilities);
        
        //This is where we put graphics event listener. 
        //Implement the Event Listener class to use a more object
        //oriented approach
        glcanvas.addGLEventListener(new FileReaderListener());
        
        glcanvas.setSize(SIZE_X, SIZE_Y);
        JFrame frame = new JFrame("My GL canvas");
        //Close on frame close button
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        
        frame.getContentPane().add(glcanvas);
        frame.setSize(SIZE_X, SIZE_Y);
        frame.setVisible(true);
    }
    
    static class RandomPointsListener implements GLEventListener
    {

        @Override
        public void init(GLAutoDrawable glad) {
        
        }

        @Override
        public void dispose(GLAutoDrawable glad) {
        
        }

        @Override
        public void display(GLAutoDrawable glad) {
            GL2 gl = glad.getGL().getGL2();
            gl.glBegin(GL.GL_POINTS);
            Random rand = new Random();
            for(int c = 0; c < 600; c++)
            {
                float x = rand.nextFloat() * 2 - 1;
                float y = rand.nextFloat() * 2 - 1;
                gl.glVertex2d(x, y);
            }
            
            gl.glEnd();
        }

        @Override
        public void reshape(GLAutoDrawable glad, int i, int i1, int i2, int i3) {
        
        }
        
    }
    
    static class FileReaderListener implements GLEventListener
    {

        @Override
        public void init(GLAutoDrawable glad) {
        }

        @Override
        public void dispose(GLAutoDrawable glad) {
        }

        @Override
        public void display(GLAutoDrawable glad) {
            GL2 gl = glad.getGL().getGL2();
            gl.glBegin(GL.GL_POINTS);
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(new File("points")));
                String line = null;
                while((line = reader.readLine()) != null)
                {
                    String values[] = line.split("\\s+");
                    float x = Float.valueOf(values[0]) / SIZE_X;
                    float y = Float.valueOf(values[1]) / SIZE_Y;
                    gl.glVertex2d(x, y);
                    System.out.println(values[0] + " " + values[1]);
                }
                
                
            } catch (Exception ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally
            {
                try {
                    reader.close();
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                gl.glEnd();
            }
        }

        @Override
        public void reshape(GLAutoDrawable glad, int i, int i1, int i2, int i3) {
        }
        
    }
}
