package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import entity.Player;
import tile.TileManager;

import javax.swing.JPanel;

public class GamePanel extends JPanel  implements Runnable{
    //screen :-{}
    final int oringinalTilesize = 16;  //we are gonna use 16x16 tiles.
    final int scale=3; //scaling the tilesize by 3 times. -> 16x3=48;
    //final tiles size:- 
    //public so we can acccess it from other classes
    public final int tilesize = oringinalTilesize * scale; //48x48
    //16 horizontal tiles and 12 vertical tiles
    final int maxSreenRow = 16;
    final int maxScreenCol = 16;
    final int screeWidth = tilesize * maxScreenCol; //768 pixels
    final int screenHeight = tilesize * maxSreenRow; //576 

    //FPS
    int FPS = 60;

    TileManager tileM =  new TileManager(this);
    Thread gameThread;
    KeyHandler keyH= new KeyHandler();
    Player player = new Player(this, keyH);

    //constructor
    public GamePanel(){
        this.setPreferredSize(new Dimension(screeWidth, screenHeight));
        this.setBackground(Color.pink); 
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        
    }

    //Threading
    
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
        player.update();
        
    }
    public void paintComponent(java.awt.Graphics g){
        super.paintComponent(g);
    //Grphics2D to proviude tghe more advanced control over geometry, coordinate transformations, color management. 
        Graphics2D g2 = (Graphics2D)g;

        tileM.draw(g2);

        player.draw(g2);
        g2.dispose();
    }
        
 
 
}