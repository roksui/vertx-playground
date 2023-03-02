package org.roksui.exception

import graphql.ErrorType
import graphql.ExceptionWhileDataFetching
import graphql.GraphqlErrorBuilder
import graphql.execution.DataFetcherExceptionHandler
import graphql.execution.DataFetcherExceptionHandlerParameters
import graphql.execution.DataFetcherExceptionHandlerResult
import java.util.concurrent.CompletableFuture

class GraphQLExceptionHandler : DataFetcherExceptionHandler {
    override fun handleException(handlerParameters: DataFetcherExceptionHandlerParameters?): CompletableFuture<DataFetcherExceptionHandlerResult> {
        val exception = handlerParameters?.exception
        
        val error = if (exception is SomeException) {
            GraphqlErrorBuilder.newError()
                .errorType(ErrorType.DataFetchingException)
                .message(exception.message)
                .build()
        } else {
            ExceptionWhileDataFetching(handlerParameters?.path, exception, null)
        }
        
        return CompletableFuture.completedFuture(DataFetcherExceptionHandlerResult.newResult().error(error).build())
        
    }
}