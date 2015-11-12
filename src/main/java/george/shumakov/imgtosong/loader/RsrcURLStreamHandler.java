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