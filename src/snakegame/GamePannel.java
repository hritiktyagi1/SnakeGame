
package snakegame;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class GamePannel extends JPanel implements ActionListener,KeyListener {
    
    private int[] snakeXLength = new int[750];
    private int[] snakeYLength = new int[750];
    private int lengthOfSnake=3;
    private int moves=0;
    private int score=0;
    private boolean gameOver=false;
    
    private int[] xpos={25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
    private int[] ypos={75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625};
    
    private Random random =new Random();
    private int enemyX,enemyY;
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    
    private ImageIcon snakeTitle = new ImageIcon(ClassLoader.getSystemResource("icons/snaketitle.jpg"));
    private ImageIcon leftMouth = new ImageIcon(ClassLoader.getSystemResource("icons/leftmouth.png"));
    private ImageIcon rightMouth = new ImageIcon(ClassLoader.getSystemResource("icons/rightmouth.png"));
    private ImageIcon upMouth = new ImageIcon(ClassLoader.getSystemResource("icons/upmouth.png"));
    private ImageIcon downMouth = new ImageIcon(ClassLoader.getSystemResource("icons/downmouth.png"));
    private ImageIcon snakeImage = new ImageIcon(ClassLoader.getSystemResource("icons/snakeimage.png"));
    private ImageIcon enemy = new ImageIcon(ClassLoader.getSystemResource("icons/enemy.png"));
    
    private Timer timer;
    private int delay=100;
    GamePannel(){
        
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
        timer= new Timer(delay,this);
        timer.start();
        
        newEnemy();
        
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); 
        g.setColor(Color.WHITE);
        g.drawRect(24, 10, 852, 55);
        g.drawRect(24, 74, 852, 576);
        
        snakeTitle.paintIcon(this, g, 25, 11);
        g.setColor(Color.BLACK);
        g.fillRect(25, 75, 850, 575);
        
        if(moves==0){
            snakeXLength[0]=100;
            snakeXLength[1]=75;
            snakeXLength[2]=50;
            
            snakeYLength[0]=100;
            snakeYLength[1]=100;
            snakeYLength[2]=100;
            
        }
        
        if(left){
            leftMouth.paintIcon(this, g, snakeXLength[0], snakeYLength[0]);
        }else if(right){
            rightMouth.paintIcon(this, g, snakeXLength[0], snakeYLength[0]);
        }else if(up){
            upMouth.paintIcon(this, g, snakeXLength[0], snakeYLength[0]);
        }else if(down){
            downMouth.paintIcon(this, g, snakeXLength[0], snakeYLength[0]);
        }
        for(int i=1;i<lengthOfSnake;i++){
            snakeImage.paintIcon(this, g, snakeXLength[i], snakeYLength[i]);
        
    }
        enemy.paintIcon(this, g, enemyX, enemyY);
        if(gameOver){
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial",Font.BOLD,50));
            g.drawString("Game Over",300,300);
            g.setFont(new Font("Arial",Font.PLAIN,20));
            g.drawString("Press SPACE To Restart", 320, 350);
        }
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial",Font.PLAIN,14));
        g.drawString("Score    :   "+score, 750, 30);
        g.drawString("Length   :   "+lengthOfSnake, 750, 50);
        g.dispose();
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        
        for(int i=lengthOfSnake-1;i>0;i--){
            snakeXLength[i]=snakeXLength[i-1];
            snakeYLength[i]=snakeYLength[i-1];
        }
        if(left){
            snakeXLength[0]=snakeXLength[0]-25;
        }
        if(right){
            snakeXLength[0]=snakeXLength[0]+25;
        }
        if(down){
            snakeYLength[0]=snakeYLength[0]+25;
        }
        if(up){
            snakeYLength[0]=snakeYLength[0]-25;
        }
        
        if(snakeXLength[0]>850){
            snakeXLength[0]=25;
        }
        if(snakeXLength[0]<25){
            snakeXLength[0]=850;
        }
        
        if(snakeYLength[0]>625){
            snakeYLength[0]=75;
        }
        if(snakeYLength[0]<75){
            snakeYLength[0]=625;
        }
        
        collidesWithEnemy();
        collidesWithBody();
        repaint();
    }
    
   

    @Override
    public void keyTyped(KeyEvent e) {
         // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        if(e.getKeyCode()==KeyEvent.VK_SPACE){
            restart();
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT && (!right)){
            left=true;
            right=false;
            up=false;
            down=false;
            moves++;
        }
        if(e.getKeyCode()==KeyEvent.VK_RIGHT && (!left)){
            left=false;
            right=true;
            up=false;
            down=false;
            moves++;
        }
        if(e.getKeyCode()==KeyEvent.VK_UP && (!down)){
            left=false;
            right=false;
            up=true;
            down=false;
            moves++;
        }
        if(e.getKeyCode()==KeyEvent.VK_DOWN && (!up)){
            left=false;
            right=false;
            up=false;
            down=true;
            moves++;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public static void main(String[] args){
        
    }

    private void newEnemy() {
         enemyX=xpos[random.nextInt(34)];
         enemyY=ypos[random.nextInt(23)];
         
         for(int i= lengthOfSnake-1;i>=0;i--){
             if(snakeXLength[i]==enemyX && snakeYLength[i]==enemyY){
                 newEnemy();
             }
             
         }
    }

    private void collidesWithEnemy() {
        if(snakeXLength[0]==enemyX && snakeYLength[0]==enemyY){
            newEnemy();
            lengthOfSnake++;
            score++;
        }
    }

    private void collidesWithBody() {
        for(int i=lengthOfSnake-1;i>0;i--){
            if(snakeXLength[i]==snakeXLength[0] && snakeYLength[i]==snakeYLength[0]){
                timer.stop();
                gameOver=true;
            }
        }
    }

    private void restart() {
        gameOver=false;
        moves=0;
        score=0;
        lengthOfSnake=3;
        left=false;
        right=true;
        up=false;
        down=false;
        timer.start();
        
        repaint();
        newEnemy();
        
    }
    
}
