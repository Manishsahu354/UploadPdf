package com.assignment.uploadpdf.util

import android.util.Base64

object Constants {
    const val BASE_URL = "https://ext.digio.in:444/"
    const val CLIENT_ID = "AIZ67DUSNZ8TGWJV4DZ7DI3T5Z2LN2W2"
    const val CLIENT_SECRET = "ASN9AVKHU6HF41KTU71G3KNXLG1ET7BC"

    fun convertStringToBase64(text: String): String {
        var finalString = ""
        try {
            finalString = Base64.encodeToString(text.toByteArray(Charsets.UTF_8), Base64.DEFAULT);
        } catch (e: Exception) {
            e.printStackTrace();
        }
        return finalString;
    }
}