/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package george.shumakov.imgtosong.loader;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;

/**
 *
 * @author George Shumakov <george.shumakov@gmail.com>
 */
public class RsrcURLConnection extends URLConnection {

    private final ClassLoader classLoader;

    public RsrcURLConnection(URL url, ClassLoader classLoader) {
        super(url);
        this.classLoader = classLoader;
    }

    @Override
    public void connect() throws IOException {
    }

    @Override
    public InputStream getInputStream() throws IOException {
        String file = URLDecoder.decode(this.url.getFile(), "UTF-8");
        InputStream result = this.classLoader.getResourceAsStream(file);
        if (result == null) {
            throw new MalformedURLException("Could not open InputStream for URL '" + this.url + "'");
        }
        return result;
    }
}
