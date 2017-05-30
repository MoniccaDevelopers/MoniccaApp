package id.astrajingga.monicca.auth;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by grb on 4/18/2017.
 */

public class register_process extends AsyncTask<String,Void,String> {

    Context ctx;
    register_process(Context ctx){
        this.ctx=ctx;
    }
    protected String doInBackground(String... params) {

        String reg_url="http://monicca.ai/api/register.php";
        String method= params[0];
        if (method.equals("register")){
            String nama = params [1];
            String harga = params [2];
            String durasi = params [3];
            String hasil = params [4];

            try {
                URL url= new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

                String data = URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(nama,"UTF-8")+"&"+
                              URLEncoder.encode("signin_password","UTF-8")+"="+URLEncoder.encode(harga,"UTF-8")+"&"+
                              URLEncoder.encode("phone","UTF-8")+"="+URLEncoder.encode(durasi,"UTF-8")+"&"+
                              URLEncoder.encode("email","UTF-8")+"=" +URLEncoder.encode(hasil,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();
                InputStream IS = httpURLConnection.getInputStream();
                IS.close();

                return "Registration Sukses";
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute (String result) {
        Toast.makeText(ctx,result,Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
