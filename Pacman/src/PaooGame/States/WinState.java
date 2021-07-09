package PaooGame.States;

import PaooGame.RefLinks;

import java.awt.*;
import java.io.IOException;

/*! \class public  class WinState extends State
    \brief Implementeaza notiunea de winstate pentru joc.
 */
public class WinState extends State {

    public Rectangle quitButton=new Rectangle(300, 250, 100, 50); /*!< dreptunghi pentru buton de play*/

    /*! \fn  public WinState(RefLinks refLink)
       \brief Constructorul de initializare al clasei.

       \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
    */
    public WinState(RefLinks refLink) {
        super(refLink);
    }

    /*! \fn public void Update()
      \brief Actualizeaza starea curenta a meniului.
       In cazul in care se apasa pe butonul  quit se va iesi din fereastra
   */
    @Override
    public void Update() throws IOException {
        if(refLink.GetMouseManager().getXmouse()>=300&&refLink.GetMouseManager().getXmouse()<=400)
        {
            if(refLink.GetMouseManager().getYmouse()>=250&&refLink.GetMouseManager().getYmouse()<=300)
            {
                if(refLink.GetMouseManager().isLeft())
                {
                    System.exit(-1);
                }
            }
        }

    }

    /*! \fn public void Draw(Graphics g)
       \brief Deseneaza (randeaza) pe ecran starea curenta a meniului.
        Se va desena un fundal negru peste care se va desena butonul quit
       \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
    */
    @Override
    public void Draw(Graphics g) {
        Graphics2D g2d=(Graphics2D) g;
        g.setColor(Color.black);
        g.fillRect(0,0, refLink.GetWidth(), refLink.GetHeight());
        Font font0=new Font("arial", Font.BOLD, 50);
        g.setFont(font0);
        g.setColor(Color.YELLOW);
        g.drawString("You win!",250, 100);
        Font fnt1=new Font("arial", Font.BOLD, 30);
        g.setFont(fnt1);
        g.drawString("Quit", quitButton.x+19, quitButton.y+30 );
        g2d.draw(quitButton);
    }

    /*! \fn  public int getLevel()
      \brief Se va returna 0 pentru ca suntem in afara jocului.

   */
    @Override
    public int getLevel() {
        return 0;
    }
}
