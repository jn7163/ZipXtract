/*
 *  Copyright (C) 2023  WirelessAlien <https://github.com/WirelessAlien>
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.wirelessalien.zipxtract

import android.app.Dialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.wirelessalien.zipxtract.databinding.FragmentDonationBinding

class DonationFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val binding = FragmentDonationBinding.inflate(layoutInflater)
        val dView = binding.root

        binding.libPayBtn.setOnClickListener {
            val url = "https://liberapay.com/WirelessAlien/donate"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        setCopyButtonListener(binding.btcBtn, binding.btcAddress.text.toString())
        setCopyButtonListener(binding.ethBtn, binding.ethAddress.text.toString())
        setCopyButtonListener(binding.dogeBtn, binding.dogeAddress.text.toString())
        setCopyButtonListener(binding.bnbBtn, binding.bnbAddress.text.toString())
        setCopyButtonListener(binding.solBtn, binding.solAddress.text.toString())
        setCopyButtonListener(binding.monBtn, binding.xmrAddress.text.toString())
        setCopyButtonListener(binding.tronBtn, binding.tronAddress.text.toString())

        return MaterialAlertDialogBuilder(requireContext())
            .setView(dView)
            .create()
    }

    private fun setCopyButtonListener(button: MaterialButton, textToCopy: String) {
        button.setOnClickListener {
            copyToClipboard(textToCopy)
            Toast.makeText(requireContext(), "Copied to clipboard", Toast.LENGTH_SHORT).show()
        }

    }

    private fun copyToClipboard(textToCopy: String) {
        val clipboardManager =
            requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("Crypto Address", textToCopy)
        clipboardManager.setPrimaryClip(clipData)
    }

}
