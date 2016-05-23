import org.apache.commons.validator.routines.UrlValidator;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 *
 * Created by Luis E. Rojas on 5/19/16.
 *
 */
class HTTPClient {
    private String _url;

    HTTPClient(String url) throws Exception{
        UrlValidator urlValidator = new UrlValidator();
        if(!urlValidator.isValid(url))
            throw new Exception("La URL escrita no es valida.");
        this._url = url;
    }

    void GetSiteInfo(){
        try{
            org.jsoup.nodes.Document doc = Jsoup.connect(this._url).get();
            String html = doc.html();
            String[] lines = html.split("\n");
            System.out.println("Este sitio web contiene " + lines.length + " Lineas de codigo HTML.");
            Elements p = doc.select("p");
            System.out.println("Este sitio web contiene: " + p.size() + " Tags de Parrafos.");
            Elements img = doc.select("img");
            System.out.println("Este sitio web contiene: " + img.size() + " Tags de Imagenes.");
            Elements forms = doc.select("form");
            System.out.println("Este sitio web contiene: " + forms.size() + " Tags de Formas.");

            if(forms.size() != 0){
                System.out.println("Para las cuales:");
                for(int i = 0; i < forms.size();i++){
                    Element e = forms.get(i);
                    System.out.println("\tEl formulario#"+i+" con Action => " + e.attr("action") + " Contiene las siguientes entradas de Datos:");
                    System.out.println("\tEl formulario utiliza el metodo: " + e.attr("method"));
                    if(e.attr("method").toLowerCase().equals("post")){
                            Connection.Response res = Jsoup.connect(e.attr("action")).data("name","practica1").method(Connection.Method.POST).execute();
                        System.out.print("\tEl Tipo de la respuesta del formulario se encuentra a contnuacion: \n");
                        System.out.println("\t" + res.contentType());
                        System.out.println();

                    }
                    Elements inputs = e.select("input");
                    for(Element input : inputs){
                        System.out.println("\t\tInput Tipo:" + input.attr("type") + " de Nombre:" + input.attr("name"));
                    }
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }


    }
}
