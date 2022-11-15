package com.assignment.uploadpdf.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.uploadpdf.data.model.PdfUploadRequestModel
import com.assignment.uploadpdf.data.model.PdfUploadResponseModel
import com.assignment.uploadpdf.data.model.SignRequestDetails
import com.assignment.uploadpdf.data.model.SignersItem
import com.assignment.uploadpdf.repository.ImageUploadRepository
import com.assignment.uploadpdf.util.Constants
import com.assignment.uploadpdf.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PdfUploadViewModel @Inject constructor(
    private val repository: ImageUploadRepository
) : ViewModel() {

    private val _uploadPdfData: MutableLiveData<NetworkResult<PdfUploadResponseModel>> =
        MutableLiveData()
    val uploadPdfData: LiveData<NetworkResult<PdfUploadResponseModel>> = _uploadPdfData

    private val _agreementStatus: MutableLiveData<NetworkResult<PdfUploadResponseModel>> =
        MutableLiveData()
    val agreementStatus: LiveData<NetworkResult<PdfUploadResponseModel>> = _agreementStatus

    private val _getLastData: MutableLiveData<NetworkResult<String>> =
        MutableLiveData()
    val getLastData: LiveData<NetworkResult<String>> = _getLastData

    fun uploadPdfFile(name: String, mobile: String, pdfUri: Uri) {
        viewModelScope.launch {
            _uploadPdfData.postValue(NetworkResult.Loading())
            val singers = SignersItem(identifier = mobile, name = name)
            val singnersList: MutableList<SignersItem> = mutableListOf()
            singnersList.add(singers)
            val uploadPdfRequest = PdfUploadRequestModel(
                signers = singnersList,
                fileData = Constants.convertStringToBase64(pdfUri.toString())
            )
            val response = repository.uploadPdfFile(uploadPdfRequest)
            when (response.isSuccessful) {
                true -> {
                    _uploadPdfData.postValue(NetworkResult.Success(response.body()))
                }
                else -> {
                    _uploadPdfData.postValue(NetworkResult.Error(response.message()))
                }
            }
        }

    }

    fun getAgreementStatus(documentId: String) {
        viewModelScope.launch {
            _agreementStatus.postValue(NetworkResult.Loading())
            val response = repository.getAgreementStatus(documentId)
            when (response.isSuccessful) {
                true -> {
                    _agreementStatus.postValue(NetworkResult.Success(response.body()))
                }
                else -> {
                    _agreementStatus.postValue(NetworkResult.Error(response.message()))
                }
            }
        }
    }

    fun getLastData(documentId: String) {
        viewModelScope.launch {
            _getLastData.postValue(NetworkResult.Loading())
            val response = repository.getLastResponse(documentId)
            when (response.isSuccessful) {
                true -> {
                    _getLastData.postValue(NetworkResult.Success(response.body()))
                }
                else -> {
                    _getLastData.postValue(NetworkResult.Error(response.message()))
                }
            }
        }

    }


}