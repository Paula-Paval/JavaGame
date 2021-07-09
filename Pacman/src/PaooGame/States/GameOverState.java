package PaooGame.States;

import PaooGame.RefLinks;

import java.awt.*;
import java.io.IOException;

/*! public class GameOverState extends State
    \brief Implementeaza  Starea de iesire din joc(pe ecran se va desena un buton quit)
 */

public class GameOverState extends State{

    public Rectangle quitButton=new Rectangle(300, 250, 100, 50); /*!< dreptunghi pentru buton de quit*/


    /*! \fn   public GameOverState(RefLinks refLink)
      \brief Constructorul clasei
      \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.

       */
    public GameOverState(RefLinks refLink) {
        super(refLink);
    }

    /*! \fn  public void Update() throws IOException
     \brief Functie de Update in cazul in care se pasa butonul quit se va iesi din fereastra.

      */
    @Override
    public void Update() throws IOException {
        if(refLink.GetMouseManager().getXmouse()>=300&&refLink.GetMouseManager().getXmouse()<=400)
        {
            if(refLink.GetMouseManager().getYmouse()>=250&&refLink.GetMouseManager().getYmouse()<=300)
            {
                if(refLink.GetMouseManager().isLeft())
                {
                  System.exit(0);
                }
            }
        }


    }
      /*! \fn  public void Draw(Graphics g)
     \brief Functia de desenare a fudalului negru, a mesajului Game over si a butonului quit.

      */

    @Override
    public void Draw(Graphics g) {
        Graphics2D g2d=(Graphics2D) g;
        g.setColor(Color.black);
        g.fillRect(0,0, refLink.GetWidth(), refLink.GetHeight());
        Font font0=new Font("arial", Font.BOLD, 50);
        g.setFont(font0);
        g.setColor(Color.YELLOW);
        g.drawString("Game Over!",230, 100);
        Font fnt1=new Font("arial", Font.BOLD, 30);
        g.setFont(fnt1);
        g.drawString("Quit", quitButton.x+19, quitButton.y+30 );
        g2d.draw(quitButton);


    }

    /*! \fn   public int getLevel()
    \brief Functia va returna ca level 0 pentru ca am iesit din joc.

     */
    @Override
    public int getLevel() {
        return 0;
    }
}
