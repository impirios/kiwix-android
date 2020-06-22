/*
 * Kiwix Android
 * Copyright (c) 2020 Kiwix <android.kiwix.org>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.kiwix.kiwixmobile.zim_manager.fileselect_view.effects

import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.navigation.findNavController
import org.kiwix.kiwixmobile.core.R
import org.kiwix.kiwixmobile.core.base.SideEffect
import org.kiwix.kiwixmobile.core.extensions.toast
import org.kiwix.kiwixmobile.core.zim_manager.fileselect_view.adapter.BooksOnDiskListItem
import org.kiwix.kiwixmobile.destinationlibrary.LocalLibraryFragmentDirections

data class OpenFileWithNavigation(private val bookOnDisk: BooksOnDiskListItem.BookOnDisk) :
  SideEffect<Unit> {

  override fun invokeWith(activity: AppCompatActivity) {
    val file = bookOnDisk.file
    if (!file.canRead()) {
      activity.toast(R.string.error_file_not_found)
    } else {
      val action = LocalLibraryFragmentDirections.actionNavigationLibraryToNavigationReader(
        file.toUri().toString()
      )
      activity.findNavController(org.kiwix.kiwixmobile.R.id.nav_host_fragment).navigate(action)

      // activity.finish()
      // activity.start<KiwixMainActivity> {
      //   data = file.toUri()
      //   flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
      // }
    }
  }
}
