package exercicio5;

public class Exe5Main {
    public static void main(String[] args) {
        Exe5HttpClient client = new Exe5HttpClient();
        String url = "https://api.example.com/data";
        
        try {
            String response = client.sendRequest(url);
            client.parseResponse(response);
            System.out.println("Request completed successfully!");
        } catch (HttpException e) {
            System.err.println("HTTP Error: " + e.getMessage());
        } catch (HttpResponseException e) {
            System.err.println("Server Response Error: " + e.getMessage());
        } catch (DeserializationException e) {
            System.err.println("Parsing Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }
}
