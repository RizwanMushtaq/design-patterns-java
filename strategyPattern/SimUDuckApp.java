package strategyPattern;

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