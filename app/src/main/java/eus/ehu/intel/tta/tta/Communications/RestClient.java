package eus.ehu.intel.tta.tta.Communications;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by eduardo on 1/01/16.
 */
public class RestClient {

    private final Map<String,String> properties=new HashMap<>();

    private String pathServer=null;
    private String pathApplication=null;

    public RestClient(String pathServer,String pathApplication){
        this.pathServer=pathServer;
        this.pathApplication=pathApplication;
    }
    public RestClient(){
    }

    private HttpURLConnection getConnection(String path) throws IOException {
        if(pathServer==null || path==null)return null;
        URL url=new URL(String.format("%s/%s",pathServer,path));
        HttpURLConnection conn=(HttpURLConnection)url.openConnection();
        for(Map.Entry<String,String> property:properties.entrySet()){
            conn.setRequestProperty(property.getKey(),property.getValue());
        }
        conn.setUseCaches(false);
        return conn;
    }

    public String getString(String path) throws IOException {
        if(path==null || path.equals("")){
            throw new RestException("Except that you do not have parameters");
        }
        HttpURLConnection conn=null;
        try{
            conn=getConnection(path);
            BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                return br.readLine();

        }finally {
            if(conn!=null){
                conn.disconnect();
            }
        }

    }

    public int postJson(final String json,String path) throws IOException {
        if(path==null || json==null || path.equals("")){
            throw new RestException("Except that you do not have parameters");
        }
        HttpURLConnection conn=null;
        try{
            conn=getConnection(path);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type","application/json; charset=UTF-8");
            PrintWriter pw=new PrintWriter(conn.getOutputStream());
                pw.print(json);
                return conn.getResponseCode();
        }finally {
            if(conn!=null)conn.disconnect();
        }
    }


    public int postFile(String path,InputStream is,String fileName) throws IOException {
        String boundary=Long.toString(System.currentTimeMillis());
        String newLine="\r\n";
        String prefix="--";
        HttpURLConnection conn=null;
        try{
            conn=getConnection(path);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
            DataOutputStream out=new DataOutputStream(conn.getOutputStream());
            out.writeBytes(prefix+boundary+newLine);
            out.writeBytes("Content-Disposition: form-data; name=\"file\";filename\""+fileName+"\""+newLine);
            out.writeBytes(newLine);
            byte[] data=new byte[1024*1024];
            int len;
            while ((len=is.read(data))>0)
                out.write(data,0,len);
            out.writeBytes(newLine);
            out.writeBytes(prefix+boundary+prefix+newLine);
            out.close();
            return conn.getResponseCode();
        }finally {
            if(conn != null){
                conn.disconnect();
            }
        }
    }

    public String getPathServer() {
        return pathServer;
    }

    public void setPathServer(String pathServer) {
        this.pathServer = pathServer;
    }

    public class RestException extends IOException {
        public RestException() { super(); }
        public RestException(String message) { super(message); }
        public RestException(String message, Throwable cause) { super(message, cause); }
        public RestException(Throwable cause) { super(cause); }
    }
}
