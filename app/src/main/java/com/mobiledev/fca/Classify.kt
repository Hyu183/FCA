package com.mobiledev.fca

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.mobiledev.fca.ml.FlowerModel
import org.tensorflow.lite.support.image.TensorImage

class Classify:AppCompatActivity() {

    val SEC_ACT_REQ_CODE = 1
    private lateinit var obj: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, ChooseImage::class.java)
        startActivityForResult(intent, SEC_ACT_REQ_CODE)
    }

   override fun onActivityResult(requestCode: Int,
                                 resultCode: Int,
                                 data: Intent?) {
       super.onActivityResult(requestCode, resultCode, data)
       when (requestCode) {
            SEC_ACT_REQ_CODE -> if (resultCode == RESULT_OK) {
                    obj = data?.getParcelableExtra<Bitmap>("BitmapImage")!!
                    setContentView(R.layout.classify)

                    //val mObject = BitmapFactory.decodeResource(resources,obj)
                    val imageViewOriginal: ImageView = findViewById(R.id.imageview_original)
                    //val imageViewEnhanced: ImageView = findViewById(R.id.imageview_enhanced)
                    imageViewOriginal.setImageBitmap(obj)

                    //val model = Esrgan.newInstance(this)
    //        val model = CompressedEsrgan.newInstance(this)
                    val model = FlowerModel.newInstance(this)

    // Creates inputs for reference.
                    val originalImage = TensorImage.fromBitmap(obj)

    // Runs model inference and gets result.
                    //val outputs = model.process(originalImage)
                    //val enhancedImage = outputs.enhancedImageAsTensorImage
                    //val enhancedImageBitmap = enhancedImage.bitmap

    // Runs classify
                    val MAX_RESULT_DISPLAY = 3
                    val outputs_classify = model.process(originalImage)
                            .probabilityAsCategoryList.apply {
                                sortByDescending { it.score } // Sort with highest confidence first
                            }.take(MAX_RESULT_DISPLAY)

                    val view_ : TextView = findViewById(R.id.textView)
                    var Str : String = ""
                    for (output in outputs_classify) {
                        Str = Str + output.label + ": "
                        Str = Str + output.score + "\n"
                    }
                    //+= output.label, output.score

                    view_.setText(Str)
                    //print(outputs_classify)



    // Releases model resources if no longer used.
                    model.close()
                    //imageViewEnhanced.setImageBitmap(enhancedImageBitmap)

                }
                }
            }
    }
