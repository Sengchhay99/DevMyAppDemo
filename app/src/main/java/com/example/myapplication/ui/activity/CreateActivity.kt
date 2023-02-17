package com.example.myapplication.ui.activity

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.viewbinding.library.activity.viewBinding
import com.example.myapplication.R
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.callback.CustomToolbarCallBack
import com.example.myapplication.databinding.ActivityCreateBinding
import com.example.myapplication.databinding.DialogSetLocationBinding
import com.example.myapplication.helper.FileUtils
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.kongzue.dialogx.dialogs.BottomDialog
import com.kongzue.dialogx.interfaces.OnBindView
import java.io.File

class CreateActivity : BaseActivity() {

    private val binding: ActivityCreateBinding by viewBinding()
    private val adapterImage = ImageAdapter(onItemClickListener())
    private var photoUri: Uri? = null

    private var imageList = mutableListOf<File>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.recyclerViewImage.adapter = adapterImage
        binding.title = " Test toolBar"
        binding.action = callBackToolBar
    }

    private fun onItemClickListener(): ImageAdapter.OnItemClickListener = object : ImageAdapter.OnItemClickListener{
        override fun viewImage() {

        }

        override fun removeImage(file: File) {
            imageList.remove(file)
            Log.e("CreateActivity.kt", "data${imageList.size}")
            adapterImage.submitList(imageList.toList())
            showSuccessMessage(getString(R.string.the_image_is_deleted))
        }
    }

    private val callBackToolBar = object : CreateActivityCallback {
        override fun onSetAddress() {
            BottomDialog.build()
                .setCustomView(object : OnBindView<BottomDialog>(R.layout.dialog_set_location) {
                    override fun onBind(dialog: BottomDialog?, v: View?) {
                        v ?: return
                        val binding = DialogSetLocationBinding.bind(v)
                        binding.clickBackLocation = callbackBackLocation(dialog)
                    }
                })
                .setAllowInterceptTouch(false)
                .setCancelable(false)
                .show(this@CreateActivity)
        }

        override fun onAddImage() {
            val items = arrayOf("Take Photo", "Choose Image From Gallery")
            MaterialAlertDialogBuilder(this@CreateActivity)
                .setTitle(getString(R.string.select_option))
                .setItems(items){_, which->
                    when (which) {
                        0 -> {
                            requestCameraPermission(R.string.turn_on_camera_permission_to_take_picture) {
                                dispatchTakePictureIntent { uri, intent ->
                                    photoUri = uri
                                    takePhotoImageResult.launch(intent)
                                }
                            }
                        }
                        1 -> {
                            chooseImageResult.launch("image/*")
                        }
                    }
                }.show()
        }

        override fun buttonBackCallBackListener() {
            finish()
        }
    }

    private fun callbackBackLocation(bottomDialog: BottomDialog?) = object : ClickCallbackAddress {
        override fun onSave() {

        }

        override fun onCancel() {
          bottomDialog?.dismiss()
        }

    }

    private var  chooseImageResult = registerGetMultipleContent {

        if (it?.isNotEmpty() == true){
            it.forEach { uri ->
                FileUtils.from(this, uri)?.let { file ->
                    imageList.add(file)
                }
            }
            adapterImage.submitList(imageList.toList())
        }

    }

    private var takePhotoImageResult = registerStartActivityForResult {
        if (it.resultCode == RESULT_OK) {
            photoUri ?: return@registerStartActivityForResult
            FileUtils.from(this, photoUri)?.let { file ->
                imageList.add(file)
                Log.e("CreateActivity.kt", "${imageList.size}")
                adapterImage.submitList(imageList)
            }
            photoUri = null
        }
    }

    interface CreateActivityCallback : CustomToolbarCallBack {
        fun onSetAddress()
        fun onAddImage()
    }

    interface ClickCallbackAddress {
        fun onSave()
        fun onCancel()
    }
}