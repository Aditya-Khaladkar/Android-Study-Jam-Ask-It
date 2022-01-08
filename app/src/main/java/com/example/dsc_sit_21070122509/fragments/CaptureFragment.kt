package com.example.dsc_sit_21070122509.fragments

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.dsc_sit_21070122509.R
import com.example.dsc_sit_21070122509.ml.MobilenetV110224Quant
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CaptureFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CaptureFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var select_image_button : FloatingActionButton
    lateinit var make_prediction : Button
    lateinit var img_view : ImageView
    lateinit var text_view : TextView
    lateinit var bitmap: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragView: View =  inflater.inflate(R.layout.fragment_capture, container, false)

        select_image_button = fragView.findViewById(R.id.button)
        make_prediction = fragView.findViewById(R.id.button2)
        img_view = fragView.findViewById(R.id.imageView2)
        text_view = fragView.findViewById(R.id.textView)

        // handling permissions

        val labels = requireContext().assets.open("labels.txt").bufferedReader().use { it.readText() }.split("\n")

        select_image_button.setOnClickListener {
            Log.d("mssg", "button pressed")
            var intent : Intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"

            startActivityForResult(intent, 250)
        }

        make_prediction.setOnClickListener {
            if (img_view.drawable == null) {
                Toast.makeText(context, "Select an image", Toast.LENGTH_LONG).show()
            } else {
                var resized = Bitmap.createScaledBitmap(bitmap, 224, 224, true)
                val model = MobilenetV110224Quant.newInstance(requireContext())

                var tbuffer = TensorImage.fromBitmap(resized)
                var byteBuffer = tbuffer.buffer

// Creates inputs for reference.
                val inputFeature0 =
                    TensorBuffer.createFixedSize(intArrayOf(1, 224, 224, 3), DataType.UINT8)
                inputFeature0.loadBuffer(byteBuffer)

// Runs model inference and gets result.
                val outputs = model.process(inputFeature0)
                val outputFeature0 = outputs.outputFeature0AsTensorBuffer

                var max = getMax(outputFeature0.floatArray)

                text_view.setText(labels[max as Int])

// Releases model resources if no longer used.
                model.close()
            }
        }

        return fragView
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 250){
            img_view.setImageURI(data?.data)

            try {
                var uri : Uri?= data?.data
                bitmap = MediaStore.Images.Media.getBitmap(context?.contentResolver, uri)
            } catch (e: Exception) {
                Log.d("@debug", "onActivityResult: $e")
            }
        }
        else if(requestCode == 200 && resultCode == Activity.RESULT_OK){
            bitmap = data?.extras?.get("data") as Bitmap
            img_view.setImageBitmap(bitmap)
        }

    }

    private fun getMax(arr: FloatArray): Any {
        var ind = 0;
        var min = 0.0f;

        for(i in 0..1000)
        {
            if(arr[i] > min)
            {
                min = arr[i]
                ind = i;
            }
        }
        return ind
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CaptureFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CaptureFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}