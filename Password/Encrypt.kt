package Password

import java.math.BigInteger
import java.security.MessageDigest

class Encrypt(str: String) {
    private val mString: String? = str
    private var mPassword: String? = null
    fun getString(): String = mString!!
    fun getEncryptedString(): String {
        val messageDigest = MessageDigest.getInstance("MD5")
        messageDigest.reset()
        messageDigest.update(mString?.toByteArray(Charsets.UTF_8))
        val digest = messageDigest.digest()
        val bigInteger = BigInteger(1,digest)
        mPassword = bigInteger.toString(16)
        while (mPassword?.length!! < 32){
            mPassword = "0$mPassword"
        }
        return mPassword!!
    }
}