package com.example.mlkitocrdemo

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // setup the textView to display text found
        var textView: TextView = findViewById(R.id.textViewContent)

        // setup the bitmap image
        var myBitmap: Bitmap = BitmapFactory.decodeResource(
            getApplicationContext().getResources(),
            R.drawable.thankyou);
        val image = InputImage.fromBitmap(myBitmap, 0)

        // the on-device model for text recognition
        val recognizer = TextRecognition.getClient()

        // pass the image to the process
        val result = recognizer.process(image)
            .addOnSuccessListener { visionText -> // Task completed successfully
                // Display the text found in the textView
                textView.setText(visionText.text)
            }
            .addOnFailureListener { // Task failed with an exception
                // ...
                textView.setText("task failed")
            }
    }

}
