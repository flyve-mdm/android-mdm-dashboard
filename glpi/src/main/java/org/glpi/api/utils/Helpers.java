/* ---------------------------------------------------------------------
*
*  LICENSE
*
*  This file is part of the GLPI API Client Library for Java,
*  a subproject of GLPI. GLPI is a free IT Asset Management.
*
*  GLPI is free software: you can redistribute it and/or
*  modify it under the terms of the GNU General Public License
*  as published by the Free Software Foundation; either version 3
*  of the License, or (at your option) any later version.
*
*  GLPI is distributed in the hope that it will be useful,
*  but WITHOUT ANY WARRANTY; without even the implied warranty of
*  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*  GNU General Public License for more details.
*  --------------------------------------------------------------------
*  @author    Rafael Hernandez - <rhernandez@teclib.com>
*  @author    Ivan Del Pino - <idelpino@teclib.com>
*  @copyright (C) 2017 Teclib' and contributors.
*  @license   GPLv3 https://www.gnu.org/licenses/gpl-3.0.html
*  @link      https://github.com/glpi-project/java-library-glpi
*  @link      http://www.glpi-project.org/
*  --------------------------------------------------------------------
*/

package org.glpi.api.utils;

import android.util.Base64;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import okhttp3.ResponseBody;

public class Helpers {

    /**
     * add private constructor
     */
    private Helpers() {
    }

    /**
     * base64 decode function
     * @param string text the data to decode
     * @return the decoded data
     */
    public static String base64decode(String text) {
        String rtext = "";
        if(text == null) { return ""; }
        try {
            byte[] bdata = Base64.decode(text, Base64.DEFAULT);
            rtext = new String(bdata, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return rtext.trim();
    }

    /**
     * base64 encode function
     * @param string text the data to encode
     * @return the encoded data
     */
    public static String base64encode(String text) {
        String rtext = "";
        if(text == null) { return ""; }
        try {
            byte[] data = text.getBytes("UTF-8");
            rtext = Base64.encodeToString(data, Base64.DEFAULT);
            rtext = rtext.trim().replace("==", "");
        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        }
        return rtext.trim();
    }

    /**
     * @param body: response file
     * @param pathname: route to download
     *                example: getExternalFilesDir(null) + File.separator + "Future Studio Icon.png"
     * @return if was success or no
     */
    public static boolean writeResponseBodyToDisk(ResponseBody body, String pathname) {
        try {
            File futureStudioIconFile = new File(pathname);

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);

                while (true) {
                    int read = inputStream.read(fileReader);
                    if (read == -1) {
                        break;
                    }
                    outputStream.write(fileReader, 0, read);
                    fileSizeDownloaded += read;
                }

                outputStream.flush();

                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }
}
