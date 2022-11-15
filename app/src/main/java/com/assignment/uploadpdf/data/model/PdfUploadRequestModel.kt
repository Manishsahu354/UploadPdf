package com.assignment.uploadpdf.data.model

import com.google.gson.annotations.SerializedName

data class PdfUploadRequestModel(

	@field:SerializedName("signers")
	val signers: List<SignersItem?>? = null,

	@field:SerializedName("display_on_page")
	val displayOnPage: String? = null,

	@field:SerializedName("expire_in_days")
	val expireInDays: Int? = null,

	@field:SerializedName("file_name")
	val fileName: String? = null,

	@field:SerializedName("file_data")
	val fileData: String? = null
)

data class SignersItem(

	@field:SerializedName("identifier")
	val identifier: String? = null,

	@field:SerializedName("reason")
	val reason: String? = null,

	@field:SerializedName("name")
	val name: String? = null
)
