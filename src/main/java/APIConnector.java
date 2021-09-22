import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class APIConnector {
    public static StringBuilder getJson() throws IOException {
        String requestUrl = "http://data.egov.kz/api/v4/gbd_ul/v1?apiKey=4805e49b8e46458dbe785c5adb77f21b&source={\"from\":1,\"size\":2,\"query\":{\"match\":{\"bin\":\"980740001878\"}}}";

        URL url = new URL(requestUrl);
        HttpURLConnection connection = null;
        StringBuilder result = new StringBuilder();
        connection = (HttpURLConnection) url.openConnection();
        connection.setDoInput(true);
        InputStream inputStream = connection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        String line = reader.readLine();
        while (line != null) {
            result.append(line);
            line = reader.readLine();
        }
        return result;
    }
}
