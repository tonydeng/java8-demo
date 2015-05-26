# Java8特性Demo

代码来源：

[java8-tutorial Github](https://github.com/winterbe/java8-tutorial)
[Java 8 Tutorial Blog](http://winterbe.com/posts/2014/03/16/java-8-tutorial/)

[Java 8 Tutorial Blog中文翻译](http://www.csdn.net/tag/java8%20newFeatures%20%e7%bf%bb%e8%af%91)

主要会介绍如何使用默认接口方法，lambda表达式，方法引用和可复用的annotation。
包括流、功能接口、扩展的map以及新的Date API等。

## 接口的默认方法

Java8允许开发者通过使用关键字```default```向接口中加入非抽象方法。这一特性被称为扩展方法。

```
interface Formula {  
    double calculate(int a);  
    
    default double sqrt(int a) {  
        return Math.sqrt(a);  
    }
}  
```

```
    Formula formula = new Formula() {  
        @Override  
        public double calculate(int a) {  
            return sqrt(a * 100);  
        }  
    };  
      
    formula.calculate(100);     // 100.0  
    formula.sqrt(16);           // 4.0  
```


## Lambda表达式

让我们使用一个简单的例子来展示在java8以前是如何对字符串列表进行排序的：

```
    List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");  
      
    Collections.sort(names, new Comparator<String>() {  
        @Override  
        public int compare(String a, String b) {  
            return b.compareTo(a);  
        }  
    });  
```

这个静态工具方法Collections.sort接受一个列表和一个用于元素比较的比较器。你会发现自己会经常创建匿名类并把它们传递给排序方法。
为了不用整天创建这些匿名类，java8带来了一个非常简短的语法--lambda表达式：

```
    Collections.sort(names, (String a, String b) -> {  
        return b.compareTo(a);  
    });  
```

现在的代码已经变得简短和便于阅读。但是，实际上，它可以变得更加简短：

```
    Collections.sort(names, (String a, String b) -> b.compareTo(a));  
```

对于这种一行代码体的表达式，你可以直接省略掉大括号{}和return关键字。它就变成下面这种更加简短的写法：

```
    Collections.sort(names, (a, b) -> b.compareTo(a));  
```


## 功能性接口

`lambda` 表达式是如何和java系统的类型进行对应的？每个 `lambda` 表达式都对应一个指定的类型，这个指定的类型是由接口确定的。该接口被称之为功能性接口，它必须且恰好只包含一个抽象方法声明。被指定接口类型所对应的 `lambda` 表达式刚好和这个接口的抽象方法想匹配。因为默认方法不是抽象的，因此你可以在你的功能性接口中添加多个默认方法。

我们可以将任意的接口用作 `lambda` 表示式，只要该接口仅仅包含一个抽象方法。为了确保你定义的接口达到要求，你可以在接口上添加 `@FunctionInterface` 注解。编译器可以检测到该注解并判断你的接口是否满足条件，如果 你定义的接口包含多个抽象方法时，编译器便会报错。

示例：

```
    @FunctionalInterface  
    interface Converter<F, T> {  
        T convert(F from);  
    }  
```

```
    Converter<String, Integer> converter = (from) -> Integer.valueOf(from);  
    Integer converted = converter.convert("123");  
    System.out.println(converted);    // 123  
```

如果FunctionInterface注解被添加，你定义的接口将总会被检测。


## 方法和构造函数引用

前部分的示例在使用静态方法引用的情况下可以被进一步的简化：

```
    Converter<String, Integer> converter = Integer::valueOf;  
    Integer converted = converter.convert("123");  
    System.out.println(converted);   // 123  
```
java8可以让你通过关键字::来传递方法和构造函数的引用。上面的示例展示了如何引用一个静态方法。我们同样也可以引用对象方法。

```
    class Something {  
        String startsWith(String s) {  
            return String.valueOf(s.charAt(0));  
        }  
    }  
```

```
    Something something = new Something();  
    Converter<String, String> converter = something::startsWith;  
    String converted = converter.convert("Java");  
    System.out.println(converted);    // "J"  
```

现在我们将看到关键字::如何为构造函数工作。首先我们定义一个拥有不同构造函数的bean类：

```
    class Person {  
        String firstName;  
        String lastName;  
      
        Person() {}  
      
        Person(String firstName, String lastName) {  
            this.firstName = firstName;  
            this.lastName = lastName;  
        }  
    }  
```

接下来我们定义一个用来创建类person的工厂接口：

```
    interface PersonFactory<P extends Person> {  
        P create(String firstName, String lastName);  
    }  
```

不使用通常的手动实现工厂类，我们通过使用构造函数将所有的工作联合在一起：

```
    PersonFactory<Person> personFactory = Person::new;  
    Person person = personFactory.create("Peter", "Parker");  
```

我们通过Person::new创建一个指向Person构造函数的引用。java编译器自动的选择正确的构造函数来匹配PersonFactory.create的函数签名。


## Lambda范围
在lambda表达式里访问外部变量和匿名类的方式是十分相似的。你可以在lambda中访问外部的final变量，访问实例字段和静态变量的方法也是如此。

### 访问本地变量

我们可以访问在lambda表示式之外的本地final变量：

```
    final int num = 1;  
    Converter<Integer, String> stringConverter =  
            (from) -> String.valueOf(from + num);  
      
    stringConverter.convert(2);     // 3  
```

但是和匿名变量不同的是变量num不必强制的被声明为final。下面的代码依然是合法的：

```
    int num = 1;  
    Converter<Integer, String> stringConverter =  
            (from) -> String.valueOf(from + num);  
      
    stringConverter.convert(2);     // 3  
```

但是实际上，变量num在编译期是被隐式的转换为fianl类型的。下面的代码是不能被成功的编译的：

```
    int num = 1;  
    Converter<Integer, String> stringConverter =  
            (from) -> String.valueOf(from + num);  //这里的num会编译报错
    num = 3;  
```

在lambda表达式内部向变量num写入值同样是不允许的。

### 访问对象字段和静态变量

和访问本地变量相反，我们在lambda表达式里即可以读取也可以写入对象字段和静态变量。这一准则同样适用于匿名类。

```
    class Lambda4 {  
        static int outerStaticNum;  
        int outerNum;  
      
        void testScopes() {  
            Converter<Integer, String> stringConverter1 = (from) -> {  
                outerNum = 23;  
                return String.valueOf(from);  
            };  
      
            Converter<Integer, String> stringConverter2 = (from) -> {  
                outerStaticNum = 72;  
                return String.valueOf(from);  
            };  
        }  
    }  
```

### 访问接口默认方法

还记得第一部分的formula示例么？接口formula定义了一个默认方法sqrt，这个方法可以被formula的实例和匿名实例所访问。
但是这个方法不能被lambda表达式所访问。

默认方法不能被lambda表示式内部的代码访问。下面的代码不能通过编译。

```
    Formula formula = (a) -> sqrt( a * 100);  
```

## 内建的功能性接口

JDK1.8包括了许多功能性接口。它们中的一些是老版本中被熟知的接口，例如Comparator和Runnable。这些已存在的接口已经通过@FunctionalInterface注解扩展为支持Lambda表达式。

同时Java8的API也包含了很多新的功能性接口简化你的开发。一些新的接口是来自非常出名的Google Guava库。即使你已经对这库十分熟悉了，你也应当留意下这些接口是如何被扩展的。

断言接口（Predicates）
Predicates是只拥有一个参数的Boolean型功能的接口。这个接口拥有多个默认方法用于构成predicates复杂的逻辑术语。

```

    Predicate<String> predicate = (s) -> s.length() > 0;  
      
    predicate.test("foo");              // true  
    predicate.negate().test("foo");     // false  
      
    Predicate<Boolean> nonNull = Objects::nonNull;  
    Predicate<Boolean> isNull = Objects::isNull;  
      
    Predicate<String> isEmpty = String::isEmpty;  
    Predicate<String> isNotEmpty = isEmpty.negate();  
```