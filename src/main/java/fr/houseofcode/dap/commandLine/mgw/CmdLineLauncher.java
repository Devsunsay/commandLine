package fr.houseofcode.dap.commandLine.mgw;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/** @author mgw **/
public final class CmdLineLauncher {

    /** Singleton. */
    private static CmdLineLauncher instance;

    /**
     * is an empty private constructor (cf.Singleton pattern).
     **/
    private CmdLineLauncher() {
        LOG.debug("CmdLineLauncher's Constructor");
    }

    /**
     * create only one new instance of CmdLineLauncher (Singleton).
     * @return the only object CmdLineLauncher (cf. Singleton).
     **/
    public static CmdLineLauncher getInstance() {
        if (instance == null) {
            instance = new CmdLineLauncher();
        }
        return instance;
    }

    /**
    * @param args
    */
    private static final Logger LOG = LogManager.getLogger();

    /**
    * @param args is the external parameters.
    * @throws IOException if the sent or received message is broken.
    * @throws GeneralSecurityException if there's a security failure.
    */

    public static void main(final String[] args) throws IOException, GeneralSecurityException {
        ServerUtils su = new ServerUtils();
        LOG.debug("Debut du main avec comme arguments :  " + args);

        String choixUserKey = args[0];
        String nbemails = su.getUnreadedMail(choixUserKey);
        String displayLabel = su.getLabels(choixUserKey);
        String allEvents = su.nextEvent(choixUserKey);

        int n = Integer.parseInt(args[1]);
        switch (n) {
        case 1:
            LOG.debug("Connexion de l'utilisateur à son calendrier : ");
            System.out.println("Tiens toi pret pour ton prochain events  ^^\n" + allEvents);
            break;

        case 2:
            LOG.debug("Connexion de l'utilisateur à son comtpe email : ");
            System.out.println("Vous avez : " + nbemails + " email(s) non lu(s) \n");
            break;

        case 3:
            LOG.debug("Connexion de l'utilisateur à sa classe de label :");
            System.out.println(displayLabel);
            break;

        case 4:
            LOG.debug("Affichage de toutes les données de l'utilisateur");
            System.out.println("Tiens toi pret pour ton prochain events  ^^\n" + allEvents);
            System.out.println("Vous avez : " + nbemails + " email(s) non lu(s) \n");
            System.out.println("Voici la liste de vos labels" + displayLabel + " message \n");
            break;

        default:
            System.out.println("Vous n'avez rien à afficher !");
        }
    }

}
