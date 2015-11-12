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

import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;

/**
 *
 * @author George Shumakov <george.shumakov@gmail.com>
 */
public class RsrcURLStreamHandlerFactory implements URLStreamHandlerFactory {

    private final ClassLoader classLoader;
    private URLStreamHandlerFactory chainFac;

    public RsrcURLStreamHandlerFactory(ClassLoader cl) {
        this.classLoader = cl;
    }

    @Override
    public URLStreamHandler createURLStreamHandler(String protocol) {
        if ("rsrc".equals(protocol)) {
            return new RsrcURLStreamHandler(this.classLoader);
        }
        if (this.chainFac != null) {
            return this.chainFac.createURLStreamHandler(protocol);
        }
        return null;
    }

    public void setURLStreamHandlerFactory(URLStreamHandlerFactory fac) {
        this.chainFac = fac;
    }
}
