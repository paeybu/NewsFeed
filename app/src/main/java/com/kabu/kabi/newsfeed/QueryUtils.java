package com.kabu.kabi.newsfeed;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public final class QueryUtils {

    /** Tag for the log messages */
    public static final String LOG_TAG = QueryUtils.class.getSimpleName();
    public static final String SAMPLE_JSON = "{\"response\":{\"status\":\"ok\",\"userTier\":\"developer\",\"total\":10471,\"startIndex\":1,\"pageSize\":10,\"currentPage\":1,\"pages\":1048,\"orderBy\":\"relevance\",\"results\":[{\"id\":\"world/2019/may/18/thailand-three-legged-dog-rescues-baby-buried-alive-by-teenage-mother\",\"type\":\"article\",\"sectionId\":\"world\",\"sectionName\":\"World news\",\"webPublicationDate\":\"2019-05-18T04:56:25Z\",\"webTitle\":\"Thailand: disabled dog rescues baby buried alive by teenage mother\",\"webUrl\":\"https://www.theguardian.com/world/2019/may/18/thailand-three-legged-dog-rescues-baby-buried-alive-by-teenage-mother\",\"apiUrl\":\"https://content.guardianapis.com/world/2019/may/18/thailand-three-legged-dog-rescues-baby-buried-alive-by-teenage-mother\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"world/2019/may/04/thailands-king-vajiralongkorn-is-crowned-as-three-day-coronation-begins\",\"type\":\"article\",\"sectionId\":\"world\",\"sectionName\":\"World news\",\"webPublicationDate\":\"2019-05-04T14:16:41Z\",\"webTitle\":\"King Vajiralongkorn of Thailand is crowned in elaborate ceremony\",\"webUrl\":\"https://www.theguardian.com/world/2019/may/04/thailands-king-vajiralongkorn-is-crowned-as-three-day-coronation-begins\",\"apiUrl\":\"https://content.guardianapis.com/world/2019/may/04/thailands-king-vajiralongkorn-is-crowned-as-three-day-coronation-begins\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"world/2019/may/03/thailand-coronation-king-maha-vajiralongkorn-rama-x\",\"type\":\"article\",\"sectionId\":\"world\",\"sectionName\":\"World news\",\"webPublicationDate\":\"2019-05-03T02:18:19Z\",\"webTitle\":\"Thailand prepares to crown King Maha Vajiralongkorn amid political tensions\",\"webUrl\":\"https://www.theguardian.com/world/2019/may/03/thailand-coronation-king-maha-vajiralongkorn-rama-x\",\"apiUrl\":\"https://content.guardianapis.com/world/2019/may/03/thailand-coronation-king-maha-vajiralongkorn-rama-x\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"world/2019/jan/23/thailand-marks-stilted-return-to-democracy-with-march-election\",\"type\":\"article\",\"sectionId\":\"world\",\"sectionName\":\"World news\",\"webPublicationDate\":\"2019-01-23T12:59:15Z\",\"webTitle\":\"Thailand to hold elections on 24 March\",\"webUrl\":\"https://www.theguardian.com/world/2019/jan/23/thailand-marks-stilted-return-to-democracy-with-march-election\",\"apiUrl\":\"https://content.guardianapis.com/world/2019/jan/23/thailand-marks-stilted-return-to-democracy-with-march-election\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"world/2019/mar/24/thailand-election-everything-you-need-to-know-about-sundays-vote\",\"type\":\"article\",\"sectionId\":\"world\",\"sectionName\":\"World news\",\"webPublicationDate\":\"2019-03-24T00:15:22Z\",\"webTitle\":\"Thailand election: everything you need to know about Sunday's vote\",\"webUrl\":\"https://www.theguardian.com/world/2019/mar/24/thailand-election-everything-you-need-to-know-about-sundays-vote\",\"apiUrl\":\"https://content.guardianapis.com/world/2019/mar/24/thailand-election-everything-you-need-to-know-about-sundays-vote\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"football/2019/jan/24/fifa-urges-thailand-release-bahraini-footballer-hakeem-al-araibi\",\"type\":\"article\",\"sectionId\":\"football\",\"sectionName\":\"Football\",\"webPublicationDate\":\"2019-01-24T17:44:36Z\",\"webTitle\":\"Fifa urges Thailand to release Bahraini footballer Hakeem al-Araibi\",\"webUrl\":\"https://www.theguardian.com/football/2019/jan/24/fifa-urges-thailand-release-bahraini-footballer-hakeem-al-araibi\",\"apiUrl\":\"https://content.guardianapis.com/football/2019/jan/24/fifa-urges-thailand-release-bahraini-footballer-hakeem-al-araibi\",\"isHosted\":false,\"pillarId\":\"pillar/sport\",\"pillarName\":\"Sport\"},{\"id\":\"world/2019/jan/26/thailand-yoga-retreat-in-sexual-assault-scandal-reopens\",\"type\":\"article\",\"sectionId\":\"world\",\"sectionName\":\"World news\",\"webPublicationDate\":\"2019-01-26T18:37:39Z\",\"webTitle\":\"Thailand yoga retreat in sexual assault scandal reopens\",\"webUrl\":\"https://www.theguardian.com/world/2019/jan/26/thailand-yoga-retreat-in-sexual-assault-scandal-reopens\",\"apiUrl\":\"https://content.guardianapis.com/world/2019/jan/26/thailand-yoga-retreat-in-sexual-assault-scandal-reopens\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"world/2019/may/04/king-vajiralongkorn-who-is-thailand-new-monarch\",\"type\":\"article\",\"sectionId\":\"world\",\"sectionName\":\"World news\",\"webPublicationDate\":\"2019-05-04T00:02:48Z\",\"webTitle\":\"King Vajiralongkorn: who is Thailand's new monarch?\",\"webUrl\":\"https://www.theguardian.com/world/2019/may/04/king-vajiralongkorn-who-is-thailand-new-monarch\",\"apiUrl\":\"https://content.guardianapis.com/world/2019/may/04/king-vajiralongkorn-who-is-thailand-new-monarch\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"world/2019/jan/07/saudi-woman-fleeing-family-wont-be-deported-say-thai-officials\",\"type\":\"article\",\"sectionId\":\"world\",\"sectionName\":\"World news\",\"webPublicationDate\":\"2019-01-07T14:55:12Z\",\"webTitle\":\"Saudi woman fleeing family temporarily admitted to Thailand\",\"webUrl\":\"https://www.theguardian.com/world/2019/jan/07/saudi-woman-fleeing-family-wont-be-deported-say-thai-officials\",\"apiUrl\":\"https://content.guardianapis.com/world/2019/jan/07/saudi-woman-fleeing-family-wont-be-deported-say-thai-officials\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"world/2019/apr/18/us-bitcoin-trader-may-face-death-penalty-in-thailand-over-sea-home\",\"type\":\"article\",\"sectionId\":\"world\",\"sectionName\":\"World news\",\"webPublicationDate\":\"2019-04-18T05:39:56Z\",\"webTitle\":\"US bitcoin trader may face death penalty in Thailand over 'sea home'\",\"webUrl\":\"https://www.theguardian.com/world/2019/apr/18/us-bitcoin-trader-may-face-death-penalty-in-thailand-over-sea-home\",\"apiUrl\":\"https://content.guardianapis.com/world/2019/apr/18/us-bitcoin-trader-may-face-death-penalty-in-thailand-over-sea-home\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"}]}}\n";

    public static List<News> fetchData(String requestUrl) {
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }

        // Extract relevant fields from the JSON response and create an {@link Event} object
        List<News> newsList = extractFromJson(jsonResponse);

        // Return the {@link Event}
        return newsList;
    }

    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with creating URL ", e);
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    public static List<News> extractFromJson(String bookJSON) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(bookJSON)) {
            return null;
        }

        try {
            JSONObject baseJsonResponse = new JSONObject(bookJSON);
            JSONObject response = baseJsonResponse.getJSONObject("response");
            JSONArray itemsArray = response.getJSONArray("results");

            if (itemsArray.length() > 0) {
                List<News> newsList = new ArrayList<News>();
                for (int i = 0; i < itemsArray.length(); i++) {
                    JSONObject result = itemsArray.getJSONObject(i);

                    String title = result.getString("webTitle");
                    String section = result.getString("sectionName");
                    newsList.add(new News(title, section));
                }
                return newsList;
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the earthquake JSON results", e);
        }
        return null;
    }
}




