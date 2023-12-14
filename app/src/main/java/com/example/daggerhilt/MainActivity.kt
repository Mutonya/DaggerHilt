import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.daggerhilt.R
import com.example.daggerhilt.model.BLog
import com.example.daggerhilt.ui.MainStateEvent
import com.example.daggerhilt.ui.MainViewModel
import com.example.daggerhilt.utils.DataState

import dagger.hilt.android.AndroidEntryPoint

import java.lang.StringBuilder


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

private val viewModel:MainViewModel by viewModels()
    lateinit var progressbar:ProgressBar
    lateinit var text: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressbar = findViewById(R.id.progressbar)
        text = findViewById(R.id.text)
        subscribeObservers()
        viewModel.setStateEvent(MainStateEvent.GetBlogEvent)

    }

    private fun subscribeObservers(){
        viewModel.dataState.observe(this, Observer { dataState->
            when(dataState){
                is DataState.Success<List<BLog>> ->{
                    displayProgressBar(false)
                    appendBlogTitle(dataState.data)
                }
                is DataState.Error ->{
                    displayProgressBar(false)
                    displayError(dataState.exception.message!!)
                }is DataState.Loading ->{
                    displayProgressBar(true)

                }
            }
        })
    }

    private fun displayError(message:String){
        if (message !=null){
            Toast.makeText(this,message,Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this,"Unkown Error",Toast.LENGTH_LONG).show()

        }
    }
    private fun displayProgressBar(isDiaplayed:Boolean){
        progressbar.visibility = if (isDiaplayed) View.VISIBLE else View.GONE
    }

    private fun appendBlogTitle(blogs:List<BLog>){
        val sb = StringBuilder()
        for (blog in blogs){
            sb.append(blog.title + "\n")
        }
        text.text = sb.toString()

    }
}

