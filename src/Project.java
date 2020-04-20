import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Project extends JPanel {
  public static void main(String[] args) {
    JFrame frame = new JFrame("Project java Game");
    Project content = new Project();
    frame.setContentPane(content);
    frame.setSize(720,600);
    frame.setLocation(100, 0);
    frame.setLayout(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.setVisible(true);
  }
  private Timer timer;
  private int width, height;
  private Player player;
  private Bom bom;
  private Bom bom1;
  private Bom bom2;
  private Bom bom3;
  private Bom bom4;
  private HeartPlayer heartPlayer;
  private  Result R;
  private Icon blackground,banana1,bomp,player1,effectbom1,start1,name,firstg,a5,newplayer1;
  private JTextField textField1;
  private Name name1;
  private Banana banana;
  private JButton start,newplayer;
  private Point point;
  private int topscore=0;
  private String playertop ;


  public Project() {
    blackground = new ImageIcon("C:/Users/My User/Desktop/lab6/blackground1.jpg");
    start1 = new ImageIcon("C:/Users/My User/Desktop/lab6/start4.gif");
    name = new ImageIcon("C:/Users/My User/Desktop/lab6/name.gif");
    firstg = new ImageIcon("C:/Users/My User/Desktop/lab6/blavk1.gif");
    a5 = new ImageIcon("C:/Users/My User/Desktop/lab6/a5.gif");
    newplayer1 = new ImageIcon("C:/Users/My User/Desktop/lab6/new1.gif");
    textField1 = new JTextField(25);
    textField1.setBounds(190,300,300,30);
    add(textField1);
    start = new JButton("");
    start.setIcon((start1));
    start.setBounds(190,350,300,91);
    add(start);
    newplayer = new JButton("");
    newplayer.setIcon((newplayer1));
    newplayer.setBounds(190,450,300,91);
    start.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent event)
      {
          requestFocus();
        name1.updateForNewFrame();
        remove(start);
        R.updateForNewFrame();
        timer.start();

      }
    }
    );

    ActionListener action = new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        if (player != null) {
          player.updateForNewFrame();
          bom.updateForNewFrame();
          bom1.updateForNewFrame();
          bom2.updateForNewFrame();
          bom3.updateForNewFrame();
          bom4.updateForNewFrame();
          heartPlayer.updateForNewFrame();
          R.updateForNewFrame();
          banana.updateForNewFrame();
          point.updateForNewFrame();
        }

      }
    };

    timer = new Timer( 40, action );

    start.addActionListener(new ActionListener()//after Gameover
    {
      public void actionPerformed(ActionEvent event)
      {
        remove(newplayer);
        if(heartPlayer.heart==0){
            width = getWidth();
            height = getHeight();
            player = new Player();
            bom = new Bom();
            bom1 = new Bom();
            bom2 = new Bom();
            bom3 = new Bom();
            bom4 = new Bom();
            heartPlayer= new HeartPlayer();
            R = new Result();
            name1 =new Name();
            banana = new Banana();
            point = new Point();
            timer.start();
          }
        }
      } );

    newplayer.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent event)
      {

        add(start);
        add(textField1);
        textField1.setText("");
        remove(newplayer);
        if(heartPlayer.heart==0){
          point = new Point();
          player = new Player();
          bom = new Bom();
          bom1 = new Bom();;
          bom2 = new Bom();
          bom3 = new Bom();
          bom4 = new Bom();
          heartPlayer= new HeartPlayer();
          R = new Result();
          name1 =new Name();
          banana = new Banana();
        }

      }
    } );

    addKeyListener( new KeyAdapter() {
      public void keyPressed(KeyEvent evt) {
        int code = evt.getKeyCode();
        if (code == KeyEvent.VK_LEFT) {
          player.centerX -= 15;
        }
        else if (code == KeyEvent.VK_RIGHT) {
          player.centerX += 15;
        }
      }
     });
   }

   public void paintComponent(Graphics g) {
    super.paintComponent(g);

    if (player== null) {
      name1 = new Name();
      width = getWidth();
      height = getHeight();
      player = new Player();
      bom = new Bom();
      bom1 = new Bom();;
      bom2 = new Bom();
      bom3 = new Bom();
      bom4 = new Bom();
      heartPlayer= new HeartPlayer();
      R = new Result();
      banana = new Banana();
      point = new Point();
    }
    blackground.paintIcon(this,g,0,0);
    name1.draw(g);
    player.draw(g);
    bom.draw(g);
    point.draw(g);
    bom1.draw(g);
    bom2.draw(g);
    bom3.draw(g);
    bom4.draw(g);
    banana.draw(g);
    heartPlayer.draw(g);
    firstg.paintIcon(this,g,-40,R.centerY);
    name.paintIcon(this,g,R.centerX,0);
    if(heartPlayer.heart==0){
      add(start);
      add(newplayer);
      firstg.paintIcon(this,g,-40,R.centerY);
      a5.paintIcon(this,g,R.centerX,0);
      g.setColor(Color.RED);
      g.setFont(name1.smallFont);
      g.drawString (("PLAYER:"+(name1.name)+"  SCORE:"+Integer.toString(point.b)),190,300);
      if(topscore<point.b){
        topscore=point.b;
        playertop =name1.name;
      }
      g.setColor(Color.GREEN);
      g.drawString (("PLAYER TOP:"+(playertop)+"  SCORE:"+Integer.toString(topscore)),190,200);

    }
  }
  private class Player {
    int centerX, centerY;

    Player() {
      centerX = 100;
      centerY = 430;
      player1 = new ImageIcon("C:/Users/My User/Desktop/lab6/paap.png");
    }
    void updateForNewFrame() {
      if (centerX < 0)
        centerX = 0;
      else if (centerX > width-80)
        centerX = width-80;
      }

      void draw(Graphics g) {
      player1.paintIcon(null,g,centerX,centerY);
    }
  }
  private class Bom {
    int centerX, centerY;
    boolean x;
    int i,j,k,time;
    double h;

    Bom() {
      centerX = (int)(width*(Math.random()));
      centerY = -(int)(height*(Math.random()));
      bomp = new ImageIcon("C:/Users/My User/Desktop/lab6/bom3.png");
      effectbom1=new ImageIcon("C:/Users/My User/Desktop/lab6/bom8.gif");
      x=false;
      k=0;
      i=0;
      j=0;
      time=10;
      h=0.0;
    }
    void updateForNewFrame() {
      if (centerY >=height) {
            centerY = -(int)(height*(Math.random()));
            h=h+0.1;
            centerX = (int)(width*(Math.random()));
          }

      if(Math.abs(centerX - player.centerX) <= 70 &&
          Math.abs(centerY - player.centerY) <= 63){
            x=true;
            j=centerY;
            i=centerX;
            h=h+0.1;
             centerY = -(int)(height*(Math.random()));
             centerX = (int)(width*(Math.random()));
           }
           if(h>1.0){
             time=time+1;
             h=0.0;
           }
          else{
              centerY =centerY+time;
          }
          if(x){
            k=k+1;
            if(k==5){
           heartPlayer.heart-=1;
           x=false;
            k=0;
          }
        }

        }
    void draw(Graphics g) {
      if(centerX>width-60){
        centerX=width-60;
      }
      bomp.paintIcon(null,g,centerX,centerY);
          if(x){
            effectbom1.paintIcon(null,g,i,j);
          }
      }
    }
    private class Banana {
      int centerY,centerX;
      Banana(){
        centerX = (int)(width*(Math.random()));
        centerY = -(int)(height*(Math.random()));
        banana1 = new ImageIcon("C:/Users/My User/Desktop/lab6/banana5.png");
      }
      void updateForNewFrame() {
        centerY += bom.time;
        if (centerY >=height) {
          centerY = -(int)(height*(Math.random()));;
          centerX = (int)(width*(Math.random()));
        }
        if(Math.abs(centerX - player.centerX) <= 90 &&
         Math.abs(centerY - player.centerY) <= 70){
           point.time=point.time+20;
           centerY = -(int)(height*(Math.random()));
          centerX = (int)(width*(Math.random()));
        }
      }
    void draw(Graphics g) {
      if(centerX>width-80){
        centerX=width-80;
      }
      banana1.paintIcon(null,g,centerX,centerY);
    }
  }

  private class HeartPlayer {
    int centerX, centerY;
    int heart;
    HeartPlayer(){
        heart=5;
        centerX = width-30;
        centerY = height-550;
      }
      void updateForNewFrame() {
        if(heart==0){
          timer.stop();
        }
      }
      void draw(Graphics g) {
        for(int i=0;i<heart;i++){
          g.setColor(Color.RED);
          g.fillArc ( (centerX-(i*22))-15, centerY, 30,20,45 ,90 );
        }
      }
    }
    private class Result {
      int centerX, centerY,x;
      String restart,result;
      Result(){
        centerX=0;
        centerY=0;
      }
      void updateForNewFrame() {
        centerX =-(width);
        centerY=-1000;
        if( heartPlayer.heart==0){
          centerY=0;
          centerX=0;
        }
      }
    }

    private class Name {
      String name;
      Font smallFont;
      Name(){
        smallFont=new Font("Courier New",Font.BOLD,20);
        name="";
      }
      void updateForNewFrame() {
        name=textField1.getText();
        remove(textField1);
      }
      void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(smallFont);
        g.drawString (name,20,30);
      }
    }
    private class Point {
      String name;
      int b;
      double time;
      Font smallFont,smallFont1;
      Point(){
        b=0;
        time=0.0;
        smallFont1=new Font("Courier New",Font.BOLD,20);
        smallFont=new Font("Courier New",Font.BOLD,40);
      }
      void updateForNewFrame() {
        time=time+0.05;
        b=(int)(time);
        if(heartPlayer.heart==0){
            timer.stop();
        }
      }
      void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(smallFont1);
        g.drawString (("score "+Integer.toString(b)),20,50);
      }
    }
}
