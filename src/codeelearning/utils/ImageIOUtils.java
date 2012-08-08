/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package codeelearning.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 *
 * @author ramzi
 */
public class ImageIOUtils {
        public static byte[] getArrayByte(InputStream input, int estimatedSize)
            throws IOException {
        final ByteArrayOutputStream output = new ByteArrayOutputStream(estimatedSize);
        try {
            final byte[] buf = new byte[8192];
            int len;

            while ((len = input.read(buf)) >= 0) {
                output.write(buf, 0, len);
            }
        } finally {
            output.close();
        }
        return output.toByteArray();
    }

    public static byte[] getArrayByte(InputStream input) throws IOException {
        return getArrayByte(input, 16);
    }

    public static byte[] getArrayByteFromFile(File f) throws IOException {
        final long length = f.length();
        if (length > Integer.MAX_VALUE) { // + de 2 Go
            throw new IOException("File too big");
        }

        FileInputStream input = new FileInputStream(f);
        try {
            return getArrayByte(input, (int) length);
        } finally {
            input.close();
        }
    }

    public static byte[] getArrayByteFromURL(URL url) throws IOException {
        InputStream input = url.openStream();
        try {
            return getArrayByte(input);
        } finally {
            input.close();
        }
    }
}
