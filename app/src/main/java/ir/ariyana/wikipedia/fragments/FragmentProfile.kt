package ir.ariyana.wikipedia.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ir.ariyana.wikipedia.databinding.FragmentProfileBinding

class FragmentProfile : Fragment() {

    lateinit var binding : FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.profileEditButton.setOnClickListener {
            childFragmentManager.beginTransaction()
                .add(FragmentBottomEdit(), null)
                .addToBackStack(null)
                .commit()
        }
    }
}