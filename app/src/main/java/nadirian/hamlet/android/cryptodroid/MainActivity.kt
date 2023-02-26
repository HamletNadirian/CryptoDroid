package nadirian.hamlet.android.cryptodroid

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import nadirian.hamlet.android.cryptodroid.databinding.ActivityMainBinding

val number: Char = 'a'
val numberAsInt = number.code

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding  //defining the binding class

    //defining the binding class
    var shiftEdtToInt = 1
    var shiftEdtStringTemp = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var plainTextEdt = binding.plaintext.text
//        var shiftEdt = binding.shiftEdt.text

        binding.shiftEdt.setText("1")

        binding.incBtn.setOnClickListener {
            shiftEdtToInt += 1
            shiftEdtStringTemp = shiftEdtToInt.toString()
            binding.shiftEdt.setText(shiftEdtStringTemp)

        }

        binding.decBtn.setOnClickListener {
            shiftEdtToInt -= 1
            shiftEdtStringTemp = shiftEdtToInt.toString()
            binding.shiftEdt.setText(shiftEdtToInt.toString())

        }
        binding.plaintext.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                //shiftEdtToInt = shiftEdt.toString().toInt()


                var encryption = encryptionCaesar(plainTextEdt.toString(), shiftEdtToInt)
                var arrayListToString = encryption.toList().joinToString(" ")
                binding.cipherTextEdt.setText(arrayListToString)
                true
            }
            false
        }
    }
}

private fun encryptionCaesar(string: String, shift: Int): ArrayList<Char> {

    val textStr = ArrayList<Char>()
    for (s in string) {
        if (s != ' ')
            textStr.add((s - number + shift).mod(26).plus(numberAsInt).toChar())
    }
    return textStr
}

private fun decryptionCaesar(string: String, shift: Int): ArrayList<Char> {
    val textStr = ArrayList<Char>()
    for (s in string) {
        if (s != ' ')
            textStr.add((s - number - shift + 26).mod(26).plus(numberAsInt).toChar())
    }
    return textStr
}

fun main() {
    var cipherText = "a"
    val ascii = cipherText.toInt()
    /* for (s in cipherText) {
         var t = (ascii - 97 - 0 + 26).mod(0)
         println(t)
     }*/

}