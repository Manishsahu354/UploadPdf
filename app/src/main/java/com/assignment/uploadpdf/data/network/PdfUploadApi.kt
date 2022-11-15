package com.assignment.uploadpdf.data.network

import com.assignment.uploadpdf.data.model.PdfUploadRequestModel
import com.assignment.uploadpdf.data.model.PdfUploadResponseModel
import retrofit2.Response
import retrofit2.http.*

interface PdfUploadApi {

    @Headers(
        "Content-Type: application/json",
        "authorization: Basic QUlaNjdEVVNOWjhUR1dKVjREWjdESTNUNVoyTE4yVzI6QVNOOUFWS0hVNkhGNDFLVFU3MUczS05YTEcxRVQ3QkM="
    )
    @POST("v2/client/document/upload")
    suspend fun uploadPdfFile(
        @Body pdfUpload: PdfUploadRequestModel
    ): Response<PdfUploadResponseModel>

    @Headers(
        "authorization: Basic QUlaNjdEVVNOWjhUR1dKVjREWjdESTNUNVoyTE4yVzI6QVNOOUFWS0hVNkhGNDFLVFU3MUczS05YTEcxRVQ3QkM="
    )
    @POST("v2/client/document/{documentId}")
    suspend fun getAgreementStatus(
        @Path("documentId") documentId: String
    ): Response<PdfUploadResponseModel>


    @Headers(
        "authorization: Basic QUlaNjdEVVNOWjhUR1dKVjREWjdESTNUNVoyTE4yVzI6QVNOOUFWS0hVNkhGNDFLVFU3MUczS05YTEcxRVQ3QkM="
    )
    @POST("v2/client/document")
    suspend fun getLastResponse(
        @Query("document_id") document_id: String
    ): Response<String>


}