// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: trace.proto

package org.roksui.grpc;

public final class TraceOuterClass {
  private TraceOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Profiles_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Profiles_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Profile_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Profile_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\013trace.proto\032\033google/protobuf/empty.pro" +
      "to\"6\n\010Profiles\022\027\n\005items\030\001 \003(\0132\010.Profile\022" +
      "\021\n\ttimestamp\030\002 \001(\003\"\027\n\007Profile\022\014\n\004text\030\001 " +
      "\001(\t2u\n\005Trace\0221\n\014UploadTraces\022\t.Profiles\032" +
      "\026.google.protobuf.Empty\0229\n\022UploadTracesS" +
      "tream\022\t.Profiles\032\026.google.protobuf.Empty" +
      "(\001B\023\n\017org.roksui.grpcP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.google.protobuf.EmptyProto.getDescriptor(),
        });
    internal_static_Profiles_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_Profiles_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Profiles_descriptor,
        new java.lang.String[] { "Items", "Timestamp", });
    internal_static_Profile_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_Profile_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Profile_descriptor,
        new java.lang.String[] { "Text", });
    com.google.protobuf.EmptyProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
