package com.example.translatorproject;

import java.io.IOException;
import java.util.Scanner;

import com.google.gson.*;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class API {
    private static String key = "9211000d57984c989ff888ce177bd667";

    // location, also known as region.
    // required if you're using a multi-service or regional (not global) resource. It can be found in the Azure portal on the Keys and Endpoint page.
    private static String location = "southeastasia";


    // Instantiates the OkHttpClient.
    static OkHttpClient client = new OkHttpClient();

    // This function performs a POST request.
    public static String Post(String Word) throws IOException {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType,
                "[{\"Text\": \"" + Word + "\"}]");
        Request request = new Request.Builder()
                .url("https://api.cognitive.microsofttranslator.com/translate?api-version=3.0&from=en&to=vi")
                .post(body)
                .addHeader("Ocp-Apim-Subscription-Key", key)
                // location required if you're using a multi-service or regional (not global) resource.
                .addHeader("Ocp-Apim-Subscription-Region", location)
                .addHeader("Content-type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    // This function prettifies the json response.
    public static String prettify(String json_text) {
        JsonParser parser = new JsonParser();
        JsonElement json = parser.parse(json_text);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(json);
    }

    public static String translate(String word) {
        try {
            String response = Post(word); // Assuming Post method is non-static now

            // Parse the response JSON to get translated text
            JsonParser parser = new JsonParser();
            JsonElement json = parser.parse(response);
            String translatedText = json.getAsJsonArray().get(0).getAsJsonObject()
                    .getAsJsonArray("translations").get(0).getAsJsonObject()
                    .get("text").getAsString();

            return translatedText; // Return the translated text
        } catch (Exception e) {
            // Optionally log the exception or notify the user
            e.printStackTrace();
            return null; // Return null or some error indicator
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String word = scanner.nextLine();
        System.out.println(translate(word));
    }
}