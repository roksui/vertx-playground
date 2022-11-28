import com.google.common.util.concurrent.ListenableFuture;
import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.roksui.grpc.Profile;
import org.roksui.grpc.Profiles;
import org.roksui.grpc.TraceGrpc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TracesClient {

    public static void main(String[] args) {
        System.out.println("gRPC Client started");
        TracesClient.getInstance().startService();
    }

    private static class SingletonHolder {
        private static final TracesClient instance = new TracesClient();
    }

    public static TracesClient getInstance() {
        return SingletonHolder.instance;
    }

    private final TraceGrpc.TraceFutureStub futureStub;
    private List<Profile> chunk = new ArrayList<>();

    private TracesClient() {
        String target = "127.0.0.1:8080";
        ManagedChannel channel = ManagedChannelBuilder
                .forTarget(target)
                .usePlaintext()
                .build();
        futureStub = TraceGrpc.newFutureStub(channel);

        buildMockTraces();
    }

    /**
     * Build mock Profiles message containing 10,000 profile items
     */
    private void buildMockTraces() {
        Profile p = Profile.newBuilder().setText(new String(new char[1000])).build();
        for (int i = 0; i < 10000; i++) {
            chunk.add(p);
        }
    }

    /**
     * Starts the continuous unary uploading service
     * Each unary message is around 10MB in serialized size
     */
    public void startService() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            int i = 0;
            while(i < 5) {
                try {
                    Profiles profiles = Profiles.newBuilder().addAllItems(chunk).setTimestamp(System.currentTimeMillis()).build();
                    long before = System.currentTimeMillis();
                    ListenableFuture<Empty> listenableFuture = futureStub.uploadTraces(profiles);
                    listenableFuture.addListener(() -> System.out.println("Received response. It took " + (System.currentTimeMillis() - before)), Executors.newSingleThreadExecutor());
                    Thread.sleep(100);
                } catch (Throwable t) {
                    t.printStackTrace();
                } finally {
                    i++;
                }
            }
        });
    }
}
