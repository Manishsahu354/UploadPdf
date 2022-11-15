package com.assignment.uploadpdf.data.model

import com.google.gson.annotations.SerializedName

data class PdfUploadResponseModel(

	@field:SerializedName("agreement_type")
	val agreementType: String? = null,

	@field:SerializedName("sign_request_details")
	val signRequestDetails: SignRequestDetails? = null,

	@field:SerializedName("is_agreement")
	val isAgreement: Boolean? = null,

	@field:SerializedName("file_name")
	val fileName: String? = null,

	@field:SerializedName("agreement_status")
	val agreementStatus: String? = null,

	@field:SerializedName("channel")
	val channel: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("signing_parties")
	val signingParties: List<SigningPartiesItem?>? = null,

	@field:SerializedName("self_signed")
	val selfSigned: Boolean? = null,

	@field:SerializedName("no_of_pages")
	val noOfPages: Int? = null,

	@field:SerializedName("self_sign_type")
	val selfSignType: String? = null
)

data class SignRequestDetails(

	@field:SerializedName("identifier")
	val identifier: String? = null,

	@field:SerializedName("expire_on")
	val expireOn: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("requested_on")
	val requestedOn: String? = null,

	@field:SerializedName("requester_type")
	val requesterType: String? = null
)

data class SigningPartiesItem(

	@field:SerializedName("identifier")
	val identifier: String? = null,

	@field:SerializedName("reason")
	val reason: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("signature_type")
	val signatureType: String? = null
)
