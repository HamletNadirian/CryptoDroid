package nadirian.hamlet.android.cryptodroid

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import androidx.appcompat.app.AppCompatActivity
import nadirian.hamlet.android.cryptodroid.databinding.ActivityMainBinding


val number: Char = 'a'
val numberAsInt = number.code
var arrayListToString = ""

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
        var cipherTextEdt = binding.cipherTextEdt.text
        binding.shiftEdt.setText("1")

        binding.shiftEdt.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                var shiftFromEditeText = binding.shiftEdt.text.toString().toInt()
                shiftEdtToInt = shiftFromEditeText
                var encryptionForExample = encryptionCaesar("a", shiftFromEditeText)
                // var arrayListToStringEncryptionForExample = encryptionForExample.toList().joinToString(" ")
                var arrayListToStringEncryptionForExample = encryptionForExample.toList().toString()
                binding.shiftLetterForExample.setText(arrayListToStringEncryptionForExample)
                true
            } else false
        })
        binding.incBtn.setOnClickListener {
            shiftEdtToInt += 1
            shiftEdtStringTemp = shiftEdtToInt.toString()
            binding.shiftEdt.setText(shiftEdtStringTemp)
            var encryptionForExample = encryptionCaesar("a", shiftEdtToInt)
            var arrayListToStringEncryptionForExample =
                encryptionForExample.toList().joinToString(" ")
            binding.shiftLetterForExample.setText(arrayListToStringEncryptionForExample)

        }

        binding.decBtn.setOnClickListener {
            shiftEdtToInt -= 1
            shiftEdtStringTemp = shiftEdtToInt.toString()
            binding.shiftEdt.setText(shiftEdtToInt.toString())

            var encryptionForExample = encryptionCaesar("a", shiftEdtToInt)
            var arrayListToStringEncryptionForExample =
                encryptionForExample.toList().joinToString(" ")
            binding.shiftLetterForExample.setText(arrayListToStringEncryptionForExample)


        }

        binding.plaintext.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                var encryption = encryptionCaesar(plainTextEdt.toString(), shiftEdtToInt)
                arrayListToString = encryption
                binding.cipherTextEdt.setText(arrayListToString)
                true
            }
            false
        }

        binding.cipherTextEdt.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                var decryption = decryptionCaesar(cipherTextEdt.toString(), shiftEdtToInt)
                arrayListToString = decryption
                binding.plaintext.setText(arrayListToString)
                true
            }
            false
        }

    }
}

private fun encryptionCaesar(string: String, shift: Int): String {
    val textStr = ArrayList<Char>()
    for (s in string) {
        if (s == ' ')
            textStr += ' '
        else
            textStr.add((s - number + shift).mod(26).plus(numberAsInt).toChar())
    }

    val to_array: Array<Char> = textStr.toTypedArray()
    var str = ""
    to_array.forEach { str += it }

    return str
}

private fun decryptionCaesar(string: String, shift: Int): String {
    val textStr = ArrayList<Char>()
    for (s in string) {
        if (s == ' ')
            textStr += ' '
        else
            textStr.add((s - number - shift + 26).mod(26).plus(numberAsInt).toChar())
    }

    val to_array: Array<Char> = textStr.toTypedArray()
    var str = ""
    to_array.forEach { str += it }

    return str
}
