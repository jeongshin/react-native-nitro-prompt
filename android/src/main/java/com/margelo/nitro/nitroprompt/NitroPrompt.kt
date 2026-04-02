package com.margelo.nitro.nitroprompt

import android.app.AlertDialog
import android.content.res.Configuration
import android.text.InputType
import android.view.ViewGroup
import android.widget.EditText
import android.widget.FrameLayout
import com.facebook.proguard.annotations.DoNotStrip
import com.margelo.nitro.core.Promise
import com.margelo.nitro.NitroModules
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@DoNotStrip
class NitroPrompt : HybridNitroPromptSpec() {
  override fun prompt(options: NitroPromptOptions): Promise<String?> {
    return Promise.async {
      suspendCoroutine { continuation ->
        val activity = NitroModules.currentActivity
          ?: run {
            continuation.resume(null)
            return@suspendCoroutine
          }

        activity.runOnUiThread {
          val themeResId = when (options.theme ?: NitroPromptThemeType.SYSTEM) {
            NitroPromptThemeType.LIGHT -> android.R.style.Theme_DeviceDefault_Light_Dialog_Alert
            NitroPromptThemeType.DARK -> android.R.style.Theme_DeviceDefault_Dialog_Alert
            NitroPromptThemeType.SYSTEM -> {
              val nightMode = activity.resources.configuration.uiMode and
                Configuration.UI_MODE_NIGHT_MASK
              if (nightMode == Configuration.UI_MODE_NIGHT_YES) {
                android.R.style.Theme_DeviceDefault_Dialog_Alert
              } else {
                android.R.style.Theme_DeviceDefault_Light_Dialog_Alert
              }
            }
          }

          val builder = AlertDialog.Builder(activity, themeResId)
          builder.setTitle(options.title)

          val container = FrameLayout(activity)
          val editText = EditText(activity).apply {
            hint = options.placeholder
            setText(options.defaultValue ?: "")

            inputType = when (options.keyboardType ?: NitroPromptKeyboardType.DEFAULT) {
              NitroPromptKeyboardType.DEFAULT -> InputType.TYPE_CLASS_TEXT
              NitroPromptKeyboardType.URL -> InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_URI
              NitroPromptKeyboardType.EMAIL -> InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
            }

            layoutParams = FrameLayout.LayoutParams(
              ViewGroup.LayoutParams.MATCH_PARENT,
              ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
              val dp16 = (16 * resources.displayMetrics.density).toInt()
              setMargins(dp16, 0, dp16, 0)
            }
          }
          container.addView(editText)
          builder.setView(container)

          builder.setPositiveButton(options.confirmLabel ?: "OK") { _, _ ->
            continuation.resume(editText.text.toString())
          }

          builder.setNegativeButton(options.cancelLabel ?: "Cancel") { _, _ ->
            continuation.resume(null)
          }

          builder.setOnCancelListener {
            continuation.resume(null)
          }

          builder.show()
        }
      }
    }
  }
}
