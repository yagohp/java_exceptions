# Java Exceptions

## Execício 1
```java
try {
  int var1 = 10 / 0;
} catch (ArithmeticException var2) {
  System.out.println("======= ArithmeticException =======");
}        
```

## Execício 2
```java
int[] numbers = {1, 2, 3};  
String text = null;

try {  
  System.out.println(numbers[5]); 
  System.out.println(text.length()); 
} catch (ArrayIndexOutOfBoundsException arrIndexOut) {  
  System.out.println("Index out of bounds!");
} catch (NullPointerException nullPointer) {  
  System.out.println("Null pointer!");  
} catch (Exception ex) {  
  System.out.println("Something went wrong!");  
}
```
## Exercício 3

```java
public void writeToFile(String filePath, String content) throws IOException {
  Files.writeString(Paths.get(filePath), content);
}

public void appendToFile(String filePath, String content) throws IOException {
  FileWriter fw = new FileWriter(filePath, true);
  try (PrintWriter pw = new PrintWriter(fw)) {
    pw.println(content);
  }
}
    
public String readFromFile(String filePath) throws IOException {
  return Files.readString(Paths.get(filePath));
}
```

ou

```java
  /**
  * Escreve um texto em um arquivo txt
  * @param filePath Localização do arquivo
  * @param content Texto que será escrito no arquivo
  * @throws IOException Se qualquer erro de I/O ocorrer
  */
  public void writeToFile(String filePath, String content) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
      writer.write(content);
    }
  }

  public String readFromFile(String filePath) throws IOException {
    StringBuilder content = new StringBuilder();
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = reader.readLine()) != null) {
        content.append(line).append("\n");
      }
    }
    
    return content.toString().trim();
  }
```
## Exercício 4

```java
private boolean paymentProcessed = false;
private boolean inventoryReserved = false;
private String transactionId = null;
        
public void processOrder(String item, int quantity) {
  try {
    // 1. Reservar inventário (state change)
    reserveInventory(item, quantity);
    inventoryReserved = true;
    System.out.println("Reservando " + quantity + " " + item);
                
    // 2. Simula requisição para API (40% de chance de falha)
    transactionId = simulatePaymentRequest(100 * quantity);
    paymentProcessed = true;
    System.out.println("Pagamento efetuado com sucesso! ID da Transação: " + transactionId);
                
    // 3. Confirm order
    System.out.println("Ordem finalizada");
                
  } catch (Exception e) {
    System.err.println("Erro: " + e.getMessage());          
  } finally {
    // Rollback
    if (!paymentProcessed && inventoryReserved) {
      cancelInventoryReservation(item, quantity);
      System.out.println("Rolled back nas reservas de inventário");
    }
  }
}
        
private void reserveInventory(String item, int quantity) throws Exception {
  if (new Random().nextDouble() < 0.1) { // 10% chance de falha no sistema de inventário
    throw new Exception("Sistema de inventário não disponível");
  }
  // modificações na base de dados
}
        
private String simulatePaymentRequest(int amount) throws Exception {
  if (new Random().nextDouble() < 0.4) { // 40% chance de falha de pagamento
    throw new Exception("Timeout no gateway de pagamento");
  }
  return "TXN-" + System.currentTimeMillis(); // ID de transição FAKE
}
        
private void cancelInventoryReservation(String item, int quantity) {
  System.out.println("Removendo " + quantity + " " + item + " do inventário");
  // Atualiza registro no banco de dados
}
        
public static void main(String[] args) {
  Exe4PaymentProcessor processor = new Exe4PaymentProcessor();
  processor.processOrder("Laptop", 2);
}
```


## Exercício 5

```java
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
```