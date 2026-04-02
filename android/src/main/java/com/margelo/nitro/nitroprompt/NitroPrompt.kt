package com.margelo.nitro.nitroprompt
  
import com.facebook.proguard.annotations.DoNotStrip

@DoNotStrip
class NitroPrompt : HybridNitroPromptSpec() {
  override fun multiply(a: Double, b: Double): Double {
    return a * b
  }
}
