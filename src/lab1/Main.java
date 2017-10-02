/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1;

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
    
    
    public static void main(String[] args) {
        GLCapabilities capabilities = new GLCapabilities(GLProfile.get(GLProfile.GL2));
        final GLCanvas glcanvas = new GLCanvas(capabilities);
        
        //This is where we put graphics event listener. 
        //Implement the Event Listener class to use a more object
        //oriented approach
        glcanvas.addGLEventListener(new GLEventListener() {

            @Override
            public void init(GLAutoDrawable glad) {
            }

            @Override
            public void dispose(GLAutoDrawable glad) {
            }

            @Override
            public void display(GLAutoDrawable glad) {
            }

            @Override
            public void reshape(GLAutoDrawable glad, int i, int i1, int i2, int i3) {
            }
        });
        
        glcanvas.setSize(300, 300);
        JFrame frame = new JFrame("My GL canvas");
        //Close on frame close button
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        
        frame.getContentPane().add(glcanvas);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }
}
