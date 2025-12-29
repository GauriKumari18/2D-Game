package src.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel  implements Runnable{
    //screen :-{}
    final int oringinalTilesize = 16;  //we are gonna use 16x16 tiles.
    final int scale=3; //scaling the tilesize by 3 times. -> 16x3=48;
    //final tiles size:-
    final int tilesize = oringinalTilesize * scale; //48x48
    //16 horizontal tiles and 12 vertical tiles
    final int maxSreenRow = 16;
    final int maxScreenCol = 12;
    final int screeWidth = tilesize * maxScreenCol; //768 pixels
    final int screenHeight = tilesize * maxSreenRow; //576 

    //FPS
    int FPS = 60;

    KeyHandler keyH= new KeyHandler();

    //Set playesr default position
    int playerX = 250;
    int playerY = 300;
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

            double drawInterval = 1000000000/FPS; //0.01666 seconds
            double nextDrawTime = System.nanoTime() + drawInterval;

            
            // long currentTime = System.nanoTime();
            // System.out.println("Current Time: " + currentTime);

            //Updating the info such as character positions
            update();
            //Drawing the screen again
            repaint();
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if(remainingTime < 0){
                    remainingTime = 0;
                }

                Thread.sleep((long)remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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