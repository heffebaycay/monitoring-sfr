package fr.heffebaycay.monitoring.monitoring_sfr.utils;

import junit.framework.TestCase;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;


public class IOUtilsTest extends TestCase {

    public void testComputeSHA256() throws Exception {
        Map<String, String> knownHashes = new HashMap<>();
        knownHashes.put("test", "9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08");
        knownHashes.put("admin", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918");
        knownHashes.put("potamochère", "a35d40078a45e90fb3cc06dc8adf2ee52a027a2fe4f1bbcf0de9ff2f064848f0");

        for(Map.Entry<String, String> hash : knownHashes.entrySet()) {
            String input = hash.getKey();
            String digest = hash.getValue();

            String computedDigest = IOUtils.computeSHA256(input);
            Assert.assertEquals(String.format("Invalid SHA-256 digest for input '%1$s' : '%2$s' (expected: '%3$s')", input, computedDigest, digest), digest, computedDigest);

        }
    }

    public void testHmacDigestSHA256() throws Exception {
        String key = "43f6168e635b9a90774cc4d3212d5703c11c9302";
        String msg = IOUtils.computeSHA256("admin");

        String hmac = IOUtils.hmacDigestSHA256(msg, key);

        Assert.assertEquals("Invalid HMAC", "7aa3e8b3ed7dfd7796800b4c4c67a0c56c5e4a66502155c17a7bcef5ae945ffa", hmac);
    }
}