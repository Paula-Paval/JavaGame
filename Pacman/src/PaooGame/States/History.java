package PaooGame.States;

import PaooGame.DB.DataBase;
import PaooGame.RefLinks;

import java.awt.*;
import java.io.IOException;

/*! public class History extends State
    \brief Implementeaza Starea de History in acest caz pe ecran se va afisa butonul de back la menu si continutul bazei de date
 */
public class History extends State{
    public Rectangle back=new Rectangle(600, 400, 80,40);/*!< dreptunghi pentru buton de back*/

    /*! \fn     public History(RefLinks refLink)
    \brief Constructorul clasei

     */
    public History(RefLinks refLink) {
        super(refLink);

    }

    /*! \fn public void Update() throws IOException
   \brief Metoda Update in care se manageriaza situatia in care se apasa butonul de back si se face update la baza de date
   \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.

    */
    @Override
    public void Update() throws IOException {


        if(refLink.GetMouseManager().getXmouse()>=600&&refLink.GetMouseManager().getXmouse()<=680)
        {
            if(refLink.GetMouseManager().getYmouse()>=400&&refLink.GetMouseManager().getYmouse()<=440)
            {
                if(refLink.GetMouseManager().isLeft())
                {
                    refLink.SetMenuState();
                }
            }
        }
        if(refLink.GetMouseManager().getXmouse()>=300&&refLink.GetMouseManager().getXmouse()<=410)
        {
            if(refLink.GetMouseManager().getYmouse()>=350&&refLink.GetMouseManager().getYmouse()<=400)
            {
                if(refLink.GetMouseManager().isLeft())
                {
                    refLink.SetHistiryState();
                }

            }
        }

    }

    /*! \fn  public void Draw(Graphics g)
   \brief Metoda de desenare in fereastra:a fundalului albastru, a continutului  bazei de date si a butonului back

    */
    @Override
    public void Draw(Graphics g) {
        DataBase.getInstance().UpdateDatabase();
        Graphics2D g2d=(Graphics2D) g;
        g.setColor(Color.blue);
        g.fillRect(0,0, refLink.GetWidth(), refLink.GetHeight());
        Font font0=new Font("arial", Font.BOLD, 20);
        g.setFont(font0);
        g.setColor(Color.BLACK);
        g.drawString("Back", back.x+15, back.y+30 );
        g2d.draw(back);
        DataBase.getInstance().Draw(g);
    }

    /*! \fn  public int getLevel()
   \brief Functia returneaza ca level 0 pentru ca suntem in afara jocului
    */
    @Override
    public int getLevel() {
        return 0;
    }
}
