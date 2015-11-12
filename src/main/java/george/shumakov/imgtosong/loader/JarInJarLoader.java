/* 
 * Copyright (c) 2015, George Shumakov
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package george.shumakov.imgtosong.loader;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

/**
 *
 * @author George Shumakov <george.shumakov@gmail.com>
 */
public class JarInJarLoader {

    private static class ManifestInfo {

        String rsrcMainClass;
        String[] rsrcClassPath;

        private ManifestInfo() {
        }

        ManifestInfo(ManifestInfo paramManifestInfo) {
            this();
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchMethodException, IOException {
        ManifestInfo mi = getManifestInfo();
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        URL.setURLStreamHandlerFactory(new RsrcURLStreamHandlerFactory(cl));
        URL[] rsrcUrls = new URL[mi.rsrcClassPath.length];
        for (int i = 0; i < mi.rsrcClassPath.length; i++) {
            String rsrcPath = mi.rsrcClassPath[i];
            if (rsrcPath.endsWith("/")) {
                rsrcUrls[i] = new URL("rsrc:" + rsrcPath);
            } else {
                rsrcUrls[i] = new URL("jar:rsrc:" + rsrcPath + "!/");
            }
        }
        ClassLoader jceClassLoader = new URLClassLoader(rsrcUrls, null);
        Thread.currentThread().setContextClassLoader(jceClassLoader);
        Class c = Class.forName(mi.rsrcMainClass, true, jceClassLoader);
        Method main = c.getMethod("main", new Class[]{args.getClass()});
        main.invoke(null, new Object[]{args});
    }

    private static ManifestInfo getManifestInfo() throws IOException {
        Enumeration resEnum = Thread.currentThread().getContextClassLoader().getResources("META-INF/MANIFEST.MF");
        while (resEnum.hasMoreElements()) {
            try {
                URL url = (URL) resEnum.nextElement();
                InputStream is = url.openStream();
                if (is != null) {
                    ManifestInfo result = new ManifestInfo(null);
                    Manifest manifest = new Manifest(is);
                    Attributes mainAttribs = manifest.getMainAttributes();
                    result.rsrcMainClass = mainAttribs.getValue("Rsrc-Main-Class");
                    String rsrcCP = mainAttribs.getValue("Class-Path");
                    if (rsrcCP == null) {
                        rsrcCP = "";
                    }
                    result.rsrcClassPath = splitSpaces(rsrcCP);
                    if ((result.rsrcMainClass != null) && (!result.rsrcMainClass.trim().equals(""))) {
                        return result;
                    }
                }
            } catch (Exception localException) {
            }
        }

        System.err.println("Missing attributes for JarRsrcLoader in Manifest (Rsrc-Main-Class, Rsrc-Class-Path)");
        return null;
    }

    private static String[] splitSpaces(String line) {
        if (line == null) {
            return null;
        }
        List result = new ArrayList();
        int firstPos = 0;
        while (firstPos < line.length()) {
            int lastPos = line.indexOf(' ', firstPos);
            if (lastPos == -1) {
                lastPos = line.length();
            }
            if (lastPos > firstPos) {
                result.add(line.substring(firstPos, lastPos));
            }
            firstPos = lastPos + 1;
        }
        return (String[]) result.toArray(new String[result.size()]);
    }
}