package com.example.myapplication.base

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.example.myapplication.R
import com.example.myapplication.helper.FileImageDirectory
import com.permissionx.guolindev.PermissionX
import es.dmoral.toasty.Toasty
import java.io.File
import java.io.IOException

open class BaseActivity : AppCompatActivity() {

    fun requestCameraPermission(@StringRes title: Int, onComplete: () -> Unit) {
        PermissionX.init(this)
            .permissions(Manifest.permission.CAMERA)
            .onExplainRequestReason { scope, deniedList ->
                scope.showRequestReasonDialog(
                    deniedList,
                    getString(title),
                    getString(R.string.confirm),
                    getString(R.string.cancel)
                )
            }
            .onForwardToSettings { scope, deniedList ->
                scope.showForwardToSettingsDialog(
                    deniedList,
                    getString(title),
                    getString(R.string.confirm),
                    getString(R.string.cancel)
                )
            }
            .request { allGranted, _, _ ->
                if (allGranted) {
                    onComplete()
                } else {
                    showErrorMessage(getString(R.string.permission_deni))
                }
            }
    }

    fun showErrorMessage(message: String) {
        Toasty.error(this, message).show()
    }

    fun showSuccessMessage(message: String) {
        Toasty.success(this, message).show()
    }

    fun registerStartActivityForResult(callback: (ActivityResult) -> Unit): ActivityResultLauncher<Intent> {
        return registerForActivityResult(ActivityResultContracts.StartActivityForResult(), callback)
    }

    fun registerGetMultipleContent(callback: (List<Uri>?) -> Unit): ActivityResultLauncher<String> {
        return registerForActivityResult(ActivityResultContracts.GetMultipleContents(), callback)
    }

    fun dispatchTakePictureIntent(onComplete: (Uri, Intent) -> Unit) {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if ((takePictureIntent.resolveActivity(packageManager)) != null) {
            var photoFile: File? = null
            try {
                photoFile = FileImageDirectory.createImageFile(this)
            } catch (e: IOException) {
                Log.e("BaseActivity.kt", "dispatchTakePictureIntent$e")
            }
            Log.e("BaseActivity.kt", "dispatchTakePictureIntent$photoFile")
            if (photoFile != null) {
                val photoUri = FileProvider.getUriForFile(
                    this,
                    packageName,
                    photoFile
                )
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                onComplete(photoUri, takePictureIntent)
            }
        }
    }
}