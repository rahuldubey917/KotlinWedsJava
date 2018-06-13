package Password

import java.math.BigInteger
import java.security.MessageDigest

class Encrypt(password: String) {

    private val mString: String? = password
    private var mPassword: String? = null
    private var mHash: String? = null

    fun getString(): String = mString!!

    fun getEncryptedString(): String? {
        val messageDigest = MessageDigest.getInstance("MD5")
        messageDigest.reset()
        messageDigest.update(mString?.toByteArray(Charsets.UTF_8))
        val digest = messageDigest.digest()
        val bigInteger = BigInteger(1, digest)
        mPassword = bigInteger.toString(16)
        while (mPassword?.length!! < 32) {
            mPassword = "0$mPassword"
        }
        return mPassword!!
    }

    fun generateHash(md5Encrypt: String): String? {
        mHash = ""
        for (t in md5Encrypt) {
            mHash += (0xf - t.toString().toInt(16)).toString(16)
        }
        return mHash!!
    }

    fun checkSum(md5Encrypt: String, hash: String): Boolean{
        var t = ""
        var i = 0
        if (md5Encrypt.length != hash.length){
            return false
        }
        for (md5 in md5Encrypt){
            t += ((md5.toString().toInt(16)).plus(hash[i++].toString().toInt(16))).toString(16)
        }
        for (k in t){
            if (k != 'f'){
                return false
            }
        }
        return true
    }
}