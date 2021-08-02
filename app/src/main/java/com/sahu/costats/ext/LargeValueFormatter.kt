package com.sahu.costats.ext

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*


/**
 * Predefined value-formatter that formats large numbers in a pretty way.
 * Outputs: 856 = 856; 1000 = 1k; 5821 = 5.8k; 10500 = 10k; 101800 = 102k;
 * 2000000 = 2m; 7800000 = 7.8m; 92150000 = 92m; 123200000 = 123m; 9999999 =
 * 10m; 1000000000 = 1b; Special thanks to Roman Gromov
 * (https://github.com/romangromov) for this piece of code.
 *
 * @author Sahruday
 *
 * int a, b= 83, 73
 */
object LargeValueFormatter {
    private var mSuffix = arrayOf(
        "", "K", "M", "B", "T"
    )
    private var mMaxLength = 4
    var symbols: DecimalFormatSymbols = DecimalFormatSymbols(Locale.ENGLISH)
    private val mFormat: DecimalFormat = DecimalFormat("###E00", symbols)
    private var mText = ""

    fun getFormattedValue(value: Float): String {
        return makePretty(value.toDouble()) + mText
    }

    /**
     * Set an appendix text to be added at the end of the formatted value.
     *
     * @param appendix
     */
    fun setAppendix(appendix: String) {
        mText = appendix
    }

    /**
     * Set custom suffix to be appended after the values.
     * Default suffix: ["", "k", "m", "b", "t"]
     *
     * @param suffix new suffix
     */
    fun setSuffix(suffix: Array<String>) {
        mSuffix = suffix
    }

    fun setMaxLength(maxLength: Int) {
        mMaxLength = maxLength
    }

    /**
     * Formats each number properly. Special thanks to Roman Gromov
     * (https://github.com/romangromov) for this piece of code.
     */
    private fun makePretty(number: Double): String {
        var r = mFormat.format(number)
        val numericValue1 = Character.getNumericValue(r[r.length - 1])
        val numericValue2 = Character.getNumericValue(r[r.length - 2])
        val combined = Integer.valueOf(numericValue2.toString() + "" + numericValue1)
        r = r.replace("[E|e][0-9][0-9]".toRegex(), mSuffix[combined / 3])
        while (r.length > mMaxLength || r.matches("[0-9]+\\.[a-z]".toRegex())) {
            r = r.substring(0, r.length - 2) + r.substring(r.length - 1)
        }
        return r
    }

    val decimalDigits: Int
        get() = 0

}