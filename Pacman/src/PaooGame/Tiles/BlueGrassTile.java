package PaooGame.Tiles;

import PaooGame.Graphics.Assets;
 /*! \class public class BlueGrassTile extends Tile
        \brief Abstractizeaza notiunea de dala de tip iarba albastra.
     */

public class BlueGrassTile extends Tile {

        /*! \fn public BlueGrassTile(int id)
            \brief Constructorul de initializare al clasei

            \param id Id-ul dalei util in desenarea hartii.
         */
        public BlueGrassTile(int id)
        {
            /// Apel al constructorului clasei de baza
            super(Assets.bluegrass, id);
        }
}

