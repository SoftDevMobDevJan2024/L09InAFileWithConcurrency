package au.edu.swin.sdmd.l09_inalongfile_2023.data

import android.content.Context

object LoooooongFile {
    private const val filename = HistoryFile.filename //"loooooong_file"

    fun appendInput(context: Context, s: String) {
        context.openFileOutput(filename, Context.MODE_APPEND).use {
            it.write(s.toByteArray())
            it.write("\n".toByteArray())
        }
    }

    fun deleteFile(context: Context) {
        context.deleteFile(filename)
    }
}