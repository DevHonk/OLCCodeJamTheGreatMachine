package com.devhonk.olccodejam;

public class Utils {
    public static int bytesLength4ToInt(byte[] bytes) {
        return (
                (int) (bytes[0]) << 24   |
                (int) (bytes[1]) << 16  |
                (int) (bytes[2]) << 8   |
                (int) (bytes[3])
        );
    }

    public static int bytesLength2ToInt(byte[] bytes) {
        return (
                        (int) (bytes[0]) << 16  |
                        (int) (bytes[1]) << 8
        );
    }


}
