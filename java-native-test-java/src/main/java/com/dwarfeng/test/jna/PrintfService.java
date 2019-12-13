package com.dwarfeng.test.jna;

import com.sun.jna.Library;

public interface PrintfService extends Library {
    public void printf(String str);
}
