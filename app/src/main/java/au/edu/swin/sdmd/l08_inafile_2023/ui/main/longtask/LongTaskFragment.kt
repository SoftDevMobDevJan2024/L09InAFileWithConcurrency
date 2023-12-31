package au.edu.swin.sdmd.l08_inafile_2023.ui.main.longtask

import android.content.Context
import android.os.Bundle
import android.view.Display
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import au.edu.swin.sdmd.l08_inafile_2023.ButtonViewModel
import au.edu.swin.sdmd.l08_inafile_2023.R
import au.edu.swin.sdmd.l08_inafile_2023.data.LoooooongFile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import androidx.fragment.app.activityViewModels

class LongTaskFragment : Fragment() {
    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.IO + job)
    private val viewModel: ButtonViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val root = inflater.inflate(R.layout.fragment_longtask, container, false)
        val bLong: Button = root.findViewById(R.id.bLong)

        /*val buttonObserver = Observer<Boolean> { state ->
            bLong.isEnabled = state
        }
        viewModel.buttonState.observe(viewLifecycleOwner, buttonObserver)*/

        bLong.setOnClickListener {
            bLong.isEnabled = false
            //viewModel.buttonState.value = false
            val cgGroup: RadioGroup = root.findViewById(R.id.radioGroup)
            val listLength = when (cgGroup.checkedRadioButtonId) {
                R.id.shortList -> 1000
                R.id.mediumList -> 10000
                R.id.longList -> 100000
                else -> 1
            }
            scope.launch {
                writeFile(context, listLength)
                bLong.isEnabled = true
                //viewModel.buttonState.postValue(true)

            }
        }
        return root
    }

    private fun writeFile(context: Context?, listLength: Int) {
        context?.let {
            LoooooongFile.deleteFile(it)
            for (i in 1..listLength) {
                val sBinary = Integer.toBinaryString(i)
                LoooooongFile.appendInput(it, "$i = $sBinary")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //scope.cancel()
    }

    override fun onDestroy() {
        super.onDestroy()
        //scope.cancel()
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): LongTaskFragment {
            return LongTaskFragment()
        }
    }
}

