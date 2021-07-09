package PaooGame.DB;
import PaooGame.Scor.Scor;
import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*! \class public class DataBase
    \brief Retine datele refereitoare la baza de date ce se ocupa de afisarea celor mai mari scoruri implementare a sablonului singleton

 */
public class DataBase  {

   private static DataBase db;    /*!< Referinta statica la baza de date.*/
   private static Connection c = null;    /*!< Conecsiunea la baza de date.*/
   private static Statement stmt = null;   /*!< Obiect Statement .*/
   private Map<String, Integer> dataAndScores = new HashMap<>();   /*!< Hashmap ce contine primele 3 scoruri.*/
   private static  int xforhero;  /*!< Coodonata x a eroului.*/
   private static int yforhero; /*!< Coodonata y a eroului.*/
   private static int xforblueinamic; /*!< Coodonata x a inamicului albastru.*/
   private static int yforblueinamic; /*!< Coodonata y a inamicului albastru.*/
   private static int xforpinkinamic; /*!< Coodonata x a inamicului roz.*/
   private static int yforpinkinamic; /*!< Coodonata y a inamicului  roz.*/
   private static int xstar1;  /*!< Coodonata x a stelei 1.*/
   private static int xstar2;  /*!< Coodonata y a stelei 1.*/
   private static int xstar3;  /*!< Coodonata x a stelei 2.*/
   private static int ystar1;  /*!< Coodonata y a stelei 2.*/
   private static int ystar2;  /*!< Coodonata x a stelei 3.*/
   private static int ystar3;  /*!< Coodonata y a stelei 3.*/



    static {
        db = new DataBase();
        System.out.println("Opened database successfully");
    }


    /*! \fn private DataBase()
     \constructor privat al clasei necesar pentru singleton
     Se creaza conexiunea la baza de date si tabele cu Data si Scorul
  */
    private DataBase() {
        try {
            System.out.println("Create db");
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:Pacman.db");

            stmt = c.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS SCOR " +
                    "( DATA TEXT PRIMARY KEY NOT NULL," +
                    " VALOARE INT NOT NULL)";
            String sql1 = "CREATE TABLE IF NOT EXISTS COORDONATE " +
                    "( x_erou INT NOT NULL," +" y_erou INT NOT NULL,"+"x_blue INT NOT NULL,"+"y_blue INT NOT NULL,"+"x_pink INT NOT NULL,"+"y_pink INT NOT NULL,"+"x_star1 INT NOT NULL,"+"y_star1 INT NOT NULL,"+

                    "x_star2 INT NOT NULL,"+"y_star2 INT NOT NULL,"+"x_star3 INT NOT NULL,"+"y_star3 INT NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.executeUpdate(sql1);
            stmt.close();

        }
        catch (SQLException | ClassNotFoundException e)
        {
            System.out.println("Exceptie la crearea bazei de date!");
            return;
        }
    }

    /*! \fn public void UpdateDatabase()
      \functie de update a bazei de date
        Aceasta functie introduce scorul nou in baza de date, dar retine doar 3 (cele cu scorul cel mai mare)

   */
    public void UpdateDatabase()  {
        try {

            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:Pacman.db");


            int nouScor = Scor.GetScoreInstance().getScore();
            System.out.println("SCORUL DE INTRODUS E " + nouScor);


            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String data = formatter.format(date);

            stmt = c.createStatement();
            String sql = "INSERT INTO SCOR (VALOARE, DATA) " +
                    "VALUES ( " + (int)nouScor + ", \'" + data + "\' );";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();

            System.out.println("\n*** Records created successfully\n");

        }
        catch (SQLException | ClassNotFoundException e  )
        {
            if (e.getMessage().startsWith("UNIQUE")) {
                return;
            }
            else {
                System.out.println("Exceptie la update!");
                System.out.println(e.getMessage());
            }
        }

        pastreaza3();

    }
    /*!
    \fn    private void pastreaza3()
            functie de pastrare a podiumului
            Aceasta functie pastreaza doar primele 3 scoruri

     */
    private void pastreaza3()
    {


        String  toDelete = null; // variabila in care punem data celui mai mic scor (avem nevoie doar de un string fiindca avem maxim 4 intrari in db)
        int counter = 0; // in acest counter tinem evidenta numarului de entries in db (el va fi maxim 3)
        try {
            System.out.println("TRYING TO CONNECT...");
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:Pacman.db");
            stmt = c.createStatement();
            System.out.println("CONNECTION SUCCEEDED");

            ResultSet rs = stmt.executeQuery("SELECT * FROM SCOR ORDER BY VALOARE DESC; "); /// luam intrarile din db in ordine descendenta
            Statement stmt2 = c.createStatement();

            while (rs.next()) {  /// parcurgem intrarile din db (in ordine descendenta)
                if (counter < 3) {
                    ///daca inca numaram sub 3 intrari in db, inseamna ca e una din cele mai mari (Ca si scor) si o punem in hashmapul clasei
                    dataAndScores.put(rs.getString("DATA"), rs.getInt("VALOARE"));
                    counter++;
                }
                else {///altfel inseamna ca e cea mai mica intrare (Deoarece parcurgem elementele selectate descendent ca scor) si trebuie s o stergem
                    toDelete = rs.getString("DATA");  // aceasta intrare trebuie s o stergem
                    System.out.println("TO DELETE VALOARE FROM DATA : " + toDelete);
                }
            }
            System.out.println("DELETE from SCOR WHERE DATA = '" + toDelete + "' ;)");
            if ((toDelete != null) && (stmt.executeUpdate("DELETE from SCOR WHERE DATA = '" + toDelete + "' ;") != 0)) {
                System.out.println("Deletion successfully done");
            }
            stmt.close();

            c.close();

        }
        catch (SQLException | ClassNotFoundException e)
        {
            System.out.println("Exceptie la top!");
            System.out.println(e.getMessage());

        }
    }

    /*!
    \fn     public void Draw(Graphics g)
      Metoda responsabila cu desenarea bazei de date
     */
    public void Draw(Graphics g)
    {
        int i = 0;
        g.setFont(new Font("default", Font.BOLD, 20));
        g.setColor(Color.black);
        g.drawString("History of the best scors:",250, 100);
        for (Map.Entry<String, Integer> entry : dataAndScores.entrySet()) {
            String k = entry.getKey();
            int v = entry.getValue();
            System.out.println("Key: " + k + ", Value: " + v);
            System.out.println(k + " : " + v);
            g.drawString(k + " : " + v, 200, 200+(i++)*40);
        }

    }

    /*!
  \fn     public satic DataBase getInstace()
    Metoda  ce returneaza o instanta la baza de date
   */
   public static DataBase getInstance()
    {
        return db;
    }


    /*!
    \fn     public void setarecoordonate()
      Metoda  ce seteaza valorile coodonatelor din baza de date
     */
    public void setarecoordonate()
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:Pacman.db");
            stmt = c.createStatement();
            System.out.println("CONNECTION SUCCEEDED");
            ResultSet result=stmt.executeQuery("SELECT * FROM COORDONATE");
            xforhero=result.getInt(1);
            yforhero=result.getInt(2);
            xforblueinamic=result.getInt(3);
            yforblueinamic=result.getInt(4);
            xforpinkinamic=result.getInt(5);
            yforpinkinamic=result.getInt(6);
            xstar1=result.getInt(7);
            ystar1=result.getInt(8);
            xstar2=result.getInt(9);
            ystar2=result.getInt(10);
            xstar3=result.getInt(11);
            ystar3=result.getInt(12);


        }
        catch (SQLException | ClassNotFoundException e)
        {
            System.out.println("Data not found!"+e.getMessage());
        }
    }

  //Metode ce returneaza valorile coodonatelor
    public static int getXforhero() {


       return xforhero;
    }

    public static int getYforhero() {
        return yforhero;
    }

    public static int getXforblueinamic() {
        return xforblueinamic;
    }

    public static int getYforblueinamic() {
        return yforblueinamic;
    }

    public static int getXforpinkinamic() {
        return xforpinkinamic;
    }

    public static int getYforpinkinamic() {
        return yforpinkinamic;
    }

    public static int getXstar1() {
        return xstar1;
    }

    public static int getXstar2() {
        return xstar2;
    }

    public static int getXstar3() {
        return xstar3;
    }

    public static int getYstar1() {
        return ystar1;
    }

    public static int getYstar2() {
        return ystar2;
    }

    public static int getYstar3() {
        return ystar3;
    }
}
