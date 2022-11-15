package com.assignment.uploadpdf.repository

import com.assignment.uploadpdf.data.model.PdfUploadRequestModel
import com.assignment.uploadpdf.data.model.PdfUploadResponseModel
import com.assignment.uploadpdf.data.network.PdfUploadApi
import retrofit2.Response
import javax.inject.Inject

class ImageUploadRepository @Inject constructor(
    private val pdfUploadApi: PdfUploadApi
) {
    suspend fun uploadPdfFile(pdfUploadRequest:PdfUploadRequestModel): Response<PdfUploadResponseModel>{
        return pdfUploadApi.uploadPdfFile(pdfUploadRequest)
    }

    suspend fun getAgreementStatus(documentId:String): Response<PdfUploadResponseModel>{
        return pdfUploadApi.getAgreementStatus(documentId)
    }
    suspend fun getLastResponse(documentId:String): Response<String>{
        return pdfUploadApi.getLastResponse(documentId)
    }
}