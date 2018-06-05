package hr.veleri.eklub.Eklub.hr.veleri.eklub.Eklub.model;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import android.os.AsyncTask;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;


public class Model extends AsyncTask<String, Void, String> {
    private final String baseUrl = "https://192.168.1.8:8080/api/";
    public String url;
    public String urlMethod;
    public List<NameValuePair> params;


    public String getJSONObjectFromURL() throws IOException, JSONException {
        HttpURLConnection urlConnection = null;
        URL url = new URL(baseUrl+this.url);
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod(urlMethod);
        urlConnection.setReadTimeout(10000 /* milliseconds */ );
        urlConnection.setConnectTimeout(15000 /* milliseconds */ );

        /*String accessToken = "Veleri2017_prograsmko";
        urlConnection.setRequestProperty("AppToken",accessToken);
        urlConnection.setRequestProperty("Content-Type", "application/" + "json");
        */
        urlConnection.setRequestProperty("User-Agent","Mozilla/5.0 ( compatible ) ");
        urlConnection.setRequestProperty("Accept","*/*");
        OutputStream os;
        if(urlMethod != "GET"){
            os = urlConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(getQuery(params));
            writer.flush();
            writer.close();
            os.close();
        }

        urlConnection.connect();

        InputStream is;
        BufferedReader br;
        if(urlMethod != "GET"){
            Log.i("response kod",String.valueOf(urlConnection.getResponseCode()));

            is = new BufferedInputStream(urlConnection.getInputStream());
            br = new BufferedReader(new InputStreamReader(is));
        }
        else
            br = new BufferedReader(new InputStreamReader(url.openStream()));


        StringBuilder sb = new StringBuilder();

        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        br.close();

        String jsonString = sb.toString();
        System.out.println("JSON: " + jsonString);
        Log.i("Json",jsonString);

        //return new JSONObject(jsonString);
        return jsonString;
        //response =  new JSONArray(jsonString);
    }

    private String getQuery(List<NameValuePair> params) throws UnsupportedEncodingException
    {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (NameValuePair pair : params)
        {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
        }

        return result.toString();
    }

    @Override
    protected String doInBackground(String... strings) {
        //disableSSLCertificateChecking();
        //requestContent("");
        trustEveryone();
        try {
           return getJSONObjectFromURL();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void trustEveryone() {
        try {
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier(){
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }});
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new X509TrustManager[]{new X509TrustManager(){
                public void checkClientTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {}
                public void checkServerTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {}
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }}}, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(
                    context.getSocketFactory());
        } catch (Exception e) { // should never happen
            e.printStackTrace();
        }
    }
}
