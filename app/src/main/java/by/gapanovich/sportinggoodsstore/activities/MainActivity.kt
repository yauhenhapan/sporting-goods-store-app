package by.gapanovich.sportinggoodsstore.activities

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import by.gapanovich.sportinggoodsstore.R
import by.gapanovich.sportinggoodsstore.fragments.CartFragment
import by.gapanovich.sportinggoodsstore.fragments.CatalogFragment
import by.gapanovich.sportinggoodsstore.fragments.FavouriteFragment
import by.gapanovich.sportinggoodsstore.fragments.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var catalogFragment: CatalogFragment
    private lateinit var cartFragment: CartFragment
    private lateinit var favouriteFragment: FavouriteFragment
    private lateinit var profileFragment: ProfileFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottom = findViewById<BottomNavigationView>(R.id.bottom_nav_menu)
        findViewById<FrameLayout>(R.id.frame_layout)

        catalogFragment = CatalogFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout, catalogFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()

        bottom.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.catalog -> {
                    catalogFragment = CatalogFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, catalogFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }

                R.id.cart -> {
                    cartFragment = CartFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, cartFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }

                R.id.favourite -> {
                    favouriteFragment = FavouriteFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, favouriteFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }

                R.id.profile -> {
                    profileFragment = ProfileFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, profileFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
            }
            true
        }
    }
}