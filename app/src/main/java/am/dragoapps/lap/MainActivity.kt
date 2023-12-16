package am.dragoapps.lap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		
		val counterTextbox = findViewById<TextView>(R.id.counterText)
		val button: ImageButton = findViewById(R.id.upButton)
		
		button.setOnClickListener {
			val a = counterTextbox.text.toString().toInt() + 5
			counterTextbox.text = a.toString()
		}
	}
}