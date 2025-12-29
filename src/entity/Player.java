package entity;
import main.GamePanel;
import main.KeyHandler;

import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        x=100;
        y=100;
        speed=4;        
        direction = "down";
    }

    // public void getPlayerImage(){
    //     try{
    //         up1 = ImageIO.read(getClass().getResourceAsStream("/player/girl_up_1.png"));
    //         up2 = ImageIO.read(getClass().getResourceAsStream("/player/girl_up_2.png"));
    //         down1 = ImageIO.read(getClass().getResourceAsStream("/player/girl_down_1.png"));
    //         down2 = ImageIO.read(getClass().getResourceAsStream("/player/girl_down_2.png"));
    //         left1 = ImageIO.read(getClass().getResourceAsStream("/player/girl_left_1.png"));
    //         left2 = ImageIO.read(getClass().getResourceAsStream("/player/girl_left_2.png"));
    //         right1= ImageIO.read(getClass().getResourceAsStream("/player/girl_right_1.png"));
    //         right2 = ImageIO.read(getClass().getResourceAsStream("/player/girl_right_2.png"));



    //     }catch(Exception e){
    //         e.printStackTrace();
    //     }
    // }
    private BufferedImage loadImage(String path) {
    try {
        var stream = getClass().getResourceAsStream("/" + path);

        if (stream == null) {
            throw new RuntimeException("Image not found: " + path);
        }

        return ImageIO.read(stream);

    } catch (Exception e) {
        throw new RuntimeException(e);
    }
}
public void getPlayerImage() {
    try {
        up1 = ImageIO.read(getClass().getResourceAsStream("/resources/player/girl_up_1.png"));
        up2 = ImageIO.read(getClass().getResourceAsStream("/resources/player/girl_up_2.png"));
        down1 = ImageIO.read(getClass().getResourceAsStream("/resources/player/girl_down_1.png"));
        down2 = ImageIO.read(getClass().getResourceAsStream("/resources/player/girl_down_2.png"));
        left1 = ImageIO.read(getClass().getResourceAsStream("/resources/player/girl_left_1.png"));
        left2 = ImageIO.read(getClass().getResourceAsStream("/resources/player/girl_left_2.png"));
        right1 = ImageIO.read(getClass().getResourceAsStream("/resources/player/girl_right_1.png"));
        right2 = ImageIO.read(getClass().getResourceAsStream("/resources/player/girl_right_2.png"));
    } catch (Exception e) {
        e.printStackTrace();
        throw new RuntimeException("Failed to load player images!", e);
    }
}




    public void update(){

        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
            
            if(keyH.upPressed == true){
            direction = "up";
            y -= speed;
            }
            else if(keyH.downPressed == true){
            direction = "down";
            y += speed;
            }
            else if(keyH.leftPressed == true){
            direction = "left";
            x -= speed;
            }
            else if(keyH.rightPressed == true){
            direction = "right";
            x += speed;
            }
             //sprite animation -> rate of changing the frames per second
            spriteCounter++;
          if (spriteCounter>8) {
            if (spriteNum ==1) {
                spriteNum=2;
            }
            else if(spriteNum==2){
                spriteNum=1;
            }
            spriteCounter=0;
          }
        }

        
    }
    public void draw(Graphics2D g2){
        //drawing code
      
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if(spriteNum ==1){
                    image = up1;
                }
                if(spriteNum ==2){
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum ==1){
                    image = down1;
                } 
                if (spriteNum==2) {
                    image= down2;
                }
                break;
            case "left":
                if(spriteNum ==1){
                    image = left1;
                }
                if(spriteNum ==2){
                    image =left2;
                }
                break;
            case "right":
                if(spriteNum ==1){
                    image = right1;
                }
                if (spriteNum==2) {
                    image = right2;
                }
                break;

        }
        g2.drawImage(image, x, y, gp.tilesize, gp.tilesize, null);
    }
}
