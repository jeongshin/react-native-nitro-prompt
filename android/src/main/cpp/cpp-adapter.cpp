#include <jni.h>
#include "nitropromptOnLoad.hpp"

JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM* vm, void*) {
  return margelo::nitro::nitroprompt::initialize(vm);
}
