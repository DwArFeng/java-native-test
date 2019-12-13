package com.dwarfeng.test.jna;

import com.sun.jna.Library;

/**
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public interface NativeTestService extends Library {

    public String describe();

    public int showInteger();

    public String helloWorldRepeat(String string);
}
