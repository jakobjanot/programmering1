---
marp: true
theme: kea
paginate: true
--- 
<!-- _class: lead -->

![bg right](https://www.byggeplads.dk/sites/default/files/styles/byggeri-slideshow/public/byggeri/2013/kea-empire-campus/kea-disponering.jpg)

# INDKAPSLING

### Programmering 1
### Lektion 6
---

...
4. Klasser og objekter
5. Arv og komposition
6. Indkapsling &larr; I dag
7. Abstrakte klasser og interfaces
8. Polymorfi
...

<!-- Vi er i gang med at læse om objekter og klasser i -->

---
<!-- _class: invert -->
# Dagens program

- Repetition af klasser og objekter
- Hvad er indkapsling
- Hvorfor bruge indkapsling?
- Øvelser i grupper
- Opsamling

---
<!-- _class: rose -->

# Repetition af klasser og objekter

---

<!-- _class: rose -->

## En klasse er en skabelon for objekter.

```java
class SavingsAccount {
  // fields
  double balance;
  double rate = 0.05;
  String owner;

  // constructor
  SavingsAccount(double balance, String owner) {
    this.balance = balance;
    this.owner = owner;
  }

  // method
  void applyInterest() {
    balance = balance * (1 + rate);
  }
}
```

<!-- 
Tænk på en klasse som en Word brev skabelon til en invitation. Med den skabelon kan du lave mange breve.

En klasse består af fields (felter) som definerer objektets tilstand (state) og metoder som definerer objektets adfærd (behavior).

I eksemplet har vi en klasse `SavingsAccount` som er en skabelon for opsparingskonti.

Klassen SavingsAccount består af felterne (fields): 
- `balance` (saldo)
- `owner` (kontoejer)

Klassen har en konstruktør (constructor) som er en metode der initialiserer objekter af klassen. Konstruktøren herover tager to parametre: balance og owner.

Herefter kan vi kalde metoden `applyInterest` på objekter af klassen SavingsAccount. Metoden tilføjer renter til saldoen.
-->

---

<!-- _class: rose -->

# Et objekt har en tilstand og adfærd

```java
SavingsAccount kimsAccount = new SavingsAccount(1000, "Kim");
kimsAccount.applyInterest();

SavingsAccount annesAccount = new SavingsAccount(2000, "Anne");
annesAccount.applyInterest();

System.out.println(kimsAccount.balance); // => 1050
System.out.println(annesAccount.balance); // => 2100
```

<!--
Vi initialiserer en konto til Kim med en saldo på 1000 kr. Herefter tilføjer vi 5% rente til saldoen.

Klasser er en måde at organisere kode på. De hjælper os med at strukturere vores kode og gøre den mere læsbar og vedligeholdelig. Vi kan knytte data og metoder sammen i en klasse, så vi kan anvede dem sammen.
Vi kan lave mange objekter af samme klasse og de vil have hver deres tilstand, i dette tilfælde forskellige saldi.
-->

---

<!-- _class: blue -->

# Hvad er indkapsling

---

<!-- _class: blue -->

## Ny adfærd: Hæve penge fra kontoen

```java
SavingsAccount kimsAccount = new SavingsAccount(1000, "Kim");
kimsAccount.balance = kimsAccount.balance - 1200;
System.out.println(kimsAccount.balance); // => -200
```
<br>
Hvad er problemet her?

<!--
At ændre `balance` direkte er et problem fordi vi ikke kan kontrollere hvad der sker med `balance`, f.eks. at saldoen aldrig må være negativ.
-->

---

<!-- _class: blue -->

# Demo

<br/>

[`git clone https://github.com/jakobjanot/programmering1`](https://github.com/jakobjanot/programmering1)

<!--
1. Gør `balance` o.a. felter `private`
2. Lav en `public` `getBalance()` metode
-->

---

<!-- _class: blue -->

## Øvelse (20 min): At hæve/indsætte penge

```java
class SavingsAccount {
  private double balance;
  private double rate = 0.05;
  private String owner;

  public SavingsAccount(double balance, String owner) {
    this.balance = balance;
    this.owner = owner;
  }

  public void applyInterest() { 
    balance = balance * (1 + rate); 
  }

  public double getBalance() {
    return balance;
  }

  // TODO: Tilføj ny adfærd
}
```

<!--
Vi gør `balance`, `rate` og `owner` private, så de ikke kan ændres udefra. Vi tilføjer en metode `getBalance` som returnerer saldoen.

Vi har nu frihed til at ændre den implementering af klassen `SavingsAccount` uden at det påvirker andre dele af programmet. Vi kan f.eks. ændre hvordan saldoen beregnes eller hvordan renten tilføjes.

At trække penge er en naturlig adfærd (behavior) for en konto og burde derfor være en metode på klassen `SavingsAccount`.
--> 

---

<!-- _class: blue -->

# Diskussion af øvelse: At hæve/indsætte penge

---

<!-- _class: blue -->

```java
class SavingsAccount {
  private double balance;
  private double rate = 0.05;
  private String owner;

  SavingsAccount(double balance, String owner) {
    this.balance = balance;
    this.owner = owner;
  }

  public void applyInterest() { 
    double interest = balance * (1 + rate);
    deposit(interest);
  }

  public double getBalance() {
    return balance;
  }

  public void withdraw(double amount) {
    if (amount > balance)
      return;
    
    balance = balance - amount;
  }

  public void deposit(double amount) {
    if (amount < 0)
      return;

    balance = balance + amount;
  }
}
```

---

<!-- _class: blue -->

```java
SavingsAccount kimsAccount = new SavingsAccount(1000, "Kim");
kimsAccount.withdraw(1200);
System.out.println(kimsAccount.getBalance()); // => 1000

kimsAccount.withdraw(200);
System.out.println(kimsAccount.getBalance()); // => 800
```

<br/>

## Resultat

- Mere kontrol over objektets tilstand
- Mere fleksibilitet til at ændre klassens implementering
- Mere læsbart og vedligeholdeligt kode

---

<!-- _class: green -->

# Hvorfor bruge indkapsling?

---

<!-- _class: green -->

# API - Application Programming Interface

## a.k.a. Klassens grænseflade til omverdenen

- `public` metoder er klassens API (eller kontrakt)
- `private` metoder er klassens interne implementering

---

<!-- _class: green -->

# Øvelse: Refaktorering af `SavingsAccount`

- Kan vi gøre koden mere læsbar uden at ændre klassens API?

---

<!-- _class: green -->
# Diskussion af øvelse: Refaktorering af `SavingsAccount`

<!-- Hvad har i gjort? -->

---

<!-- _class: green -->

```java
class SavingsAccount {
  private double balance;
  private double rate = 0.05;
  private String owner;

  SavingsAccount(double balance, String owner) {
    this.balance = balance;
    this.owner = owner;
  }

  public void applyInterest() { 
    deposit(calculateInterest());
  }

  [...]

  private double calculateYearlyInterest() {
    return balance * (1 + rate);
  }
}
```

<!-- En måde at gøre koden mere læsbar er at flytte beregningen af renten til en separat metode `calculateInterest`. -->
---

<!-- _class: green -->
# Gruppearbejde (2 time)

## Opgave - Beregning af rente

1. Implementer daglig rente
2. Flyt implementeringen til en ny klasse til beregning af rente - API'et skal være `calculateInterest(double balance)`
3. Refaktorer `SavingsAccount` til at bruge den nye klasse
4. Lav en ny klasse `CheckingAccount` med samme funktionalitet som `SavingsAccount`, der tillader overtræk (negativ saldo).
5. Implementer strafrente på overtrækket.
6. (Valgfri) Lav en ny klasse `Bank`, der kan tilskrive renter på liste `CheckingAccount`.

---
<!-- _class: green -->
# Diskussion af gruppearbejde

<!-- Hvad har i lavet? -->

---

# Opsamling - hvad har vi lært?

- Indkapsling er en måde at skjule data og metoder fra omverdenen
- Indkapsling giver mere kontrol over objektets tilstand
- `public` metoder er klassens API
- `private` metoder er klassens interne implementering
- Indkapsling gør koden mere læsbar og vedligeholdelig, da vi kan tilføje så mange `private` metoder som vi vil uden at påvirke klassens brugere

---

<!-- _class: invert -->

# Næste gang

- Hvordan lader vi `Bank` have en liste med forskellige typer konti, dvs. `CheckingAccount`, `SavingsAccount` m.fl.?
- Hvordan sikrer vi os at alle konti har metoden `applyInterest()` til at tilføje renter?

---

<!-- _class: invert -->

# Næste gang - Abstrakte klasser og interfaces

- Hvordan kan vi bruge interfaces til at definere en kontrakt for klasser?
- Hvordan kan vi bruge abstrakte klasser til at dele kode mellem klasser?
