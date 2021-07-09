package PaooGame;


import PaooGame.Input.MouseManager;
import PaooGame.Maps.Map;

import PaooGame.Input.KeyManager;
import PaooGame.States.State;

/*! \class public class RefLinks
    \brief Clasa ce retine o serie de referinte ale unor elemente pentru a fi usor accesibile.

    Altfel ar trebui ca functiile respective sa aiba o serie intreaga de parametri si ar ingreuna programarea.
 */
public class RefLinks
{
    private Game game;          /*!< Referinta catre obiectul Game.*/
    private Map map;            /*!< Referinta catre harta curenta.*/

    /*! \fn public RefLinks(Game game)
        \brief Constructorul de initializare al clasei.

        \param game Referinta catre obiectul game.
     */
    public RefLinks(Game game)
    {
        this.game = game;
    }

    /*! \fn public KeyManager GetKeyManager()
        \brief Returneaza referinta catre managerul evenimentelor de tastatura.
     */
    public KeyManager GetKeyManager()
    {
        return game.GetKeyManager();
    }

    /*! \fn public int GetWidth()
        \brief Returneaza latimea ferestrei jocului.
     */
    public int GetWidth()
    {
        return game.GetWidth();
    }

    /*! \fn public int GetHeight()
        \brief Returneaza inaltimea ferestrei jocului.
     */
    public int GetHeight()
    {
        return game.GetHeight();
    }

    /*! \fn public Game GetGame()
        \brief Intoarce referinta catre obiectul Game.
     */
    public Game GetGame()
    {
        return game;
    }

    /*! \fn public void SetGame(Game game)
        \brief Seteaza referinta catre un obiect Game.

        \param game Referinta obiectului Game.
     */
    public void SetGame(Game game)
    {
        this.game = game;
    }

    /*! \fn public Map GetMap()
        \brief Intoarce referinta catre harta curenta.
     */
    public Map GetMap()
    {
        return map;
    }

    /*! \fn public void SetMap(Map map)
        \brief Seteaza referinta catre harta curenta.

        \param map Referinta catre harta curenta.
     */
    public void SetMap(Map map)
    {
        this.map = map;
    }

    /*! \fn public  void SetPlayStatus()
           \brief Seteaza starea curenta de play

        */
    public void SetPlayStatus()
    {
        game.SetPlayState();
    }
    /*! \fn public MouseManager GetMouseManager()
           \brief Returneaza referinta la mouse

        */
    public MouseManager GetMouseManager()
    {
        return game.GetMouseManager();
    }

    /*! \fn public int getLevel()
         \brief Returneaza referinta la nivel

      */
    public int getLevel()
    {
        return game.getLevel();
    }
    /*! \fn public boolean getRunState()
         \brief Returneaza referinta la starea jocului(daca inca ruleaza sau nu)

      */

    public boolean getRunState()
    {
        return game.isRunState();
    }
    /*! \fn public void SetWinState()
     \brief Seteaza starea de Win

  */
    public void SetWinState(){game.SetWinState();}
    /*! \fn public void SetGameOverState()
   \brief Seteaza starea de Game Over

*/
    public void SetGameOverState(){game.SetGameOverState();}
    /*! \fn public void  SetHistiryState()
  \brief Seteaza starea de History

*/
    public void SetHistiryState(){game.SetHistoryState();}
    /*! \fn public void  SetMenuState()
\brief Seteaza starea de Menu

*/
    public void SetMenuState(){game.SetMenuState();}

}
