import java.io.Serializable;

/**
 * ������pojo��ʵ����Serializable���Ա����ϵ�л�����
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