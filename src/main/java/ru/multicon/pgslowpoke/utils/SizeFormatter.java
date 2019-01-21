package ru.multicon.pgslowpoke.utils;

import org.springframework.stereotype.Component;

@Component
public class SizeFormatter {
    public String humanReadable(long size) {
        long limit = 10 * 1024;

        String negative = "";
        if(size < 0) {
            negative = "-";
            size = Math.abs(size);
        }

        if(size < limit) {
            return String.format("%s%s bytes", negative, size);
        } else {
            size = Math.round((double) size / 1024);
            if (size < limit) {
                return String.format("%s%s kB", negative, size);
            } else {
                size = Math.round((double)size / 1024);
                if (size < limit) {
                    return String.format("%s%s MB", negative, size);
                } else {
                    size = Math.round((double)size / 1024);
                    if (size < limit) {
                        return String.format("%s%s GB", negative, size);
                    } else {
                        size = Math.round((double)size / 1024);
                            return String.format("%s%s TB", negative, size);
                    }
                }
            }
        }
    }
}
