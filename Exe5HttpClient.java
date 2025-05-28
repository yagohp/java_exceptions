package exercicio5;

import java.util.Random;

class Exe5HttpClient {
    private Random random = new Random();

    // Simula uma requisição HTTP que pode falhar
    public String sendRequest(String url) throws HttpException, HttpResponseException {
        // simula erros aleatórios
        int errorCode = random.nextInt(3); // 0, 1, or 2

        if (errorCode == 0) {
            throw new HttpException("Connection failed: Timeout");
        } else if (errorCode == 1) {
            throw new HttpResponseException("Server error: 500 Internal Server Error");
        }

        return "{\"data\": \"Success!\"}"; // Mock JSON response
    }

    // simula a deserialização na resposta da requisição
    public void parseResponse(String json) throws DeserializationException {
        if (json == null || !json.contains("data")) {
            throw new DeserializationException("Invalid JSON: Cannot deserialize");
        }
        System.out.println("Parsed data: " + json);
    }
}