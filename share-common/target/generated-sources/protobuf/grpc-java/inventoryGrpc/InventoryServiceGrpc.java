package inventoryGrpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.69.0)",
    comments = "Source: inventory-item.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class InventoryServiceGrpc {

  private InventoryServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "InventoryService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<inventoryGrpc.InventoryRequest,
      inventoryGrpc.InventoryResponse> getIsInStockMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "isInStock",
      requestType = inventoryGrpc.InventoryRequest.class,
      responseType = inventoryGrpc.InventoryResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<inventoryGrpc.InventoryRequest,
      inventoryGrpc.InventoryResponse> getIsInStockMethod() {
    io.grpc.MethodDescriptor<inventoryGrpc.InventoryRequest, inventoryGrpc.InventoryResponse> getIsInStockMethod;
    if ((getIsInStockMethod = InventoryServiceGrpc.getIsInStockMethod) == null) {
      synchronized (InventoryServiceGrpc.class) {
        if ((getIsInStockMethod = InventoryServiceGrpc.getIsInStockMethod) == null) {
          InventoryServiceGrpc.getIsInStockMethod = getIsInStockMethod =
              io.grpc.MethodDescriptor.<inventoryGrpc.InventoryRequest, inventoryGrpc.InventoryResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "isInStock"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  inventoryGrpc.InventoryRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  inventoryGrpc.InventoryResponse.getDefaultInstance()))
              .setSchemaDescriptor(new InventoryServiceMethodDescriptorSupplier("isInStock"))
              .build();
        }
      }
    }
    return getIsInStockMethod;
  }

  private static volatile io.grpc.MethodDescriptor<inventoryGrpc.InventoryRequest,
      inventoryGrpc.InventoryResponse> getDeductFromInventoryGrpcMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "deductFromInventoryGrpc",
      requestType = inventoryGrpc.InventoryRequest.class,
      responseType = inventoryGrpc.InventoryResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<inventoryGrpc.InventoryRequest,
      inventoryGrpc.InventoryResponse> getDeductFromInventoryGrpcMethod() {
    io.grpc.MethodDescriptor<inventoryGrpc.InventoryRequest, inventoryGrpc.InventoryResponse> getDeductFromInventoryGrpcMethod;
    if ((getDeductFromInventoryGrpcMethod = InventoryServiceGrpc.getDeductFromInventoryGrpcMethod) == null) {
      synchronized (InventoryServiceGrpc.class) {
        if ((getDeductFromInventoryGrpcMethod = InventoryServiceGrpc.getDeductFromInventoryGrpcMethod) == null) {
          InventoryServiceGrpc.getDeductFromInventoryGrpcMethod = getDeductFromInventoryGrpcMethod =
              io.grpc.MethodDescriptor.<inventoryGrpc.InventoryRequest, inventoryGrpc.InventoryResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "deductFromInventoryGrpc"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  inventoryGrpc.InventoryRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  inventoryGrpc.InventoryResponse.getDefaultInstance()))
              .setSchemaDescriptor(new InventoryServiceMethodDescriptorSupplier("deductFromInventoryGrpc"))
              .build();
        }
      }
    }
    return getDeductFromInventoryGrpcMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static InventoryServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<InventoryServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<InventoryServiceStub>() {
        @java.lang.Override
        public InventoryServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new InventoryServiceStub(channel, callOptions);
        }
      };
    return InventoryServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static InventoryServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<InventoryServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<InventoryServiceBlockingStub>() {
        @java.lang.Override
        public InventoryServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new InventoryServiceBlockingStub(channel, callOptions);
        }
      };
    return InventoryServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static InventoryServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<InventoryServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<InventoryServiceFutureStub>() {
        @java.lang.Override
        public InventoryServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new InventoryServiceFutureStub(channel, callOptions);
        }
      };
    return InventoryServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void isInStock(inventoryGrpc.InventoryRequest request,
        io.grpc.stub.StreamObserver<inventoryGrpc.InventoryResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getIsInStockMethod(), responseObserver);
    }

    /**
     */
    default void deductFromInventoryGrpc(inventoryGrpc.InventoryRequest request,
        io.grpc.stub.StreamObserver<inventoryGrpc.InventoryResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeductFromInventoryGrpcMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service InventoryService.
   */
  public static abstract class InventoryServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return InventoryServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service InventoryService.
   */
  public static final class InventoryServiceStub
      extends io.grpc.stub.AbstractAsyncStub<InventoryServiceStub> {
    private InventoryServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected InventoryServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new InventoryServiceStub(channel, callOptions);
    }

    /**
     */
    public void isInStock(inventoryGrpc.InventoryRequest request,
        io.grpc.stub.StreamObserver<inventoryGrpc.InventoryResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getIsInStockMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deductFromInventoryGrpc(inventoryGrpc.InventoryRequest request,
        io.grpc.stub.StreamObserver<inventoryGrpc.InventoryResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeductFromInventoryGrpcMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service InventoryService.
   */
  public static final class InventoryServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<InventoryServiceBlockingStub> {
    private InventoryServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected InventoryServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new InventoryServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public inventoryGrpc.InventoryResponse isInStock(inventoryGrpc.InventoryRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getIsInStockMethod(), getCallOptions(), request);
    }

    /**
     */
    public inventoryGrpc.InventoryResponse deductFromInventoryGrpc(inventoryGrpc.InventoryRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeductFromInventoryGrpcMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service InventoryService.
   */
  public static final class InventoryServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<InventoryServiceFutureStub> {
    private InventoryServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected InventoryServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new InventoryServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<inventoryGrpc.InventoryResponse> isInStock(
        inventoryGrpc.InventoryRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getIsInStockMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<inventoryGrpc.InventoryResponse> deductFromInventoryGrpc(
        inventoryGrpc.InventoryRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeductFromInventoryGrpcMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_IS_IN_STOCK = 0;
  private static final int METHODID_DEDUCT_FROM_INVENTORY_GRPC = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_IS_IN_STOCK:
          serviceImpl.isInStock((inventoryGrpc.InventoryRequest) request,
              (io.grpc.stub.StreamObserver<inventoryGrpc.InventoryResponse>) responseObserver);
          break;
        case METHODID_DEDUCT_FROM_INVENTORY_GRPC:
          serviceImpl.deductFromInventoryGrpc((inventoryGrpc.InventoryRequest) request,
              (io.grpc.stub.StreamObserver<inventoryGrpc.InventoryResponse>) responseObserver);
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

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getIsInStockMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              inventoryGrpc.InventoryRequest,
              inventoryGrpc.InventoryResponse>(
                service, METHODID_IS_IN_STOCK)))
        .addMethod(
          getDeductFromInventoryGrpcMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              inventoryGrpc.InventoryRequest,
              inventoryGrpc.InventoryResponse>(
                service, METHODID_DEDUCT_FROM_INVENTORY_GRPC)))
        .build();
  }

  private static abstract class InventoryServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    InventoryServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return inventoryGrpc.InventoryItem.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("InventoryService");
    }
  }

  private static final class InventoryServiceFileDescriptorSupplier
      extends InventoryServiceBaseDescriptorSupplier {
    InventoryServiceFileDescriptorSupplier() {}
  }

  private static final class InventoryServiceMethodDescriptorSupplier
      extends InventoryServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    InventoryServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (InventoryServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new InventoryServiceFileDescriptorSupplier())
              .addMethod(getIsInStockMethod())
              .addMethod(getDeductFromInventoryGrpcMethod())
              .build();
        }
      }
    }
    return result;
  }
}
