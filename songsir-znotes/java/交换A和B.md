使用Java交换A和B来波基础代码

```
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 输入a和b
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        // 引入第三个变量交换a和b
        thirdVariable(a, b);
        // 不引入第三个变量直接交换a和b
        simpleChange(a, b);
        // 通过异或运算交换a和b
        xorChange(a, b);
    }

    private static void xorChange(int a, int b) {
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println("通过异或运算交换a和b：a = " + a + ",b = " + b);
    }

    private static void simpleChange(int a, int b) {
        a = a + b;
        b = a - b;
        a = a - b;
        System.out.println("不引入第三个变量直接交换a和b：a = " + a + ",b = " + b);
    }

    private static void thirdVariable(int a, int b) {
        int c;
        c = a;
        a = b;
        b = c;
        System.out.println("引入第三个变量交换a和b：a = " + a + ",b = " + b);
    }
}
```
输出结果：

```
D:\java\jdk8\bin\java 
123
456
引入第三个变量交换a和b：a = 456,b = 123
不引入第三个变量直接交换a和b：a = 456,b = 123
通过异或运算交换a和b：a = 456,b = 123
```
那么，效率如何呢？

交换方法通过循环100万次输出结果如下：


```
D:\java\jdk8\bin\java 
123
456
引入第三个变量交换a和b：a = 123,b = 456，1000000次循环耗时：4
不引入第三个变量直接交换a和b：a = 123,b = 456，1000000次循环耗时：4
通过异或运算交换a和b：a = 123,b = 456，1000000次循环耗时：6
```

异或运算效率最低？
暂时还不能从更深的方面解释其原理。

**但是，交换两个数耗时很短，以上方法均可使用！**

Over。
