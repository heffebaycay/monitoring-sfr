package fr.heffebaycay.monitoring.monitoring_sfr.utils;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class IOUtils {

    private static final String ENCODING_UTF8 = "UTF-8";
    private static final String ENCODING_ASCII = "ASCII";
    private static final String HASH_SHA256 = "SHA-256";
    private static final String HMAC_SHA256 = "HmacSHA256";
    private static final Logger logger = LoggerFactory.getLogger(IOUtils.class);

    static public String computeSHA256(String input) {

        String strDigest = null;

        try {
            MessageDigest md = MessageDigest.getInstance(HASH_SHA256);

            byte[] digest = md.digest(input.getBytes("UTF-8"));

            strDigest = Hex.encodeHexString(digest);

        } catch (NoSuchAlgorithmException e) {
            logger.error("Failed to find hashing algorithm: {}", e);
        } catch (UnsupportedEncodingException e) {
            logger.error("Failed to find requested encoding: {}", e);
        } finally {
            return strDigest;
        }
    }

    public static String hmacDigestSHA256(String msg, String keyString) {
        return hmacDigest(msg, keyString, HMAC_SHA256);
    }

    public static String hmacDigest(String msg, String keyString, String algorithm) {
        String digest = null;

        try {
            SecretKeySpec key = new SecretKeySpec(keyString.getBytes(ENCODING_UTF8), algorithm);
            Mac mac = Mac.getInstance(algorithm);
            mac.init(key);

            byte[] bytes = mac.doFinal(msg.getBytes(ENCODING_ASCII));

            StringBuffer hash = new StringBuffer();
            for (int i = 0; i < bytes.length; i++) {
                String hex = Integer.toHexString(0xFF & bytes[i]);
                if (hex.length() == 1) {
                    hash.append('0');
                }
                hash.append(hex);
            }
            digest = hash.toString();

        } catch (UnsupportedEncodingException e) {
            logger.error("Failed to find requested encoding: {}", e);
        } catch (NoSuchAlgorithmException e) {
            logger.error("Failed to find requested algorithm: {}", e);
        } catch (InvalidKeyException e) {
            logger.error("Provided key is invalid: {}", e);
        } finally {
            return digest;
        }
    }


}
