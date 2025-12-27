package src.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicComboBoxUI.KeyHandler;

public class GamePanel extends JPanel  implements Runnable{
    //screen :-
    final int oringinalTilesize = 16;  //we are gonna use 16x16 tiles.
    final int scale=3; //scaling the tilesize by 3 times. -> 16x3=48;
    //final tiles size:-
    final int tilesize = oringinalTilesize * scale; //48x48
    //16 horizontal tiles and 12 vertical tiles
    final int maxSreenRow = 16;
    final int maxScreenCol = 12;
    final int screeWidth = tilesize * maxScreenCol; //768 pixels
    final int screenHeight = tilesize * maxSreenRow; //576 

    KeyHandler keyH= new KeyHandler();

    //Set playesr default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed =4;

    //constructor
    public GamePanel(){
        this.setPreferredSize(new Dimension(screeWidth, screenHeight));
        this.setBackground(Color.pink);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    //Threading
    Thread gameThread;
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    //Game loop
    @Override
    public void run(){
        while(gameThread != null){
            //Updating the info such as character positions
            update();
            //Drawing the screen again
            repaint();
        }
    }
    public void update(){
        if (keyH.upPressed== true) {
            playerY -= playerSpeed;
        } else if(keyH.downPressed== true){
            playerY += playerSpeed;
        } else if(keyH.leftPressed== true){
            playerX -= playerSpeed;
        }  else if(keyH.rightPressed== true){
            playerX += playerSpeed;
    }
    public void paintComponent(java.awt.Graphics g){
        super.paintComponent(g);
    //Grphics2D to proviude tghe more advanced control over geometry, coordinate transformations, color management. 
        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.white);

        g2.fillRect(playerX, playerY, tilesize, tilesize);
        g2.dispose();
    }
        
 
 
}
