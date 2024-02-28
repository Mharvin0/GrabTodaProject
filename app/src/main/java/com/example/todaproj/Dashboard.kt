package com.example.todaproj

import android.os.Bundle
import android.util.TypedValue
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat

//Define your activity class
class Dashboard : AppCompatActivity() {

    // Lifecycle method called when the activity is created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // call a function to initialize UI components
        initializeUI()
    }
    // Function to initialize UI components
    private fun initializeUI() {
        val grabTodaTextView = findViewById<TextView>(R.id.grabtoda)
        val grabTodaDescTextView = findViewById<TextView>(R.id.grabtoDesc)
        val grabTodaImageView = findViewById<ImageView>(R.id.grabtodaImage)

        // Set text and image for the GrabToda section
        grabTodaTextView.text = "GrabToda"
        grabTodaTextView.setTextColor(ContextCompat.getColor(this, R.color.primary))
        grabTodaTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40f)
        grabTodaTextView.typeface = ResourcesCompat.getFont(this, R.font.poppins_semibold)
        grabTodaDescTextView.setTextColor(ContextCompat.getColor(this, R.color.primary))
        grabTodaImageView.setImageResource(R.drawable.dashboard_icon)

        // Initialize rows
        initializeRow1()
        initializeRow2()
        initializeRow3()
    }

    // Function to initialize the first row od cards
    private fun initializeRow1() {
        //Retrieve the parent LinearLayout for the first row
        val row1LinearLayout = findViewById<LinearLayout>(R.id.row1)

        // Initialize Booking card
        val bookingCardView = createCardView()
        val bookingNameTextView = createTextView("booking", R.id.bookingName)
        val bookingImageView = createImageView(R.drawable.booking_icon)
        bookingCardView.addView(bookingNameTextView)
        bookingCardView.addView(bookingImageView)

        // Add the Booking card to the row
        row1LinearLayout.addView(bookingCardView)

        // Initialize Calculator card
        val calculatorCardView = createCardView()
        val calculatorNameTextView = createTextView("Calculator", R.id.calculatorName)
        val calculatorImageView = createImageView(R.drawable.calculator_icon)
        calculatorCardView.addView(calculatorNameTextView)
        calculatorCardView.addView(calculatorImageView)

        // Add the Calculator card to the row
        row1LinearLayout.addView(calculatorCardView)
    }

    // Function to initialize the second row od cards
    private fun initializeRow2() {
        //Retrieve the parent LinearLayout for the second row
        val row2LinearLayout = findViewById<LinearLayout>(R.id.row2)

        // Initialize Highlight card
        val highlightsCardView = createCardView()
        val highlightsNameTextView = createTextView("Highlight", R.id.highlightName)
        val highlightsImageView = createImageView(R.drawable.highlight_icon)
        highlightsCardView.addView(highlightsNameTextView)
        highlightsCardView.addView(highlightsImageView)

        // Add the Booking card to the row
        row2LinearLayout.addView(highlightsCardView)

        // Initialize Profile card
        val profileCardView = createCardView()
        val profileNameTextView = createTextView("Profile", R.id.profileName)
        val profileImageView = createImageView(R.drawable.profile_icon)
        profileCardView.addView(profileNameTextView)
        profileCardView.addView(profileImageView)

        // Add the Profile card to the row
        row2LinearLayout.addView(profileCardView)

    }

    // Function to initialize the third row of cards
    private fun initializeRow3() {
        // Retrieve the parent LinearLayout for the third row
        val row3LinearLayout = findViewById<LinearLayout>(R.id.row3)

        // Initialize History card
        val historyCardView = createCardView()
        val historyNameTextView = createTextView("History", R.id.historyName)
        val historyImageView = createImageView(R.drawable.history_icon)
        historyCardView.addView(historyNameTextView)
        historyCardView.addView(historyImageView)

        // Add the History card to the row
        row3LinearLayout.addView(historyCardView)

        // Initialize Logout card
        val logoutCardView = createCardView()
        val logoutNameTextView = createTextView("Logout", R.id.logoutName)
        val logoutImageView = createImageView(R.drawable.logout_icon)
        logoutCardView.addView(logoutNameTextView)
        logoutCardView.addView(logoutImageView)

        // Add the Logout card to the row
        row3LinearLayout.addView(logoutCardView)
    }

    // Function to create a CardView programmatically
    private fun createCardView(): CardView {
        val cardView = CardView(this)
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        layoutParams.setMargins(10.dpToPx(), 10.dpToPx(), 10.dpToPx(), 10.dpToPx())
        cardView.layoutParams = layoutParams
        cardView.radius = 20.dpToPx().toFloat()
        cardView.cardElevation = 20.dpToPx().toFloat()
        return cardView
    }

    // Function to create a TextView programmatically
    private fun createTextView(text: String, id: Int): TextView {
        val textView = TextView(this)
        textView.layoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        textView.id = id
        textView.text = text
        textView.setTextColor(ContextCompat.getColor(this, R.color.primary))
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
        textView.typeface = ResourcesCompat.getFont(this, R.font.poppins_semibold)
        val layoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.addRule(RelativeLayout.BELOW, R.id.bookingImage)
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL)
        layoutParams.topMargin = 10.dpToPx()
        textView.layoutParams = layoutParams
        return textView
    }

    // Function to create an ImageView programmatically
    private fun createImageView(drawableId: Int): ImageView {
        val imageView = ImageView(this)
        val layoutParams = RelativeLayout.LayoutParams(70.dpToPx(), 70.dpToPx())
        layoutParams.addRule(RelativeLayout.BELOW, R.id.bookingName)
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL)
        layoutParams.topMargin = 15.dpToPx()
        imageView.layoutParams = layoutParams
        imageView.setImageResource(drawableId)
        return imageView
    }

    // Extension function to convert dp to px
    private fun Int.dpToPx(): Int {
        val scale = resources.displayMetrics.density
        return (this * scale + 0.5f).toInt()
    }
}