package au.edu.swin.sdmd.l08_inafile_2023.ui.main.converter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import au.edu.swin.sdmd.l08_inafile_2023.R
import au.edu.swin.sdmd.l08_inafile_2023.data.HistoryFile
import au.edu.swin.sdmd.l08_inafile_2023.databinding.FragmentMainBinding

class ConverterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_converter, container, false)
        val bConvert: Button = root.findViewById(R.id.bConvert)
        bConvert.setOnClickListener {
            val etDecimal: EditText = root.findViewById(R.id.etDecimal)
            val sDecimal = etDecimal.text.toString()
            if (sDecimal != "") {
                val iDecimal = sDecimal.toInt()
                val sBinary = Integer.toBinaryString(iDecimal)
                val tvResult = root.findViewById<TextView>(R.id.tvBinary)
                tvResult.text = sBinary

                /**
                 * This is where we update our files.
                 */
                updateHistory(sDecimal, sBinary)
            } else {
                val tvBinary: TextView = root.findViewById(R.id.tvBinary)
                tvBinary.text = "No number entered"
            }
        }
        return root
    }

    private fun updateHistory(sDecimal: String, sBinary: String) {
        context?.let {
            HistoryFile.appendInput(it, "$sDecimal = $sBinary")
        }
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
        fun newInstance(sectionNumber: Int): ConverterFragment {
            return ConverterFragment()
        }
    }
}