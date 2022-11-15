package com.assignment.uploadpdf

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import com.assignment.uploadpdf.databinding.ActivityMainBinding
import com.assignment.uploadpdf.util.NetworkResult
import com.assignment.uploadpdf.viewmodel.PdfUploadViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var resultLauncher: ActivityResultLauncher<Intent>? = null
    private lateinit var binding: ActivityMainBinding
    private lateinit var pdfUploadViewModel: PdfUploadViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListeners()
    }

    private fun initListeners() {
        pdfUploadViewModel = ViewModelProvider(this)[PdfUploadViewModel::class.java]
        resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            val data: Intent? = result.data
            if (data != null) {
                val pdfUri: Uri? = data.data
                if (pdfUri != null) {
                    pdfUploadViewModel.uploadPdfFile(
                        binding.etName.text.toString(),
                        binding.etMobile.text.toString(), pdfUri
                    )
                }
            }
        }
        pdfUploadViewModel.uploadPdfData.observe(this) { response ->
            when (response) {
                is NetworkResult.Loading -> {
                }

                is NetworkResult.Success -> {
                    response.data?.let { result ->
                        binding.tvId.text = result.id
                        result.id?.let { id ->
                            pdfUploadViewModel.getAgreementStatus(id)
                        }
                    }
                }
                is NetworkResult.Error -> {
                    binding.tvId.text = response.message
                }
                else -> {}
            }

        }

        pdfUploadViewModel.agreementStatus.observe(this) { response ->
            when (response) {
                is NetworkResult.Loading -> {
                }

                is NetworkResult.Success -> {
                    response.data?.let { result ->
                        binding.tvAgreementStatus.text = result.agreementStatus
                        result.id?.let { id ->
                            pdfUploadViewModel.getLastData(id)
                        }
                    }
                }
                is NetworkResult.Error -> {
                    binding.tvAgreementStatus.text = response.message
                }
                else -> {}
            }

        }

        pdfUploadViewModel.getLastData.observe(this) { response ->
            when (response) {
                is NetworkResult.Loading -> {
                }
                is NetworkResult.Success -> {
                    response.data?.let { result ->
                        binding.tvLastData.text = result
                    }
                }
                is NetworkResult.Error -> {
                    binding.tvLastData.text = response.message
                }
                else -> {}
            }

        }


        binding.btnUpload.setOnClickListener {
            if (binding.etName.text.isEmpty()) {
                Toast.makeText(this, "Please Enter Name", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (binding.etMobile.text.isEmpty()) {
                Toast.makeText(this, "Please Enter Mobile Number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (binding.etMobile.text.length != 10) {
                Toast.makeText(this, "Please Enter Valid Mobile Number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (ActivityCompat.checkSelfPermission(this@MainActivity, READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this@MainActivity, arrayOf(READ_EXTERNAL_STORAGE), 1
                )
            } else {
                selectPDF()
            }
        }
    }

    private fun selectPDF() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "application/pdf"
        resultLauncher?.launch(intent)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1 && grantResults.isNotEmpty()
            && grantResults[0]
            == PackageManager.PERMISSION_GRANTED
        ) {
            selectPDF();
        } else {
            Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }
}