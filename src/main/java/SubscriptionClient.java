import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.WebSocket;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.handler.graphql.ApolloWSMessageType;

public class SubscriptionClient extends AbstractVerticle {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx(new VertxOptions().setBlockedThreadCheckInterval(1000*60*60));
        vertx.deployVerticle(new SubscriptionClient());

        try {
            Thread.sleep(50000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void start() {
        HttpClient httpClient = vertx.createHttpClient(new HttpClientOptions().setDefaultPort(8080));
        httpClient.webSocket("/graphql", webSocketRes -> {
            if (webSocketRes.succeeded()) {
                WebSocket webSocket = webSocketRes.result();
                System.out.println("Local websocket: " + webSocket.localAddress().host() + ":" + webSocket.localAddress().port());
                System.out.println("Remote websocket: " + webSocket.remoteAddress().host() + ":" + webSocket.remoteAddress().port());
                webSocket.handler(message -> {
                    System.out.println("Received message: " + message.toString());
                });


                JsonObject initialRequest = new JsonObject()
                    .put("id", "0")
                    .put("type", ApolloWSMessageType.CONNECTION_INIT.getText());

                String body = "subscription { newProject }";

                JsonObject request = new JsonObject()
                    .put("id", "1")
                    .put("type", ApolloWSMessageType.START.getText())
                    .put("payload", new JsonObject().put("query", body));
                webSocket.write(initialRequest.toBuffer());
                webSocket.write(request.toBuffer());

                //webSocket.end();

            } else {
                System.out.println("Some error occurred on receiving the websocket message");
                webSocketRes.cause().printStackTrace();
            }
        });
    }
}
