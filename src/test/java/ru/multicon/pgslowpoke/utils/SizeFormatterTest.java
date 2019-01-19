package ru.multicon.pgslowpoke.utils;

import org.junit.Assert;
import org.junit.Test;

public class SizeFormatterTest {

    @Test
    public void humanReadableBytes10() {
        //подготавливаем
        SizeFormatter sizeFormatter = new SizeFormatter();
        long bytes = 10L;
        String expected = "10 bytes";

        //выполняем
        String actual = sizeFormatter.humanReadable(bytes);

        //сравниваем
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void humanReadableBytesNegative10() {
        //подготавливаем
        SizeFormatter sizeFormatter = new SizeFormatter();
        long bytes = -10L;
        String expected = "-10 bytes";

        //выполняем
        String actual = sizeFormatter.humanReadable(bytes);

        //сравниваем
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void humanReadableBytes1000() {
        //подготавливаем
        SizeFormatter sizeFormatter = new SizeFormatter();
        long bytes = 1000L;
        String expected = "1000 bytes";

        //выполняем
        String actual = sizeFormatter.humanReadable(bytes);

        //сравниваем
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void humanReadableBytesNegative1000() {
        //подготавливаем
        SizeFormatter sizeFormatter = new SizeFormatter();
        long bytes = -1000L;
        String expected = "-1000 bytes";

        //выполняем
        String actual = sizeFormatter.humanReadable(bytes);

        //сравниваем
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void humanReadableKB() {
        //подготавливаем
        SizeFormatter sizeFormatter = new SizeFormatter();
        long bytes = 1000000L;
        String expected = "977 kB";

        //выполняем
        String actual = sizeFormatter.humanReadable(bytes);

        //сравниваем
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void humanReadableKBNegative() {
        //подготавливаем
        SizeFormatter sizeFormatter = new SizeFormatter();
        long bytes = -1000000L;
        String expected = "-977 kB";

        //выполняем
        String actual = sizeFormatter.humanReadable(bytes);

        //сравниваем
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void humanReadableMB() {
        //подготавливаем
        SizeFormatter sizeFormatter = new SizeFormatter();
        long bytes = 1000000000L;
        String expected = "954 MB";

        //выполняем
        String actual = sizeFormatter.humanReadable(bytes);

        //сравниваем
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void humanReadableMBNegative() {
        //подготавливаем
        SizeFormatter sizeFormatter = new SizeFormatter();
        long bytes = -1000000000L;
        String expected = "-954 MB";

        //выполняем
        String actual = sizeFormatter.humanReadable(bytes);

        //сравниваем
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void humanReadableGB() {
        //подготавливаем
        SizeFormatter sizeFormatter = new SizeFormatter();
        long bytes = 1000000000000L;
        String expected = "931 GB";

        //выполняем
        String actual = sizeFormatter.humanReadable(bytes);

        //сравниваем
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void humanReadableGBNegative() {
        //подготавливаем
        SizeFormatter sizeFormatter = new SizeFormatter();
        long bytes = -1000000000000L;
        String expected = "-931 GB";

        //выполняем
        String actual = sizeFormatter.humanReadable(bytes);

        //сравниваем
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void humanReadableTB() {
        //подготавливаем
        SizeFormatter sizeFormatter = new SizeFormatter();
        long bytes =  1000000000000000L;
        String expected = "909 TB";

        //выполняем
        String actual = sizeFormatter.humanReadable(bytes);

        //сравниваем
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void humanReadableTBNegative() {
        //подготавливаем
        SizeFormatter sizeFormatter = new SizeFormatter();
        long bytes = -1000000000000000L;
        String expected = "-909 TB";

        //выполняем
        String actual = sizeFormatter.humanReadable(bytes);

        //сравниваем
        Assert.assertEquals(expected, actual);
    }



//    GB
//            TB
//



}