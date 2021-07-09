package PaooGame.Input;


import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/*! \class public class MouseManager implements MouseListener, MouseMotionListener
    \brief Gestioneaza intrarea (input-ul) de mouse.

    Clasa citeste daca au fost apasat  mouse-ul, stabiliteste ce buton  a fost actionat si seteaza corespunzator un flag(right , left).

 */

public class MouseManager implements MouseListener, MouseMotionListener {

    private int xmouse, ymouse; /*!< Cooddonate mouse*/
    private boolean left, right; /*!< flaguri pentru butoane mouse*/


    /*! \fn public void Update()
      \brief Update
   */
    public void Update()
    {

    }
 /*! \fn public void  mouseMoved(MouseEvent e)
      \brief Metoda ce preia coordonatele mouse-ului
   */
    @Override
    public void mouseMoved(MouseEvent e) {
        xmouse = e.getX();
        ymouse = e.getY();

    }
    /*! \fn public void mousePressed(MouseEvent e)
      \brief Metoda ce seteaza flagurile pentru butoanele apasate de mouse-ului
   */

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1)
        {
            System.out.println("S-a apasat click stanga");
            left = true;
        }
        else if (e.getButton() == MouseEvent.BUTTON3)
        {
            System.out.println("S-a apasat click dreapta");
            right = true;
        }


    }

      /*! \fn public  void mouseReleased(MouseEvent e)
      \brief Metoda ce reseteaza butoanele mouse-ului
   */


    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1)
        {
            left = false;
        }
        else if (e.getButton() == MouseEvent.BUTTON3)
        {
            right = false;
        }

    }
      /*! \fn public  boolean isLeft()
      \brief Metoda ce returneaza valoare butonului stang(apasat=true, resetat=false)
   */
    public boolean isLeft()
    {
        return left;
    }
    /*! \fn public  boolean isRight()
      \brief Metoda ce returneaza valoare butonului drept(apasat=true, resetat=false)
   */

    public boolean isRight()
    {
        return right;
    }
 /*! \fn public int getXmouse()
      \brief Metoda ce returneaza coodnoata x a mouse-ului
   */

    public int getXmouse() {
        return xmouse;
    }
    /*! \fn public int setYmouse()
      \brief Metoda ce returneaza coodnoata y a mouse-ului
   */

    public int getYmouse() {
        return ymouse;
    }

    //Metode suprancarcate din interfetele implementate
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }
}
