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

### 断言接口（Predicates）
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

### 功能接口（Functions）
Functions接受一个参数并产生一个结果。默认方法能够用于将多个函数链接在一起。

```
    Function<String, Integer> toInteger = Integer::valueOf;  
    Function<String, String> backToString = toInteger.andThen(String::valueOf);  
      
    backToString.apply("123");     // "123"  
    
```

### 供应接口（Suppliers）
Suppliers对于给定的泛型类型产生一个实例。不同于Functions，Suppliers不需要任何参数。

```
    Supplier<Person> personSupplier = Person::new;  
    personSupplier.get();   // new Person  
```

### 消费接口（Consumers）
Consumers代表在只有一个输入参数时操作被如何执行。

```
    Consumer<Person> greeter = (p) -> System.out.println("Hello, " + p.firstName);  
    greeter.accept(new Person("Luke", "Skywalker"));  
```

### 比较接口（Comparators）
Comparators在老版本中就已经被熟知。Java8向该接口中添加了多种默认方法。

```
    Comparator<Person> comparator = (p1, p2) -> p1.firstName.compareTo(p2.firstName);  
      
    Person p1 = new Person("John", "Doe");  
    Person p2 = new Person("Alice", "Wonderland");  
      
    comparator.compare(p1, p2);             // > 0  
    comparator.reversed().compare(p1, p2);  // < 0  
```

### 选项接口（Optionals）
Optionals并不是功能性接口，反而它是一种特殊的工具用来阻止NullPointerException。我们首先快速的浏览Optionals是如何工作的，因为它在下一章节是十分重要的概念。

Optional是一种可以包含null和non-null值的简单容器。考虑到方法可以返回non-null结果，偶尔也可能任何都不返回。在Java8中，你可以返回Optional而不是返回null。

```
    Optional<String> optional = Optional.of("bam");  
      
    optional.isPresent();           // true  
    optional.get();                 // "bam"  
    optional.orElse("fallback");    // "bam"  
      
    optional.ifPresent((s) -> System.out.println(s.charAt(0)));     // "b"  
```

### 流接口（Streams）

java.util.Stream代表着一串你可以在其上进行多种操作的元素。流操作既可以是连续的也可以是中断的。中断操作返回操作结果。而连续操作返回流本身，这样你就可以在该行上继续操作。流是创建在数据源上的，例如：java.util.Collection、list集合和set集合。流操作既可以顺序执行也可以并行执行。
我们首先了解下顺序的流是如何工作的。我们首先创建一个字符串链表。

```
    List<String> stringCollection = new ArrayList<>();  
    stringCollection.add("ddd2");  
    stringCollection.add("aaa2");  
    stringCollection.add("bbb1");  
    stringCollection.add("aaa1");  
    stringCollection.add("bbb3");  
    stringCollection.add("ccc");  
    stringCollection.add("bbb2");  
    stringCollection.add("ddd1");  
```

Java8的Collections类已经被扩展了，你可以简单的调用Collection.stream()或者Collection.parallelSteam()来创建流。下面部分将介绍大部分流操作。

#### Filter

Filter接受一个predicate来过滤流中的所有元素。这个操作是连续的，它可以让我们在结果上继续调用另外一个流操作forEach。ForEach接受一个consumer，它被用来对过滤流中的每个元素执行操作。ForEach是一个中断操作，因此我们不能在ForEach后调用其他流操作。

```
    stringCollection  
        .stream()  
        .filter((s) -> s.startsWith("a"))  
        .forEach(System.out::println);  
      
    // "aaa2", "aaa1"  
```

#### Sorted

Sorted是一个连续操作，它返回流的已排序版本。如果你没有显示的指定Comparator，那么流中元素的排序规则为默认的。

```
    stringCollection  
        .stream()  
        .sorted()  
        .filter((s) -> s.startsWith("a"))  
        .forEach(System.out::println);  
      
    // "aaa1", "aaa2"  
```

需要注意的是sorted只创建了流的排序结果，它并没有改变集合中元素的排序位置。stringCollection中元素排序是没有改变的。

```
    System.out.println(stringCollection);  
    // ddd2, aaa2, bbb1, aaa1, bbb3, ccc, bbb2, ddd1  
```


#### Map
连续性操作map通过指定的Function将流中的每个元素转变为另外的对象。下面的示例将每个字符串转换为大写的字符串。此外，你也可以使用map将每个元素的类型改变为其它类型。转换后流的泛型类型依赖于你传入的Function的泛型类型。

```
    stringCollection  
        .stream()  
        .map(String::toUpperCase)  
        .sorted((a, b) -> b.compareTo(a))  
        .forEach(System.out::println);  
      
    // "DDD2", "DDD1", "CCC", "BBB3", "BBB2", "AAA2", "AAA1"  
```


#### Match
各种匹配操作可以用来检测是否某种predicate和流中元素相匹配。所有的这些操作是中断的并返回一个boolean结果。

```
    boolean anyStartsWithA =   
        stringCollection  
            .stream()  
            .anyMatch((s) -> s.startsWith("a"));  
      
    System.out.println(anyStartsWithA);      // true  
      
    boolean allStartsWithA =   
        stringCollection  
            .stream()  
            .allMatch((s) -> s.startsWith("a"));  
      
    System.out.println(allStartsWithA);      // false  
      
    boolean noneStartsWithZ =   
        stringCollection  
            .stream()  
            .noneMatch((s) -> s.startsWith("z"));  
      
    System.out.println(noneStartsWithZ);      // true  
 ```
 
#### Count
Count是中断型操作，它返回流中的元素数量。

```
    long startsWithB =   
        stringCollection  
            .stream()  
            .filter((s) -> s.startsWith("b"))  
            .count();  
      
    System.out.println(startsWithB);    // 3  
```

#### Reduce
这个中断性操作使用指定的function对流中元素实施消减策略。此操作的返回值是一个包括所有被消减元素的Optional。

```
    Optional<String> reduced =  
        stringCollection  
            .stream()  
            .sorted()  
            .reduce((s1, s2) -> s1 + "#" + s2);  
      
    reduced.ifPresent(System.out::println);  
    // "aaa1#aaa2#bbb1#bbb2#bbb3#ccc#ddd1#ddd2"  
```

### Parallel Streams
在前面部分我们提到流可以是顺序的也可以是并行的。顺序流的操作是在单线程上执行的，而并行流的操作是在多线程上并发执行的。

随后的例子我们展示了并行流可以多么容易的提高性能。

首先，我们创建一个包含唯一元素的大容器：

```
    int max = 1000000;  
    List<String> values = new ArrayList<>(max);  
    for (int i = 0; i < max; i++) {  
        UUID uuid = UUID.randomUUID();  
        values.add(uuid.toString());  
    }  
```

现在我们开始测试排序这些元素需要多长时间。

**Sequential Sort**

```
    long t0 = System.nanoTime();  
      
    long count = values.stream().sorted().count();  
    System.out.println(count);  
      
    long t1 = System.nanoTime();  
      
    long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);  
    System.out.println(String.format("sequential sort took: %d ms", millis));  
      
    // sequential sort took: 899 ms  
```


**Parallel Sort**

```
    long t0 = System.nanoTime();  
      
    long count = values.parallelStream().sorted().count();  
    System.out.println(count);  
      
    long t1 = System.nanoTime();  
      
    long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);  
    System.out.println(String.format("parallel sort took: %d ms", millis));  
      
    // parallel sort took: 472 ms  
```
你会观察到这两种模式的代码基本上是一致的，但是并行排序所花费的时间大约是顺序排序的一半。


### Map
我们已经提到maps不支持流。然而现在maps包括了许多新的非常有用的方法用于执行通用任务。

```
    Map<Integer, String> map = new HashMap<>();  
      
    for (int i = 0; i < 10; i++) {  
        map.putIfAbsent(i, "val" + i);  
    }  
      
    map.forEach((id, val) -> System.out.println(val));  
```

上述的代码应该很清晰了：putIfAbsent使得我们不用写是否为null值的检测语句；forEach使用consumer来对map中的每个元素进行操作。下面的例子向我们展示使用功能性函数在map里执行代码：

```
    map.computeIfPresent(3, (num, val) -> val + num);  
    map.get(3);             // val33  
      
    map.computeIfPresent(9, (num, val) -> null);  
    map.containsKey(9);     // false  
      
    map.computeIfAbsent(23, num -> "val" + num);  
    map.containsKey(23);    // true  
      
    map.computeIfAbsent(3, num -> "bam");  
    map.get(3);             // val33  
```

接下来，我们将学习如何删除给定键所对应的元素。删除操作还需要满足给定的值需要和map中的值想等：

```
    map.remove(3, "val3");  
    map.get(3);             // val33  
      
    map.remove(3, "val33");  
    map.get(3);             // null  
```

其他一些帮助性方法：

```
    map.getOrDefault(42, "not found");  // not found  
```


合并map中的实体是十分容易的：

```
    map.merge(9, "val9", (value, newValue) -> value.concat(newValue));  
    map.get(9);             // val9  
      
    map.merge(9, "concat", (value, newValue) -> value.concat(newValue));  
    map.get(9);             // val9concat  
```