package ir.ariyana.wikipedia.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ir.ariyana.wikipedia.databinding.FragmentBottomEditBinding

class FragmentBottomEdit : BottomSheetDialogFragment() {

    lateinit var binding : FragmentBottomEditBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBottomEditBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.profileBottomSheetConfirm.setOnClickListener {

            val username = binding.profileBottomSheetName.editText?.text.toString()
            val subject = binding.profileBottomSheetSubject.editText?.text.toString()
            val phoneNumber = binding.profileBottomSheetPhone.editText?.text.toString()
            val email = binding.profileBottomSheetEmail.editText?.text.toString()

            activity?.getSharedPreferences("profile", Context.MODE_PRIVATE)
                ?.edit()
                ?.putString("username", username)
                ?.putString("subject", subject)
                ?.putString("phoneNumber", phoneNumber)
                ?.putString("email", email)
                ?.apply()

            dismiss()
        }
    }
}