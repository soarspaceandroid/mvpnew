#include <jni.h>
#include <string>

extern "C" {
    JNIEXPORT jstring JNICALL
    Java_com_sonar_encryption_EncryDecryHelper_getAppPrivateKey(
            JNIEnv* env,
            jobject /* this */) {
        std::string app_private_key = "MIICXwIBAAKBgQDoXEy3lWIqqlrDARb9Iqob3NEVhzzzXL2++zrf/XWoSlLCgAvbJsPtwCFEkkrWxx2BLAh7zEv9KkM+zTx9vj0BXYntjbgpUeklx2ppGkuOPi1QOhm8cAZrZoBUjY+TLdNrxupyBpZ9Ihu4ajUwqGQQIUlijEzfah7Dk0YQomsBRQIDAQABAoGBAOB8a9HhjNnTg6RqZR9iYh5RE99MrOIhc2ATsCm+4fEY/SPUOaAcr6X+vSVWgN9Ht4WYWQnhsniAYD9IYWzKYnwbvVcSXgskIB8if7lJHKqRqIP4Q7PUybIuIXdJlNFiZa6abaMhZGmIMAvcz7+zNQtGx1NLgGyJ/dD0EnItVirxAkEA+0ZA0Str7ga84Kw5L8xrO8qZq2pTFwL9VoG2xz7h/gx10q4One555SK3agDma+NJ8eGXrxERLvnqTw2szHUNjwJBAOy6/JVC67uXviNIUgFGuia+8LY2hONZidDXT4B/IOlt2CWkmY821uPKCap9X33e49zyHERSCwf6zbYgyOJ2AesCQQDKw/1IDWcR8+P01WVsWePqounwdmNIfYQbi58IF4lcbYeilAx9i1RX//TMGJ/YTdh81NOHVRlttARM+LcJYienAkEAuw1k12vxaGEWVDqo0WlZXZQyD0g06I+vXd1DbH0HPGsXB2dwDsF+Oq9Bw/0cnxCna8XCoBlTrhJ2yRpOoOIt0QJBAInSnx3qiJsThzJAjUqG+4xd7giXYHizrgOd0benX7a5SVRy1z38iWScklpWTfSdDuzPPyNHBqPTQSlTATsWulk=";
        return env->NewStringUTF(app_private_key.c_str());
    }

    JNIEXPORT jstring JNICALL
    Java_com_sonar_encryption_EncryDecryHelper_getAppPublicKey(
            JNIEnv* env,
            jobject /* this */) {
        std::string app_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDoXEy3lWIqqlrDARb9Iqob3NEVhzzzXL2++zrf/XWoSlLCgAvbJsPtwCFEkkrWxx2BLAh7zEv9KkM+zTx9vj0BXYntjbgpUeklx2ppGkuOPi1QOhm8cAZrZoBUjY+TLdNrxupyBpZ9Ihu4ajUwqGQQIUlijEzfah7Dk0YQomsBRQIDAQAB";
        return env->NewStringUTF(app_public_key.c_str());
    }

    JNIEXPORT jstring JNICALL
    Java_com_sonar_encryption_EncryDecryHelper_getBackPrivateKey(
            JNIEnv* env,
            jobject /* this */) {
        std::string back_public_key = "MIICXgIBAAKBgQDqif5huwumO/5kAFoEvTgdXB12vnIBOkH4zVO7AHdrvzADGJZCJV0S459YiJqqD6NDYa3+6NbuLczvZjesmn0AfdKBOrIFwtyPgwMfA+Al72kDsJz13PeHlr/bpshfUHzOfX3xWsuzM/6BUrmCz1wiSSzhtz7aDqDgJTQVqHj7IwIDAQABAoGAAY2cY6VLdHRdQOhHsFRYdMlRYKHuO1fZa/5gGZLtN4dyl/KnvBRnQ2LCgWGWNJ4Z5K5W6qM8GSOwgb1ZjQkQ3ta3aMpkdQ5aMbWsQGaBOwzaEK6/9jVkFpJR/Z4L1DtR/PbMhbFL3TJNXfV2JW3Y2mS6N8gI4ezPkWN35VOATsECQQD6/Kd/HJmQg72tn9i2Jjh/4005AAOFgrCCnhnt/QrGXdMsLOXxRasMeB7gZuRAp4Se1qxc4+byD61083Ik+02xAkEA7zk88kts5htv/rNz3d7C6rxBMaEZp4Co1BhbEGub0yJaaTbM/m4ljKYVRpxqp1AyjtetiScQyRsbqZimOpBnEwJBAOtO4q4+nw6pJLTaLzAex0vHTEgLZkHO1DOxjHfejDKMFobNFUvN2F7ZY4FZldez9peAhpqUJJp+7k2+TB616RECQQCI93motHy2RxFZgD+f0dJB+eVN2BJLMEP5o7RH84S1Xx7qzMCUEFaZb+IQmrAJ+cXouR4Xv/FBY67z7H2IXHNXAkEA4Loo0FZqbOWm/5G5ed0wj3Ng55blg4gmPNu1ECohXWne5XBBvb2s//7rP9o3Ezoszm4gCrJiHYu4Yi+BgW95Gg==";
        return env->NewStringUTF(back_public_key.c_str());
    }
}
