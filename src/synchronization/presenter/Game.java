/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package synchronization;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.Random;

/**
 *
 * @author Satria Ramadhani
 */

/*  
	Saya Ibnu Adeng Kurnia NIM 2101769 mengerjakan latihan ke-7 (LP7)
	dalam mata kuliah desain dan pemrograman berorientasi objek C2 2023
	untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. 
	Aamiin.
*/

/*  Design ini disusun dan/atau digunakan hanya untuk lingkungan sendiri.
	Tidak untuk menjadi konsumsi/kepentingan umum.
	Hanya untuk melengkapi tugas DPBO 2023.
*/

public class Game extends Canvas implements Runnable
{
    /**
     * 
     * Attribute declaration.
     */
    
    /* View-related attributes. */
    public static final int width = 640;
    public static final int height = 480;
    private Display display;
    
    /* Process-related attributes. */
    private boolean running;
    private Handler handler;
    private Thread thread;
    
    /* Animation-related attributes. */
    private boolean startCounting = false;
    private int score = 0;
    private int counter = 0;
    private int stateCounter = 0;
    private int direction = 0;
    private int flagScore = 0;
    
    /* Posisi objek pada game yang acak. */
    private Random rand = new Random();
    
    // Default constructor.
    public Game()
    {
        try
        {
            // Initialize display.
            display = new Display(width, height, "Synchronization Tutorial, Edit by IBNU AK ©️ 2023");
            display.open(this); 
            
            // Initialize game handler.
            handler = new Handler();
            
            // Initialize controller (keyboard input).
            this.setFocusable(true);
            this.requestFocus();
            this.addKeyListener(new Controller(this, handler));
            
            // Initialize all object.
            running = true;
            if(running)
            {
                handler.add(new Player(this.width/2, this.height/2));
                handler.add(new RandomBox(rand.nextInt(640), rand.nextInt(480)));
            }
        } catch(Exception e)
        {
            System.err.println("Failed to instance data.");
        }
    }
    
    /**
     * 
     * Getter and Setter.
     */
    
    /* Game running status. */
    
    public boolean isRunning()
    {
        return running;
    }

    public void setRunning(boolean running)
    {
        this.running = running;
    }
    
    /* Game score. */
    
    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        if(this.flagScore == 0)
        {
            this.score = this.score + score;
        }
        this.flagScore = 1;
    }
    
    public int getFlagScore()
    {
        return this.flagScore;
    }
    
    public void setFlagScore(int flagScore)
    {
        this.flagScore = flagScore;
    }
    
    /**
     * 
     * Public methods.
     */
    
    // Clamp, so game object won't get offset the display bound.
    public static int clamp(int var, int min, int max)
    {
        if(var >= max)
        {
            return var = max;
        }
        else if(var <= min)
        {
            return var = min;
        }
        
        return var;
    }
    
    // Close display.
    public void close()
    {
        display.close();
    }
    
    /**
     * 
     * Game controller.
     */
    
    // Start threading.
    public synchronized void start()
    {
        thread = new Thread(this);
        thread.start(); running = true;
        
    }
    
    // Stop threading.
    public synchronized void stop()
    {
        try
        {
            thread.join();
            running = false;
        }
        catch(InterruptedException e)
        {
            System.out.println("Thread error : " + e.getMessage());
        }
    }
    
    // Initialize game when it run for the first time.
    public void render()
    {
        // Use buffer strategy.
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null)
        {
            this.createBufferStrategy(3);
            return;
        }
        
        // Initialize graphics.
        Graphics g = bs.getDrawGraphics();
        Image bg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/assets/game.jpg"));
        g.drawImage(bg, 0, 0, null);
        
        if(running == true)
        {
            // Render handler.
            handler.render(g);
            
            // Render score.
            Font oldFont = g.getFont();
            Font newFont = oldFont.deriveFont(oldFont.getSize() * 1.3f);
            g.setFont(newFont);
            
            g.setColor(Color.white);
            g.drawString("Score Yang Diperoleh : " + Integer.toString(score), 20, 30);
            
        }
        
        // Loop the process so it seems like "FPS".
        g.dispose();
        bs.show();
    }
    
    // Main loop proccess.
    public void loop()
    {
        GameObject player = null;
        GameObject random_box = null;
        
        handler.loop();
        if(this.running)
        {   
            counter++;
            if(startCounting)
            {
                stateCounter++;
            }
            
            if(stateCounter >= 40)
            {
                stateCounter = 0;
                startCounting = false;
            }
            
            if(counter >= 50)
            {
                direction = (direction == 0) ? 1 : 0;
                counter = 0;
            }
            
            for(int i = 0; i < handler.count(); i++)
            {
                if(handler.get(i).getType().equals("Player"))
                {
                    player = handler.get(i);
                }
                if(handler.get(i).getType().equals("RandomBox"))
                {
                    random_box = handler.get(i);
                }
            }
            
            if((player.getX() + 30 >= random_box.getX() && player.getX() <= random_box.getX() + 30) && (player.getY() + 30 >= random_box.getY() && player.getY() <= random_box.getY() + 30))
            {
                this.score += 10;
                handler.remove(random_box);
                handler.add(new RandomBox(rand.nextInt(640), rand.nextInt(480)));
            }
        }
    }
    
    /**
     * 
     * Override interface.
     */
    
    @Override
    public void run()
    {
        double fps = 60.0;
        double ns = (1000000000 / fps);
        double delta = 0;
        
        // Timer attributes.
        long time = System.nanoTime();
        long now = 0;
        long timer = System.currentTimeMillis();
        
        int frames = 0;
        while(running)
        {
            now = System.nanoTime();
            delta += ((now - time) / ns);
            time = now;
            
            while(delta > 1)
            {
                loop();
                delta--;
            }
            
            if(running)
            {
                render();
                frames++;
            }
            
            if((System.currentTimeMillis() - timer) > 1000)
            {
                timer += 1000;
                frames = 0;
            }
        }
        
        stop();
    }
}