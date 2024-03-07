package com.example.todaproj

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.marginTop
import androidx.core.view.setPadding
import org.w3c.dom.Text


class MyProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(createProfileLayout())
    }

    private fun createProfileLayout(): LinearLayout {
        val mainLayout = LinearLayout(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(Color.WHITE)
        }

        val profileHeaderLayout = RelativeLayout(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                350.dpToPx()
            )
        }

        val profileContentLayout = LinearLayout(this).apply {
            layoutParams = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT
            ).apply {
                setPadding(0, 0, 0, 120.dpToPx())
            }
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(Color.WHITE)
        }

        // Profile image card view
        val profileImageCardView = createProfileImageCardView()
        profileContentLayout.addView(profileImageCardView)

        // Text view "My Profile"
        val profileTextView = createProfileTextView()
        profileContentLayout.addView(profileContentLayout)

        profileHeaderLayout.addView(profileContentLayout)

        // Stats card view
        val statsCardView = createStatsCardView()
        mainLayout.addView(statsCardView)

        // Contact info layout
        val contactInfoLinearLayout = createContactInfoLayout()
        mainLayout.addView(contactInfoLinearLayout)

        // Follow button
        val followButton = createFollowButton()
        mainLayout.addView(followButton)

        return mainLayout
    }

    private fun createProfileImageCardView(): CardView {
        val cardView = CardView(this).apply {
            layoutParams = LinearLayout.LayoutParams(140.dpToPx(), 140.dpToPx()).apply {
                gravity = android.view.Gravity.CENTER_HORIZONTAL
                topMargin = 35.dpToPx()
            }
            100.dpToPx()
        }
        cardView.addView(ImageView(this).apply {
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )
            setImageResource(R.drawable.profile_icon)
        })
        return cardView
    }

    private fun createProfileTextView(): TextView {
        return TextView(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                gravity = android.view.Gravity.CENTER_HORIZONTAL
                topMargin = 10.dpToPx()
            }
            text = "My Profile"
            textSize = 23f
            setTextColor(Color.WHITE)
        }
    }

    private fun createStatsCardView(): CardView {
        // Implementation for stats card view
        // Example:
        return CardView(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                120.dpToPx()
            ).apply {
                setMargins(20.dpToPx(), 0, 20.dpToPx(), 0)
            }
            5.dpToPx()
            // Add content for stats card view
        }
    }


    private fun createContactInfoLayout(): LinearLayout {
        // Implementation for contact info layout
        // Example:
        return LinearLayout(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                gravity = android.view.Gravity.CENTER
                topMargin = 30.dpToPx()
            }
        }
    }

    private fun createFollowButton(): Button {
        return Button(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                gravity = android.view.Gravity.CENTER_HORIZONTAL
                topMargin = 15.dpToPx()
            }
            text = "Follow me"
            textSize = 18f
            // Add onClickListener if needed
        }
    }

    // Extension function to convert dp to pixels
    private fun Int.dpToPx(): Int {
        val scale = resources.displayMetrics.density
        return (this * scale + 0.5f).toInt()
    }
}


