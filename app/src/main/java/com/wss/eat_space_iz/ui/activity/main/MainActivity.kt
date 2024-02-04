package com.wss.eat_space_iz.ui.activity.main


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.wss.eat_space_iz.R
import com.wss.eat_space_iz.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_frame_container) as NavHostFragment
        navController = navHostFragment.navController

        with(binding) {
            mainBottomNav.setupWithNavController(navController)
            mainBottomNav?.apply {
                navController?.let { navController ->
                    NavigationUI.setupWithNavController(
                        this,
                        navController
                    )
                    setOnItemSelectedListener { item ->
                        NavigationUI.onNavDestinationSelected(item, navController)
                        true
                    }
                    setOnItemReselectedListener {
                        navController.popBackStack(destinationId = it.itemId, inclusive = false)
                    }
                }
            }
        }
    }

    override fun onClick(v: View) {
        with(binding) {
            when (v) {

            }
        }
    }

}