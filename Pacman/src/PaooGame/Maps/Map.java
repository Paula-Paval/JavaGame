package PaooGame.Maps;

import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import java.awt.*;
import java.io.*;
import java.util.Scanner;

/*! \ class Map
    \brief Implementeaza notiunea de harta a jocului.
 */
public class Map
{
    private RefLinks refLink;   /*!< O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.*/
    private int width;          /*!< Latimea hartii in numar de dale.*/
    private int height;         /*!< Inaltimea hartii in numar de dale.*/
    private int [][] tiles;     /*!< Referinta catre o matrice cu codurile dalelor ce vor construi harta.*/

    /*! \fn public Map(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public Map(RefLinks refLink) throws IOException {
            /// Retine referinta "shortcut".
        this.refLink = refLink;
            ///incarca harta de start. Functia poate primi ca argument id-ul hartii ce poate fi incarcat.
        LoadWorld();
    }

    /*! \fn public  void Update()
        \brief Actualizarea hartii in functie de evenimente (un copac a fost taiat)
     */
    public  void Update()
    {

    }

    /*! \fn public void Draw(Graphics g)
        \brief Functia de desenare a hartii.

        \param g Contextl grafi in care se realizeaza desenarea.
     */
    public void Draw(Graphics g)
    {
            ///Se parcurge matricea de dale (codurile aferente) si se deseneaza harta respectiva
        for(int y = 0; y < refLink.GetGame().GetHeight()/Tile.TILE_HEIGHT; y++)
        {
            for(int x = 0; x < refLink.GetGame().GetWidth()/Tile.TILE_WIDTH; x++)
            {
                GetTile(x, y).Draw(g, (int)x * Tile.TILE_HEIGHT, (int)y * Tile.TILE_WIDTH);

            }
        }
    }

    /*! \fn public Tile GetTile(int x, int y)
        \brief Intoarce o referinta catre dala aferenta codului din matrice de dale.

        In situatia in care dala nu este gasita datorita unei erori ce tine de cod dala, coordonate gresite etc se
        intoarce o dala predefinita (ex. bluegrassTile, )
     */
    public Tile GetTile(int x, int y)
    {
        if(x < 0 || y < 0 || x >= width || y >= height)
        {
            return Tile.bluegrassTile; //suntem in exteriorul hartii
        }
        Tile t = Tile.tiles[tiles[x][y]]; //intoarce dala respectiva
        if(t == null)
        {
            return Tile.greengrassTile; //in caz de eroare
        }
        return t;
    }


    /*! \fn private void LoadWorld()
        \brief Functie de incarcare a hartii jocului din fisier.

     */
    private void LoadWorld() throws IOException {
        //atentie latimea si inaltimea trebuiesc corelate cu dimensiunile ferestrei sau
        //se poate implementa notiunea de camera/cadru de vizualizare al hartii
            ///Se stabileste latimea hartii in numar de dale.
        width = 23;
            ///Se stabileste inaltimea hartii in numar de dale
        height = 17;
            ///Se construieste matricea de coduri de dale
        tiles = new int[width][height];
            //Se incarca matricea cu coduri
        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
            {
                tiles[x][y] = MiddleEastMap(y, x);

            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    /*! \fn private int MiddleEastMap(int x ,int y)
            \brief O harta incarcata static.

            \param x linia pe care se afla codul dalei de interes.
            \param y coloana pe care se afla codul dalei de interes.
         */


    private int MiddleEastMap(int x , int y) throws IOException {
            ///Definire statica a matricei de coduri de dale.
        java.util.Scanner input=new Scanner(new File("map1.txt"));

        if(refLink.getLevel()==2)
        {
        input= new Scanner(new File("map2.txt"));
        }

        int[][] map = new int[17][23];
        for (int i = 0; i < map.length ; i++)
        {
            for (int j = 0; j <map[i].length; j++)
            {
                map[i][j] = input.nextInt();

            }
        }

        return map[x][y];
    }
}