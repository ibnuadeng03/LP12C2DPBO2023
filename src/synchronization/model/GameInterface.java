/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package synchronization;

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

public interface GameInterface
{
    public void render(Graphics object);  // Render object.
    public void loop();    // Loop / refresh object.
}