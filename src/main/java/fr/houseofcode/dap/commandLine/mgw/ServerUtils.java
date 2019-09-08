package fr.houseofcode.dap.commandLine.mgw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/** @author mgw **/
public class ServerUtils {
    /** @author mgw **/
    private static final String USER_AGENT = "Mozilla/5.0";

    /**
     * get the next event's user's url.
     * @return the url with the next event of the defined user if it exists
     * @param userKey allows a value for the user's parameter added to the url
     * @throws IOException if the sent or received message is broken.
     */
    public String nextEvent(final String userKey) throws IOException {
        // TODO Auto-generated method stub
        String event = callServer("/calendar/nextEvent", userKey);
        return event;
    }

    /**
     * get the labels' user's url.
     * @return the url with the next event of the defined user if it exists
     * @param userKey allows a value for the user's parameter added to the url
     * @throws IOException if the sent or received message is broken.
     */
    public String getLabels(final String userKey) throws IOException {
        // TODO Auto-generated method stub
        String labels = callServer("/email/labels", userKey);
        return labels;
    }

    /**
     * get the unread emails' user's url.
     * @return the url with the next event of the defined user if it exists
     * @param userKey allows a value for the user's parameter added to the url
     * @throws IOException if the sent or received message is broken.
     */
    public String getUnreadedMail(final String userKey) throws IOException {
        // TODO Auto-generated method stub
        String unreadedMail = callServer("/email/nbUnread", userKey);
        return unreadedMail;
    }

    /**
     * get the next event.
     * @return the url with the next event of the defined user if it exists
     * @param url provide the service's url
     * @param userKey allows a value for the user's parameter added to the url
     * @throws IOException if the sent or received message is broken.
     */
    public String callServer(final String url, final String userKey) throws IOException {
        // HTTP GET request

        URL obj = new URL("http://localhost:8080" + url + "?userKey=" + userKey);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + "http://localhost:8080" + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

        return response.toString();

    }

}
