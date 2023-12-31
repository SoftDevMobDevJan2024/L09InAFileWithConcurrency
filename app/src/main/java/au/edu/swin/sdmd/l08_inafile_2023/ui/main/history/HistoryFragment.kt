package au.edu.swin.sdmd.l08_inafile_2023.ui.main.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import au.edu.swin.sdmd.l08_inafile_2023.R
import au.edu.swin.sdmd.l08_inafile_2023.data.HistoryFile

class HistoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_history, container, false)


        /**
         * The detailed retrieval of data from the file has moved to another
         * class; this gets a list of the strings in the file and provides them
         * to the list adapter. This is just using the old style list widget for
         * simplicity.
         */
        context?.let { context ->
            val numbers = HistoryFile.getFileContents(context)
            val lvNumbers: ListView = root.findViewById(R.id.history_list)
            lvNumbers.adapter = ArrayAdapter<Any?>(
                context,
                android.R.layout.simple_list_item_1, numbers
            )
        }
        return root
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
        fun newInstance(sectionNumber: Int): HistoryFragment {
            return HistoryFragment()
        }
    }
}