package com.smarthub.baseapplication.ui.fragments.sitedetail

import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import com.google.android.material.appbar.AppBarLayout

abstract class AppBarStateChangeListener : OnOffsetChangedListener {
    enum class State {
        EXPANDED, COLLAPSED, IDLE
    }

    private var mCurrentState = State.IDLE
    override fun onOffsetChanged(appBarLayout: AppBarLayout, i: Int) {
        mCurrentState = if (i == 0) {
            if (mCurrentState != State.EXPANDED) {
                onStateChanged(
                    appBarLayout,
                    State.EXPANDED
                )
            }
            State.EXPANDED
        } else if (Math.abs(i) >= appBarLayout.totalScrollRange) {
            if (mCurrentState != State.COLLAPSED) {
                onStateChanged(
                    appBarLayout,
                    State.COLLAPSED
                )
            }
            State.COLLAPSED
        } else {
            if (mCurrentState != State.IDLE) {
                onStateChanged(
                    appBarLayout,
                    State.IDLE
                )
            }
            State.IDLE
        }
        onOffsetChanged(mCurrentState, Math.abs(i / appBarLayout.totalScrollRange.toFloat()))
    }

    abstract fun onStateChanged(appBarLayout: AppBarLayout?, state: State?)
    abstract fun onOffsetChanged(state: State?, offset: Float)
}