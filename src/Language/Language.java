package Language;
/**
 * sets language based on user's system default
 */

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Language {

    public static Locale getUserLanguage() {
        System.out.println(Locale.getDefault()); //starting default
        //Locale frenchLocale = new Locale("fr", "FR"); //creates french locale comment out after testing
      //  Locale.setDefault(frenchLocale); //changes default to french, comment out after testing
      //  Locale locale = frenchLocale;//sets french to the locale comment out after testing
        Locale locale = Locale.getDefault(); //gets the default from windows
        return locale;
    }

    public static ResourceBundle  getUserBundle()
    {
        Locale locale = getUserLanguage(); //sets the default language to locale
        ResourceBundle rb = ResourceBundle.getBundle("Language/Lang"); //takes locale to the Lang files adn looks for matching language
        return rb;
    }
        /*
        try {
            if (Locale.getDefault().getLanguage().equals("fr")) {

                String rocket = rb.getString("userid");

                System.out.println(rb.getString("userid"));
                System.out.println(rb.getString("password"));
                System.out.println(rb.getString("submit"));
                System.out.println(rb.getString("location"));
                System.out.println(rb.getString("exit"));
            }
        } catch (MissingResourceException e) {
            e.printStackTrace();
           /* Alert language = new Alert(Alert.AlertType.ERROR);
            language.setTitle("Language Not Found");
            language.setContentText("Please Enter a Language");
            language.showAndWait();


        }

         */
    }


