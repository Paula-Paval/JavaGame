package PaooGame.Tiles;

import PaooGame.Graphics.Assets;
 /*! \class public class BlueGrassTile extends Tile
        \brief Abstractizeaza notiunea de dala de tip iarba verde.
     */

public class GreenGrassTile extends Tile {

    /*! \fn public GreenGrassTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public  GreenGrassTile(int id)
    {
        /// Apel al constructorului clasei de baza
        super(Assets.greengrass, id);
    }
}