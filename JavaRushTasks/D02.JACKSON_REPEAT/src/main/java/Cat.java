class Cat
{
    public String name;
    public int age;
    public int weight;
    public int size;
    Cat(){}

    @Override
    public String toString() {
        return getClass().getName() + " " + name + " " + age + " " + weight;
    }
}