package com.wds.codebook.web3.utils;

import org.springframework.stereotype.Component;
import org.web3j.crypto.LinuxSecureRandom;

import java.security.SecureRandom;
/**
 * @author wds
 * @DateTime: 2024/5/8 13:54
 */
@Component
public class SecureRandomUtils {
    private static final SecureRandom SECURE_RANDOM;
    private static int isAndroid;

    public static SecureRandom secureRandom() {
        return SECURE_RANDOM;
    }

    private static boolean isAndroidRuntime() {
        if (isAndroid == -1) {
            String runtime = System.getProperty("java.runtime.name");
            isAndroid = runtime != null && "Android Runtime".equals(runtime) ? 1 : 0;
        }

        return isAndroid == 1;
    }

    private SecureRandomUtils() {
    }

    static {
        if (isAndroidRuntime()) {
            new LinuxSecureRandom();
        }

        SECURE_RANDOM = new SecureRandom();
        isAndroid = -1;
    }
}