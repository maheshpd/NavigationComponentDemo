package com.createsapp.navigationcomponentdemo

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfigutation: AppBarConfiguration

    private lateinit var listener: NavController.OnDestinationChangedListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = findNavController(R.id.fragment)
        drawerLayout = findViewById(R.id.drawer_layout)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

//        appBarConfigutation = AppBarConfiguration(navController.graph, drawerLayout)
        appBarConfigutation = AppBarConfiguration(
            setOf(R.id.homeFragment, R.id.orderFragment, R.id.profileFragment),
            drawerLayout
        )
        bottomNavigationView.setupWithNavController(navController)
        setupActionBarWithNavController(navController, appBarConfigutation)

        listener =
            NavController.OnDestinationChangedListener { controller, destination, arguments ->
                if (destination.id == R.id.firstFragment) {
                    supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.colorPrimaryDark)))
                } else if (destination.id == R.id.secondFragment) {
                    supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.colorAccent)))
                }
            }
    }

    override fun onResume() {
        super.onResume()
        navController.addOnDestinationChangedListener(listener)
    }

    override fun onPause() {
        super.onPause()

        navController.removeOnDestinationChangedListener(listener)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment)
        return navController.navigateUp(appBarConfigutation) || super.onSupportNavigateUp()
    }
}