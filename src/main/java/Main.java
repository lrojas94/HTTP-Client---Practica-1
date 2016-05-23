import java.io.BufferedReader;
import java.io.InputStreamReader;
/**
 * Created by Luis E. Rojas on 5/19/16.
 *
 *
 */
public class Main {
    public static void main(String args[]){
        System.out.println("Porfavor, digite la URL que desea analizar:");
        BufferedReader buffer=new BufferedReader(new InputStreamReader(System.in));

        try{
            String url = buffer.readLine();
            System.out.println("Usted escribio: " + url);
            HTTPClient client = new HTTPClient(url);
            client.GetSiteInfo();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
