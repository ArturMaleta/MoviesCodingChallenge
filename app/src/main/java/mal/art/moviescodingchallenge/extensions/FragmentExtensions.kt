package mal.art.moviescodingchallenge.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

fun Fragment.replaceFragment(
    supportFragmentManager: FragmentManager,
    container: Int,
    fragment: Fragment,
    tag: String
) {
    val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
    val prev = supportFragmentManager.findFragmentByTag(tag)
    if (prev != null) {
        fragmentTransaction.remove(prev)
    }

    fragmentTransaction.replace(container, fragment, tag)
    fragmentTransaction.addToBackStack(null)
    fragmentTransaction.commit()
}