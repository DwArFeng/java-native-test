# Java调用.net代码试验

为了测试java调用.net的可行性，花费大约一天的时间进行测试，最终形成了该项目，个人认为本项目的访问方式比较稳妥，.net对java的侵入性较小。

## 先后测试的方案

- ~~C# + jni4net 技术栈~~

  该技术栈经过测试，首先不能满足正常使用，在这个技术栈上花费了大量的时间，仍然不能解决方法访问时的 `UnsatisfiedLinkError`，其次，该技术栈需要通过命令行将现成的C#代码编译出dll和jar，配合原生的dll一起使用。对于原有的java程序过度具有侵占性。考虑再三后，决定放弃该技术栈。?

- jna + C# + CPP 技术栈

  该技术栈侵占性很小，需要本地调用的方法只需要以接口的形式给出，并实现 `Library`接口即可。
  ``` java
  public interface NativeTestService extends Library {

    public String describe();

    public int showInteger();

    public String helloWorldRepeat(String string);
    
  }
  ```
  该接口可以使用Spring Ioc进行维护。
  
  ``` java
  @Configuration
  public class JnaConfiguration {

    @Bean
    public NativeTestService getNativeTestService() {
        return Native.load("NativeBridge", NativeTestService.class);
    }

  }
  ```
  并且在调用的时候自动装配。
  ``` java
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
  ```
  
## 本项目的组成，以及使用步骤

本项目由三个部分组成

1. C#实现代码
2. Cpp桥接代码
3. Java调用代码。


该项目运行时，java调用cpp的dll，cpp接收到java的调用后访问C#的dll，最终执行C#的实现方法。

在项目编译时，请按照以下步骤执行: 

1. 编译C#项目，获得dll文件。
2. 将C#的dll文件拷贝到 `java-native-test\java-native-test-cpp\NativeBridge\libs` 然后编译cpp项目，获得cpp的dll文件。
3. 将C#的dll和Cpp的dll拷贝到jdk的bin目录下。
4. 运行java测试，观察结果。

## 对于java调用C#的思考

即使通过jna框架大幅度的减小了C#对java的侵占性，但是依然不能说是十分完美。C#和Cpp的dll文件需要放置在jdk的bin目录下运行，这几乎是完全不可接受的。

经过了一天的实践，我的看法是，与其费劲的使用java调用.net的语言，不如使用.net编写一个控制台程序，通过java的`Runtime`来执行程序，同时截获.net程序的输出流来获取结果。这样做可以实现.net和java代码的零耦合，双方互不侵占。

两种方法均不能跨平台，原因很简单: Linux不支持dll格式，Linux的库文件多以.so结尾（参考 `/usr/lib64`下的库文件）。

---
**以上。**