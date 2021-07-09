package PaooGame.Scor;

import PaooGame.RefLinks;

import java.awt.*;

/*! \class Scor
    \brief Implementeaza notiunea de scor implementat prin SINGLETON
 */
public class Scor {


    private int scor;  /*!< referinta la scor */

    static Scor scoreObj = new Scor();   /*!< obiect static de tip scor */

    /*! \fn  private Scor()
      \brief Constructorul static al acestei clase

       */
    private Scor()
    {
        scor = 0;
    }
  /*! \fn public Scor GetScoreInstance()
      \brief metoda de get scor ca instanta statica

       */

    static public Scor GetScoreInstance()
    {
        return scoreObj;
    }
     /*! \fn  int getScore()
      \brief metoda de get

       */

    public int getScore() {
        return scor;
    }
      /*! \fn     public void setScore(int scor)
      \brief metoda de get

       */

    public void setScore(int scor) {
        this.scor = scor;
    }
      /*! \fn   public void incrementScore( int scor)
      \brief metoda de get

       */


    public void incrementScore( int scor)
    {
        this.scor += scor;
    }



}
