package com.amar.sitambol.ui.profile

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.amar.sitambol.R
import com.amar.sitambol.data.database.PrefManager
import com.amar.sitambol.ui.tentang.TentangActivity

class ProfileFragment : Fragment(), ProfileContract.View {

    private lateinit var prefManager: PrefManager
    lateinit var presenter: ProfilePresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_profile, container, false)

        prefManager = PrefManager(requireActivity())
        presenter = ProfilePresenter(this)

        initFragment(view)

        return view
    }

    @SuppressLint("SetTextI18n", "UseSwitchCompatOrMaterialCode")
    override fun initFragment(view: View) {
        val tvTitle = view.findViewById<TextView>(R.id.tv_title)
        val ivBack = view.findViewById<ImageView>(R.id.iv_back)

        val tvUsername = view.findViewById<TextView>(R.id.tv_username)
        val tvEmail = view.findViewById<TextView>(R.id.tv_email)

        val layoutEdit = view.findViewById<RelativeLayout>(R.id.layout_edit)
        val swDark = view.findViewById<Switch>(R.id.sw_dark)
        val layoutTentang = view.findViewById<RelativeLayout>(R.id.layout_tentang)
        val tvLogout = view.findViewById<TextView>(R.id.tv_logout)

        tvTitle.text = "Profile"
        ivBack.visibility = View.GONE

        tvUsername.text = prefManager.prefUsername
        tvEmail.text = prefManager.prefEmail

        layoutEdit.setOnClickListener {

        }
        swDark.setOnCheckedChangeListener { compoundButton, isChecked ->
            when(isChecked) {
                true -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }
                false -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }
        }
        layoutTentang.setOnClickListener {
            startActivity(Intent(requireActivity(), TentangActivity::class.java))
        }
        tvLogout.setOnClickListener {
            presenter.doLogout(prefManager)
        }
    }

    override fun onResultLogin(prefManager: PrefManager) {

    }

    override fun onResultLogout() {
        showMessage("Berhasil Logout...")
    }

    override fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}