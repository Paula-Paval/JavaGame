package PaooGame.Tiles;

import PaooGame.Graphics.Assets;
 /*! \class public class BlueGrassTile extends Tile
        \brief Abstractizeaza notiunea de dala de tip perete.
     */

public class WallTile extends Tile {

    /*! \fn public WallTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public  WallTile(int id)
    {
        /// Apel al constructorului clasei de baza
        super(Assets.wall, id);
    }
    /*! \fn publicboolean IsSolid()
       \brief seteaza dala ca solida

    */
    @Override
    public boolean IsSolid()
    {
        return true;
    }
}