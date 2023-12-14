import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//pacman
public class Pacman extends JFrame implements KeyListener{
   
   PacmanCanvas canvas;
   PacmanThread thread;
   
   public Pacman(){
      setSize(500,500);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setResizable(false);
      setTitle("Durano Pacman v1.0");
      //
      canvas = new PacmanCanvas(this); 
      thread = new PacmanThread(canvas);
      add(canvas);  
      //
      setVisible(true);
      addKeyListener(this);
   }
   public void keyTyped(KeyEvent k){}
   public void keyReleased(KeyEvent k){}
   public void keyPressed(KeyEvent k){
      int keycode = k.getKeyCode();
      switch(keycode){
         case KeyEvent.VK_RIGHT: canvas.direction=0; break;
         case KeyEvent.VK_LEFT: canvas.direction=1; break;
         case KeyEvent.VK_DOWN: canvas.direction=2; break;
         case KeyEvent.VK_UP: canvas.direction=3;
      }
   }
   //
   static public void main(String... args){
      new Pacman();
   }
}//eoc
//pacman canvas
class PacmanCanvas extends Canvas{
   
   Pacman pacman;
   int x,y;
   int startAngle,arcLen;
   int direction;
   
   
   public PacmanCanvas(Pacman pacman){
      this.pacman=pacman;
      startAngle=30;
      arcLen=300;
      direction = 0;
   }

   public void paint(Graphics g){
      g.setColor(Color.BLUE);
      g.fillRect(0,0,getWidth(),getHeight());
      //the pacman
      g.setColor(Color.YELLOW);
      g.fillArc(x,y,50,50,startAngle,arcLen);
   }
}//end of canvas
//pacman thread
class PacmanThread implements Runnable{
   PacmanCanvas canvas;
   Thread t = new Thread(this);
   
   public PacmanThread(PacmanCanvas canvas){
      this.canvas=canvas;
      t.start();
   }
   public void run(){
      //perpetual loop
      for(;;){
         switch(canvas.direction){
            case 0:if(canvas.x<canvas.getWidth()-50){ //right
                     canvas.x+=5;
                     canvas.startAngle=30;
                     canvas.arcLen = 300;
                     canvas.repaint();
                  }  break;//left
            case 1:if(canvas.x>0){
                     canvas.x-=5;
                     canvas.startAngle = 150;
                     canvas.arcLen = -300;
                     canvas.repaint();
                  } break;  
            case 2: if(canvas.y<canvas.getHeight()-50){//down
                     canvas.y+=5;
                     canvas.startAngle = -60;
                     canvas.arcLen = 300;
                     canvas.repaint();
                  }break;
            case 3: if(canvas.y>0){//up
                     canvas.y-=5;
                     canvas.startAngle = 60;
                     canvas.arcLen = -300;
                     canvas.repaint();
                  }
                  
         }
         
         //put a delay
         try{
            t.sleep(100);
         }catch(Exception e){}
      }
   }
}//end of thread