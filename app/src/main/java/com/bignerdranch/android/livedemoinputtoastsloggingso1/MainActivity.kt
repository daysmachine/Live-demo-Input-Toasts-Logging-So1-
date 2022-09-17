package com.bignerdranch.android.livedemoinputtoastsloggingso1

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {

    //Pointers to our fun Views
    private lateinit var firstButton: Button
    private lateinit var secondButton: Button
    private lateinit var thirdButton: Button
    private lateinit var mainTextEdit: EditText

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var mainSwitch: Switch

    private lateinit var mainSeek: SeekBar

    private var switchPosition: Boolean = false

    private lateinit var mainProgress: ProgressBar

    private val LOG_TAG_BUTTON = "ButtonFun"
    private val LOG_TAG_TEXT = "TextFun"
    private val LOG_TAG_SWITCH = "SwitchFun"
    private val LOG_TAG_SEEK = "SeekFun"

    override fun onCreate(savedInstanceState: Bundle?) {

        // Leave under-the-hood stuff intact
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //
        this.connectViews()
        this.setupButtonCallbacks()
        this.setupTextCallbacks()
        this.setupSwitchCallbacks()
        this.setupSeekBarCallbacks()

        this.rememberSwitchPosition()
    }

    private fun connectViews() {

        //
        this.firstButton = this.findViewById(R.id.first_button)
        this.secondButton = this.findViewById(R.id.second_button)
        this.thirdButton = this.findViewById(R.id.third_button)


        //
        this.mainTextEdit = this.findViewById(R.id.main_text_edit)

        //
        this.mainSwitch = this.findViewById(R.id.main_switch)
        this.mainSeek = this.findViewById(R.id.main_seek)
        this.mainProgress = this.findViewById(R.id.main_progress_bar)
    }

    private fun setupButtonCallbacks(){

        // Setup the first callback for first button
        this.firstButton.setOnClickListener { view : View ->
            val btn: Button = (view as Button)
            btn.text = this.getString(R.string.clicked_message)

           if(this.switchPosition){
               println("Making button 1 Toast")
               Toast.makeText(this, this.getString(R.string.short_toast_message), Toast.LENGTH_SHORT).show()
           }

            println("first button was clicked!")
        }

        // Reusable listeners
        val buttonClickListener = View.OnClickListener { v: View ->
            val btn: Button = (v as Button)
            btn.text = this.getString(R.string.clicked_message)
            println("A button was clicked")
        }

        val buttonLongPressListener = View.OnLongClickListener { v: View -> Boolean
            val btn: Button = (v as Button)
            btn.text = this.getString(R.string.long_pressed_message)

            if(this.switchPosition){
                println("Making button Lambda Toast")

                Log.i(this.LOG_TAG_BUTTON, "Making button 1 Toast")
                Toast.makeText(this, this.getString(R.string.long_toast_message), Toast.LENGTH_LONG).show()
            }



            println("A button was long pressed!")
            true
        }

        this.secondButton.setOnClickListener(buttonClickListener)
        this.secondButton.setOnLongClickListener(buttonLongPressListener)
        this.thirdButton.setOnClickListener(buttonClickListener)
        this.thirdButton.setOnLongClickListener(buttonLongPressListener)

    }

    //
    private fun setupTextCallbacks(){
        this.mainTextEdit.setOnKeyListener {v: View, k: Int, event: KeyEvent -> Boolean

            if( event.keyCode == KeyEvent.KEYCODE_K){
                println("K was pressed!")

                Log.i(this.LOG_TAG_TEXT, "LOG TEXT THINGY")
            }

            println("Text key press was detected!: $k :: $event")
            false
        }
    }

    //
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private fun setupSwitchCallbacks(){
        this.mainSwitch.setOnClickListener { v: View ->
            val sw: Switch = (v as Switch)
            println("Switch was clicked to: " + sw.isChecked)

            Log.i(this.LOG_TAG_SWITCH, "LOG SWITCH THINGY")
        }
    }

    private fun setupSeekBarCallbacks(){

        this.mainSeek.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {

            // TODO: Fix Some Stuff (not really, just an example comment of TODO moved above)
            override fun onProgressChanged(sb: SeekBar?, progress: Int, fromUser: Boolean)
            {
                if (switchPosition) {
                    val p: Int = (sb?.progress as Int)
                    mainProgress.progress = p
                }

                println("SeekBar has been changed to: ${sb?.progress}")

                Log.i(LOG_TAG_SEEK, "AH DID NOT CATCH WHAT HE DID")
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}

        })
    }

    //
    private fun rememberSwitchPosition(){
        this.switchPosition = this.mainSwitch.isChecked
    }
}

