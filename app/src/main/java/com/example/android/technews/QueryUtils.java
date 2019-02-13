package com.example.android.technews;

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

public class QueryUtils {

    private QueryUtils() {
    }

    public static List<News> fetchNewsData(String requestUrl) {
        URL url = createUrl(requestUrl);

        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<News> newsList = extractFeatureFromJson(jsonResponse);
        return newsList;
    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
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

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.d("Error response code:", String.valueOf(urlConnection.getResponseCode()));
            }
        } catch (IOException e) {
            e.printStackTrace();
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

        private static List <News> extractFeatureFromJson (String newsJSON){

            if (TextUtils.isEmpty(newsJSON)) {
                return null;
            }

            List<News> newsList = new ArrayList<>();

            try {

                JSONObject baseJsonResponse = new JSONObject(newsJSON);

                String response = baseJsonResponse.getString("response");

                JSONObject object = new JSONObject(response);

                JSONArray newsArray = object.getJSONArray("results");


                for (int i = 0; i < newsArray.length(); i++) {

                    JSONObject currentResults = newsArray.getJSONObject(i);

                    String title = currentResults.getString("webTitle");
                    String sectionName = currentResults.getString("sectionName");
                    String date = currentResults.getString("webPublicationDate");
                    String webUrl = currentResults.getString("webUrl");



                    JSONArray webTitle = currentResults.getJSONArray("tags");
                       String author = " ";
                       if(webTitle.length() != 0){
                           JSONObject currenttagsAuthor = webTitle.getJSONObject(0);
                           author = currenttagsAuthor.getString("webTitle");
                    } else {
                           author = "No Author";
                       }

                       News news = new News(title, sectionName, date, webUrl,author);

                            newsList.add(news);
                        }
                    } catch (JSONException e){
                        e.printStackTrace();
                }

        return newsList;
    }
}