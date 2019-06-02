package com.boros.android.starter.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.boros.android.starter.R
import com.boros.android.starter.model.ToolbarModel
import com.boros.android.starter.sharedPreferences.SharedPreferencesManager
import com.boros.android.starter.ui.view.SettingsCheckboxItemView
import com.boros.android.starter.viewModel.SettingsViewModel
import com.boros.android.starter.workers.WorkUtil
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : BaseFragment() {

    private lateinit var viewModel: SettingsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    private fun init() {
        viewModel = ViewModelProviders.of(this).get(SettingsViewModel::class.java)
        updateToolbar(ToolbarModel(title = getString(R.string.settings), isBackIconNeeded = true))

        settingsItem?.setOnClickListener { }
        settingsCheckboxItem?.addCheckChangedListener(object : SettingsCheckboxItemView.CheckChangedListener {
            override fun onCheckChanged(isChecked: Boolean) {
                SharedPreferencesManager.settings.isAlertNotificationEnabled = isChecked
                if(isChecked) {
                    WorkUtil.scheduleAlertNotificationWork()
                } else {
                    WorkUtil.cancelAlertNotificationWorker()
                }
            }
        })
    }

}
