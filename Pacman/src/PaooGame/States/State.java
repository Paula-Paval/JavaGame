package PaooGame.States;

import java.awt.*;
import java.io.IOException;

import PaooGame.RefLinks;

/*! \class State
    \brief Implementeaza notiunea abstracta de stare a jocului/programului.

    Un joc odata ce este lansat in executie nu trebuie "sa arunce jucatorul direct in lupta", este nevoie de
    un meniu care sa contine optiuni: Menu, History Game Over Win State etc. Toate aceste optiuni nu sunt altceva
    decat stari ale programului (jocului) ce trebuiesc incarcate si afisate functie de starea curenta.
 */
public abstract class State
{
        ///Urmatoarele atribute sunt statice pentru a evita dealocarea spatiului de memorie la trecerea dintr-o stare in alta.
    private static State previousState  = null; /*!< Referinta catre starea anterioara a jocului.*/
    private static State currentState   = null; /*!< Referinta catre starea curenta a jocului: game, meniu, settings, about etc.*/
    protected RefLinks refLink;
    protected int level;
    public State(RefLinks refLink)
    {
        this.refLink = refLink;
        level=1;
    }

    /*! \fn public static void SetState(State state)
        \brief Seteaza starea curenta a jocului.

        \param state Noua stare a programului (jocului).
     */
    public static void SetState(State state)
    {
        previousState = currentState;
        currentState = state;
    }

    public static State GetState()
    {
        return currentState;
    }

        ///Metoda abstracta destinata actualizarii starii curente
    public abstract void Update() throws IOException;
        ///Metoda abstracta destinata desenarii starii curente
    public abstract void Draw(Graphics g);
    //Metoda abstract destinata gestionarii nivelului
    public abstract int getLevel();
}
