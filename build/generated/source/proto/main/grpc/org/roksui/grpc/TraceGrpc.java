package org.roksui.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.48.1)",
    comments = "Source: trace.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class TraceGrpc {

  private TraceGrpc() {}

  public static final String SERVICE_NAME = "Trace";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.roksui.grpc.Profiles,
      com.google.protobuf.Empty> getUploadTracesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UploadTraces",
      requestType = org.roksui.grpc.Profiles.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.roksui.grpc.Profiles,
      com.google.protobuf.Empty> getUploadTracesMethod() {
    io.grpc.MethodDescriptor<org.roksui.grpc.Profiles, com.google.protobuf.Empty> getUploadTracesMethod;
    if ((getUploadTracesMethod = TraceGrpc.getUploadTracesMethod) == null) {
      synchronized (TraceGrpc.class) {
        if ((getUploadTracesMethod = TraceGrpc.getUploadTracesMethod) == null) {
          TraceGrpc.getUploadTracesMethod = getUploadTracesMethod =
              io.grpc.MethodDescriptor.<org.roksui.grpc.Profiles, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UploadTraces"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.roksui.grpc.Profiles.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new TraceMethodDescriptorSupplier("UploadTraces"))
              .build();
        }
      }
    }
    return getUploadTracesMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static TraceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TraceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TraceStub>() {
        @java.lang.Override
        public TraceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TraceStub(channel, callOptions);
        }
      };
    return TraceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static TraceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TraceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TraceBlockingStub>() {
        @java.lang.Override
        public TraceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TraceBlockingStub(channel, callOptions);
        }
      };
    return TraceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static TraceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TraceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TraceFutureStub>() {
        @java.lang.Override
        public TraceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TraceFutureStub(channel, callOptions);
        }
      };
    return TraceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class TraceImplBase implements io.grpc.BindableService {

    /**
     */
    public void uploadTraces(org.roksui.grpc.Profiles request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUploadTracesMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getUploadTracesMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                org.roksui.grpc.Profiles,
                com.google.protobuf.Empty>(
                  this, METHODID_UPLOAD_TRACES)))
          .build();
    }
  }

  /**
   */
  public static final class TraceStub extends io.grpc.stub.AbstractAsyncStub<TraceStub> {
    private TraceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TraceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TraceStub(channel, callOptions);
    }

    /**
     */
    public void uploadTraces(org.roksui.grpc.Profiles request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUploadTracesMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class TraceBlockingStub extends io.grpc.stub.AbstractBlockingStub<TraceBlockingStub> {
    private TraceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TraceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TraceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.google.protobuf.Empty uploadTraces(org.roksui.grpc.Profiles request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUploadTracesMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class TraceFutureStub extends io.grpc.stub.AbstractFutureStub<TraceFutureStub> {
    private TraceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TraceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TraceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> uploadTraces(
        org.roksui.grpc.Profiles request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUploadTracesMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_UPLOAD_TRACES = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final TraceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(TraceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_UPLOAD_TRACES:
          serviceImpl.uploadTraces((org.roksui.grpc.Profiles) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class TraceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    TraceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.roksui.grpc.TraceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Trace");
    }
  }

  private static final class TraceFileDescriptorSupplier
      extends TraceBaseDescriptorSupplier {
    TraceFileDescriptorSupplier() {}
  }

  private static final class TraceMethodDescriptorSupplier
      extends TraceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    TraceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (TraceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new TraceFileDescriptorSupplier())
              .addMethod(getUploadTracesMethod())
              .build();
        }
      }
    }
    return result;
  }
}
