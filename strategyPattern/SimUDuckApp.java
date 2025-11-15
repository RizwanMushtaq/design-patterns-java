package strategyPattern;

/**
 * Implemented Duck app using Strategy Pattern.
 * Definition of Strategy Pattern -> It defines the family of algorithms.
 * encapsulates each one, and makes them interchangeable. Strategy lets the
 * algorithm vary independently of clients that use it.
 * <p>
 * Design Principle 1 -> Identify the aspects of your application that vary
 * and separate them from what stays the same.
 * Design Principle 2 -> Program to an interface, not an implementation.
 * Design Principle 3 -> Favor composition over inheritance.
 */
class SimUDuckApp{
  public static void main(String[] args){
    System.out.println("SimUDuckApp");
    Duck mallard = new MallardDuck();
    mallard.performQuack();
    mallard.performFly();
    Duck model = new ModelDuck();
    model.performFly();
    model.setFlyBehavior(new FlyRocketPowered());
    model.performFly();
  }
}

class MallardDuck extends Duck{
  public MallardDuck(){
    setFlyBehavior(new FlyWithWings());
    setQuackBehavior(new Quack());
  }

  @Override
  void display() {
    System.out.println("I am real Mallard Duck.");
  }
}

class ModelDuck extends Duck{
  public ModelDuck(){
    setFlyBehavior(new FlyNoWay());
    setQuackBehavior(new Quack());
  }

  @Override
  void display() {
    System.out.println("I am a model duck.");
  }
}

abstract class Duck {
  private FlyBehavior flyBehavior;
  private QuackBehavior quackBehavior;
  public Duck(){}
  abstract void display();
  void setFlyBehavior(FlyBehavior flyBehavior){
    this.flyBehavior = flyBehavior;
  }
  void setQuackBehavior(QuackBehavior quackBehavior){
    this.quackBehavior = quackBehavior;
  }
  void performFly(){
    flyBehavior.fly();
  }
  void performQuack(){
    quackBehavior.quack();
  }
  void swim(){
    System.out.println("All ducks swim !!!");
  }
}

/**
 * Separating what changes from what stays the same.
 * <p>
 * We know that fly() and quack() are the parts of the Duck that vary across
 * ducks.
 * <p>
 * To separate these behaviors from the Duck class, we'll pull both methods
 * out of the Duck class and create a new set of classes to represent each
 * behavior.
 * <p>
 * Here, the Duck behaviors live in separate classes. In this way the Duck
 * class won't need to know any of the implementation details for their own
 * behaviors.
 */

interface FlyBehavior {
  void fly();
}
class FlyWithWings implements FlyBehavior{
  @Override
  public void fly() {
    System.out.println("I'm flying!!!");
  }
}
class FlyNoWay implements FlyBehavior{
  @Override
  public void fly() {
    System.out.println("I can't fly.");
  }
}
class FlyRocketPowered implements FlyBehavior{
  @Override
  public void fly() {
    System.out.println("I'm flying with a rocket!");
  }
}

interface QuackBehavior{
  void quack();
}
class Quack implements QuackBehavior{
  @Override
  public void quack() {
    System.out.println("Quack!!!");
  }
}
class MuteQuack implements QuackBehavior{
  @Override
  public void quack() {
    System.out.println("<< Silence >>");
  }
}
class Squeak implements QuackBehavior{
  @Override
  public void quack() {
    System.out.println("Squeak!!!");
  }
}