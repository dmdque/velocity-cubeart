import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.event.*;

/*import java.awt.Graphics;*/
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.imageio.ImageIO;
//import javax.swing.JPanel;

public class Vel extends JFrame{

  private BufferedImage image;
  protected Console console;
  protected Image _render;
  protected final int TILE = 1;
  protected final int CUBE = 4;

  class Console extends JPanel
  {
    public Console (int width, int height)
    {
      this.setPreferredSize(new Dimension (width, height));
      this.setBackground(Color.white); // Sets the background to white so that when super.paintComponent is called, a white background is shown instead of the default grey
    }
    public void paintComponent(Graphics g)  // This should be moved to the goButtonListener, which creates an image, and this method only prints the image, instead of generating it
    {
      try
      {
        g.clearRect (0, 0, 1366, 768);
        super.paintComponent (g); // Calls super.paintComponent to clear the JPanel before updating the image
        drawVel();
        g.drawImage (_render, 0, 0, null);
      }
      catch (Exception e)
      {
      }
    }
  }


  public Vel() {
    console = new Console (1366, 768);
    //try {                
    //image = ImageIO.read(new File("image name and path"));
    //} catch (IOException ex) {
    //// handle exception...
    //}
    JPanel content = new JPanel (new BorderLayout (5, 5));

    content.add (console, BorderLayout.CENTER);

    setContentPane(content);
    pack();
    setTitle ("Cube Art");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

  }

  public void drawVel(){
    System.out.println("in drawVel()");
    System.out.println("i");
    _render = loadImage ("bs.png"); // bs768p.png for faster render, and smaller images
    Graphics g = _render.getGraphics();
    //g.setColor(Color.black);
    readFile("vel.input");
    //for(int i = 0; i < 100; i++){
      //g.drawLine(i, i, 50 + i, 50);
    //}
    //g.fillRect(0,0,100,20);
    //drawCircleA(10, 10, 10);
    //console.repaint();
  }

  public void drawCircleA(int x, int y, int d){
    Graphics g = _render.getGraphics();
    g.setColor(Color.black);
    g.fillRect(x + d, y + d, d, d);
  }

  public void drawCircleB(int x, int y, int d){
    Graphics g = _render.getGraphics();
    g.setColor(Color.black);
    //g.fillRect(x + d, y, d, d);
    g.fillRect(x + d, y + d, d, d);
    g.fillRect(x + 2*d, y, d, d);
    g.fillRect(x + 2*d, y + d, d, d);
  }

  public void drawCircleC(int x, int y, int d){
    Graphics g = _render.getGraphics();
    g.setColor(Color.black);
    g.fillRect(x, y + d, 3*d, d);
    g.fillRect(x + d, y, d, 2*d);
    //g.fillRect(x + 2*d, y, d, d);
    //g.fillRect(x + 2*d, y + 2*d, d, d);

    //g.fillRect(x, y, d, d);

    //g.fillRect(x + d, y, d, d);
    //g.fillRect(x + d, y + d, d, d);
    g.fillRect(x + 2*d, y, d, d);
    g.fillRect(x + 2*d, y + d, d, d);
  }
  public void drawCircleD(int x, int y, int d){
    Graphics g = _render.getGraphics();
    g.setColor(Color.black);
    g.fillRect(x, y, 3*d, 3*d);
  }

  public void drawCircleE(int x, int y, int d){
    Graphics g = _render.getGraphics();
    g.setColor(Color.black);
    g.fillRect(x + d, y + d, d, d);
  }

  public static BufferedImage loadImage (String fileName) {
    BufferedImage image = null;
    try {
      image = ImageIO.read (new File (fileName));
    }
    catch (IOException e) {
    }
    return image;
  }
  
  public void readFile(String filename){
    System.out.println("in readFile");
    Graphics g = _render.getGraphics();
    String thisLine;
    //Open the file for reading
    try {
      BufferedReader br = new BufferedReader(new FileReader(filename));
      int x = 0;
      while ((thisLine = br.readLine()) != null) { // while loop begins here
        System.out.println(thisLine);
        for(int i = 0; i < 26; i++){
          if(thisLine.charAt(i) == '1'){
            drawCircleA(i * CUBE, x * CUBE, TILE);
          } else if(thisLine.charAt(i) == '2'){
            drawCircleB(i * CUBE, x * CUBE, TILE);
          } else if(thisLine.charAt(i) == '3'){
            drawCircleC(i * CUBE, x * CUBE, TILE);
          } else if(thisLine.charAt(i) == '4'){
            drawCircleD(i * CUBE, x * CUBE, TILE);
          } else{
          }
        }

      x++;
      } // end while 
    } // end try
    catch (IOException e) {
      System.err.println("Error: " + e);
    }
  }

  public static void main (String[] args){
    Vel window = new Vel();
    window.setVisible(true);
  } // main
}
