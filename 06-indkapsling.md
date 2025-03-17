---
marp: true
theme: kea
paginate: true
--- 
<!-- _class: lead -->

# Programmering 1

## Indkapsling

Lektion 6

---

...
5. Klasse og objekter
6. Indkapsling &larr; &larr; I dag
7. Arv og komposition
8. Abstrakte klasser og interfaces
9. Polymorfi
...

<!-- Vi er i gang med at læse om objekter og klasser i -->

---
<!-- _class: invert -->
# Dagens program

- Opsamling fra sidst
- Indkapsling
- Demo
- Hvorfor indkapsling?
- Øvelser i grupper
- Opsamling

---
<!-- _class: rose -->

# Opsamling fra sidst

---

<!-- _class: rose -->

## En klasse er en skabelon for objekter.

```java
class BankAccount {
  // fields
  double balance;
  double rate;
  String owner;

  // constructor
  BankAccount(double balance, String owner) {
    this.balance = balance;
    this.owner = owner;
    this.rate = 0.05;
  }

  // method
  addInterest() {
    balance = balance * (1 + rate);
  }
}
```

<!-- 
Tænk på en klasse som en Word brev skabelon til en invitation. Med den skabelon kan du lave mange breve.

En klasse består af fields (felter) som definerer objektets tilstand (state) og metoder som definerer objektets adfærd (behavior).

I eksemplet har vi en klasse `BankAccount` som er en skabelon for bankkonti.

Klassen BankAccount består af felterne (fields): 
- `balance` (saldo)
- `owner` (kontoejer)

Klassen har en konstruktør (constructor) som er en metode der initialiserer objekter af klassen. Konstruktøren herover tager to parametre: balance og owner.

Herefter kan vi kalde metoden `addInterest` på objekter af klassen BankAccount. Metoden tilføjer renter til saldoen.
-->

---

<!-- _class: rose -->

```java
BankAccount kimsAccount = new BankAccount(1000, "Kim");
kimsAccount.addInterest();

System.out.println(kimsAccount.balance); // => 1050
```

<!--
Vi initialiserer en bankkonto til Kim med en saldo på 1000 kr. Herefter tilføjer vi 5% rente til saldoen.

Klasser er en måde at organisere kode på. De hjælper os med at strukturere vores kode og gøre den mere læsbar og vedligeholdelig. Vi kan knytte data og metoder sammen i en klasse, så vi kan anvede dem sammen.
-->

---

<!-- _class: blue -->

# Indkapsling

---

<!-- _class: blue -->

## Lad os nu trække penge fra Kim's konto

```java
account.balance = account.balance - 2000;
System.out.println(account.balance); // => -950
```
<br>
Hvad er problemet her?

<!--
At trække penge er en naturlig adfærd (behavior) for en konto og burde derfor være en metode på klassen `BankAccount`.

At ændre `balance` direkte er et problem fordi vi kan ikke kan forhindre at saldoen bliver negativ. Det er ikke tilladt i en bankkonto.
-->


---

<!-- _class: blue -->

## Løsning

Lad os 
- gøre `balance` til et `private` felt 
- tilføje en ny `public` metode `withdraw(double amount)` til klassen

---

<!-- _class: blue -->

# DEMO

## [link](https://github.com/jakobjanot/programmering1/tree/master/06-indkapsling/01-public-metoder-og-felter)
---

<!-- _class: blue -->

# Øvelse

- Tilføj en metode `withdraw(double )` til `BankAccount` klassen
- Metoden skal trække et beløb fra saldoen

```java
class BankAccount {
  // fields
  double balance;
  double rate;
  String owner;

  // constructor
  BankAccount(double balance, String owner) {
    this.balance = balance;
    this.owner = owner;
    this.rate = 0.05;
  }

  // method
  addInterest() {
    balance = balance * (1 + rate);
  }
}
```

  

---

<!-- _class: rose -->


---

## What is an Object?

- An instance of a class
- Contains state and behavior
- Example:
    ```java
    public class Main {
            public static void main(String[] args) {
                    Car myCar = new Car("Red", "Toyota");
                    myCar.displayInfo(); // Output: Color: Red, Model: Toyota
            }
    }
    ```

---

## Creating Objects

- Using the `new` keyword
- Example:
    ```java
    Car myCar = new Car("Red", "Toyota");
    ```

---

## Fields and Methods

- Fields: Variables that hold the state of the object
- Methods: Functions that define the behavior of the object
- Example:
    ```java
    public class Car {
            private String color; // field
            private String model; // field

            public void displayInfo() { // method
                    System.out.println("Color: " + color + ", Model: " + model);
            }
    }
    ```

---

## Constructors

- Special methods to initialize objects
- Example:
    ```java
    public class Car {
            private String color;
            private String model;

            public Car(String color, String model) {
                    this.color = color;
                    this.model = model;
            }
    }
    ```

---

## Summary

- Classes define the structure and behavior of objects
- Objects are instances of classes
- Use constructors to initialize objects
- Fields and methods define the state and behavior of objects

---
```