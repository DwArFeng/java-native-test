package com.dwarfeng.test.jna;

import com.dwarfeng.dutil.basic.io.CT;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class NativeTestServiceTest {

    @Autowired
    private NativeTestService nativeTestService;

    @Test
    public void describe() {
        CT.trace(nativeTestService.describe());
    }

    @Test
    public void showInteger() {
        CT.trace(nativeTestService.showInteger());
    }

    @Test
    public void helloWorldRepeat() {
        CT.trace(nativeTestService.helloWorldRepeat("1233"));
    }

}