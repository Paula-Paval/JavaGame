package PaooGame.Items;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;
/*! \class public  class Star extends  Item
    \brief Implementeaza notiunea de Stea(Bonus).

 */
public class Star extends  Item{
    private BufferedImage image;    /*!< Referinta catre imaginea stelei.*/
    private boolean eat;  /*!< Flag ce ne arata daca a fost mancat sau nu.*/

    /*! \public Star(RefLinks refLink, float x, float y, int width, int height)
    \brief Constructor clasa

 */
    public Star(RefLinks refLink, float x, float y, int width, int height) {
        super(refLink, x, y, width, height);
        this.image = Assets.star;
        eat=false;
    }
      /*! \ public void Update()
    \brief Metoda update
 */

    @Override
    public void Update() {
        image=null;
    }

    /*! \ public void Draw()
   \brief Metoda de desenare pe ecran
*/
    @Override
    public void Draw(Graphics g) {

        g.drawImage(image, (int)x, (int)y, width, height, null);
    }

    /*! \ public  boolean isEat()
 \brief Retueneaza starea  mancat sau nu
*/
    public boolean isEat() {
        return eat;
    }
      /*! \ public void setEat(boolean eat)
 \brief Seteaza tarea  stelei
*/

    public void setEat(boolean eat) {
        this.eat = eat;
    }
}
