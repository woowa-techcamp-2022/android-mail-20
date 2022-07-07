package com.woowatech.android_mail_20.extension

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.FragmentManager

fun FragmentManager.add(container: FragmentContainerView, fragment: Fragment) {
    with(this.beginTransaction()) {
        add(container.id, fragment)
        commit()
    }
}

fun FragmentManager.replace(container: FragmentContainerView, fragment: Fragment) {
    with(this.beginTransaction()) {
        if (fragments.size > 0) {
            addToBackStack(fragments[fragments.size - 1]::class.java.simpleName)
            replace(container.id, fragment)
            commit()
        }
    }
}

fun FragmentManager.replaceWithoutBackStack(container: FragmentContainerView, fragment: Fragment) {
    with(this.beginTransaction()) {
        replace(container.id, fragment)
        commit()
    }
}