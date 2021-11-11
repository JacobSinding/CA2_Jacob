package utils;

import com.google.gson.Gson;
import dtos.MemeDTO;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class HttpUtils {

    private static Gson gson = new Gson();

    public static MemeDTO fetchDataParallel() throws IOException, MalformedURLException, ExecutionException, InterruptedException {
        ExecutorService es = Executors.newCachedThreadPool();
        Future<MemeDTO> memeDTOFuture = es.submit(
                () -> gson.fromJson(HttpUtils.fetchData("https://api.imgflip.com/get_memes"), MemeDTO.class)
        );
        MemeDTO memeDTO = memeDTOFuture.get();
        return memeDTO;
    }

    public static String fetchData(String _url) throws MalformedURLException, IOException {
        URL url = new URL(_url);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        //con.setRequestProperty("Accept", "application/json;charset=UTF-8");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("User-Agent", "server");

        Scanner scan = new Scanner(con.getInputStream());
        String jsonStr = null;
        if (scan.hasNext()) {
            jsonStr = scan.nextLine();
        }
        scan.close();
        return jsonStr;
    }
}

