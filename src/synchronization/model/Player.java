/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package synchronization;

import java.awt.Color;
import java.awt.Graphics;

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

public class Player extends GameObject
{
    /**
     * Constructor.
     */
    
    // Default constructor.
    public Player()
    {
        super(0, 0, "Player");
        super.setHeight(30);
    }
    
    // Constructor with player position.
    public Player(int x, int y)
    {
        super(x, y, "Player");
        super.setHeight(30);
    }
    
    /**
     * Override interface.
     */
    
    @Override
    public void render(Graphics object)
    {
        // Set player shape.
        object.setColor(Color.decode("#FFC1C1"));
        object.fillOval(x, y, 30, 30);
    }
    
    @Override
    public void loop()
    {
        // Initialize velocity, so object can move.
        this.x += this.velX;
        this.y += this.velY;
        
        // Initialize player bound, so it won't get offset the display.
        x = Game.clamp(x, 0, (Game.width - 50));
        y = Game.clamp(y, 0, (Game.height - 70));
    }
}