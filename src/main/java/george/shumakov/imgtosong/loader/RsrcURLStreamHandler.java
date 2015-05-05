/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package george.shumakov.imgtosong.loader;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

/**
 *
 * @author George Shumakov <george.shumakov@gmail.com>
 */
public class RsrcURLStreamHandler extends URLStreamHandler {

    private final ClassLoader classLoader;

    public RsrcURLStreamHandler(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    protected URLConnection openConnection(URL u) throws IOException {
        return new RsrcURLConnection(u, this.classLoader);
    }

    @Override
    protected void parseURL(URL url, String spec, int start, int limit) {
        String file;
        if (spec.startsWith("rsrc:")) {
            file = spec.substring(5);
        } else {
            if (url.getFile().equals("./")) {
                file = spec;
            } else {
                if (url.getFile().endsWith("/")) {
                    file = url.getFile() + spec;
                } else {
                    file = spec;
                }
            }
        }
        setURL(url, "rsrc", "", -1, null, null, file, null, null);
    }
}