import java.io.Serializable;

/**
 * 测试用pojo，实现了Serializable，以便进行系列化操作
 *
 * @author duanxx
 */
public class Person implements Serializable {
    private static final long serialVersionUID = -3562550857760039655L;

    @Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + "]";
    }


    private String name;
    private int age;

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public int getAge() {
        return age;
    }


    public void setAge(int age) {
        this.age = age;
    }
}