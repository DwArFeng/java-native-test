package com.dwarfeng.test.jna;

import com.dwarfeng.dutil.basic.io.CT;
import com.sun.jna.Native;
import com.sun.jna.Platform;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JnaConfiguration {

    @Bean
    public PrintfService getPrintfService() {
        return Native.load((Platform.isWindows() ? "msvcrt" : "c"), PrintfService.class);
    }

    @Bean
    public NativeTestService getNativeTestService() {
        return Native.load("NativeBridge", NativeTestService.class);
    }

}
