package com.harsh.model

import java.text.DecimalFormat

/**
 * Transforms string to Title Case String
 */
fun String.toTitleCase(): String {
  var space = true
  val builder = StringBuilder(this)
  val len = builder.length

  for (i in 0 until len) {
    val c = builder[i]
    if (space) {
      if (!Character.isWhitespace(c)) {
        // Convert to title case and switch out of whitespace mode.
        builder.setCharAt(i, Character.toTitleCase(c))
        space = false
      }
    } else if (Character.isWhitespace(c)) {
      space = true
    } else {
      builder.setCharAt(i, Character.toLowerCase(c))
    }
  }

  return builder.toString()
}

fun String.toNumCase(): String {
  val formatter = DecimalFormat("#,###")
  val myNumber = this.toDouble()
  return formatter.format(myNumber)
}