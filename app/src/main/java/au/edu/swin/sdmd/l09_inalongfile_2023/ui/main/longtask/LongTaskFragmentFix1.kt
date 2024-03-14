package au.edu.swin.sdmd.l09_inalongfile_2023.ui.main.longtask

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import au.edu.swin.sdmd.l09_inalongfile_2023.ButtonViewModel
import au.edu.swin.sdmd.l09_inalongfile_2023.R
import au.edu.swin.sdmd.l09_inalongfile_2023.data.LoooooongFile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

// FIXED 1: import
import kotlinx.coroutines.withContext

class LongTaskFragmentFix1 : Fragment() {
    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.IO + job)
    private val viewModel: ButtonViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val root = inflater.inflate(R.layout.fragment_longtask, container, false)
        val bLong: Button = root.findViewById(R.id.bLong)

        bLong.setOnClickListener {
            bLong.isEnabled = false
            val cgGroup: RadioGroup = root.findViewById(R.id.radioGroup)
            val listLength = when (cgGroup.checkedRadioButtonId) {
                R.id.shortList -> 1000
                R.id.mediumList -> 10000
                R.id.longList -> 100000
                else -> 1
            }
            scope.launch {
                // long-running task
                writeFile(context, listLength)
                /*
                * FATAL EXCEPTION: DefaultDispatcher-worker-1
                * Process: au.edu.swin.sdmd.l09_inalongfile_2023,
                *          PID: 5044 android.view.ViewRootImpl$CalledFromWrongThreadException:
                *           Only the original thread that created a view hierarchy can touch its views.
                * */
//                bLong.isEnabled = true

                // FIXED 1 (minimum change):  use withContext(Dispatchers.Main)
                // to switch back to the main thread to update bLong
                withContext(Dispatchers.Main) {
                    bLong.isEnabled = true
                }
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
        fun newInstance(sectionNumber: Int): LongTaskFragmentFix1 {
            return LongTaskFragmentFix1()
        }
    }
}

