package au.edu.swin.sdmd.l08_inafile_2023.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import au.edu.swin.sdmd.l08_inafile_2023.R
import au.edu.swin.sdmd.l08_inafile_2023.ui.main.converter.ConverterFragment
import au.edu.swin.sdmd.l08_inafile_2023.ui.main.history.HistoryFragment
import au.edu.swin.sdmd.l08_inafile_2023.ui.main.longtask.LongTaskFragment
import au.edu.swin.sdmd.l08_inafile_2023.ui.main.process.ProcessFragment

private val TAB_TITLES = arrayOf(
    R.string.title_process,
    R.string.title_converter,
    R.string.title_history,
    R.string.title_longlist
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment.
        return when(position) {
            0 -> ProcessFragment.newInstance(0)
            1 -> ConverterFragment.newInstance(1)
            2 -> HistoryFragment.newInstance(2)
            3 -> LongTaskFragment.newInstance(2)
            else -> ConverterFragment.newInstance(0)
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 3 total pages.
        return 4
    }
}