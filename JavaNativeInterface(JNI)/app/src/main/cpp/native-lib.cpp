#include <string>

#include <jni.h>

extern "C"
JNIEXPORT void JNICALL
Java_com_example_factorialapp_FactorialCalculator_factorialJNI(JNIEnv *env, jclass clazz, jint n, jobject activity) {
    jlong result = 1;
    for (int i = 1; i <= n; i++) {
        result *= i;
    }

    // Get the Java class FactorialCalculator
    jclass calculatorClass = env->FindClass("com/example/factorialapp/FactorialCalculator");
    if (calculatorClass == nullptr) {
        return; // Class not found, handle error appropriately
    }

    // Get the method ID for FactorialCalculator.sendResultToKotlin
    jmethodID sendResultMethod = env->GetStaticMethodID(calculatorClass, "sendResultToKotlin", "(JLcom/example/factorialapp/MainActivity;)V");
    if (sendResultMethod == nullptr) {
        return; // Method not found, handle error appropriately
    }

    // Call the sendResultToKotlin method and pass the result and activity object
    env->CallStaticVoidMethod(calculatorClass, sendResultMethod, result, activity);
}
