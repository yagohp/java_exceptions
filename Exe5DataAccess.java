package exercicio5;

public class Exe5DataAccess {
    private Exe5HttpClient httpClient = new Exe5HttpClient();

    public String get(String url) throws HttpException, HttpResponseException {
        return httpClient.sendRequest(url);
    }
}
